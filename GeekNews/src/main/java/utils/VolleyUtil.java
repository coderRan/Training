package utils;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.zdr.geeknews.entity.NetNews;
import com.zdr.geeknews.net.NetRequest;

/**
 * 对volley的二次封装
 * Created by zdr on 16-8-8.
 */
public class VolleyUtil {

    private static RequestQueue requestQueue;

    public static void init(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
    }

    public static NetRequest get(String url, final CallBack callback) {
        NetRequest request = new NetRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (callback != null) {
                            callback.onSuccess(s);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if (callback != null) {
                            callback.onError(volleyError.toString());
                        }
                    }
                }
        );

        return request;
    }

    public static NetRequest.Builder get(String url) {
        NetRequest.Builder builder = new NetRequest.Builder();
        builder.setMethod(Request.Method.GET).setUrl(url);
        return builder;
    }

    public static void start(NetRequest request) {
        requestQueue.add(request);
    }

    public static NetRequest.Builder post(String url){
        return new NetRequest.Builder()
                .setMethod(Request.Method.GET)
                .setUrl(url);
    }
}
