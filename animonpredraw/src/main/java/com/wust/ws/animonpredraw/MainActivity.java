package com.wust.ws.animonpredraw;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {

    private ImageView imageView ;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageview);
        button = (Button) findViewById(R.id.bt_ok);
        imageView.setImageResource(R.drawable.meinv);
        //POST��ʽҲ����onCreate������ʱ��Ϳ�ʼ�������������ֶ���������ַ����ǳɹ��ġ�
        imageView.post(new Runnable() {
            @Override
            public void run() {
                func_animation_three();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ObjectAnimator oa = (ObjectAnimator) AnimatorInflater.loadAnimator(MainActivity.this,R.animator.objectdemo);
////                oa.setEvaluator(new ArgbEvaluator());
//                oa.setTarget(imageView);
//                oa.start();
//                oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                        Log.e("TAG","ANIMATION VALUE:"+(int) animation.getAnimatedValue());
//                        imageView.setBackgroundColor((int) animation.getAnimatedValue());
//                    }
//                });
//                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this,R.animator.translate);
//                if (set == null){
//                    Log.e("TAG","the animator set is null");
//                }
//                Log.e("TAG","the animator set is not null");
//                set.setTarget(imageView);
//                set.start();
//                func_animation_one();
//                func_objectanim_xml();
//                func_animation_two();
                func_animation_three();
            }
        });
    }

    public void BottonOk(View view){
        //startActivity(new Intent(MainActivity.this,ValueAnimationActivity.class));
        //startActivity(new Intent(MainActivity.this,AnimationSetActivity.class));
        //startActivity(new Intent(MainActivity.this,XmlAnimatonActivity.class));
        //startActivity(new Intent(MainActivity.this,LayoutAnimationActivity.class));
        startActivity(new Intent(MainActivity.this,ViewAnimatonActivity.class));
    }

    /**
     * ���������XML�ļ���:
     *  android:duration="500"
     *  android:valueFrom="0.0F"
     *  android:valueTo="360.0F"
     *  android:propertyName="rotationX"  ���зǳ���Ҫ��˵���������������������
     */
    public void func_objectanim_xml() {

        ObjectAnimator oa = (ObjectAnimator) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.reveser);
        oa.setTarget(imageView);
        oa.start();
    }

    /**
     * �����������X���Ͻ���360����ת����ʱ0.5�룬��תһ��
     */
    public void func_animation_one() {
        ObjectAnimator//
                .ofFloat(imageView, "rotationX", 0.0F, 360.0F)//
                .setDuration(500)//
                .start();
    }

    /**
     * ��������У�zhy����㶨����������ƣ�ImageViewû���������
     * �����涯��ͬʱ�ı��˼������ԣ���ʹ��ObjectAnimator����ʱֻ���ֶ�������
     * X,Y���ϵ����ź�͸����
     */
    public void func_animation_two(){
        ObjectAnimator anim = ObjectAnimator//
                .ofFloat(imageView, "zhy", 1.0F,  0.2F)//
                .setDuration(500);//
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                imageView.setAlpha(cVal);
                imageView.setScaleX(cVal);
                imageView.setScaleY(cVal);
            }
        });
    }

    /**
     * ʹ��propertyValuesHolder,ʵ��һ���������Ķ��Ч��
     * ʵ��Ч����������֮���ֻ���
     */
    public void func_animation_three(){
        propertyValuesHolder(imageView);
    }
    public void propertyValuesHolder(View view)
    {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(1000).start();
    }

    /**
     * activity����֮�󣬾Ϳ�ʼ�������������ֶ���ʼ����
     */
    ViewTreeObserver.OnPreDrawListener onPreDrawListener = new ViewTreeObserver.OnPreDrawListener() {
        @Override
        public boolean onPreDraw() {
            func_animation_three();
            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
