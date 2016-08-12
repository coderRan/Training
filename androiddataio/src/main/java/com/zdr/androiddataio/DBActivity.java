package com.zdr.androiddataio;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zdr.androiddataio.db.NewsSqlLite;
import com.zdr.androiddataio.utils.Constants;

public class DBActivity extends AppCompatActivity {
    TextView tvShow;
    EditText delID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        tvShow = (TextView) findViewById(R.id.tv_show);
        delID = (EditText) findViewById(R.id.ed_id);

    }

    public void onclick(View view) {
        insert();
        query();
        delete();
        query();
    }

    public void insert() {
        NewsSqlLite openHelper = new NewsSqlLite(this);
        SQLiteDatabase database = openHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.NetNewsDB.COL_CTIME, "20080808");
        values.put(Constants.NetNewsDB.COL_TITLE, "标题");
        values.put(Constants.NetNewsDB.COL_DESCRIPTION, "来源");
        values.put(Constants.NetNewsDB.COL_URL, "www.baidu.com");
        values.put(Constants.NetNewsDB.COL_URL_PIC, "www.baidu.com?pic");
        Long result = database.insert(Constants.NetNewsDB.TBL_NAME, null, values);

        tvShow.setText("结果：" + result);
        database.close();

    }

    public void updata() {
    }

    public void query() {
        NewsSqlLite openHelper = new NewsSqlLite(this);
        SQLiteDatabase database = openHelper.getWritableDatabase();

        Cursor cursor = database.query(
                Constants.NetNewsDB.TBL_NAME,
                new String[]{Constants.NetNewsDB.COL_ID,Constants.NetNewsDB.COL_TITLE, Constants.NetNewsDB.COL_CTIME},
                null, null, null, null, null
        );

        StringBuilder sb = new StringBuilder();
        while (cursor.moveToNext()) {
            sb.append(cursor.getString(0) + ":" +
                    cursor.getString(cursor.getColumnIndex(Constants.NetNewsDB.COL_TITLE)));
            sb.append(":"+cursor.getString(cursor.getColumnIndex(Constants.NetNewsDB.COL_CTIME))+"");
        }
        tvShow.setText(sb.toString());
        cursor.close();
        database.close();
    }

    public void delete() {
        if(delID.getText().toString()== null){
            Toast.makeText(DBActivity.this, "请输入ID", Toast.LENGTH_SHORT).show();
            return;
        }
        int id = Integer.parseInt(delID.getText().toString());

        NewsSqlLite openHelper = new NewsSqlLite(this);
        SQLiteDatabase database = openHelper.getWritableDatabase();

        int flag = database.delete(Constants.NetNewsDB.TBL_NAME,
                Constants.NetNewsDB.COL_ID + " = ?",
                new String[]{delID.getText().toString()});

        Log.e("FLAG", flag+"");
    }

}
