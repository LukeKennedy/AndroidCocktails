package edu.rosehulman.cocktailguide;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import edu.rosehulman.cocktailguide.db.DBHelper;

public class DrinkCategoryActivity extends FragmentActivity implements OnItemClickListener {

	private SimpleCursorAdapter mAdapter;
	private Cursor mCursor;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_menu);
        
        ListView listView = (ListView)findViewById(R.id.category_list);
        
        listView.setOnItemClickListener(this);
        
        if (mCursor != null) {
        	mCursor.close();
        }
        mCursor = Drink.GetDrinkCategories(this);
        mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, mCursor, new String[] {DBHelper.colCategoryName}, new int[] {android.R.id.text1}, 0);
        Log.d("lol", "got a cursor");
        listView.setAdapter(mAdapter);
        Log.d("lol", "set the adapter");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_browse_drinks, menu);
        return true;
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
//		Toast.makeText(this, "You hit Category " + id, Toast.LENGTH_SHORT).show();
		mCursor.moveToPosition(pos);
		int categoryID = mCursor.getInt(mCursor.getColumnIndex(DBHelper.colCategoryID));
		String categoryName = mCursor.getString(mCursor.getColumnIndex(DBHelper.colCategoryName));
		DialogFragment newFragment = new CategoryItemFragment(categoryID, categoryName);
		newFragment.show(getSupportFragmentManager(), "categoryItemPicker");
	}
}
