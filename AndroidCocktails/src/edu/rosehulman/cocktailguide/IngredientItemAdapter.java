package edu.rosehulman.cocktailguide;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import edu.rosehulman.cocktailguide.db.DBHelper;

public class IngredientItemAdapter extends ResourceCursorAdapter {
	
	private Context mContext;

	public IngredientItemAdapter(Context context, Cursor cursor) {
		super(context, R.layout.ingredient_item, cursor, 0);
		mContext = context;
	}
	
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView ingName = (TextView)view.findViewById(R.id.ingredient_name);
		TextView ingAmt = (TextView)view.findViewById(R.id.ingredient_amount);
		Button button = (Button)view.findViewById(R.id.edit_button);
		button.setOnClickListener((OnClickListener)mContext);
		button.setTag(cursor.getPosition());
		
		String name = cursor.getString(cursor.getColumnIndex(DBHelper.colIngredientsName));
		double amt = cursor.getDouble(cursor.getColumnIndex(DBHelper.colIngredientAmount));
		
		ingName.setText(name);
		ingAmt.setText("" + amt);
	}

}
