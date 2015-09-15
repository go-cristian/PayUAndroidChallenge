package co.iyubinest.payuchallenge.presentation.client;

import java.util.List;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import co.iyubinest.payuchallenge.BaseActivity;
import co.iyubinest.payuchallenge.Navigator;
import co.iyubinest.payuchallenge.R;
import co.iyubinest.payuchallenge.domain.client.Client;
import co.iyubinest.payuchallenge.presentation.client.ClientListAdapter.ClientClickListener;

public class ClientListActivity extends BaseActivity implements ClientListView {

	private ClientListPresenter presenter;

	private ListView clientListView;

	private final ClientClickListener onClientClickListener = new ClientClickListener() {

		@Override
		public void onClick(Client client) {
			Navigator.accountList(getBaseActivity(), client);
		}
	};

	@Override
	public void init() {
		clientListView = (ListView) findViewById(R.id.client_list);
		presenter = new ClientListPresenter(this, getApp()
				.getGlobalDependencies().getClientManager());
		presenter.init();
	}

	@Override
	@LayoutRes
	public int viewId() {
		return R.layout.client_list_activity;
	}

	@Override
	public void load(List<Client> clientList) {
		clientListView.setAdapter(new ClientListAdapter(this, clientList,
				onClientClickListener));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.client_list_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.client_list_menu_add:
			Navigator.createClient(getBaseActivity());
			return true;

		default:
			break;
		}
		return false;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (Navigator.isClientAdded(requestCode, resultCode)) {
			Client client = Navigator.getAddedClient(data);
			presenter.addClient(client);
		}
	}

}
