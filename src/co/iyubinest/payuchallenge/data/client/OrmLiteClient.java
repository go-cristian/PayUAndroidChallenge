package co.iyubinest.payuchallenge.data.client;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import co.iyubinest.payuchallenge.domain.client.Client;

@DatabaseTable
public class OrmLiteClient {

	public static final String ID = "_id";
	public static final String NAME = "name";
	public static final String ADDRESS = "address";
	public static final String PHONE = "phone";

	@DatabaseField(generatedId = true, columnName = ID)
	private Integer id;

	@DatabaseField(columnName = NAME)
	private String name;

	@DatabaseField(columnName = ADDRESS)
	private String address;

	@DatabaseField(columnName = PHONE)
	private String phone;

	public static OrmLiteClient to(Client client) {
		OrmLiteClient ormLiteClient = new OrmLiteClient();
		ormLiteClient.setName(client.getName());
		ormLiteClient.setAddress(client.getAddress());
		ormLiteClient.setPhone(client.getPhone());
		return ormLiteClient;
	}

	public static Client to(OrmLiteClient ormLiteClient) {
		return new Client(ormLiteClient.getId(), ormLiteClient.getName(),
				ormLiteClient.getAddress(), ormLiteClient.getPhone());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
