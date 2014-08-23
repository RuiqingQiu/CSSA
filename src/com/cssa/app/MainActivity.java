/*
 * Filename: MainActivity.java
 * Author: Zhaoyang Zeng
 */
package com.cssa.app;
//HAHAHAHAHA MY EGIT!
//LOL I DID IT
import java.util.ArrayList;

import managerLayer.manager;

import com.cssa.app.R;
import com.cssa.app.qiu_rui_qing.qiu_rui_qing;
import com.cssa.app.tao_kang.tao_kang;
import com.cssa.app.webpages.*;
import com.fima.cardsui.views.CardUI;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ViewFlipper;
import android.widget.LinearLayout;

import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;


@SuppressLint({ "NewApi", "CutPasteId" })
public class MainActivity extends Activity implements OnGestureListener{
	public static MainActivity mainActivity;
	//handler used in postdelay method
	private Handler h; 
	private GestureDetector FlipDetector;
	//arraylist used to store list of images for animation purpose
	public static ArrayList<Bitmap> imgs = new ArrayList<Bitmap>();
	//index used in animation to indicate which image should be presenteed
	private int index=0;
	//view flipper for flip image on the top of the activity
	public static ViewFlipper viewFlipper;
	public ImageButton buttonOnMainPage; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainActivity = this;
		ActionBar bar = getActionBar();
		bar.setDisplayShowTitleEnabled(false); 
		bar.setIcon(
				   new ColorDrawable(getResources().getColor(android.R.color.transparent))); 
		bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.bar));


		FlipDetector = new GestureDetector(this,this);
		//strict mode for internet access
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		h=new Handler();
		//load imgresouces to variable imgs. 'hit' is an id that idicate which animation we want
		//loadImgResources("hit");
		new GetTask(this).execute();
		//find viewflipper from layout
		viewFlipper = (ViewFlipper) findViewById(R.id.vf);
		buttonOnMainPage = (ImageButton)findViewById(R.id.button_qiu);
		
		/*********Try to set linear layout ratioly*********
		 * Zinsser
		 */
		final LinearLayout first2buttons_layout = (LinearLayout) findViewById(R.id.first2buttons);
		final LinearLayout.LayoutParams first2buttons_params = (LinearLayout.LayoutParams) first2buttons_layout.getLayoutParams();
		final ViewTreeObserver first2buttons_vto = first2buttons_layout.getViewTreeObserver(); 
		final LinearLayout second2buttons_layout = (LinearLayout) findViewById(R.id.second2buttons);
		final LinearLayout.LayoutParams second2buttons_params = (LinearLayout.LayoutParams) second2buttons_layout.getLayoutParams();
		final ViewTreeObserver second2buttons_vto = second2buttons_layout.getViewTreeObserver(); 
		final LinearLayout flipper = (LinearLayout) findViewById(R.id.flipper);
		final LinearLayout.LayoutParams flipper_params= (LinearLayout.LayoutParams) flipper.getLayoutParams();
		first2buttons_vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
			
			@Override
			public boolean onPreDraw() {
				int width = first2buttons_layout.getWidth();
				first2buttons_params.height = (int) (width*0.5);
				first2buttons_layout.setLayoutParams(first2buttons_params);
				width = second2buttons_layout.getWidth();
				second2buttons_params.height = (int) (width*0.5);
				second2buttons_layout.setLayoutParams(second2buttons_params);
				flipper_params.width = (int) (width);
				flipper.setLayoutParams(flipper_params);
				return true;
			}
		});
		/*
		params.height=(int) (params.width*0.5);
		Log.e("height",String.valueOf(params.height));
		Log.e("width",String.valueOf(params.width));
		Log.e("weight",String.valueOf(params.weight));
		findViewById(R.id.first2buttons).setLayoutParams(params);*/
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
		Intent i = new Intent(getApplicationContext(), SponsorPage.class);
		startActivity(i);
	}
	/**-------------------------END_OF_HU_JIA_YING-----------------**/
	
	public void Food2Cssa(View v){
		Intent i = new Intent(getApplicationContext(), Food2Cssa.class);
		startActivity(i);
	}
	/**
	 * below are main activity methods which Zhaoyang_Zeng will take care of 
	 * **/
	
	
	
	//setFilpperImage function is used to set the image to viewflipper
	private void setFlipperImage(Bitmap res) {
	    ImageView image = new ImageView(getApplicationContext());
	    image.setImageBitmap(res);
	    //add that view to viewflipper
	    viewFlipper.addView(image);
	}
	
	//load imaResources acoording to id
	private void loadImgResources(String id)
	{
		imgs = (ArrayList<Bitmap>) manager.getManager().getImageList();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	


	//helper function conver dp to pixel
	public static float convertDpToPixel(float dp, Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float px = dp * (metrics.densityDpi / 160f);
	    return px;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent me){
		return FlipDetector.onTouchEvent(me);
	}
	
	//if you swipe screen, this method is called
	@Override
	public boolean onFling(MotionEvent event, MotionEvent arg1, float arg2,
			float arg3) {
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
        float y=TitleBarHeight+StatusBarHeight+buttonOnMainPage.getHeight()*2;
    
        //call animation if a fling occured in that certain area
        if (Math.abs(downXValue - currentX) > Math.abs(downYValue
                - currentY)) {
        	
            // going backwards: pushing stuff to the right
            if (downXValue <= currentX && downYValue>y && downYValue>=contentViewTop) {
        		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.animator.right_out));
                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.animator.left_in));
        		viewFlipper.showPrevious();
        	
            }

            // going forwards: pushing stuff to the left
           if (downXValue > currentX && downYValue>y && downYValue>=contentViewTop) {
        		viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.animator.right_in));
                viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.animator.left_out));
        		viewFlipper.showNext();
        		

            }

        } /**else {
            
            if (downYValue < currentY) {
            	//TextView text = (TextView)findViewById(R.id.textView2);
        		//text.setText("hit down");

            }
            if (downYValue > currentY) {
            	//TextView text = (TextView)findViewById(R.id.textView2);
        		//text.setText("hit up");

            }
        }**/
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

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

}
class GetTask extends AsyncTask<Object, Void, ArrayList<Bitmap>> {
    Context context;
    ProgressDialog mDialog;
    GetTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mDialog = new ProgressDialog(context);
        mDialog.setMessage("Please wait...");
        mDialog.show();
    }

    @Override
    protected ArrayList<Bitmap> doInBackground(Object... params) {
        // here you can get the details from db or web and fetch it..
    	return (ArrayList<Bitmap>) manager.getManager().getImageList();
    }

    @Override
    protected void onPostExecute(ArrayList<Bitmap> result) {
        MainActivity.imgs = result;
    	Log.e("do in back ground", "downloading!");
        mDialog.dismiss();
      //set all pictures in imgs to viewflipper for furthur gesture detection
        for(int i=0;i<MainActivity.imgs.size();i++)
      	{
        	//  This will create dynamic image view and add them to ViewFlipper
      		ImageView image = new ImageView(context);
    	    image.setImageBitmap(MainActivity.imgs.get(i));
    	    //add that view to viewflipper
    	    MainActivity.viewFlipper.addView(image);
      	}
        
    }
}
