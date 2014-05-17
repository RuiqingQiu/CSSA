package com.cssa.app.tao_kang;

import com.cssa.app.R;
import com.cssa.app.tao_kang.SlidingMenu;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;



public class tao_kang extends Activity implements OnClickListener{
	SlidingMenu mSlidingMenu;  
	private ActionBar mActionBar;

	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.slidemain);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		mSlidingMenu = (SlidingMenu) findViewById(R.id.slidingMenu);
		mSlidingMenu.setAlignScreenWidth(dm.widthPixels / 4);
		
		View leftView=getLayoutInflater().inflate(R.layout.left_menu, null);
		View rightView=getLayoutInflater().inflate(R.layout.right_menu, null);
		View centerView=getLayoutInflater().inflate(R.layout.tk_chapter1, null);
		
		mSlidingMenu.setLeftView(leftView);
		mSlidingMenu.setRightView(rightView);
		mSlidingMenu.setCenterView(centerView);
        
		//Button showLeftMenu=(Button)centerView.findViewById(R.id.center_left_btn);
		//showLeftMenu.setOnClickListener(this);
		//Button showRightMenu=(Button)centerView.findViewById(R.id.center_right_btn);
		//showRightMenu.setOnClickListener(this);
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar, menu);
        mActionBar = getActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
    	String toast = "";
    	if(item.getItemId() == R.id.showRightMenu)
    	{
    		toast = "showRight";
    		mSlidingMenu.showRightView();
    	}
    	 if(item.getItemId() == android.R.id.home)
         {
             finish();
             return true;
         }
        return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
				/**if(v.getId() == R.id.center_right_btn) {
		      
		        	mSlidingMenu.showRightView();
			
				}**/
	}
    
	public void ch1(View v){
		View centerView=getLayoutInflater().inflate(R.layout.tk_chapter1, null);
		mSlidingMenu.setCenterView(centerView);
		
	}
    
	public void ch2(View v){
		View centerView=getLayoutInflater().inflate(R.layout.tk_chapter2, null);
		mSlidingMenu.setCenterView(centerView);
		
	}
	public void ch3(View v){
		View centerView=getLayoutInflater().inflate(R.layout.tk_chapter3, null);
		mSlidingMenu.setCenterView(centerView);
		
	}
	public void ch4(View v){
		View centerView=getLayoutInflater().inflate(R.layout.tk_chapter4, null);
		mSlidingMenu.setCenterView(centerView);
		
	}
	public void ch5(View v){
		View centerView=getLayoutInflater().inflate(R.layout.tk_chapter5, null);
		mSlidingMenu.setCenterView(centerView);
		
	}
}


