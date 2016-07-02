package com.wust.ws.animonpredraw;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class ViewAnimatonActivity extends Activity {

    private ImageView imageView;
    private float mScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animaton);
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
        imageView = (ImageView) findViewById(R.id.id_ball);
    }


    public void viewAnim(View view)
    {
        // need API12
        imageView.animate()//
                .alpha(0)//
                .y(mScreenHeight / 2).setDuration(1000)
                // need API 12
                .withStartAction(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Log.e("TAG", "START");
                    }
                    // need API 16
                }).withEndAction(new Runnable()
        {

            @Override
            public void run()
            {
                Log.e("TAG", "END");
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        imageView.setY(0);
                        imageView.setAlpha(1.0f);
                    }
                });
            }
        }).start();
    }

    /**
     * 这个动画作用在自己的身上
     * @param view
     */
    public void propertyValuesHolder(View view){
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(1000).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_animaton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
