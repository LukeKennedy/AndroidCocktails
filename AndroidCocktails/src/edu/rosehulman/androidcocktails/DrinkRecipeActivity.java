package edu.rosehulman.androidcocktails;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class DrinkRecipeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drink_recipe_layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_drink_recipe, menu);
        return true;
    }
}
