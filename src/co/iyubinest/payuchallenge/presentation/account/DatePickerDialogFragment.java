package co.iyubinest.payuchallenge.presentation.account;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class DatePickerDialogFragment extends DialogFragment {

	private OnDateSetListener onDateSetListener;

	public static DatePickerDialogFragment newInstance(
			OnDateSetListener onDateSetListener) {
		DatePickerDialogFragment fragment = new DatePickerDialogFragment();
		Bundle bundle = new Bundle();
		fragment.setArguments(bundle);
		fragment.setOnDateSetListener(onDateSetListener);
		return fragment;
	}

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new DatePickerDialog(getActivity(), onDateSetListener, Calendar
				.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(
				Calendar.MONTH), Calendar.getInstance().get(
				Calendar.DAY_OF_MONTH));
	}

	public void setOnDateSetListener(OnDateSetListener onDateSetListener) {
		this.onDateSetListener = onDateSetListener;
	}
}
