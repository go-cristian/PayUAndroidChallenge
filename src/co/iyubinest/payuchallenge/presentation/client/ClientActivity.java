package co.iyubinest.payuchallenge.presentation.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import co.iyubinest.payuchallenge.BaseActivity;
import co.iyubinest.payuchallenge.R;
import co.iyubinest.payuchallenge.domain.client.Client;

public class ClientActivity extends BaseActivity implements ClientView {

	public static final String CLIENT_KEY = "client";
	public static final int REQUEST_CODE = 1000;

	private ClientPresenter presenter;

	public static void start(BaseActivity baseActivity) {
		Intent intent = new Intent(baseActivity, ClientActivity.class);
		baseActivity.startActivityForResult(intent, REQUEST_CODE);
	}

	private EditText nameInput, phoneInput, addressInput;

	@Override
	public void init() {
		nameInput = (EditText) findViewById(R.id.client_input_name);
		phoneInput = (EditText) findViewById(R.id.client_input_phone);
		addressInput = (EditText) findViewById(R.id.client_input_address);
		presenter = new ClientPresenter(this, getApp()
				.getGlobalDependencies().getClientManager());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.client_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.client_menu_ok:
			presenter.createClient(nameInput.getText().toString(), phoneInput
					.getText().toString(), addressInput.getText().toString());
			return true;

		default:
			break;
		}
		return false;
	}

	@Override
	@LayoutRes
	public int viewId() {
		return R.layout.client_activity;
	}

	@Override
	public void onClientCreated(Client client) {
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putSerializable(CLIENT_KEY, client);
		intent.putExtras(bundle);
		setResult(RESULT_OK, intent);
		finish();
	}

	public static boolean isClientAdded(int requestCode, int resultCode) {
		return requestCode == REQUEST_CODE && resultCode == RESULT_OK;
	}

	public static Client getAddedClient(Intent data) {
		return (Client) data.getExtras().getSerializable(CLIENT_KEY);
	}

	@Override
	public void handleError() {
		Toast.makeText(this, getString(R.string.general_error),
				Toast.LENGTH_SHORT).show();
	}
}
