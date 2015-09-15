package co.iyubinest.payuchallenge.presentation.client;

import co.iyubinest.payuchallenge.domain.client.Client;

public interface ClientView {

	void onClientCreated(Client client);

	void handleError();

}
