package utils;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by zdr on 16-8-5.
 */
public class NetImageCache implements ImageLoader.ImageCache {
    private LruCache<String, Bitmap> mCache;

    public NetImageCache() {

        mCache = new LruCache<String, Bitmap>((int)(Runtime.getRuntime().totalMemory()/8)){
            @Override
            protected int sizeOf(String key, Bitmap value) {

                return value.getRowBytes()*value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String s) {
        return mCache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        mCache.put(s, bitmap);
    }
}
