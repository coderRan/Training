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
 * 收藏页面的DAO
 * Created by zdr on 16-8-13.
 */
public class NetNewsKeepDao {
    List<NetNews> mData = new ArrayList<>();

    private NewsSqlLite openHelper;
    private SQLiteDatabase readDB;
    private SQLiteDatabase writDB;

    public NetNewsKeepDao(Context context) {
        openHelper = new NewsSqlLite(context);
    }

    public List<NetNews> findNewsByType(String type) {
        readDB = openHelper.getReadableDatabase();

        List<NetNews> list = new ArrayList<>();

        Cursor cursor = readDB.query(
                Constants.NetNewsDB.TAB_NAME,
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
    public String newsAt(String url){
        readDB = openHelper.getReadableDatabase();

        Cursor cursor = readDB.query(
                Constants.NetNewsDB.TAB_NAME,
                null,Constants.NetNewsDB.COL_URL + " = ?",
                new String[]{url}, null, null, null
        );

        cursor.moveToNext();
        String u = cursor.getString(cursor.getColumnIndex(Constants.NetNewsDB.COL_ID));
        cursor.close();
        return u;
    }
    public Long addNetNews(NetNews nn) {
        writDB = openHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.NetNewsDB.COL_CTIME, nn.getCtime());
        values.put(Constants.NetNewsDB.COL_TITLE, nn.getTitle());
        values.put(Constants.NetNewsDB.COL_DESCRIPTION, nn.getDescription());
        values.put(Constants.NetNewsDB.COL_URL, nn.getUrl());
        values.put(Constants.NetNewsDB.COL_URL_PIC,nn.getPicUrl());
        values.put(Constants.NetNewsDB.COL_TYPE,"keep");

        Long result = writDB.insert(Constants.NetNewsDB.TAB_NAME, null, values);

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
        return writDB.delete(Constants.NetNewsDB.TAB_NAME,
                Constants.NetNewsDB.COL_TYPE+ " = ?", new String[]{type});
    }
    public int delNewsById(String id){
        readDB = openHelper.getReadableDatabase();

        return readDB.delete(
                Constants.NetNewsDB.TAB_NAME,
                Constants.NetNewsDB.COL_ID + " = ?",
                new String[]{id}
        );
    }

    public boolean isKeep(String url){
        readDB = openHelper.getReadableDatabase();

        Cursor cursor = readDB.query(
                Constants.NetNewsDB.TAB_NAME,
                null,Constants.NetNewsDB.COL_TYPE + " = ? and "+ Constants.NetNewsDB.COL_URL+ "= ?",
                new String[]{"keep",url}, null, null, null
        );
        boolean isKeep;
        isKeep = cursor.moveToNext();
        readDB.close();
        cursor.close();
        return isKeep;
    }
    public String getKeepIdByUrl(String url){
        readDB = openHelper.getReadableDatabase();

        Cursor cursor = readDB.query(
                Constants.NetNewsDB.TAB_NAME,
                null,Constants.NetNewsDB.COL_TYPE + " = ? and "+ Constants.NetNewsDB.COL_URL+ "= ?",
                new String[]{"keep",url}, null, null, null
        );
        cursor.moveToNext();
        String id = cursor.getString(0);
        readDB.close();
        cursor.close();
        return id;
    }
}
