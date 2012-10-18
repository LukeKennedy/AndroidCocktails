package edu.rosehulman.cocktailguide;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class CategoryItemFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
		alertBuilder.setTitle(R.string.sample_category);
		alertBuilder.setItems(R.array.category_item_array, (OnClickListener)getActivity());
		return alertBuilder.create();
	}
}
