package co.iyubinest.payuchallenge.presentation.movement;

import java.util.List;

import co.iyubinest.payuchallenge.domain.account.Account;
import co.iyubinest.payuchallenge.domain.movement.Movement;
import co.iyubinest.payuchallenge.domain.movement.MovementManager;

public class MovementListPresenter {

	private final MovementListView view;

	private final MovementManager movementManager;

	private List<Movement> movements;

	private Account account;

	public MovementListPresenter(MovementListView movementListView,
			MovementManager movementManager) {
		this.view = movementListView;
		this.movementManager = movementManager;
	}

	public void init(Account account) {
		this.account = account;
		updateList();
	}

	public void updateList() {
		movements = movementManager.getMovements(account);
		view.load(movements);
	}

	public Account getAccount() {
		return account;
	}

}
