package co.iyubinest.payuchallenge.presentation.account;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import co.iyubinest.payuchallenge.BaseActivity;
import co.iyubinest.payuchallenge.Navigator;
import co.iyubinest.payuchallenge.R;
import co.iyubinest.payuchallenge.domain.account.Account;
import co.iyubinest.payuchallenge.domain.client.Client;
import co.iyubinest.payuchallenge.presentation.account.AccountListAdapter.AccountClickListener;

public class AccountListActivity extends BaseActivity implements
		AccountListView {

	private static final String ACCCOUNT_CLIENT_KEY = "account_client_key";

	private AccountListPresenter presenter;

	private ListView accountListView;

	private final AccountClickListener accountClickListener = new AccountClickListener() {

		@Override
		public void onClick(Account account) {
			Navigator.movementList(getBaseActivity(), account);
		}
	};

	@Override
	public void init() {
		accountListView = (ListView) findViewById(R.id.account_list);
		Client client = (Client) getIntent().getExtras().getSerializable(
				ACCCOUNT_CLIENT_KEY);
		presenter = new AccountListPresenter(this, getApp()
				.getGlobalDependencies().getAccountManager());
		presenter.init(client);
	}

	@Override
	@LayoutRes
	public int viewId() {
		return R.layout.account_list_activity;
	}

	public static void start(BaseActivity baseActivity, Client client) {
		Intent intent = new Intent(baseActivity, AccountListActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(ACCCOUNT_CLIENT_KEY, client);
		intent.putExtras(bundle);
		baseActivity.startActivity(intent);
	}

	@Override
	public void load(List<Account> accounts) {
		accountListView.setAdapter(new AccountListAdapter(this, accounts,
				accountClickListener));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.account_list_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.account_add_menu_ok:
			presenter.createAccount();
			break;
			
		case R.id.account_add_menu_info:
			Navigator.showInfo(getBaseActivity(), presenter.getClient());

		default:
			break;
		}
		return false;
	}

	@Override
	public void handleError() {
		Toast.makeText(this, getString(R.string.general_error),
				Toast.LENGTH_SHORT).show();
	}
}
