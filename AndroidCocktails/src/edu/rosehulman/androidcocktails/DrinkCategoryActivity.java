package edu.rosehulman.androidcocktails;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class DrinkCategoryActivity extends FragmentActivity implements OnItemClickListener, OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_menu);
        
        ((ListView)findViewById(R.id.category_list)).setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_browse_drinks, menu);
        return true;
    }

	@Override
	public void onItemClick(AdapterView<?> list, View parent, int pos, long id) {
		Toast.makeText(this, "You hit Category " + id, Toast.LENGTH_SHORT).show();
		DialogFragment newFragment = new CategoryItemFragment();
		newFragment.show(getSupportFragmentManager(), "categoryItemPicker");
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		Toast.makeText(this, "You hit Category item " + which, Toast.LENGTH_SHORT).show();
		Intent drinkRecipyIntent = new Intent(this, DrinkRecipeActivity.class);
		startActivity(drinkRecipyIntent);
	}
}
