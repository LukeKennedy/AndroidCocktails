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
		return DBHelper.getInstance(context).getDrinkFromCategoryCategories(
				categoryID);
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

	public ArrayList<Drink> getDrinksThatCanBeMadeByTheIngriedentsOwned(Context context) {
		ArrayList<Drink> toReturn = new ArrayList<Drink>();
		ArrayList<Drink> allDrinks = getAllDrinksAsArrayList(context);
		ArrayList<Ingredient> ingredientsOwned = Ingredient.GetAllIngredients(context);
		Boolean hasAllTheStuff;
		for(Drink drink : allDrinks) {
			hasAllTheStuff = true;
			for(Ingredient ingredientInDrink : drink.getIngredientList()) {
				if(ingredientsOwned.contains(ingredientInDrink)) {
					Ingredient fromOwned = ingredientsOwned.get(ingredientsOwned.indexOf(ingredientInDrink));
					if(fromOwned.getAmount() < ingredientInDrink.getAmount()) {
						hasAllTheStuff = false;
						break;
					}
				}
			}
			if(hasAllTheStuff) {
				toReturn.add(drink);
			}
		}
		return toReturn;
	}

	public ArrayList<Drink> getAllDrinksAsArrayList(Context context) {
		ArrayList<Drink> toReturn = new ArrayList<Drink>();
		Cursor drinkData = DBHelper.getInstance(context).getAllDrinks();
		drinkData.moveToFirst();
		while (drinkData.isAfterLast() == false) {
			int ID = drinkData.getInt(drinkData
					.getColumnIndex(DBHelper.colDrinkID));

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
			toReturn.add(drink);
			drinkData.moveToNext();
		}
		return toReturn;
	}
}