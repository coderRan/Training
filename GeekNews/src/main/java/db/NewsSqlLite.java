package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import utils.Constants;

/**
 * 数据库操作类
 * Created by zdr on 16-8-12.
 */
public class NewsSqlLite extends SQLiteOpenHelper {
    private static final String DB_NAME = "NEWS.db";
    private static final int DB_VERSION = 1;
    public NewsSqlLite(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.NetNewsDB.getCreateDBSQL());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
