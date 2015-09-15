package co.iyubinest.payuchallenge.data.movement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import co.iyubinest.payuchallenge.data.account.OrmLiteAccount;
import co.iyubinest.payuchallenge.domain.account.Account;
import co.iyubinest.payuchallenge.domain.client.Client;
import co.iyubinest.payuchallenge.domain.movement.Movement;
import co.iyubinest.payuchallenge.domain.movement.Movement.MOVEMENT_TYPE;
import co.iyubinest.payuchallenge.domain.movement.MovementManager;
import co.iyubinest.payuchallenge.domain.ormlite.OrmLiteHelper;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class OrmLiteMovementManager implements MovementManager {

	private Dao<OrmLiteMovement, Integer> movementDao;
	private Dao<OrmLiteAccount, Integer> accountDao;

	public OrmLiteMovementManager(Context context) throws Exception {
		movementDao = OpenHelperManager.getHelper(context, OrmLiteHelper.class)
				.getMovementDao();
		accountDao = OpenHelperManager.getHelper(context, OrmLiteHelper.class)
				.getAccountDao();
	}

	@Override
	public List<Movement> getMovements(Account account) {
		List<Movement> movements = new ArrayList<Movement>();
		try {
			List<OrmLiteMovement> localQuery = movementDao.queryForEq(
					OrmLiteMovement.ACCOUNT, account.getNumber());
			for (OrmLiteMovement ormLiteMovement : localQuery) {
				movements.add(OrmLiteMovement.to(ormLiteMovement));
			}
		} catch (Exception e) {
		} finally {
			return movements;
		}
	}

	@Override
	public void create(Movement movement, Account account) throws Exception {

		OrmLiteAccount ormLiteAccount = accountDao.queryForId(account
				.getNumber());

		if (account.canProcessMovement(movement)) {
			if (movement.getType() == MOVEMENT_TYPE.CREDIT) {
				ormLiteAccount.setValue(ormLiteAccount.getValue()
						+ movement.getValue());
			} else {
				ormLiteAccount.setValue(ormLiteAccount.getValue()
						- movement.getValue());
			}
			accountDao.update(ormLiteAccount);

			OrmLiteMovement ormLiteMovement = new OrmLiteMovement();
			ormLiteMovement.setDate(new Date());
			ormLiteMovement.setValue(movement.getValue());
			ormLiteMovement.setType(movement.getType().toString());
			ormLiteMovement.setAccount(ormLiteAccount);
			movementDao.create(ormLiteMovement);
		}
	}

	@Override
	public List<Movement> getAccountsDetails(Date startDate, Date endDate,
			Client client) {
		List<Movement> movements = new ArrayList<Movement>();
		try {
			List<OrmLiteMovement> localQuery = movementDao.queryForEq(
					OrmLiteMovement.CLIENT, client);
			for (OrmLiteMovement ormLiteMovement : localQuery) {
				movements.add(OrmLiteMovement.to(ormLiteMovement));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return movements;
		}
	}
}
