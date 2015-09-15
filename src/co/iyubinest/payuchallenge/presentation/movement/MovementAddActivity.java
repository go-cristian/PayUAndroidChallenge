package co.iyubinest.payuchallenge.presentation.movement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import co.iyubinest.payuchallenge.BaseActivity;
import co.iyubinest.payuchallenge.R;
import co.iyubinest.payuchallenge.domain.account.Account;

public class MovementAddActivity extends BaseActivity implements
		MovementAddView {

	private static final String MOVEMENT_ACCOUNT_KEY = "movement_account_key";

	private static final int REQUEST_CODE = 1000;

	private MovementAddPresenter presenter;

	private Spinner typeInput;

	private EditText valueInput;

	@Override
	public void init() {
		typeInput = (Spinner) findViewById(R.id.movement_add_type);
		valueInput = (EditText) findViewById(R.id.movement_add_value);
		presenter = new MovementAddPresenter(this, getApp()
				.getGlobalDependencies().getMovementManager());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.movement_add_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.movement_add_menu_ok:
			Account account = (Account) getIntent().getExtras()
					.getSerializable(MOVEMENT_ACCOUNT_KEY);
			presenter.addMovement(typeInput.getSelectedItemPosition(),
					Float.valueOf(valueInput.getText().toString()), account);
			return true;

		default:
			break;
		}
		return false;
	}

	@Override
	@LayoutRes
	public int viewId() {
		return R.layout.movement_add_activity;
	}

	public static void start(BaseActivity baseActivity, Account account) {
		Intent intent = new Intent(baseActivity, MovementAddActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(MOVEMENT_ACCOUNT_KEY, account);
		intent.putExtras(bundle);
		baseActivity.startActivityForResult(intent, REQUEST_CODE);
	}

	@Override
	public void onMovementCreated() {
		setResult(RESULT_OK);
		finish();
	}

	public static boolean isMovementAdded(int requestCode, int resultCode) {
		return requestCode == REQUEST_CODE && resultCode == RESULT_OK;
	}

	@Override
	public void handleError() {
		Toast.makeText(this, getString(R.string.general_error),
				Toast.LENGTH_SHORT).show();
	}

}
