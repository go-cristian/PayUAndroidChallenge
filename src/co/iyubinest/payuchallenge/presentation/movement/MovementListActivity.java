package co.iyubinest.payuchallenge.presentation.movement;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import co.iyubinest.payuchallenge.BaseActivity;
import co.iyubinest.payuchallenge.Navigator;
import co.iyubinest.payuchallenge.R;
import co.iyubinest.payuchallenge.domain.account.Account;
import co.iyubinest.payuchallenge.domain.movement.Movement;

public class MovementListActivity extends BaseActivity implements
		MovementListView {

	private static final String MOVEMENT_ACCOUNT_KEY = "movement_account_key";

	private MovementListPresenter presenter;

	private ListView movementListView;

	private MovementListAdapter adapter;

	@Override
	public void init() {
		movementListView = (ListView) findViewById(R.id.movement_list);
		Account account = (Account) getIntent().getExtras().getSerializable(
				MOVEMENT_ACCOUNT_KEY);
		presenter = new MovementListPresenter(this, getApp()
				.getGlobalDependencies().getMovementManager());
		presenter.init(account);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.movement_list_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.movement_list_menu_add:
			Navigator.movementAdd(getBaseActivity(), presenter.getAccount());
			return true;

		default:
			break;
		}
		return false;
	}

	@Override
	@LayoutRes
	public int viewId() {
		return R.layout.movement_list_activity;
	}

	@Override
	public void load(List<Movement> movements) {
		adapter = new MovementListAdapter(this, movements);
		movementListView.setAdapter(adapter);
	}

	@Override
	public void updateList() {
		adapter.notifyDataSetChanged();
	}

	public static void start(BaseActivity baseActivity, Account account) {
		Intent intent = new Intent(baseActivity, MovementListActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(MOVEMENT_ACCOUNT_KEY, account);
		intent.putExtras(bundle);
		baseActivity.startActivity(intent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (Navigator.isMovementAdded(requestCode, resultCode)) {
			presenter.updateList();
		}
	}
}
