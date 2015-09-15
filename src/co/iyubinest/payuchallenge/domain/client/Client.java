package co.iyubinest.payuchallenge.domain.client;

import java.io.Serializable;
import java.util.List;

import co.iyubinest.payuchallenge.domain.account.Account;

public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private final Integer id;
	private final String name;
	private final String address;
	private final String phone;
	
	private List<Account> accounts; 
	
	public Client(Integer id,
			String name, 
			String address, 
			String phone) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}
	
	public List<Account> getAccounts(){
		return accounts;
	}
	
}
