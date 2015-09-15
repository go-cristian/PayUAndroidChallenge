package co.iyubinest.payuchallenge.data.movement;

import java.util.Date;

import co.iyubinest.payuchallenge.data.account.OrmLiteAccount;
import co.iyubinest.payuchallenge.data.client.OrmLiteClient;
import co.iyubinest.payuchallenge.domain.account.Account;
import co.iyubinest.payuchallenge.domain.client.Client;
import co.iyubinest.payuchallenge.domain.movement.Movement;
import co.iyubinest.payuchallenge.domain.movement.Movement.MOVEMENT_TYPE;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class OrmLiteMovement {

	public static final String ID = "_id";
	public static final String TYPE = "type";
	public static final String VALUE = "address";
	public static final String DATE = "date";
	public static final String ACCOUNT = "account";
	public static final String CLIENT = "client";

	@DatabaseField(generatedId = true, columnName = ID)
	private Integer id;

	@DatabaseField(columnName = TYPE)
	private String type;

	@DatabaseField(columnName = VALUE)
	private float value;

	@DatabaseField(columnName = DATE, dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss")
	private Date date;

	@DatabaseField(foreign = true, columnName = ACCOUNT)
	private OrmLiteAccount account;

	@DatabaseField(foreign = true, columnName = CLIENT)
	private OrmLiteClient client;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public OrmLiteAccount getAccount() {
		return account;
	}

	public void setAccount(OrmLiteAccount account) {
		this.account = account;
	}

	public OrmLiteClient getClient() {
		return client;
	}

	public void setClient(OrmLiteClient client) {
		this.client = client;
	}

	public static Movement to(OrmLiteMovement ormLiteMovement) {
		MOVEMENT_TYPE movementType = ormLiteMovement.getType().equals(
				MOVEMENT_TYPE.CREDIT.toString()) ? MOVEMENT_TYPE.CREDIT
				: MOVEMENT_TYPE.DEBIT;
		Account account = OrmLiteAccount.to(ormLiteMovement.getAccount());
		Client client = OrmLiteClient.to(ormLiteMovement.getClient());
		return new Movement(movementType, ormLiteMovement.getValue(),
				ormLiteMovement.getDate(), account, client);
	}

}
