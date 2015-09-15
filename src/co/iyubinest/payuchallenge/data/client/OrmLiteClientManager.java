package co.iyubinest.payuchallenge.data.client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import co.iyubinest.payuchallenge.domain.client.Client;
import co.iyubinest.payuchallenge.domain.client.ClientManager;
import co.iyubinest.payuchallenge.domain.ormlite.OrmLiteHelper;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class OrmLiteClientManager implements ClientManager {

	private Dao<OrmLiteClient, Integer> clientDao;

	public OrmLiteClientManager(Context context) throws SQLException {
		clientDao = OpenHelperManager.getHelper(context, OrmLiteHelper.class)
				.getClientDao();
	}

	@Override
	public List<Client> getClients() {
		List<Client> clients = new ArrayList<Client>();
		try {
			// can be better for a lot of results
			List<OrmLiteClient> localQuery = clientDao.queryForAll();
			for (OrmLiteClient ormLiteClient : localQuery) {
				clients.add(OrmLiteClient.to(ormLiteClient));
			}
		} catch (SQLException e) {
			// silent the exception we only need the client list.
			e.printStackTrace();
		} finally {
			return clients;
		}
	}

	@Override
	public Client create(Client client) throws Exception {
		int id = clientDao.create(OrmLiteClient.to(client));
		return new Client(id, client.getName(), client.getAddress(),
				client.getPhone());
	}

}
