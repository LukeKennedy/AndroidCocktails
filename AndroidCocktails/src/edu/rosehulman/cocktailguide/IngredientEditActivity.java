package edu.rosehulman.cocktailguide;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IngredientEditActivity extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_edit_layout);
        
        ((Button)findViewById(R.id.btn_save)).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_ingredient_edit, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		Intent result = new Intent();
		setResult(Activity.RESULT_OK, result);
		finish();
	}
}
