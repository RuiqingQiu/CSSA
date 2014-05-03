package com.cssa.app.tao_kang;

import com.cssa.app.R;
import android.app.Activity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;

import com.cssa.app.tao_kang.popMenu.OnItemClickListener;

import android.view.View.OnClickListener;

public class chapter3 extends Activity implements OnClickListener, OnItemClickListener {
	private popMenu popMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
        setContentView(R.layout.tk_chapter3);
        findViewById(R.id.btn_title_popmenu).setOnClickListener(this);
		
		// 初始化弹出菜单
		popMenu = new popMenu(this);
		popMenu.addItems(new String[]{"ch1", "ch2", "cha3", "ch4","ch5"});
		popMenu.setOnItemClickListener( this);
       

    }

    public void onClick(View v) {
		if(v.getId() == R.id.btn_title_popmenu){
			popMenu.showAsDropDown(v);
		}
	}

	public void onItemClick(int index) {
		if(index == 0)
		{
			Intent i = new Intent(getApplicationContext(), chapter1.class);
			startActivity(i);
		}
		if(index == 1)
		{
			Intent i = new Intent(getApplicationContext(), chapter2.class);
			startActivity(i);
		}
		if(index == 2)
		{
			Intent i = new Intent(getApplicationContext(), chapter3.class);
			startActivity(i);
		}
		if(index == 3)
		{
			Intent i = new Intent(getApplicationContext(), chapter4.class);
			startActivity(i);
		}
		if(index == 4)
		{
			Intent i = new Intent(getApplicationContext(), chapter5.class);
			startActivity(i);
		}
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
}