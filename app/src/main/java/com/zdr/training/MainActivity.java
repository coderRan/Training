package com.zdr.training;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    ProgressBar mBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        mBar = (ProgressBar) findViewById(R.id.pb_bar);


    }

    public void download(View view) {
        DownLoadTask down = new DownLoadTask();
        down.execute("www.baidu.com");
    }

    public class DownLoadTask extends AsyncTask<String, Integer, String> {

        /**
         * 在后台执行的方法，获取资源
         * @param params
         * @return
         */
        @Override
        protected String doInBackground(String... params) {
            //取出要下载的地址
            String uri = params[0];
            for (int i = 0; i < 100;i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //在doInBackground对外公布任务进度，类似sendMessage
                publishProgress(i);
            }
            return "下载结束";
        }

        /**
         * 主线程在执行doInBackground方法之前执行的方法
         * 该方法在主线程中执行
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mBar.setMax(100);
            mBar.setProgress(0);
            tv.setText("开始下载");
        }

        /**
         * 主线程在执行doInBackground之后执行该方法
         *
         * @param s doInBackground 方法的返回结果
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv.setText(s);
        }

        /**
         * 用于接收 publishProgress 公布的参数运行在主线程中
         * @param values publishProgress 的参数
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int p = values[0];
            mBar.setProgress(p);
        }
    }

    @Override
    public void finish() {
        super.finish();

    }
}
