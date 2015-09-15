package co.iyubinest.payuchallenge.presentation.account;

import java.util.List;

import co.iyubinest.payuchallenge.domain.account.Account;
import co.iyubinest.payuchallenge.domain.account.AccountManager;
import co.iyubinest.payuchallenge.domain.client.Client;

public class AccountListPresenter {

	private final AccountListView view;
	private final AccountManager accountManager;
	private Client client;

	public AccountListPresenter(AccountListView accountListView,
			AccountManager accountManager) {
		this.view = accountListView;
		this.accountManager = accountManager;
	}

	public void init(Client client) {
		this.client = client;
		updateAccountList();
	}

	private void updateAccountList() {
		List<Account> accounts = accountManager.getAccounts(client);
		view.load(accounts);
	}

	public void createAccount() {
		try {
			accountManager.createAccount(client);
			updateAccountList();
		} catch (Exception e) {
			e.printStackTrace();
			view.handleError();
		}
	}

	public Client getClient() {
		return client;
	}
}
