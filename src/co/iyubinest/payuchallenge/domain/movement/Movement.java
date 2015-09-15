package co.iyubinest.payuchallenge.domain.movement;

import java.io.Serializable;
import java.util.Date;

import co.iyubinest.payuchallenge.domain.account.Account;
import co.iyubinest.payuchallenge.domain.client.Client;

public class Movement implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum MOVEMENT_TYPE {
		DEBIT, CREDIT;

		public static MOVEMENT_TYPE from(String type) {
			if (type.equals(CREDIT.toString())) {
				return CREDIT;
			} else {
				return DEBIT;
			}
		}
	}

	private final MOVEMENT_TYPE type;
	private final float value;
	private final Date date;
	private final Account account;
	private final Client client;

	public Movement(MOVEMENT_TYPE type, float value, Date date,
			Account account, Client client) {
		super();
		this.type = type;
		this.value = value;
		this.date = date;
		this.account = account;
		this.client = client;
	}

	public MOVEMENT_TYPE getType() {
		return type;
	}

	public float getValue() {
		return value;
	}

	public Date getDate() {
		return date;
	}

	public Account getAccount() {
		return account;
	}

	public Client getClient() {
		return client;
	}

}
