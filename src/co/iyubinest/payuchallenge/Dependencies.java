package co.iyubinest.payuchallenge;

import android.app.Application;
import co.iyubinest.payuchallenge.data.account.OrmLiteAccountManager;
import co.iyubinest.payuchallenge.data.client.OrmLiteClientManager;
import co.iyubinest.payuchallenge.data.movement.OrmLiteMovementManager;
import co.iyubinest.payuchallenge.domain.account.AccountManager;
import co.iyubinest.payuchallenge.domain.client.ClientManager;
import co.iyubinest.payuchallenge.domain.movement.MovementManager;

public class Dependencies {

	public ClientManager clientManager;
	public AccountManager accountManager;
	public MovementManager movementManager;

	public Dependencies(Application application) throws Exception {
		clientManager = new OrmLiteClientManager(application);
		accountManager = new OrmLiteAccountManager(application);
		movementManager = new OrmLiteMovementManager(application);
	}

	public ClientManager getClientManager() {
		return clientManager;
	}

	public AccountManager getAccountManager() {
		return accountManager;
	}

	public MovementManager getMovementManager() {
		return movementManager;
	}
}
