package com.cpm.Loreal_GT;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cpm.Constants.CommonString1;
import com.cpm.capitalfoods.R;
import com.cpm.upload.UploadImageWithRetrofit_WithoutWait;

import java.io.File;
import java.util.ArrayList;

public class SplashScreenActivity extends Activity {

    private static int SPLASH_TIME_OUT = 3000;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    ArrayList fileNameList;
    Context context;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);
        context = this;
        StartAnimations();
        File f = new File(CommonString1.OLD_FILE_PATH);
        if (f.exists()) {
            File file[] = f.listFiles();
            if (file.length > 0) {

                fileNameList = new ArrayList();
                fileNameList.clear();

                for (int i = 0; i < file.length; i++) {
                    fileNameList.add(file[i].getName());
                }

                UploadImageWithRetrofit_WithoutWait.uploadedFiles = 0;
                UploadImageWithRetrofit_WithoutWait uploadImg = new UploadImageWithRetrofit_WithoutWait(context, fileNameList);
                uploadImg.UploadImagesRecursive_ForOLDPATH();
            } else {
                if (f.exists()) {
                    f.delete();
                }
                new Handler().postDelayed(new Runnable() {
                    /*
                     * Showing splash screen with a timer. This will be useful when you
                     * want to show case your app logo / company
                     */
                    @Override
                    public void run() {

                        Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                        finish();
                    }
                }, SPLASH_TIME_OUT);
            }
        } else {
            new Handler().postDelayed(new Runnable() {

                /*
                 * Showing splash screen with a timer. This will be useful when you
                 * want to show case your app logo / company
                 */

                @Override
                public void run() {
                    Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }

    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.logo);
        iv.clearAnimation();
        iv.startAnimation(anim);

    }

}
