package edu.rosehulman.cocktailguide.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	public static final String dbName = "drinksDB";
	public static final int dbVersion = 5;

	static final String ASCENDING_ORDER = "ASC";
	static final String DECENDING_ORDER = "DESC";

	public static final String drinksTable = "Drinks";
	public static final String colDrinkID = "_id";
	public static final String colDrinkName = "Name";
	public static final String colDrinkPicture = "Picture";
	public static final String colDrinkDescription = "Description";
	public static final String colDrinkNumConsumed = "Consumed";

	public static final String ingredientsTable = "Ingredients";
	public static final String colIngredientsID = "_id";
	public static final String colIngredientsName = "Name";
	public static final String colIngredientAmount = "Amount";

	public static final String ingredientsInDrinksTable = "Ingredients_Drinks";
	public static final String colIngredientsInDrinksID = "_id";
	public static final String colIngredientsInDrinksDrinkID = "DrinkID";
	public static final String colIngredientsInDrinksIngredientID = "IngredientID";
	public static final String colIngredientsInDrinksAmount = "AmountInDrink";

	public static final String directionsTable = "Directions";
	public static final String colDirectionID = "_id";
	public static final String colDirectionDrinkID = "DrinkID";
	public static final String colDirection = "Direction";
	public static final String colDirectionOrder = "DirectionOrder";

	public static final String categoriesTable = "Categories";
	public static final String colCategoryID = "_id";
	public static final String colCategoryName = "Name";

	public static final String drinksInCategoriesTable = "DrinksInCategories";
	public static final String colDrinksInCategoriesID = "_id";
	public static final String colDrinksInCategoriesDrinkID = "DrinkID";
	public static final String colDrinksInCategoriesCategoryID = "CategoryID";

	private static DBHelper mDBHelper;

	public static DBHelper getInstance(Context context) {
		if (mDBHelper == null) {
			mDBHelper = new DBHelper(context);
		}
		return mDBHelper;
	}

	private DBHelper(Context context) {
		super(context, dbName, null, dbVersion);
		SQLiteDatabase db = this.getWritableDatabase();
		resetDatabase(db);
	}

	private void initializeDatabaseValues(SQLiteDatabase db) {
		// Categories
		ContentValues cv = new ContentValues();
		cv.put(colCategoryName, "Rum Based");
		cv.put(colCategoryID, 1);
		db.insert(categoriesTable, colCategoryID, cv);

		cv = new ContentValues();
		cv.put(colCategoryName, "Vodka Based");
		cv.put(colCategoryID, 2);
		db.insert(categoriesTable, colCategoryID, cv);

		cv = new ContentValues();
		cv.put(colCategoryName, "Fruity Drinks");
		cv.put(colCategoryID, 3);
		db.insert(categoriesTable, colCategoryID, cv);

		cv = new ContentValues();
		cv.put(colCategoryName, "Short Cocktails");
		cv.put(colCategoryID, 4);
		db.insert(categoriesTable, colCategoryID, cv);

		// Drinks
		cv = new ContentValues();
		cv.put(colDrinkID, 1);
		cv.put(colDrinkName, "Rum and Coke");
		cv.put(colDrinkPicture, 0x7f020000);
		cv.put(colDrinkDescription, "Rum and Coke is best drink with coke.");
		cv.put(colDrinkNumConsumed, 0);
		db.insert(drinksTable, colDrinkID, cv);

		cv = new ContentValues();
		cv.put(colDrinkID, 2);
		cv.put(colDrinkName, "Cuba Libre");
		cv.put(colDrinkPicture, 0x7f020000);
		cv.put(colDrinkDescription,
				"Rum and Coke with lime juice to add a sweet twist.");
		cv.put(colDrinkNumConsumed, 0);
		db.insert(drinksTable, colDrinkID, cv);

		cv = new ContentValues();
		cv.put(colDrinkID, 3);
		cv.put(colDrinkName, "Screwdriver");
		cv.put(colDrinkPicture, 0x7f020000);
		cv.put(colDrinkDescription,
				"An easy, fruity drink that can be made for any occation in no time at all! Exceptionally easy to make super strong with no loss of flavor.");
		cv.put(colDrinkNumConsumed, 0);
		db.insert(drinksTable, colDrinkID, cv);

		cv = new ContentValues();
		cv.put(colDrinkID, 4);
		cv.put(colDrinkName, "Amaretto Sour");
		cv.put(colDrinkPicture, 0x7f020000);
		cv.put(colDrinkDescription,
				"A sour sipper with based on almond liquor.");
		cv.put(colDrinkNumConsumed, 0);
		db.insert(drinksTable, colDrinkID, cv);

		// Drinks into Categories
		cv = new ContentValues();
		cv.put(colDrinksInCategoriesDrinkID, 1);
		cv.put(colDrinksInCategoriesCategoryID, 1);
		db.insert(drinksInCategoriesTable, colDrinksInCategoriesID, cv);

		cv = new ContentValues();
		cv.put(colDrinksInCategoriesDrinkID, 2);
		cv.put(colDrinksInCategoriesCategoryID, 1);
		db.insert(drinksInCategoriesTable, colDrinksInCategoriesID, cv);

		cv = new ContentValues();
		cv.put(colDrinksInCategoriesDrinkID, 3);
		cv.put(colDrinksInCategoriesCategoryID, 2);
		db.insert(drinksInCategoriesTable, colDrinksInCategoriesID, cv);

		cv = new ContentValues();
		cv.put(colDrinksInCategoriesDrinkID, 2);
		cv.put(colDrinksInCategoriesCategoryID, 3);
		db.insert(drinksInCategoriesTable, colDrinksInCategoriesID, cv);

		cv = new ContentValues();
		cv.put(colDrinksInCategoriesDrinkID, 3);
		cv.put(colDrinksInCategoriesCategoryID, 3);
		db.insert(drinksInCategoriesTable, colDrinksInCategoriesID, cv);

		cv = new ContentValues();
		cv.put(colDrinksInCategoriesDrinkID, 4);
		cv.put(colDrinksInCategoriesCategoryID, 4);
		db.insert(drinksInCategoriesTable, colDrinksInCategoriesID, cv);

		// Ingredients
		cv = new ContentValues();
		cv.put(colIngredientsID, 1);
		cv.put(colIngredientsName, "Rum");
		cv.put(colIngredientAmount, 1);
		db.insert(ingredientsTable, colIngredientsID, cv);

		cv = new ContentValues();
		cv.put(colIngredientsID, 2);
		cv.put(colIngredientsName, "Cola");
		cv.put(colIngredientAmount, 2);
		db.insert(ingredientsTable, colIngredientsID, cv);

		cv = new ContentValues();
		cv.put(colIngredientsID, 3);
		cv.put(colIngredientsName, "Lime Juice");
		cv.put(colIngredientAmount, 0);
		db.insert(ingredientsTable, colIngredientsID, cv);

		cv = new ContentValues();
		cv.put(colIngredientsID, 4);
		cv.put(colIngredientsName, "Vodka");
		cv.put(colIngredientAmount, 10);
		db.insert(ingredientsTable, colIngredientsID, cv);

		cv = new ContentValues();
		cv.put(colIngredientsID, 5);
		cv.put(colIngredientsName, "Orange Juice");
		cv.put(colIngredientAmount, 10);
		db.insert(ingredientsTable, colIngredientsID, cv);
		
		cv = new ContentValues();
		cv.put(colIngredientsID, 6);
		cv.put(colIngredientsName, "Amaretto Liquor");
		cv.put(colIngredientAmount, 5);
		db.insert(ingredientsTable, colIngredientsID, cv);

		cv = new ContentValues();
		cv.put(colIngredientsID, 7);
		cv.put(colIngredientsName, "Sweet and Sour Mix");
		cv.put(colIngredientAmount, 2);
		db.insert(ingredientsTable, colIngredientsID, cv);

		// Ingredients into Drinks
		cv = new ContentValues();
		cv.put(colIngredientsInDrinksID, 1);
		cv.put(colIngredientsInDrinksDrinkID, 1);
		cv.put(colIngredientsInDrinksIngredientID, 1);
		cv.put(colIngredientsInDrinksAmount, 1);
		db.insert(ingredientsInDrinksTable, colIngredientsInDrinksID, cv);

		cv = new ContentValues();
		cv.put(colIngredientsInDrinksID, 2);
		cv.put(colIngredientsInDrinksDrinkID, 1);
		cv.put(colIngredientsInDrinksIngredientID, 2);
		cv.put(colIngredientsInDrinksAmount, 2);
		db.insert(ingredientsInDrinksTable, colIngredientsInDrinksID, cv);

		cv = new ContentValues();
		cv.put(colIngredientsInDrinksID, 3);
		cv.put(colIngredientsInDrinksDrinkID, 2);
		cv.put(colIngredientsInDrinksIngredientID, 1);
		cv.put(colIngredientsInDrinksAmount, 1);
		db.insert(ingredientsInDrinksTable, colIngredientsInDrinksID, cv);

		cv = new ContentValues();
		cv.put(colIngredientsInDrinksID, 4);
		cv.put(colIngredientsInDrinksDrinkID, 2);
		cv.put(colIngredientsInDrinksIngredientID, 2);
		cv.put(colIngredientsInDrinksAmount, 2);
		db.insert(ingredientsInDrinksTable, colIngredientsInDrinksID, cv);

		cv = new ContentValues();
		cv.put(colIngredientsInDrinksID, 5);
		cv.put(colIngredientsInDrinksDrinkID, 2);
		cv.put(colIngredientsInDrinksIngredientID, 3);
		cv.put(colIngredientsInDrinksAmount, 1);
		db.insert(ingredientsInDrinksTable, colIngredientsInDrinksID, cv);

		cv = new ContentValues();
		cv.put(colIngredientsInDrinksID, 6);
		cv.put(colIngredientsInDrinksDrinkID, 3);
		cv.put(colIngredientsInDrinksIngredientID, 4);
		cv.put(colIngredientsInDrinksAmount, 2);
		db.insert(ingredientsInDrinksTable, colIngredientsInDrinksID, cv);

		cv = new ContentValues();
		cv.put(colIngredientsInDrinksID, 7);
		cv.put(colIngredientsInDrinksDrinkID, 3);
		cv.put(colIngredientsInDrinksIngredientID, 5);
		cv.put(colIngredientsInDrinksAmount, 3);
		db.insert(ingredientsInDrinksTable, colIngredientsInDrinksID, cv);
		
		cv = new ContentValues();
		cv.put(colIngredientsInDrinksDrinkID, 4);
		cv.put(colIngredientsInDrinksIngredientID, 6);
		cv.put(colIngredientsInDrinksAmount, 2);
		db.insert(ingredientsInDrinksTable, colIngredientsInDrinksID, cv);

		cv = new ContentValues();
		cv.put(colIngredientsInDrinksDrinkID, 4);
		cv.put(colIngredientsInDrinksIngredientID, 7);
		cv.put(colIngredientsInDrinksAmount, 1);
		db.insert(ingredientsInDrinksTable, colIngredientsInDrinksID, cv);
		
		// Directions
		cv = new ContentValues();
		cv.put(colDirectionDrinkID, 1);
		cv.put(colDirection, "Pour ingredients together over ice.");
		cv.put(colDirectionOrder, 1);
		db.insert(directionsTable, colDirectionID, cv);

		cv = new ContentValues();
		cv.put(colDirectionDrinkID, 1);
		cv.put(colDirection, "Stir gently to mix.");
		cv.put(colDirectionOrder, 2);
		db.insert(directionsTable, colDirectionID, cv);

		cv = new ContentValues();
		cv.put(colDirectionDrinkID, 2);
		cv.put(colDirection, "Pour ingredients together over ice.");
		cv.put(colDirectionOrder, 1);
		db.insert(directionsTable, colDirectionID, cv);

		cv = new ContentValues();
		cv.put(colDirectionDrinkID, 2);
		cv.put(colDirection, "Stir gently to mix.");
		cv.put(colDirectionOrder, 2);
		db.insert(directionsTable, colDirectionID, cv);

		cv = new ContentValues();
		cv.put(colDirectionDrinkID, 3);
		cv.put(colDirection, "Pour ingredients together into a tall glass.");
		cv.put(colDirectionOrder, 1);
		db.insert(directionsTable, colDirectionID, cv);

		cv = new ContentValues();
		cv.put(colDirectionDrinkID, 3);
		cv.put(colDirection, "Stir to mix.");
		cv.put(colDirectionOrder, 2);
		db.insert(directionsTable, colDirectionID, cv);
		
		cv = new ContentValues();
		cv.put(colDirectionDrinkID, 4);
		cv.put(colDirection, "Pour ingredients into a cocktail shaker over ice.");
		cv.put(colDirectionOrder, 1);
		db.insert(directionsTable, colDirectionID, cv);

		cv = new ContentValues();
		cv.put(colDirectionDrinkID, 4);
		cv.put(colDirection, "Shake well.");
		cv.put(colDirectionOrder, 2);
		db.insert(directionsTable, colDirectionID, cv);
		
		cv = new ContentValues();
		cv.put(colDirectionDrinkID, 4);
		cv.put(colDirection, "Strain into tumbler over fresh ice.");
		cv.put(colDirectionOrder, 3);
		db.insert(directionsTable, colDirectionID, cv);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + drinksTable + "(" + colDrinkID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + colDrinkName
				+ " TEXT, " + colDrinkPicture + " Integer, "
				+ colDrinkNumConsumed + " Integer, " + colDrinkDescription
				+ " String);");
		db.execSQL("CREATE TABLE " + ingredientsTable + "(" + colIngredientsID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + colIngredientsName
				+ " TEXT, " + colIngredientAmount + " REAL);");
		db.execSQL("CREATE TABLE " + ingredientsInDrinksTable + "("
				+ colIngredientsInDrinksID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ colIngredientsInDrinksDrinkID + " INTEGER, "
				+ colIngredientsInDrinksIngredientID + " INTEGER, "
				+ colIngredientsInDrinksAmount + " REAL, FOREIGN KEY ("
				+ colIngredientsInDrinksDrinkID + ") REFERENCES " + drinksTable
				+ " (" + colDrinkID + "), FOREIGN KEY ("
				+ colIngredientsInDrinksIngredientID + ") REFERENCES "
				+ ingredientsTable + " (" + colIngredientsID + "));");
		db.execSQL("CREATE TABLE " + directionsTable + "(" + colDirectionID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + colDirectionDrinkID
				+ " INTEGER, " + colDirection + " TEXT, " + colDirectionOrder
				+ " INTEGER, FOREIGN KEY (" + colDirectionDrinkID
				+ ") REFERENCES " + drinksTable + " (" + colDrinkID + "));");
		db.execSQL("CREATE TABLE " + categoriesTable + "(" + colCategoryID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + colCategoryName
				+ " TEXT);");
		db.execSQL("CREATE TABLE " + drinksInCategoriesTable + "("
				+ colDrinksInCategoriesID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ colDrinksInCategoriesDrinkID + " INTEGER, "
				+ colDrinksInCategoriesCategoryID + " INTEGER, FOREIGN KEY ("
				+ colDrinksInCategoriesDrinkID + ") REFERENCES " + drinksTable
				+ " (" + colDrinkID + "), FOREIGN KEY ("
				+ colDrinksInCategoriesCategoryID + ") REFERENCES "
				+ categoriesTable + " (" + colCategoryID + "));");
		initializeDatabaseValues(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		resetDatabase(db);
	}

	private void resetDatabase(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS " + ingredientsInDrinksTable);
		db.execSQL("DROP TABLE IF EXISTS " + drinksInCategoriesTable);
		db.execSQL("DROP TABLE IF EXISTS " + directionsTable);
		db.execSQL("DROP TABLE IF EXISTS " + drinksTable);
		db.execSQL("DROP TABLE IF EXISTS " + ingredientsTable);
		db.execSQL("DROP TABLE IF EXISTS " + categoriesTable);
		onCreate(db);
	}

	// db.query(table name, columns, where string, where params, group string,
	// having string, order string)
	// String Table Name: The name of the table to run the query against
	// String [ ] columns: the columns to retrieve
	// String WHERE clause: where clause, if none pass null
	// String [ ] selection args: The parameters of the WHERE clause
	// String Group by: A string specifying group by clause
	// String Having: A string specifying HAVING clause
	// String Order By by: A string Order By by clause

	public Cursor getAllDrinks() {
		SQLiteDatabase db = this.getReadableDatabase();
		String tableName = drinksTable;
		String[] columns = new String[] { colDrinkID, colDrinkName };
		String where = null;
		String[] whereParams = null;
		String groupBy = null;
		String having = null;
		String orderBy = colDrinkName + " " + ASCENDING_ORDER;
		Cursor c = db.query(tableName, columns, where, whereParams, groupBy,
				having, orderBy);
		return c;
	}

	public Cursor getDrinkCategories() {
		SQLiteDatabase db = this.getReadableDatabase();
		String tableName = categoriesTable;
		String[] columns = new String[] { colCategoryID, colCategoryName };
		String where = null;
		String[] whereParams = null;
		String groupBy = null;
		String having = null;
		String orderBy = colDrinkName + " " + ASCENDING_ORDER;
		Cursor c = db.query(tableName, columns, where, whereParams, groupBy,
				having, orderBy);
		return c;
	}

	public Cursor getDrinkFromCategoryCategories(Integer catID) {
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT * FROM " + drinksTable + " JOIN "
				+ drinksInCategoriesTable + " ON " + drinksTable + "."
				+ colDrinkID + " = " + colDrinksInCategoriesDrinkID + " WHERE "
				+ colDrinksInCategoriesCategoryID + "=? ORDER BY "
				+ colDrinkName + " " + ASCENDING_ORDER + ";";
		return db.rawQuery(query, new String[] { catID.toString() });
	}

	public Cursor getDrinkByID(Integer drinkID) {
		SQLiteDatabase db = this.getReadableDatabase();
		String tableName = drinksTable;
		String[] columns = new String[] { colDrinkID, colDrinkName,
				colDrinkNumConsumed, colDrinkPicture };
		String where = colDrinkID + "=?";
		String[] whereParams = new String[] { drinkID.toString() };
		String groupBy = null;
		String having = null;
		String orderBy = null;
		Cursor c = db.query(tableName, columns, where, whereParams, groupBy,
				having, orderBy);
		return c;
	}

	public Cursor getDrinkByName(String drinkName) {
		SQLiteDatabase db = this.getReadableDatabase();
		String tableName = drinksTable;
		String[] columns = new String[] { colDrinkID, colDrinkName,
				colDrinkNumConsumed, colDrinkPicture };
		String where = colDrinkName + "=?";
		String[] whereParams = new String[] { drinkName };
		String groupBy = null;
		String having = null;
		String orderBy = null;
		Cursor c = db.query(tableName, columns, where, whereParams, groupBy,
				having, orderBy);
		return c;
	}

	public void addConsumed(String name, int consumed) {
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "UPDATE " + drinksTable + " SET " + colDrinkNumConsumed
				+ "=" + consumed + " WHERE " + colDrinkName + "=" + name + ";";
		db.execSQL(query);
	}

	public Cursor getDirectionsForDrink(Integer drinkID) {
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT * FROM " + directionsTable + " WHERE "
				+ colDirectionDrinkID + "=?;";
		return db.rawQuery(query, new String[] { drinkID.toString() });
	}

	public Cursor getIngredientsForDrink(Integer drinkID) {
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT * FROM " + ingredientsInDrinksTable + " JOIN "
				+ ingredientsTable + " ON " + colIngredientsID + " = "
				+ colIngredientsInDrinksIngredientID + " WHERE "
				+ colIngredientsInDrinksDrinkID + "=?;";
		return db.rawQuery(query, new String[] { drinkID.toString() });
	}

	public void updateIngredientsAvailable(int ingredientID, int available) {
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "UPDATE " + ingredientsTable + " SET "
				+ colIngredientAmount + "=" + available + " WHERE "
				+ colIngredientsID + "=" + ingredientID + ";";
		db.execSQL(query);
	}

	public Cursor getAllIngredients() {
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT * FROM " + ingredientsTable + " WHERE "
				+ colIngredientAmount + " > 0;";
		return db.rawQuery(query, null);
	}
}
