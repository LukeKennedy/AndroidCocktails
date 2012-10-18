package edu.rosehulman.cocktailguide;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import edu.rosehulman.cocktailguide.db.DBHelper;

public class Drink {
	private int mID;
	private String mName;
	private String mDescription;
	private ArrayList<Ingredient> mIngredientList;
	private ArrayList<Direction> mDirections;
	private Drawable mPicture;
	private int mConsumed;

	private Drink(Context context, int ID, String name, String desc,
			int drawableID, int numConsumed) {
		mID = ID;
		mName = name;
		mDescription = desc;
		Resources r = context.getResources();
		mPicture = r.getDrawable(drawableID);
		mConsumed = numConsumed;
		mIngredientList = Ingredient.GetIngredientsForDrink(context, mID);
		mDirections = Direction.GetDirectionsForDrink(context, mID);
	}

	public String getName() {
		return mName;
	}

	public String getDescription() {
		return mDescription;
	}

	public ArrayList<Ingredient> getIngredientList() {
		return mIngredientList;
	}

	public ArrayList<Direction> getDirections() {
		return mDirections;
	}

	public Drawable getPicture() {
		return mPicture;
	}

	public int getConsumed() {
		return mConsumed;
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
		int ID = drinkData
				.getInt(drinkData.getColumnIndex(DBHelper.colDrinkID));
		String drinkName = drinkData.getString(drinkData
				.getColumnIndex(DBHelper.colDrinkName));
		String drinkDescription = drinkData.getString(drinkData
				.getColumnIndex(DBHelper.colDrinkDescription));
		int drinkNumConsumed = drinkData.getInt(drinkData
				.getColumnIndex(DBHelper.colDrinkNumConsumed));
		int drinkDrawableID = drinkData.getInt(drinkData
				.getColumnIndex(DBHelper.colDrinkPicture));

		Drink drink = new Drink(context, ID, drinkName, drinkDescription,
				drinkDrawableID, drinkNumConsumed);
		return drink;
	}

	public void addConsumed(Context context) {
		mConsumed++;
		DBHelper.getInstance(context).addConsumed(mName, mConsumed);
	}
}