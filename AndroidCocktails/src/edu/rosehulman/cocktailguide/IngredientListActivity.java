package edu.rosehulman.cocktailguide;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import edu.rosehulman.cocktailguide.db.DBHelper;

public class IngredientListActivity extends ListActivity implements OnClickListener {

	static String KEY_INGREDIENT_ID = "KEY_INGREDIENT_ID";
	static String KEY_INGREDIENT_NAME = "KEY_INGREDIENT_NAME";
	static String KEY_INGREDIENT_AMT = "KEY_INGREDIENT_AMT";
	
	static int REQUEST_EDIT_INGREDIENT = 1;
	
	private Cursor mCursor;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_list_view);
        
        mCursor = DBHelper.getInstance(this).getAllIngredients();
        final IngredientItemAdapter adapter = new IngredientItemAdapter(this, mCursor);
        
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_ingredient_list, menu);
        return true;
    }
    
    @Override
    public void onClick(View v) {
    	mCursor.moveToPosition((Integer)v.getTag());
    	int id = mCursor.getInt(mCursor.getColumnIndex(DBHelper.colIngredientsID));
		String name = mCursor.getString(mCursor.getColumnIndex(DBHelper.colIngredientsName));
		double amount = mCursor.getDouble(mCursor.getColumnIndex(DBHelper.colIngredientAmount));
		Intent ingredientEditIntent = new Intent(this, IngredientEditActivity.class);
		ingredientEditIntent.putExtra(KEY_INGREDIENT_ID, id);
		ingredientEditIntent.putExtra(KEY_INGREDIENT_NAME, name);
		ingredientEditIntent.putExtra(KEY_INGREDIENT_AMT, amount);
		startActivityForResult(ingredientEditIntent, REQUEST_EDIT_INGREDIENT);
    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_EDIT_INGREDIENT) {
			if (resultCode == RESULT_OK) {
				int id = data.getIntExtra(KEY_INGREDIENT_ID, -1);
				double amount = data.getDoubleExtra(KEY_INGREDIENT_AMT, 0.00);
				Ingredient ing = new Ingredient(id, "", amount);
				ing.updateAmountAvailable(this, amount);
				
				mCursor = DBHelper.getInstance(this).getAllIngredients();
				((IngredientItemAdapter)getListAdapter()).changeCursor(mCursor);
			}
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}
}
