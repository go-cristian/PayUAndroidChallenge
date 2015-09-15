package co.iyubinest.payuchallenge.data.account;

import co.iyubinest.payuchallenge.data.client.OrmLiteClient;
import co.iyubinest.payuchallenge.domain.account.Account;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class OrmLiteAccount {

	public static final String NUMBER = "number";
	public static final String VALUE = "value";
	public static final String CLIENT = "client";

	@DatabaseField(generatedId = true, columnName = NUMBER)
	private int number;

	@DatabaseField(columnName = VALUE)
	private float value;

	@DatabaseField(foreign = true, columnName = CLIENT)
	private OrmLiteClient client;

	public static OrmLiteAccount to(Account account) {
		OrmLiteAccount ormLiteAccount = new OrmLiteAccount();
		ormLiteAccount.setClient(OrmLiteClient.to(account.getClient()));
		ormLiteAccount.setValue(account.getValue());
		return ormLiteAccount;
	}

	public static Account to(OrmLiteAccount ormLiteAccount) {
		Account account = new Account();
		if (ormLiteAccount.getClient() != null) {
			account.setClient(OrmLiteClient.to(ormLiteAccount.getClient()));
		}
		account.setNumber(ormLiteAccount.getNumber());
		return account;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public OrmLiteClient getClient() {
		return client;
	}

	public void setClient(OrmLiteClient client) {
		this.client = client;
	}

}
