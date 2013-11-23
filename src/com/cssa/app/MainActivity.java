/*
 * Filename: MainActivity.java
 * Author: Zhaoyang Zeng
 */
package com.cssa.app;
//HAHAHAHAHA MY EGIT!
//LOL I DID IT
import java.util.ArrayList;


import com.cssa.app.R;
import com.cssa.app.qiu_rui_qing.qiu_rui_qing;
import com.cssa.app.tao_kang.tao_kang;
import com.cssa.app.hu_jia_ying.*;

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
	
	//Gesture Recognizer used to detect gesture
	private GestureDetector HitMachine;
	//handler used in postdelay method
	private Handler h; 
	//arraylist used to store list of images for animation purpose
	private ArrayList<Integer> imgs =new ArrayList<Integer>();
	//index used in animation to indicate which image should be presenteed
	private int index=0;
	//boolean idicate if animation is start or not
	private boolean hitting=false;
	//view flipper for flip image on the top of the activity
	private ViewFlipper viewFlipper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//create a new Gesture Detector name hitmachine to catch certain gesture like onDown
		HitMachine=new GestureDetector(this,this);
		h=new Handler();
		//load imgresouces to variable imgs. 'hit' is an id that idicate which animation we want
		loadImgResources("hit");
		//find viewflipper from layout
		viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper1);
		//set all pictures in imgs to viewflipper for furthur gesture detection
		for(int i=0;i<imgs.size();i++)
		        {
		        //  This will create dynamic image view and add them to ViewFlipper
		            setFlipperImage(imgs.get(i));
		        }
		
		
		//animation block may be delete in the future
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
	
	/**-------------------------HU_JIA_YING-----------------**/
	public void hu_jia_ying(View v) {
		Intent i = new Intent(getApplicationContext(), hu_jia_ying.class);
		startActivity(i);
	}
	/**-------------------------END_OF_HU_JIA_YING-----------------**/
	/**
	 * below are main activity methods which Zhaoyang_Zeng will take care of 
	 * **/
	
	
	
	//setFilpperImage function is used to set the image to viewflipper
	private void setFlipperImage(int res) {
	    //Log.i("Set Filpper Called", res+"");
		//create a imageview
	    ImageView image = new ImageView(getApplicationContext());
	    image.setBackgroundResource(res);
	    //add that view to viewflipper
	    viewFlipper.addView(image);
	}
	
	//load imaResources acoording to id
	private void loadImgResources(String id)
	{
		//create a new fetchmachine for picture fetching
		ActivityImageFetchMachine imgMachine = new ActivityImageFetchMachine();
		//use it like iterator, if it has next, then add it.
		while(imgMachine.hasNext()){
			imgs.add(imgMachine.next());
		}
	}

	//this is a runnable used in the draw method for furthur animation
	private Runnable r = new Runnable(){
		
		 @Override
        public void run() {
			 //call draw method here
                draw(imgs,index);
        }
	};
	
	//draw method used for animation
	private void draw(ArrayList<Integer> list, int id){
		//get the imgview
		index=id;
		ImageView img = (ImageView)findViewById(R.id.imageView1);
		//draw on that imgview
		img.setImageResource(list.get(id));
		//increment index
		index++;
		//detect the end for animation
		if (index>(imgs.size()-1)){
			index=0;
			hitting=false;
			
		}else{
			//call draw method 0.1 sec later
		    h.postDelayed(r, 100); 
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//ontouchevent is handled by hitmachine
	@Override
	public boolean onTouchEvent(MotionEvent me) {
		return HitMachine.onTouchEvent(me);
	}

	//ondown method is called when you hit screen really really hard :)
	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		//TextView text = (TextView)findViewById(R.id.textView2);
		//Log.e("WOW", "DOWN");
		
		//if hitting animation is not started yet, start it
		if (hitting==false){
			hitting=true;
			draw(imgs,index);
		}
		return false;
	}

	//helper function conver dp to pixel
	public static float convertDpToPixel(float dp, Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float px = dp * (metrics.densityDpi / 160f);
	    return px;
	}
	
	//if you swipe screen, this method is called
	@Override
	public boolean onFling(MotionEvent event, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		//get start point and end point
		float downXValue = event.getRawX();
        float downYValue = event.getRawY();
        float currentX = arg1.getRawX();
        float currentY = arg1.getRawY();
        Rect rectgle= new Rect();
        Window window= getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectgle);
        
        //get the height of that top flipper img
        int StatusBarHeight= rectgle.top;
        int contentViewTop= 
            window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int TitleBarHeight= contentViewTop - StatusBarHeight;
        float y=TitleBarHeight+StatusBarHeight+viewFlipper.getHeight();
        //Log.e("Y",String.valueOf(y));
        
        //call animation if a fling occured in that certain area
        if (Math.abs(downXValue - currentX) > Math.abs(downYValue
                - currentY)) {

            // going backwards: pushing stuff to the right
            if (downXValue < currentX && downYValue<=y && downYValue>=contentViewTop) {
            	//TextView text = (TextView)findViewById(R.id.textView2);
        		//text.setText("hit right");
        		ImageView img = (ImageView)findViewById(R.id.imageView1);
        		//img.setImageResource(R.drawable.one);
        		//index=0;
        		//animation 
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
        		//animation
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
		//Log.e("WOW", "UP");
		return false;
	}

}
