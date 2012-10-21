package edu.rosehulman.cocktailguide;

import edu.rosehulman.cocktailguide.db.DBHelper;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.View;
import android.widget.TextView;

public class IngredientItemAdapter extends ResourceCursorAdapter {

	public IngredientItemAdapter(Context context, Cursor cursor) {
		super(context, R.layout.ingredient_item, cursor, 0);
	}
	
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView ingName = (TextView)view.findViewById(R.id.ingredient_name);
		TextView ingAmt = (TextView)view.findViewById(R.id.ingredient_amount);
		
		String name = cursor.getString(cursor.getColumnIndex(DBHelper.colIngredientsName));
		double amt = cursor.getDouble(cursor.getColumnIndex(DBHelper.colIngredientAmount));
		
		ingName.setText(name);
		ingAmt.setText("" + amt);
	}

}
