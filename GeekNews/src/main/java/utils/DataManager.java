package utils;

import com.zdr.geeknews.entity.NewsType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zdr on 16-8-5.
 */
public class DataManager {
    /**
     * 初始化新闻频道
     * @return 新闻频道数据
     */
    public static List<NewsType> initData(){
        List<NewsType> newsTypes = new ArrayList<>();
        newsTypes.add(new NewsType(1, "社会", "http://apis.baidu.com/txapi/social/social?num=10&page=",1));
        newsTypes.add(new NewsType(2, "体育", "http://apis.baidu.com/txapi/tiyu/tiyu?num=10&page=",1));
        newsTypes.add(new NewsType(3, "科技", "http://apis.baidu.com/txapi/keji/keji?num=10&page=",1));
        newsTypes.add(new NewsType(4, "国际", "http://apis.baidu.com/txapi/world/world?num=10&page=",1));
        newsTypes.add(new NewsType(5, "奇闻", "http://apis.baidu.com/txapi/qiwen/qiwen?num=10&page=",1));
        newsTypes.add(new NewsType(6, "娱乐", "http://apis.baidu.com/txapi/huabian/newtop?num=10&page=",1));
        newsTypes.add(new NewsType(7, "apple", "http://apis.baidu.com/txapi/apple/apple?num=10&page=",1));
        newsTypes.add(new NewsType(8, "美女", "http://apis.baidu.com/txapi/mvtp/meinv?num=10&page=",1));
        newsTypes.add(new NewsType(9, "健康", "http://apis.baidu.com/txapi/health/health?num=10&page=",1));
        newsTypes.add(new NewsType(10, "星座", "http://apis.baidu.com/txapi/xingzuo/xingzuo?",2));
        newsTypes.add(new NewsType(10, "秀智商", "http://apis.baidu.com/txapi/naowan/naowan",3));
        newsTypes.add(new NewsType(10, "解梦", "http://apis.baidu.com/txapi/dream/dream?",4));

        return newsTypes;
    }
}