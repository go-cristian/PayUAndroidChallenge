package co.iyubinest.payuchallenge;

import android.content.Intent;
import co.iyubinest.payuchallenge.domain.account.Account;
import co.iyubinest.payuchallenge.domain.client.Client;
import co.iyubinest.payuchallenge.presentation.account.AccountInfoActivity;
import co.iyubinest.payuchallenge.presentation.account.AccountListActivity;
import co.iyubinest.payuchallenge.presentation.client.ClientActivity;
import co.iyubinest.payuchallenge.presentation.movement.MovementAddActivity;
import co.iyubinest.payuchallenge.presentation.movement.MovementListActivity;

public class Navigator {

	public static void clientDetail(BaseActivity baseActivity, Client client) {

	}

	public static void accountList(BaseActivity baseActivity, Client client) {
		AccountListActivity.start(baseActivity, client);
	}

	public static void movementList(BaseActivity baseActivity, Account account) {
		MovementListActivity.start(baseActivity, account);
	}

	public static void movementAdd(BaseActivity baseActivity, Account account) {
		MovementAddActivity.start(baseActivity, account);
	}

	public static boolean isMovementAdded(int requestCode, int resultCode) {
		return MovementAddActivity.isMovementAdded(requestCode, resultCode);
	}

	public static void createClient(BaseActivity baseActivity) {
		ClientActivity.start(baseActivity);
	}

	public static boolean isClientAdded(int requestCode, int resultCode) {
		return ClientActivity.isClientAdded(requestCode, resultCode);
	}

	public static Client getAddedClient(Intent data) {
		return ClientActivity.getAddedClient(data);
	}

	public static void showInfo(BaseActivity baseActivity, Client client) {
		AccountInfoActivity.start(baseActivity, client);
	}

}
