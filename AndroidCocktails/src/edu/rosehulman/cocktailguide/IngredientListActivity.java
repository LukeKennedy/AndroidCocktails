package edu.rosehulman.cocktailguide;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import edu.rosehulman.cocktailguide.db.DBHelper;

public class IngredientListActivity extends Activity implements OnClickListener {

	private Cursor mCursor;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_list_view);
        
        mCursor = DBHelper.getInstance(this).getAllIngredients();
        final IngredientItemAdapter adapter = new IngredientItemAdapter(this, mCursor);
        
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_ingredient_list, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		Intent ingredientEditIntent = new Intent(this, IngredientEditActivity.class);
		startActivity(ingredientEditIntent);
	}
}
