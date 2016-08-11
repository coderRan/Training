package com.zdr.geeknews.fragmentdeom;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.zdr.geeknews.R;
import com.zdr.geeknews.net.NetRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DreamFragment extends Fragment {

    String httpUrl = "http://apis.baidu.com/txapi/dream/dream?";
    String httpArg = "word=";
    RequestQueue queue;
    TextView tvResult;
    EditText edKey;
    Button btSubmin;

    public DreamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queue = Volley.newRequestQueue(getActivity());
        httpUrl = getArguments().getString("api");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dream, container, false);
        tvResult = (TextView) v.findViewById(R.id.tv_result);
        edKey = (EditText) v.findViewById(R.id.et_key);
        btSubmin = (Button) v.findViewById(R.id.bt_submit);

        btSubmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get();
            }
        });
        return v;
    }

    public void get() {
        NetRequest request = new NetRequest(Request.Method.GET,
                httpUrl + httpArg+edKey.getText().toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        String str = parseJSON(s);

                        if(!"NULL".equals(str)){
                            tvResult.setText(str);
                        }else {
                            Toast.makeText(getActivity(), "试试换个关键字", Toast.LENGTH_SHORT).show();
                        }

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

    public String parseJSON(String json) {
        String str = null;
        try {
            JSONObject result = new JSONObject(json);
            int code = result.getInt("code");
            if(code!=200)
                return "NULL";
            String msg = result.getString("msg");
            JSONArray newsList = result.getJSONArray("newslist");
            for (int i = 0; i < newsList.length(); i++) {

                JSONObject ob = newsList.getJSONObject(i);

                str = ob.getString("result");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

}
