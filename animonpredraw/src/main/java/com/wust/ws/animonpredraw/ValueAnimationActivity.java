package com.wust.ws.animonpredraw;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;


public class ValueAnimationActivity extends Activity {

    private ImageView imageView;
    private float mScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animation);

        imageView = (ImageView) findViewById(R.id.id_ball);
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
    }

    /**
     *自由落体 动画
     */
    public void verticalRun(View view){
        ValueAnimator animator = ValueAnimator.ofFloat(0, mScreenHeight
                - imageView.getHeight());
        animator.setTarget(imageView);
        animator.setDuration(1000).start();
//      animator.setInterpolator(value)
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                imageView.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
    }

    /**
     * PointF和Point的区别就是x,y的单位一个是float,一个是int;RectF,Rect也是
     *   fraction  = 当前运动时间/总运动时间  =  t / duration，分数
     * 抛物线   公式：y = ax *+ bx + c
     * 下面动画的公式采用的是：  x = at; y = 1/2*x*x;
     * @param view
     */
    public void paowuxian(View view)
    {

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0, 0));//动画接受的数值类型，抛物线里面接受的是点坐标
        valueAnimator.setInterpolator(new LinearInterpolator());//变化率，线性的
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {//自定义TypeValue
            // fraction = t / duration
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                Log.e("TAG", fraction * 3 + "");
                // x方向200px/s ，则y方向0.5 * 10 * t
                PointF point = new PointF();
                point.x = 200 * fraction * 3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });

        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                PointF point = (PointF) animation.getAnimatedValue();
                imageView.setX(point.x);
                imageView.setY(point.y);
            }
        });
    }


    /**
     * 透明度动画，但是动画结束之后，删除对象
     * @param view
     */
    public void fadeOut(View view){
        ObjectAnimator anim = ObjectAnimator.ofFloat(imageView,"alpha",0.5f);
        anim.addListener(new AnimatorListenerAdapter() {
            /**
             * {@inheritDoc}
             *  AnimatorListenerAdapter 这个类实现了AnimatorListener接口里面的方法，但是都是空的
             *  如果只需要关心某件事，就只需要使用这个AnimatorListenerAdapter即可
             * @param animation
             */
            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e("TAG","onAnimationEnd");
                ViewGroup parent = (ViewGroup) imageView.getParent();
                if (parent!=null)
                    parent.removeView(imageView);
            }
        });
        //监控所有动画的状态
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e("TAG","onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e("TAG","onAnimationEnd2");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.e("TAG","onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.e("TAG","onAnimationRepeat");
            }
        });
        //开始动画
        anim.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_value_animation, menu);
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
