package com.zdr.training;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class LargeImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_image);
    }

    public void onclick(View view) {
        Bitmap bitmap =
                BitmapFactory.decodeResource(getResources(), R.drawable.a_1);
        ((ImageView) findViewById(R.id.iv)).setImageBitmap(bitmap);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(),  R.drawable.a_1, options);
        options.inSampleSize = calculateInSampleSize(options, 100, 100);
        options.inJustDecodeBounds = false;

        Bitmap bitmap2 =
                BitmapFactory.decodeResource(getResources(),  R.drawable.a_1, options);
        ((ImageView) findViewById(R.id.iv2)).setImageBitmap(bitmap2);
    }
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public void onclick2(View v){
        ImageView imageView = (ImageView) findViewById(R.id.iv2);

        Glide.with(this)
                .load("http://image60.360doc.com/DownloadImg/04/1613/31674132_7.jpg")
                .fallback(R.mipmap.ic_launcher)
                .into(imageView);
    }
}
