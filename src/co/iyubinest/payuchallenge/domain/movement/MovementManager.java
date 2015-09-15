package co.iyubinest.payuchallenge.domain.movement;

import java.util.Date;
import java.util.List;

import co.iyubinest.payuchallenge.domain.account.Account;
import co.iyubinest.payuchallenge.domain.client.Client;

public interface MovementManager {

	List<Movement> getMovements(Account account);

	void create(Movement movement, Account account) throws Exception;

	List<Movement> getAccountsDetails(Date startDate, Date endDate,
			Client client);

}
