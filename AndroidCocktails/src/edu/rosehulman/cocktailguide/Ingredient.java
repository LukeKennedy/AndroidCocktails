package edu.rosehulman.cocktailguide;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import edu.rosehulman.cocktailguide.db.DBHelper;

public class Ingredient {
	private String mName;
	private int mID;
	private double mAmount;

	public Ingredient(int ID, String name, double amount) {
		mID = ID;
		mName = name;
		mAmount = amount;
	}

	public String getName() {
		return mName;
	}

	public double getAmount() {
		return mAmount;
	}

	@Override
	public String toString() {
		return mName + " - " + mAmount;
	}

	public static ArrayList<Ingredient> GetIngredientsForDrink(Context context,
			int drinkID) {
		ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
		Cursor cur = DBHelper.getInstance(context).getIngredientsForDrink(
				drinkID);
		cur.moveToFirst();
		while (cur.isAfterLast() == false) {
			String name = cur.getString(cur
					.getColumnIndex(DBHelper.colIngredientsName));
			double amount = cur.getDouble(cur
					.getColumnIndex(DBHelper.colIngredientsInDrinksAmount));
			int id = cur.getInt(cur.getColumnIndex(DBHelper.colIngredientsID));
			ingredientList.add(new Ingredient(id, name, amount));
			cur.moveToNext();
		}
		return ingredientList;
	}

	public static ArrayList<Ingredient> GetAllIngredients(Context context) {
		ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
		Cursor cur = DBHelper.getInstance(context).getAllIngredients();
		cur.moveToFirst();
		while (cur.isAfterLast() == false) {
			String name = cur.getString(cur
					.getColumnIndex(DBHelper.colIngredientsName));
			double amount = cur.getDouble(cur
					.getColumnIndex(DBHelper.colIngredientAmount));
			int id = cur.getInt(cur.getColumnIndex(DBHelper.colIngredientsID));
			ingredientList.add(new Ingredient(id, name, amount));
			cur.moveToNext();
		}
		return ingredientList;
	}

	public void updateAmountAvailable(Context context, Double amount) {
		mAmount = amount;
		DBHelper.getInstance(context).updateIngredientsAvailable(mID, amount);
	}

	@Override
	public boolean equals(Object o) {
		Ingredient other = (Ingredient) o;
		return this.mName.equalsIgnoreCase(other.mName);
	}
}
