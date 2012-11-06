package edu.rosehulman.cocktailguide;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DrinkRecipeActivity extends Activity {

	private LinearLayout mLinearLayout;
	private Drink mDrink;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drink_recipe_layout);
        
        mLinearLayout = (LinearLayout)findViewById(R.id.drink_linear_layout);
        
        if (mDrink == null) {
	        int drinkID = getIntent().getIntExtra(CategoryItemFragment.KEY_DRINK_ID, -1);
	        mDrink = Drink.GetDrink(this, drinkID);
        }
        
        ((TextView)findViewById(R.id.drink_name)).setText(mDrink.getName());
        ImageView image = (ImageView)findViewById(R.id.drink_image);
        Drawable drawable = mDrink.getPicture();
        image.setImageDrawable(drawable);
        
        for (Ingredient ingredient : mDrink.getIngredientList()) {
        	TextView textView = new TextView(this);
        	textView.setTextSize(20);
        	textView.setText(ingredient.toString());
        	
        	mLinearLayout.addView(textView);
        }
        
        for (Direction direction : mDrink.getDirections()) {
        	TextView textView = new TextView(this);
        	textView.setText(direction.getDirection());
        	
        	mLinearLayout.addView(textView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_drink_recipe, menu);
        return true;
    }
}
