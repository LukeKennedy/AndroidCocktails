package edu.rosehulman.cocktailguide;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IngredientEditActivity extends Activity implements OnClickListener {

	private TextView mIngName;
	private EditText mIngAmt;
	private int mIngID;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_edit_layout);
        
        Intent intent = getIntent();
        mIngID = intent.getIntExtra(IngredientListActivity.KEY_INGREDIENT_ID, -1);
        String name = intent.getStringExtra(IngredientListActivity.KEY_INGREDIENT_NAME);
        double amt = intent.getDoubleExtra(IngredientListActivity.KEY_INGREDIENT_AMT, -1);
        
        mIngName = (TextView)findViewById(R.id.ing_name);
        mIngAmt = (EditText)findViewById(R.id.edit_text_amt);
        
        mIngName.setText(name);
        mIngAmt.setText(amt + "");
        
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
		result.putExtra(IngredientListActivity.KEY_INGREDIENT_AMT, Double.parseDouble(mIngAmt.getText().toString()));
		result.putExtra(IngredientListActivity.KEY_INGREDIENT_ID, mIngID);
		setResult(Activity.RESULT_OK, result);
		finish();
	}
}
