package com.cssa.app;

import java.util.ArrayList;


import com.cssa.app.R;
import com.cssa.app.qiu_rui_qing.qiu_rui_qing;
import com.cssa.app.tao_kang.tao_kang;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements OnGestureListener {

	private GestureDetector HitMachine;
	private Handler h; 
	private ArrayList<Integer> imgs =new ArrayList<Integer>();
	private int index=0;
	private boolean hitting=false;
	private ViewFlipper viewFlipper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		HitMachine=new GestureDetector(this,this);
		h=new Handler();
		loadImgResources("hit");
		viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper1);
		 for(int i=0;i<imgs.size();i++)
		        {
		        //  This will create dynamic image view and add them to ViewFlipper
		            setFlipperImage(imgs.get(i));
		        }
		ImageView img = (ImageView)findViewById(R.id.imageView1);
		img.setImageResource(R.drawable.gif0);
		
		
		//draw(imgs,index);
	}
	
	/**
	 * Each method create an intent and start an activity
	 * **/
	/**--------------------------TAO_KANG---------------------------**/
	public void tao_kang(View v){
		Intent i = new Intent(getApplicationContext(), tao_kang.class);
		startActivity(i);
	}
	/**--------------------------END_OF_TAO_KANG--------------------**/
	
	/**--------------------------QIU_RUI_QING-----------------------**/
	public void qiu_rui_qing(View v) {
		Intent i = new Intent(getApplicationContext(), qiu_rui_qing.class);
		startActivity(i);
	}
	/**-------------------------END_OF_QIU_RUI_QING-----------------**/
	/**
	 * below are main activity methods which Zhaoyang_Zeng will take care of 
	 * **/
	
	
	
	
	private void setFlipperImage(int res) {
	    //Log.i("Set Filpper Called", res+"");
	    ImageView image = new ImageView(getApplicationContext());
	    image.setBackgroundResource(res);
	    viewFlipper.addView(image);
	}
	
	
	private void loadImgResources(String id)
	{
		if (id == "hit")
		{
		imgs.add(R.drawable.gif1);
		imgs.add(R.drawable.gif2);
		imgs.add(R.drawable.gif3);
		imgs.add(R.drawable.gif4);
		imgs.add(R.drawable.gif0);		
		}
	}

	private Runnable r = new Runnable(){
		
		 @Override
        public void run() {
                draw(imgs,index);
        }
	};
	
	private void draw(ArrayList<Integer> list, int id){
		ImageView img = (ImageView)findViewById(R.id.imageView1);
		img.setImageResource(list.get(id));
		index++;
		if (index>(imgs.size()-1)){
			index=0;
			hitting=false;
			
		}else{
		    h.postDelayed(r, 100); 
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent me) {
	return HitMachine.onTouchEvent(me);
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		//TextView text = (TextView)findViewById(R.id.textView2);
		Log.e("WOW", "DOWN");
		if (hitting==false){
			hitting=true;
			draw(imgs,index);
		}
		return false;
	}

	public static float convertDpToPixel(float dp, Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float px = dp * (metrics.densityDpi / 160f);
	    return px;
	}
	
	@Override
	public boolean onFling(MotionEvent event, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		float downXValue = event.getRawX();
        float downYValue = event.getRawY();
        float currentX = arg1.getRawX();
        float currentY = arg1.getRawY();
        Rect rectgle= new Rect();
        Window window= getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectgle);
        int StatusBarHeight= rectgle.top;
        int contentViewTop= 
            window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int TitleBarHeight= contentViewTop - StatusBarHeight;
        float y=TitleBarHeight+StatusBarHeight+viewFlipper.getHeight();
        Log.e("Y",String.valueOf(y));
        if (Math.abs(downXValue - currentX) > Math.abs(downYValue
                - currentY)) {

            // going backwards: pushing stuff to the right
            if (downXValue < currentX && downYValue<=y && downYValue>=contentViewTop) {
            	//TextView text = (TextView)findViewById(R.id.textView2);
        		//text.setText("hit right");
        		ImageView img = (ImageView)findViewById(R.id.imageView1);
        		//img.setImageResource(R.drawable.one);
        		//index=0;
        		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.animator.right_out));
                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.animator.left_in));
        		viewFlipper.showPrevious();
        	
            }

            // going forwards: pushing stuff to the left
            if (downXValue > currentX && downYValue<=y && downYValue>=contentViewTop) {
            	//TextView text = (TextView)findViewById(R.id.textView2);
        		//text.setText("hit left");
        		ImageView img = (ImageView)findViewById(R.id.imageView1);
        		//img.setImageResource(R.drawable.two);
        		//index=1;
        		viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.animator.right_in));
                viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.animator.left_out));
        		viewFlipper.showNext();
        		

            }

        } else {
            
            if (downYValue < currentY) {
            	//TextView text = (TextView)findViewById(R.id.textView2);
        		//text.setText("hit down");

            }
            if (downYValue > currentY) {
            	//TextView text = (TextView)findViewById(R.id.textView2);
        		//text.setText("hit up");

            }
        }
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		Log.e("WOW", "UP");
		return false;
	}

}
