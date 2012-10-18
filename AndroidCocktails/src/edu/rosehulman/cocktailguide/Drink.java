package edu.rosehulman.cocktailguide;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

public class Drink {
	private String mName;
	private String mDescription;
	private ArrayList<Ingredient> mIngredientList;
	private ArrayList<String> mDirections;
	private Drawable mPicture;
	private int mConsumed;
}