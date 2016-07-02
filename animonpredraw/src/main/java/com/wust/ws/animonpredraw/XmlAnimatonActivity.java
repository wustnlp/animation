package com.wust.ws.animonpredraw;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class XmlAnimatonActivity extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_animaton);
        imageView = (ImageView) findViewById(R.id.id_ball);
    }

    /**
     * XML动画，根节点是  objectAnimator
     * @param view
     */
    public void scaleX(View view){
        Animator anim = AnimatorInflater.loadAnimator(this,R.animator.scalex);
        anim.setTarget(imageView);
        anim.start();
    }

    /**
     * XML动画，根节点是  set
     * 动画的缩放是有中心点，或者叫中心轴的，默认的如果不设置就是以对象的中心点为  中心，
     * 如果自己手动设置，就需要设置一个点，下面的代码中就是将中心点设置为  对象的  左上角
     * @param view
     */
    public void scaleXandScaleY(View view){
        Animator anim = AnimatorInflater.loadAnimator(this,R.animator.scale);
        //下面的坐标如果不设置，就是以中心点为坐标
        imageView.setPivotX(0);
        imageView.setPivotY(0);
        imageView.invalidate();

        anim.setTarget(imageView);
        anim.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_xml_animaton, menu);
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
