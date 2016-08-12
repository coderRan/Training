package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.zdr.geeknews.entity.NetNews;

import java.util.ArrayList;
import java.util.List;

import db.NewsSqlLite;
import utils.Constants;

/**
 * 操作NetNews的DAO
 * Created by zdr on 16-8-12.
 */
public class NetNewsDao {
    List<NetNews> mData = new ArrayList<>();

    private NewsSqlLite openHelper;
    private SQLiteDatabase readDB;
    private SQLiteDatabase writDB;

    public NetNewsDao(Context context) {
        openHelper = new NewsSqlLite(context);
    }

    public List<NetNews> findNewsByType(String type) {
        readDB = openHelper.getReadableDatabase();

        List<NetNews> list = new ArrayList<>();

        Cursor cursor = readDB.query(
                Constants.NetNewsDB.TBL_NAME,
                null,Constants.NetNewsDB.COL_TYPE + " = ?",
                new String[]{type}, null, null, null
        );
        while (cursor.moveToNext()) {
            NetNews nn = new NetNews();
            nn.setCtime(cursor.getString(cursor.getColumnIndex(Constants.NetNewsDB.COL_CTIME)));
            nn.setDescription(cursor.getString(cursor.getColumnIndex(Constants.NetNewsDB.COL_DESCRIPTION)));
            nn.setTitle(cursor.getString(cursor.getColumnIndex(Constants.NetNewsDB.COL_TITLE)));
            nn.setPicUrl(cursor.getString(cursor.getColumnIndex(Constants.NetNewsDB.COL_URL_PIC)));
            nn.setUrl(cursor.getString(cursor.getColumnIndex(Constants.NetNewsDB.COL_URL)));
            list.add(nn);
        }
        readDB.close();
        cursor.close();
        Log.e("CURSOR", list.size()+"");
        return list;
    }

    public Long addNetNews(NetNews nn) {
        writDB = openHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.NetNewsDB.COL_CTIME, nn.getCtime());
        values.put(Constants.NetNewsDB.COL_TITLE, nn.getTitle());
        values.put(Constants.NetNewsDB.COL_DESCRIPTION, nn.getDescription());
        values.put(Constants.NetNewsDB.COL_URL, nn.getUrl());
        values.put(Constants.NetNewsDB.COL_URL_PIC,nn.getPicUrl());
        values.put(Constants.NetNewsDB.COL_TYPE,nn.getType());

        Long result = writDB.insert(Constants.NetNewsDB.TBL_NAME, null, values);

        writDB.close();

        return result;
    }

    public void addALLNetNews(List<NetNews> netNewsList) {
        for (NetNews nn:netNewsList) {
            addNetNews(nn);

        }
    }
    public int delNewsByType(String type){
        writDB = openHelper.getWritableDatabase();
        return writDB.delete(Constants.NetNewsDB.TBL_NAME,
                Constants.NetNewsDB.COL_TYPE+ " = ?", new String[]{type});
    }
}
