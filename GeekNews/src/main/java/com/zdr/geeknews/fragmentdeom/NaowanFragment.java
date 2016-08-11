package com.zdr.geeknews.fragmentdeom;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NaowanFragment extends Fragment {

    private String url = "http://apis.baidu.com/txapi/naowan/naowan";
    RequestQueue queue;

    TextView tvQuest;
    TextView tvAns;
    Button showAns;
    Button next;

    public NaowanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queue = Volley.newRequestQueue(getActivity());
        url = getArguments().getString("api");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_naowan, container, false);
        tvQuest = (TextView) v.findViewById(R.id.tv_quest);
        tvAns = (TextView) v.findViewById(R.id.tv_ans);
        showAns = (Button) v.findViewById(R.id.bt_show);
        next = (Button) v.findViewById(R.id.bt_next);
        get();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAns.setText("");
                get();
            }
        });
        return v;

    }

    public void get() {
        NetRequest request = new NetRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        final List<String> reslut = parseJSON(s);
                        tvQuest.setText(reslut.get(0));
                        showAns.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvAns.setText(reslut.get(1));
                            }
                        });
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

    public List<String> parseJSON(String json) {
        List<String> list = new ArrayList<>();

        try {
            JSONObject result = new JSONObject(json);
            int code = result.getInt("code");
            String msg = result.getString("msg");

            JSONArray newsList = result.getJSONArray("newslist");
            for (int i = 0; i < newsList.length(); i++) {

                JSONObject ob = newsList.getJSONObject(i);
                //"quest": "两只狗赛跑，甲狗跑得快，乙狗跑得慢，跑到终点时，哪只狗出汗多?",  //问题
                //"result": "狗不会出汗"  //答复
                list.add(ob.getString("quest"));
                list.add(ob.getString("result"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }


}
