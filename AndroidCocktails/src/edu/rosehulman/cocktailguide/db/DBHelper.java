package edu.rosehulman.cocktailguide.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	static final String dbName = "drinksDB";
	static final int dbVersion = 1;

	static final String ASCENDING_ORDER = "ASC";
	static final String DECENDING_ORDER = "DESC";

	static final String drinksTable = "Drinks";
	static final String colDrinkID = "ID";
	static final String colDrinkName = "Name";
	static final String colDrinkPicture = "Picture";
	static final String colDrinkNumConsumed = "Consumed";

	static final String ingredientsTable = "Ingredients";
	static final String colIngredientsID = "ID";
	static final String colIngredientsName = "Name";
	static final String colIngredientAmount = "Amount";

	static final String ingredientsInDrinksTable = "Ingredients_Drinks";
	static final String colIngredientsInDrinksID = "ID";
	static final String colIngredientsInDrinksDrinkID = "DrinkID";
	static final String colIngredientsInDrinksIngredientID = "IngredientID";
	static final String colIngredientsInDrinksAmount = "Amount";

	static final String directionsTable = "Directions";
	static final String colDirectionID = "ID";
	static final String colDirectionDrinkID = "DrinkID";
	static final String colDirection = "Direction";
	static final String colDirectionOrder = "Order";

	static final String categoriesTable = "Categories";
	static final String colCategoryID = "ID";
	static final String colCategoryName = "Name";

	static final String drinksInCategoriesTable = "Categories";
	static final String colDrinksInCategoriesID = "ID";
	static final String colDrinksInCategoriesDrinkID = "DrinkID";
	static final String colDrinksInCategoriesCategoryID = "CategoryID";

	private static DBHelper mDBHelper;

	public static DBHelper getInstance(Context context) {
		if (mDBHelper == null) {
			return new DBHelper(context);
		} else {
			return mDBHelper;
		}
	}

	private DBHelper(Context context) {
		super(context, dbName, null, dbVersion);

		// EXAMPLE STUFF
		// SQLiteDatabase db=this.getWritableDatabase();
		// ContentValues cv=new ContentValues();
		// cv.put(colDeptID, 1);
		// cv.put(colDeptName, "Sales");
		// db.insert(deptTable, colDeptID, cv);
		//
		// cv.put(colDeptID, 2);
		// cv.put(colDeptName, "IT");
		// db.insert(deptTable, colDeptID, cv);
		// db.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + drinksTable + "(" + colDrinkID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + colDrinkName
				+ " TEXT, " + colDrinkPicture + " Integer, "
				+ colDrinkNumConsumed + " Integer);");
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
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + drinksTable);
		db.execSQL("DROP TABLE IF EXISTS " + ingredientsTable);
		db.execSQL("DROP TABLE IF EXISTS " + ingredientsInDrinksTable);
		db.execSQL("DROP TABLE IF EXISTS " + directionsTable);
		db.execSQL("DROP TABLE IF EXISTS " + categoriesTable);
		db.execSQL("DROP TABLE IF EXISTS " + drinksInCategoriesTable);
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
}
