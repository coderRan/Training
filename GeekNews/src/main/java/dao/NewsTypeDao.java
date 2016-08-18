package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.zdr.geeknews.entity.NetNews;
import com.zdr.geeknews.entity.NewsType;

import java.util.ArrayList;
import java.util.List;

import db.NewsSqlLite;
import utils.Constants;

/**
 * NewsTypeDao，存储新闻类型
 * Created by zdr on 16-8-15.
 */
public class NewsTypeDao {

    private NewsSqlLite helper;
    private SQLiteDatabase db;

    public NewsTypeDao(Context context) {
        helper = new NewsSqlLite(context);
    }

    public long addNewsType(NewsType nt) {
        db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.NewsTypeDB.COL_CHECKED, nt.isChecked());
        values.put(Constants.NewsTypeDB.COL_FRAGMETTYPE, nt.getFragmetType());
        values.put(Constants.NewsTypeDB.COL_TYPE, nt.getTypeName());
        values.put(Constants.NewsTypeDB.COL_URL, nt.getUrl());

        Long result = db.insert(Constants.NewsTypeDB.TAB_NAME, null, values);
        db.close();
        return result;
    }

    public void addAll(List<NewsType> data) {

        for (NewsType nt : data) {
            addNewsType(nt);
        }

    }

    public List<NewsType> findAllType(){
        List<NewsType> list = new ArrayList<>();
        db = helper.getWritableDatabase();

        Cursor cursor = db.query(
                Constants.NewsTypeDB.TAB_NAME,
                null,null,
                null, null, null, null
        );
        while (cursor.moveToNext()) {
            NewsType nt = new NewsType();
            nt.setChecked(cursor.getInt(cursor.getColumnIndex(Constants.NewsTypeDB.COL_CHECKED)));
            nt.setTypeName(cursor.getString(cursor.getColumnIndex(Constants.NewsTypeDB.COL_TYPE)));
            nt.setUrl(cursor.getString(cursor.getColumnIndex(Constants.NewsTypeDB.COL_URL)));
            nt.setFragmetType(cursor.getInt(cursor.getColumnIndex(Constants.NewsTypeDB.COL_FRAGMETTYPE)));

            list.add(nt);
        }

        db.close();
        cursor.close();
        return list;
    }
    public List<NewsType> findTypeByIschecked(int checked){
        List<NewsType> list = new ArrayList<>();
        db = helper.getWritableDatabase();

        Cursor cursor = db.query(
                Constants.NewsTypeDB.TAB_NAME,
                null,Constants.NewsTypeDB.COL_CHECKED+" = ?",
                new String[]{checked+""}, null, null, null
        );
        while (cursor.moveToNext()) {
            NewsType nt = new NewsType();
            nt.setChecked(cursor.getInt(cursor.getColumnIndex(Constants.NewsTypeDB.COL_CHECKED)));
            nt.setTypeName(cursor.getString(cursor.getColumnIndex(Constants.NewsTypeDB.COL_TYPE)));
            nt.setUrl(cursor.getString(cursor.getColumnIndex(Constants.NewsTypeDB.COL_URL)));
            nt.setFragmetType(cursor.getInt(cursor.getColumnIndex(Constants.NewsTypeDB.COL_FRAGMETTYPE)));

            list.add(nt);
        }
        Log.e("findTypeByIscheckeSIZE:",list.size() + "");
        db.close();
        cursor.close();
        return list;
    }
    public int upChecked(String url,int flag){
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.NewsTypeDB.COL_CHECKED,flag);
        int result = db.update(Constants.NewsTypeDB.TAB_NAME, values,
                Constants.NewsTypeDB.COL_URL + " = ?",
                new String[]{url});

        db.close();
        return result;
    }

}
