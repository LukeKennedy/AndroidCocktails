package edu.rosehulman.cocktailguide;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DrinksICanMakeFragment extends DialogFragment implements OnItemClickListener {
	
	private ArrayList<Drink> mDrinks;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = new Dialog(getActivity());
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		dialog.setContentView(inflater.inflate(
				R.layout.list_view_items, null));
		dialog.setTitle("Drinks you can make");
		
		ListView listView = (ListView) dialog.findViewById(R.id.list_view);
		
		mDrinks = Drink.getDrinksThatCanBeMadeByTheIngredientsOwned(getActivity());
		ArrayAdapter<Drink> adapter = new ArrayAdapter<Drink>(getActivity(), android.R.layout.simple_list_item_1, mDrinks);
		
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(this);
		
		return dialog;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		Drink drink = mDrinks.get(pos);
		Intent drinkRecipeIntent = new Intent(getActivity(), DrinkRecipeActivity.class);
		drinkRecipeIntent.putExtra(CategoryItemFragment.KEY_DRINK_ID, drink.getId());
		startActivity(drinkRecipeIntent);
	}

}
