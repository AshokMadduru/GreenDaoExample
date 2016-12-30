package in.shoksworld.greendao;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import in.shoksworld.greendao.storage.dbgenerator.DaoMaster;
import in.shoksworld.greendao.storage.dbgenerator.DaoSession;
import in.shoksworld.greendao.storage.DbHelper;

/**
 * Application Class
 */

public class GreenDao extends Application {
    static GreenDao applicationInstance;
    public DaoSession daoSession;
    public DbHelper dbHelper;
    public String databaseName = "greendaodemo-database";

    @Override
    public void onCreate() {
        super.onCreate();

        applicationInstance = this;
    }

    /**
     * @return application instance
     */
    public static synchronized GreenDao getInstance() {
        return applicationInstance;
    }

    public DaoSession getDaoSession() {
        if (this.daoSession == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(GreenDao.this,
                    databaseName, null);
            SQLiteDatabase database = helper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(database);
            this.daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public DbHelper getDbHelper() {
        if (dbHelper == null) {
            dbHelper = new DbHelper();
        }
        return dbHelper;
    }
}
