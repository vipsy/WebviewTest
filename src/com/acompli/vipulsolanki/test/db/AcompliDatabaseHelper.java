package com.acompli.vipulsolanki.test.db;

import android.content.Context;

import com.turbomanage.storm.DatabaseHelper;
import com.turbomanage.storm.api.Database;
import com.turbomanage.storm.api.DatabaseFactory;

@Database(name="acompli", version=1)
public class AcompliDatabaseHelper extends DatabaseHelper {

	public AcompliDatabaseHelper(Context ctx, DatabaseFactory dbFactory) {
		super(ctx, dbFactory);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public UpgradeStrategy getUpgradeStrategy() {
		return UpgradeStrategy.BACKUP_RESTORE;
	}
	
	

}
