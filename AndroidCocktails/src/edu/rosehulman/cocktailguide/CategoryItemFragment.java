package edu.rosehulman.cocktailguide;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import edu.rosehulman.cocktailguide.db.DBHelper;

public class CategoryItemFragment extends DialogFragment implements OnItemClickListener {

	private SimpleCursorAdapter mAdapter;
	private final int categoryID;
	
	public CategoryItemFragment(int categoryID) {
		this.categoryID = categoryID;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Dialog dialog = new Dialog(getActivity());
		dialog.setContentView(R.layout.list_view_items);
		dialog.setTitle("Something");
		
		ListView listView = (ListView)getActivity().findViewById(R.id.list_view);
		Cursor mCursor = Drink.GetDrinksFromCateogry(getActivity(), categoryID);
        mAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, mCursor, new String[] {DBHelper.colCategoryName}, new int[] {android.R.id.text1}, 0);
        
        Log.d("lol", "got a cursor");
        listView.setAdapter(mAdapter);
        Log.d("lol", "set the adapter");
		
		return dialog;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		// TODO Auto-generated method stub
		
	}
	
}
