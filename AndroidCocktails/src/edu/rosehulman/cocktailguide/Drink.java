package edu.rosehulman.cocktailguide;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import edu.rosehulman.cocktailguide.db.DBHelper;

public class Drink {
	private String mName;
	private String mDescription;
	private ArrayList<Ingredient> mIngredientList;
	private ArrayList<String> mDirections;
	private Drawable mPicture;
	private int mConsumed;
	
	private Drink(Context context, String name, String desc, int drawableID, int numConsumed) {
		mName = name;
		mDescription = desc;
		Resources r = context.getResources();
		mPicture = r.getDrawable(drawableID);
		mConsumed = numConsumed;
	}
	
	public static Cursor GetAllDrinks(Context context) {
		return DBHelper.getInstance(context).getAllDrinks();
	}
	
	public static Cursor GetDrinkCategories(Context context) {
		return DBHelper.getInstance(context).getDrinkCategories();
	}
	
	public static Cursor GetDrinksFromCateogry(Context context, int categoryID) {
		return null;
	}
	
	public static Drink GetDrink(Context context, int drinkID) {
		Cursor drinkData = DBHelper.getInstance(context).getDrinkByID(drinkID);
		drinkData.moveToFirst();
		String drinkName = drinkData.getString(drinkData.getColumnIndex(DBHelper.colDrinkName));
		String drinkDescription = drinkData.getString(drinkData.getColumnIndex(DBHelper.colDrinkDescription));
		int drinkNumConsumed = drinkData.getInt(drinkData.getColumnIndex(DBHelper.colDrinkNumConsumed));
		int drinkDrawableID = drinkData.getInt(drinkData.getColumnIndex(DBHelper.colDrinkPicture));
		
		Drink drink = new Drink(context, drinkName, drinkDescription, drinkDrawableID, drinkNumConsumed);
		return drink;
	}
}