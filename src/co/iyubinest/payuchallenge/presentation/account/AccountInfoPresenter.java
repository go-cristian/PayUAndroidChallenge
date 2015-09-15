package co.iyubinest.payuchallenge.presentation.account;

import java.util.Date;
import java.util.List;

import co.iyubinest.payuchallenge.domain.client.Client;
import co.iyubinest.payuchallenge.domain.movement.Movement;
import co.iyubinest.payuchallenge.domain.movement.MovementManager;

public class AccountInfoPresenter {

	private final AccountInfoView view;
	private final MovementManager movementManager;

	public AccountInfoPresenter(AccountInfoView view,
			MovementManager movementManager) {
		this.view = view;
		this.movementManager = movementManager;
	}

	public void getInfo(Date startDate, Date endDate, Client client) {
		List<Movement> accounts = movementManager.getAccountsDetails(startDate,
				endDate, client);
		view.load(accounts);
	}

}
