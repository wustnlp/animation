package com.wust.ws.animonpredraw;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;


public class AnimationSetActivity extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_set);
        imageView = (ImageView) findViewById(R.id.id_ball);
    }

    public void togetherRun(View view){
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(imageView,"scaleX",1.0f,2.0f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(imageView,"scaleY",1.0f,2.0f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(2000);
        animSet.setInterpolator(new LinearInterpolator());
        animSet.playTogether(anim1, anim2);//两个动画同时执行
       // animSet.playSequentially(anim1,anim2);//两个动画依次执行
        animSet.start();
    }

    /**
     * anim1，anim2,anim3同时执行
     * anim4接着执行
     */
    public void playWithAfter(View view){
        float cx = imageView.getX();
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 2.0f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(imageView,"scaleY",1.0f,2.0f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(imageView,"x",cx,0f);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(imageView,"x",cx);

        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim1).with(anim2);
        animSet.play(anim2).with(anim3);
        animSet.play(anim4).after(anim3);
        animSet.setDuration(1000);
        animSet.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_animation_set, menu);
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
