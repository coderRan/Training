package net;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zdr on 16-8-5.
 */
public class NetRequest extends StringRequest{
    private Priority priority;
    private Map<String,String> headers;

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

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void addHeaders(String key,String value) {
        this.headers.put(key,value);
    }
}
