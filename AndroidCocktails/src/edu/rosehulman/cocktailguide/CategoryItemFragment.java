package edu.rosehulman.cocktailguide;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import edu.rosehulman.cocktailguide.db.DBHelper;

public class CategoryItemFragment extends DialogFragment implements OnItemClickListener {

	private SimpleCursorAdapter mAdapter;
	private final int categoryID;
	private final String categoryName;
	private Cursor mCursor;
	
	static String KEY_DRINK_ID = "KEY_DRINK_ID";
	
	public CategoryItemFragment(int categoryID, String categoryName) {
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Dialog dialog = new Dialog(getActivity());
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		dialog.setContentView(inflater.inflate(
				R.layout.list_view_items, null));
		dialog.setTitle(categoryName);
		
		ListView listView = (ListView) dialog.findViewById(R.id.list_view);
		mCursor = Drink.GetDrinksFromCateogry(getActivity(), categoryID);
        mAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, mCursor, new String[] {DBHelper.colDrinkName}, new int[] {android.R.id.text1}, 0);
        
        Log.d("lol", "got a cursor");
        listView.setAdapter(mAdapter);
        Log.d("lol", "set the adapter");
        
        listView.setOnItemClickListener(this);
		
		return dialog;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		mCursor.moveToPosition(pos);
		int drinkId = mCursor.getInt(mCursor.getColumnIndex(DBHelper.colDrinkID));
		Intent drinkRecipeIntent = new Intent(getActivity(), DrinkRecipeActivity.class);
		drinkRecipeIntent.putExtra(KEY_DRINK_ID, drinkId);
		startActivity(drinkRecipeIntent);
	}
	
}
