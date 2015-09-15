package co.iyubinest.payuchallenge.presentation.account;

import java.util.List;

import co.iyubinest.payuchallenge.domain.account.Account;

public interface AccountListView {

	void load(List<Account> accounts);

	void handleError();

}
