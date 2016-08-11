package utils;

import android.support.v4.app.Fragment;

import com.zdr.geeknews.entity.NewsType;
import com.zdr.geeknews.fragmentdeom.DreamFragment;
import com.zdr.geeknews.fragmentdeom.NaowanFragment;
import com.zdr.geeknews.fragmentdeom.NetNewsFragment;
import com.zdr.geeknews.fragmentdeom.XingZuoFragment;

import java.util.List;

/**
 * 根据不同的URL创建不同的HomeFragment下的fragment
 * Created by zdr on 16-8-5.
 */
public class FragmentItemFactory {
    public static Fragment getFragment(int id){
        Fragment fragment = null;
        switch (id){
            case 1:
                fragment = new NetNewsFragment();
                break;
            case 2:
                fragment = new XingZuoFragment();
                break;
            case 3:
                fragment = new NaowanFragment();
                break;
            case 4:
                fragment = new DreamFragment();
                break;
        }

        return fragment;
    }
}
