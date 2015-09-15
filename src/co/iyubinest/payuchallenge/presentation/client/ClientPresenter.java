package co.iyubinest.payuchallenge.presentation.client;

import co.iyubinest.payuchallenge.domain.client.Client;
import co.iyubinest.payuchallenge.domain.client.ClientManager;

public class ClientPresenter {

	private ClientView view;
	private ClientManager clientManager;

	public ClientPresenter(ClientView clientView, ClientManager clientManager) {
		this.view = clientView;
		this.clientManager = clientManager;
	}

	public void createClient(String name, String phone, String address) {
		try {
			Client client = clientManager.create(new Client(null, name,
					address, phone));
			view.onClientCreated(client);
		} catch (Exception e) {
			e.printStackTrace();
			view.handleError();
		}
	}

}
