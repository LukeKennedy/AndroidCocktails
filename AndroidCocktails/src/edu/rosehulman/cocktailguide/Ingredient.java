package edu.rosehulman.cocktailguide;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import edu.rosehulman.cocktailguide.db.DBHelper;

public class Ingredient {
	private String mName;
	private double mAmount;

	public Ingredient(String name, double amount) {
		mName = name;
		mAmount = amount;
	}

	public String getName() {
		return mName;
	}

	public double getAmount() {
		return mAmount;
	}

	public static ArrayList<Ingredient> GetIngredientsForDrink(Context context, int drinkID) {
		ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
		Cursor cur = DBHelper.getInstance(context).getIngredientsForDrink(drinkID);
		cur.moveToFirst();
		while (cur.isAfterLast() == false) {
			String name = cur.getString(cur.getColumnIndex(DBHelper.colIngredientsName));
			int amount = cur.getInt(cur.getColumnIndex(DBHelper.colIngredientsInDrinksAmount));
			ingredientList.add(new Ingredient(name, amount));
			cur.moveToNext();
		}
		return ingredientList;
	}
}
