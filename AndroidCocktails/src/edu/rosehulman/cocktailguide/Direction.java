package edu.rosehulman.cocktailguide;

import java.util.ArrayList;
import java.util.Collections;

import android.content.Context;
import android.database.Cursor;
import edu.rosehulman.cocktailguide.db.DBHelper;

public class Direction implements Comparable<Direction> {
	private String mDirection;
	private int mOrder;
	
	public Direction(String dir, int order) {
		mDirection = dir;
		mOrder = order;
	}

	public String getDirection() {
		return mDirection;
	}

	public int getOrder() {
		return mOrder;
	}
	
	public static ArrayList<Direction> GetDirectionsForDrink(Context context,
			int drinkID) {
		ArrayList<Direction> dirList = new ArrayList<Direction>();
		Cursor cur = DBHelper.getInstance(context).getDirectionsForDrink(drinkID);
		cur.moveToFirst();
		while (cur.isAfterLast() == false) {
			String direction = cur.getString(cur.getColumnIndex(DBHelper.colDirection));
			int order = cur.getInt(cur.getColumnIndex(DBHelper.colDirectionOrder));
			dirList.add(new Direction(direction, order));
			cur.moveToNext();
		}
		Collections.sort(dirList);
		return dirList;
	}

	@Override
	public int compareTo(Direction another) {
		if(mOrder > another.getOrder()) {
			return 1;
		} else if (mOrder < another.getOrder()) {
			return -1;
		}
		return 0;
	}
}
