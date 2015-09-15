package co.iyubinest.payuchallenge.presentation.client;

import java.util.List;

import co.iyubinest.payuchallenge.domain.client.Client;
import co.iyubinest.payuchallenge.domain.client.ClientManager;

public class ClientListPresenter {

	private final ClientListView view;

	private final ClientManager clientManager;
	
	private List<Client> clientList;

	public ClientListPresenter(ClientListView view, ClientManager clientManager) {
		this.view = view;
		this.clientManager = clientManager;
	}

	public void init() {
		clientList = clientManager.getClients();
		view.load(clientList);
	}

	public void addClient(Client client) {
		init();
	}

}
