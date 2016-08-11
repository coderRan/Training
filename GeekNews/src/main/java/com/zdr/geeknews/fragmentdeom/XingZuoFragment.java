package com.zdr.geeknews.fragmentdeom;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
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
public class XingZuoFragment extends Fragment {
    private String url = "http://apis.baidu.com/txapi/xingzuo/xingzuo?";
    private String httpArg = "me=白羊&he=白羊&";
    TextView tvContext;

    TextView tvYouqing;
    TextView tvAiqing;
    TextView tvHunyin;
    TextView tvQinqing;
    Spinner spinnerM;
    Spinner spinnerF;
    Button btn;
    RequestQueue queue;

    public XingZuoFragment() {
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
        View v = inflater.inflate(R.layout.fragment_xing_zuo, container, false);

        btn = (Button) v.findViewById(R.id.btn);
        tvYouqing = (TextView) v.findViewById(R.id.tv_youqing);
        tvAiqing = (TextView) v.findViewById(R.id.tv_aiqing);
        tvHunyin = (TextView) v.findViewById(R.id.tv_hunyin);
        tvQinqing = (TextView) v.findViewById(R.id.tv_qinqing);
        tvContext = (TextView) v.findViewById(R.id.tv_context);
//        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_spinner_item,
//                getResources().getStringArray(R.array.xingzuo));
        //spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerM = (Spinner) v.findViewById(R.id.spinner_m);
        //spinnerM.setAdapter(spinnerAdapter);
        spinnerF = (Spinner) v.findViewById(R.id.spinner_f);
        //spinnerF.setAdapter(spinnerAdapter);

        spinnerM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                httpArg += "me=" + getResources().getStringArray(R.array.xingzuo)[position] + "&";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                httpArg += "me=白羊&";
            }
        });
        spinnerF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                httpArg += "he=" + getResources().getStringArray(R.array.xingzuo)[position] + "&";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                httpArg += "he=白羊&";
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get();
            }
        });
        return v;
    }

    public void get() {
        NetRequest request = new NetRequest(Request.Method.GET,
                url + httpArg + "all=1",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        List<String> reslut = parseJSON(s);
                        tvYouqing.setText("友情"+reslut.get(1));
                        tvAiqing.setText("爱情"+reslut.get(2));
                        tvHunyin.setText("婚姻"+reslut.get(3));
                        tvQinqing.setText("亲情"+reslut.get(4));
                        tvContext.setText(reslut.get(5));


                        Log.e("XINGZUO_NET", s);

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
        String grade = null;
        List<String> list = new ArrayList<>();

        try {
            JSONObject result = new JSONObject(json);
            int code = result.getInt("code");
            String msg = result.getString("msg");

            JSONArray newsList = result.getJSONArray("newslist");
            for (int i = 0; i < newsList.length(); i++) {

                JSONObject ob = newsList.getJSONObject(i);

                //"grade":"友情：★★★★★爱情：★★★★婚姻：★★★亲情：★★★★"
                grade = ob.getString("grade");
                String[] grades = grade.split("(友情)|(亲情)|(婚姻)|(爱情)");
                for (int j = 0; j < grades.length; j++) {
                    Log.e("GRADLE", grades[j]);
                }
                list.addAll(Arrays.asList(grades));
                list.add(ob.getString("content"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
