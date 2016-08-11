package com.zdr.androiddataio;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvShow = (TextView) findViewById(R.id.tv_show);
    }

    /**
     * 写入监听
     *
     * @param view
     */
    public void write(View view) {
        //spWrite(tvShow.getText().toString() + "");
       // writeInternal();
        //writeSD();
        //writeImg();
        readImg();
    }

    /**
     * 读取监听
     *
     * @param view
     */
    public void read(View view) {
        tvShow.setText(spRead());
    }

    public void spWrite(String s) {
        SharedPreferences sp = getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("contex", s);

        editor.commit();


    }

    public String spRead() {
        SharedPreferences sp = getSharedPreferences("settings", Context.MODE_PRIVATE);

        return sp.getString("context", "NULL");
    }

    public void writeInternal(){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("a.txt", Context.MODE_PRIVATE);
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(fos));

            bw.write("这是我写入的内容");

            bw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void writeSD(){
        //判断是否存在SD卡,通过获取SD卡的状态判断
        if(Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED
        )){

            Log.e("dataDir:"," "+Environment.getDataDirectory().getAbsolutePath());
            Log.e("rootDir:"," "+Environment.getRootDirectory().getAbsolutePath());
            Log.e("pubDir:"," "+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath());
        }
        String s = Environment.getExternalStorageDirectory().getAbsolutePath();
        File f = new File(s, "/topNews");
        if(!f.exists()){
            f.mkdir();
        }
        File config = new File(f, "config");
        try {
            config.createNewFile();

            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(config))
            );
            bw.write("这是文件读写");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeImg(){

        File splash = new File(Environment.getExternalStorageDirectory(), "splash.jpg");

        try {
            InputStream fis = getAssets().open("splash.jpg");

            OutputStream fos = new FileOutputStream(splash);

            byte[] buff = new byte[1024];
            int len = -1;
            while ((len = fis.read(buff))!=-1){
                fos.write(buff, 0, len);
            }

            fos.close();
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readImg(){

        File img = new File(Environment.getExternalStorageDirectory(), "splash.jpg");

        try {
            InputStream ins = new FileInputStream(img);

            Bitmap bitmap = BitmapFactory.decodeStream(ins);
            ((ImageView) findViewById(R.id.img)).setImageBitmap(bitmap);

            ins.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}