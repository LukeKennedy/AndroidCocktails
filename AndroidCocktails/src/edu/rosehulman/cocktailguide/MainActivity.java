package edu.rosehulman.cocktailguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		((Button) findViewById(R.id.btn_browse_drinks))
				.setOnClickListener(this);
		((Button) findViewById(R.id.btn_my_ingredients))
				.setOnClickListener(this);
		((Button) findViewById(R.id.btn_drinks_i_can_make)).setOnClickListener(this);
		((Button) findViewById(R.id.btn_random_drink)).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_browse_drinks:
			Intent browseDrinksIntent = new Intent(this,
					DrinkCategoryActivity.class);
			startActivity(browseDrinksIntent);
			break;
		case R.id.btn_my_ingredients:
			Intent ingredientsIntent = new Intent(this,
					IngredientListActivity.class);
			startActivity(ingredientsIntent);
			break;
		case R.id.btn_drinks_i_can_make:
			new DrinksICanMakeFragment().show(getSupportFragmentManager(), "drinksICanMakeFragment");
			break;
		case R.id.btn_random_drink:
			Intent randomDrinksIntent = new Intent(this,
					DrinkRecipeActivity.class);
			startActivity(randomDrinksIntent);
			break;
		default:
			break;
		}
	}
}
