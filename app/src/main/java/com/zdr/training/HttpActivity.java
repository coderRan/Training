package com.zdr.training;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zdr.training.utils.CallBack;
import com.zdr.training.utils.VolleyUtil;

import net.NetRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class HttpActivity extends AppCompatActivity {
    TextView show;
    Button getBtn;
    ImageView iv;
    List<JsonModel> models;
    public Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    parseJSON((String) msg.obj);
                    String s = models.get(1).getTitle() + "\r\n"
                            + models.get(2).getTitle();
                    show.setText(s);
                    break;
                case 2:
                    show.setText(((String) msg.obj));
                    break;
                case 3:
                    show.setText(((StringBuilder) msg.obj).toString());
                    break;
                default:
                    break;
            }

        }
    };

    private void parseJSON(String obj) {
        models = new ArrayList<>();
        try {
            JSONObject result = new JSONObject(obj);
            int code = result.getInt("code");
            String msg = result.getString("msg");
            JSONArray newsList = result.getJSONArray("newslist");
            for (int i = 0; i < newsList.length(); i++) {
                JSONObject ob = newsList.getJSONObject(i);
                JsonModel model = new JsonModel();

                model.setCtime(ob.getString("ctime"));
                model.setDescription(ob.getString("description"));
                model.setPicUrl(ob.getString("picUrl"));
                model.setUrl(ob.getString("url"));
                model.setTitle(ob.getString("title"));
                models.add(model);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        VolleyUtil.init(this);
        show = (TextView) findViewById(R.id.tv_show);
        getBtn = (Button) findViewById(R.id.btn_httpGet);
        //iv = (ImageView) findViewById(R.id.iv_img);

    }

    public void doGet(View view) {
        postHttp();

    }

    public void getImg() {
        String sUrl = "http://10.0.2.2:8080/android/image.png";
        HttpURLConnection conn = null;
        try {
            URL url = new URL(sUrl);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.connect();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = conn.getInputStream();

                Bitmap bitmap = BitmapFactory.decodeStream(in);

                Message msg = mHandle.obtainMessage();
                msg.what = 2;
                msg.obj = bitmap;
                mHandle.sendMessage(msg);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getHttp() {
        //String sUrl = "http://10.0.2.2/ch031/admin_log.jsp";
        //String sUrl = "http://www.baidu.com";
        //String sUrl = "http://apis.baidu.com/txapi/naowan/naowan";
        String sUrl = "http://apis.baidu.com/txapi/keji/keji";
        String httpArg = "num=10&page=1";
        StringBuilder sb = new StringBuilder();
        HttpURLConnection conn = null;
        //String ss = "http://c.3g.163.com/nc/article/list/T1348649580692/0-20.html";
        try {
            URL url = new URL(sUrl + "?" + httpArg);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("apikey", "6b68bca2d6662442fb1103b2427b4fed");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.connect();

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();
                byte[] buff = new byte[1024];
                int length = -1;
                while ((length = inputStream.read(buff)) != -1) {
                    sb.append(new String(buff, 0, length));
                }
                inputStream.close();
            }
            Log.e("TAG", sb.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                conn.disconnect();

        }
        Message msg = mHandle.obtainMessage();
        msg.what = 2;
        msg.obj = sb.toString();

        mHandle.sendMessage(msg);

    }

    public void postHttp() {
        //http://localhost:8080/Server/doReg.jsp?name=sdsd&pwd=asdada
        String sUrl = "http://10.0.2.2:8080/Server/doReg.jsp";
        String parms = "name=sdsd&pwd=asdada";

        VolleyUtil.post(sUrl+"?")
                .setMethod(Request.Method.POST)
                .setCallBack(new CallBack() {
                    @Override
                    public void onSuccess(String respone) {
                        show.setText(respone);
                    }

                    @Override
                    public void onError(String err) {
                        Log.e("ERROR", err);
                    }
                }).buile()
                .addParams("name","张三")
                .addParams("pwd","123456")
                .settag("post")
                .start();

    }

    public void volleyGet() {
        RequestQueue queue = Volley.newRequestQueue(this);

        NetRequest request = new NetRequest(
                Request.Method.GET,
                "http://apis.baidu.com/txapi/naowan/naowan",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        show.setText(s);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("VOLLEY_ERR", volleyError.toString());
                    }
                }
        );

        request.addHeaders("apikey", "6b68bca2d6662442fb1103b2427b4fed");

        queue.add(request);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VolleyUtil.cancel("post");

    }
}
