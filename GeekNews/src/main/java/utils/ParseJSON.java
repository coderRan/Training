package utils;

import com.zdr.geeknews.entity.NetNews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zdr on 16-8-6.
 */
public class ParseJSON {

    public static List<NetNews> parseJSON(String json) {
        List<NetNews> temp = new ArrayList<>();
        try {
            JSONObject result = new JSONObject(json);
            int code = result.getInt("code");
            String msg = result.getString("msg");
            JSONArray newsList = result.getJSONArray("newslist");
            for (int i = 0; i < newsList.length(); i++) {
                JSONObject ob = newsList.getJSONObject(i);
                NetNews news = new NetNews();

                news.setCtime(ob.getString("ctime"));
                news.setDescription(ob.getString("description"));
                news.setPicUrl(ob.getString("picUrl"));
                news.setUrl(ob.getString("url"));
                news.setTitle(ob.getString("title"));
                temp.add(news);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return temp;
    }

}
