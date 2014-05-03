/*
 * Author: Taokang
 */
package com.cssa.app.tao_kang;

import com.cssa.app.MainActivity;
import com.cssa.app.R;
import com.cssa.app.tao_kang.popMenu.OnItemClickListener;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;



public class tao_kang extends Activity implements OnClickListener, OnItemClickListener {
	private popMenu popMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tk_chapter1);
        setActionBarLayout( R.layout.header );
        findViewById(R.id.btn_title_popmenu).setOnClickListener(this);
		
		// 初始化弹出菜单
		popMenu = new popMenu(this);
		popMenu.addItems(new String[]{"ch1", "ch2", "cha3", "ch4","ch5"});
		popMenu.setOnItemClickListener( this);
       

    }
    public void home(View v){
		Intent i = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(i);
    }

    public void onClick(View v) {
		if(v.getId() == R.id.btn_title_popmenu){
			popMenu.showAsDropDown(v);
		}
	}

	public void onItemClick(int index) {
		if(index == 0)
		{
			setContentView(R.layout.tk_chapter1);
		}
		if(index == 1)
		{
			setContentView(R.layout.tk_chapter2);
		}
		if(index == 2)
		{
			setContentView(R.layout.tk_chapter3);
		}
		if(index == 3)
		{
			setContentView(R.layout.tk_chapter4);
		}
		if(index == 4)
		{
			setContentView(R.layout.tk_chapter5);
		}
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void setActionBarLayout( int layoutId ){
		ActionBar actionBar = getActionBar( );
		if( null != actionBar ){
			actionBar.setDisplayShowHomeEnabled( false );
			actionBar.setDisplayShowCustomEnabled(true);

			LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = inflator.inflate(layoutId, null);
			ActionBar.LayoutParams layout = new ActionBar.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
			actionBar.setCustomView(v,layout);
		}
	}
    
}

