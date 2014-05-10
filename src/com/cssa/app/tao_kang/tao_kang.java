package com.cssa.app.tao_kang;

import com.cssa.app.R;
import com.cssa.app.tao_kang.SlidingMenu;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class tao_kang extends Activity implements OnClickListener{
	SlidingMenu mSlidingMenu;  

	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.main);


		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		mSlidingMenu = (SlidingMenu) findViewById(R.id.slidingMenu);
		mSlidingMenu.setAlignScreenWidth(dm.widthPixels / 3);
		
		View leftView=getLayoutInflater().inflate(R.layout.left_menu, null);
		View rightView=getLayoutInflater().inflate(R.layout.right_menu, null);
		View centerView=getLayoutInflater().inflate(R.layout.tk_chapter1, null);
		
		mSlidingMenu.setLeftView(leftView);
		mSlidingMenu.setRightView(rightView);
		mSlidingMenu.setCenterView(centerView);
        
		//Button showLeftMenu=(Button)centerView.findViewById(R.id.center_left_btn);
		//showLeftMenu.setOnClickListener(this);
		Button showRightMenu=(Button)centerView.findViewById(R.id.center_right_btn);
		showRightMenu.setOnClickListener(this);
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
				switch (v.getId()) {
		        case R.id.center_right_btn:
		        	mSlidingMenu.showRightView();
					break;
				default:
					break;
				}
	}
    
    
}


