package co.iyubinest.payuchallenge.domain.account;

import java.util.List;

import co.iyubinest.payuchallenge.domain.client.Client;

public interface AccountManager {

	List<Account> getAccounts(Client client);

	void createAccount(Client client) throws Exception;

}
