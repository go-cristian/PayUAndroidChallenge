package co.iyubinest.payuchallenge.data.account;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import co.iyubinest.payuchallenge.data.client.OrmLiteClient;
import co.iyubinest.payuchallenge.domain.account.Account;
import co.iyubinest.payuchallenge.domain.account.AccountManager;
import co.iyubinest.payuchallenge.domain.client.Client;
import co.iyubinest.payuchallenge.domain.ormlite.OrmLiteHelper;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class OrmLiteAccountManager implements AccountManager {

	private Dao<OrmLiteAccount, Integer> accountDao;
	private Dao<OrmLiteClient, Integer> clientDao;

	public OrmLiteAccountManager(Context context) throws Exception {
		accountDao = OpenHelperManager.getHelper(context, OrmLiteHelper.class)
				.getAccountDao();
		clientDao = OpenHelperManager.getHelper(context, OrmLiteHelper.class)
				.getClientDao();
	}

	@Override
	public List<Account> getAccounts(Client client) {
		List<Account> accounts = new ArrayList<Account>();
		try {
			List<OrmLiteAccount> localQuery = accountDao.queryForEq(
					OrmLiteAccount.CLIENT, client.getId());
			for (OrmLiteAccount ormLiteAccount : localQuery) {
				accounts.add(OrmLiteAccount.to(ormLiteAccount));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return accounts;
		}
	}

	@Override
	public void createAccount(Client client) throws Exception {
		OrmLiteAccount ormLiteAccount = new OrmLiteAccount();
		ormLiteAccount.setValue(0f);
		OrmLiteClient ormLiteClient = clientDao.queryForId(client.getId());
		ormLiteAccount.setClient(ormLiteClient);
		accountDao.create(ormLiteAccount);
	}
}
