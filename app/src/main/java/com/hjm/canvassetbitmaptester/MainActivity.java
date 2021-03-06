package com.hjm.canvassetbitmaptester;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Bitmap bitmap = null;
    private ImageView imageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.target);

        Button button = (Button) findViewById(R.id.translate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView != null) {
                    Bitmap old = bitmap;
                    bitmap = translateImage(R.drawable.sample);

                    imageView.setImageBitmap(bitmap);
                    if (old != null) {
                        old.recycle();
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (imageView != null) {
            imageView.setImageBitmap(null);
            imageView = null;
        }

        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
        }
    }

    private Bitmap translateImage(@DrawableRes int targetId) {
        Bitmap src = BitmapFactory.decodeResource(getResources(), targetId);
        Bitmap dst = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas();
        canvas.translate(200, 200);
        canvas.setBitmap(dst);
        canvas.drawBitmap(src, 0, 0, new Paint());

        return dst;
    }
}
