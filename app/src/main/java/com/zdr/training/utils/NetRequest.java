package com.zdr.training.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zdr on 16-8-5.
 */
public class NetRequest extends StringRequest{
    private Priority priority;
    private Map<String,String> headers;
    private Map<String,String> params;

    public NetRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        init();
    }

    public NetRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
        init();
    }

    private void init(){
        this.priority = Priority.NORMAL;
        this.headers = new HashMap<>();
        this.params = new HashMap<>();
    }
    /**
     * 改变优先级
     * @return
     */
    @Override
    public Priority getPriority() {

        return priority;
    }

    /**
     * 添加请求头
     * @return
     * @throws AuthFailureError
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {

        return headers;
    }
    /**
     * post请求调用该方法
     * @return
     */
    @Override
    public Map<String, String> getParams() {
        return params;
    }

    public NetRequest settag(Object tag){
        super.setTag(tag);
        return this;
    }

    public NetRequest setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public NetRequest addHeaders(String key,String value) {
        this.headers.put(key,value);
        return this;
    }

    public NetRequest addParams(String key, String params) {
        this.params.put(key,params);
        return this;
    }

    public static class Builder{
        private int method;
        private String url;
        private CallBack callBack;
        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setMethod(int method) {
            this.method = method;
            return this;
        }

        public Builder setCallBack(CallBack callBack) {
            this.callBack = callBack;
            return this;
        }

        public NetRequest buile(){
            NetRequest nr = new NetRequest(
                    method,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            if(callBack!=null)
                                callBack.onSuccess(s);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            if(callBack!=null)
                                callBack.onError(volleyError.toString());

                        }
                    }
            );
            return nr;
        }
    }
    public void start(){
        VolleyUtil.start(this);
    }

}
