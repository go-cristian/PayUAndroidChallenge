package co.iyubinest.payuchallenge.domain.client;

import java.util.List;

public interface ClientManager {

	List<Client> getClients();

	Client create(Client client) throws Exception;

}
