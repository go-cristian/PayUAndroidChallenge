package co.iyubinest.payuchallenge.presentation.movement;

import java.util.Date;

import co.iyubinest.payuchallenge.domain.account.Account;
import co.iyubinest.payuchallenge.domain.movement.Movement;
import co.iyubinest.payuchallenge.domain.movement.Movement.MOVEMENT_TYPE;
import co.iyubinest.payuchallenge.domain.movement.MovementManager;

public class MovementAddPresenter {

	private MovementAddView view;
	private MovementManager movementManager;

	public MovementAddPresenter(MovementAddView movementAddView,
			MovementManager movementManager) {
		this.view = movementAddView;
		this.movementManager = movementManager;
	}

	public void addMovement(int selectedType, float value, Account account) {
		try {
			Movement movement = new Movement(
					selectedType == 0 ? MOVEMENT_TYPE.CREDIT
							: MOVEMENT_TYPE.DEBIT, value, new Date(), account,
					account.getClient());
			movementManager.create(movement, account);
			view.onMovementCreated();
		} catch (Exception e) {
			view.handleError();
		}
	}
}
