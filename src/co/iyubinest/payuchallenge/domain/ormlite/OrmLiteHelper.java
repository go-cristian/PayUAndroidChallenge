package co.iyubinest.payuchallenge.domain.ormlite;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import co.iyubinest.payuchallenge.data.account.OrmLiteAccount;
import co.iyubinest.payuchallenge.data.client.OrmLiteClient;
import co.iyubinest.payuchallenge.data.movement.OrmLiteMovement;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class OrmLiteHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "androcode_ormlite.db";
	private static final int DATABASE_VERSION = 1;

	private Dao<OrmLiteClient, Integer> clientDao;
	private Dao<OrmLiteAccount, Integer> accountDao;
	private Dao<OrmLiteMovement, Integer> movementDao;

	public OrmLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, OrmLiteClient.class);
			TableUtils.createTable(connectionSource, OrmLiteAccount.class);
			TableUtils.createTable(connectionSource, OrmLiteMovement.class);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		onCreate(db, connectionSource);
	}

	public Dao<OrmLiteClient, Integer> getClientDao() throws SQLException {
		if (clientDao == null) {
			clientDao = getDao(OrmLiteClient.class);
		}
		return clientDao;
	}

	public Dao<OrmLiteAccount, Integer> getAccountDao() throws SQLException {
		if (accountDao == null) {
			accountDao = getDao(OrmLiteAccount.class);
		}
		return accountDao;
	}

	public Dao<OrmLiteMovement, Integer> getMovementDao() throws SQLException {
		if (movementDao == null) {
			movementDao = getDao(OrmLiteMovement.class);
		}
		return movementDao;
	}

	@Override
	public void close() {
		super.close();
		clientDao = null;
	}

}