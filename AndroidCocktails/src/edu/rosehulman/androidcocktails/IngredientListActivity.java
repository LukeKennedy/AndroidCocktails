package edu.rosehulman.androidcocktails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IngredientListActivity extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_list_view);
        
        ((Button)findViewById(R.id.editButton1)).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_ingredient_list, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		Intent ingredientEditIntent = new Intent(this, IngredientEditActivity.class);
		startActivity(ingredientEditIntent);
	}
}
