package co.iyubinest.payuchallenge.presentation.account;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.widget.DatePicker;
import android.widget.TextView;
import co.iyubinest.payuchallenge.BaseActivity;
import co.iyubinest.payuchallenge.R;
import co.iyubinest.payuchallenge.domain.client.Client;
import co.iyubinest.payuchallenge.domain.movement.Movement;

public class AccountInfoActivity extends BaseActivity implements
		AccountInfoView {

	private static final String CLIENT_KEY = "client_key";

	private TextView accountInfoText;

	private Date startDate, endDate;

	private final OnDateSetListener startDateSetListener = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int month, int day) {
			startDate = new GregorianCalendar(year, month, day, 0, 0, 0)
					.getTime();
			showDatePicker(endDateSetListener);
		}
	};

	private final OnDateSetListener endDateSetListener = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int month, int day) {
			endDate = new GregorianCalendar(year, month, day, 23, 59, 59)
					.getTime();
			Client client = (Client) getIntent().getSerializableExtra(
					CLIENT_KEY);
			presenter.getInfo(startDate, endDate, client);
		}
	};

	private AccountInfoPresenter presenter;

	@Override
	public void init() {
		accountInfoText = (TextView) findViewById(R.id.account_info_text);
		presenter = new AccountInfoPresenter(this, getApp()
				.getGlobalDependencies().getMovementManager());
		showDatePicker(startDateSetListener);
	}

	private void showDatePicker(OnDateSetListener onDateSetListener) {
		DatePickerDialogFragment.newInstance(onDateSetListener).show(
				getSupportFragmentManager(), "date_fragment");
	}

	@Override
	@LayoutRes
	public int viewId() {
		return R.layout.account_info_activity;
	}

	public static void start(BaseActivity baseActivity, Client client) {
		Intent intent = new Intent(baseActivity, AccountInfoActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(CLIENT_KEY, client);
		intent.putExtras(bundle);
		baseActivity.startActivity(intent);
	}

	@Override
	public void load(List<Movement> movements) {
		StringBuilder builder = new StringBuilder();
		for (Movement movement : movements) {
			builder.append(movement.getAccount().getNumber()).append("-")
					.append(movement.getValue()).append("-")
					.append(movement.getType().toString()).append("\n");
		}
		accountInfoText.setText(builder.toString());
	}
}
