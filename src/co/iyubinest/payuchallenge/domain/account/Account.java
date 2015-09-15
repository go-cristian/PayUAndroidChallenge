package co.iyubinest.payuchallenge.domain.account;

import java.io.Serializable;

import co.iyubinest.payuchallenge.domain.client.Client;
import co.iyubinest.payuchallenge.domain.movement.Movement;
import co.iyubinest.payuchallenge.domain.movement.Movement.MOVEMENT_TYPE;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	private Client client;
	private int number;
	private float value;

	public int getNumber() {
		return number;
	}

	public float getValue() {
		return value;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public boolean canProcessMovement(Movement movement) {
		if (movement.getType() == MOVEMENT_TYPE.DEBIT
				&& value - movement.getValue() <= 0) {
			return false;
		}
		return true;
	}
}
