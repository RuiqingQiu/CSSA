/*
 * Filename: qiu_rui_qing.java
 * Author: Ruiqing Qiu
 * Date: Nov. 16, 2013
 */
package com.cssa.app.qiu_rui_qing;

import com.cssa.app.R;
import com.cssa.app.R.id;
import com.cssa.app.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class qiu_rui_qing extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qiu_rui_qing);
        
        //A button for going to pool page
        Button pool_info = (Button) findViewById(R.id.poolButton);
        Button civitas_info = (Button) findViewById(com.cssa.app.R.id.FirstActivityButton);
        pool_info.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), PoolPage.class);
				startActivity(i);
				
			}
        	
        });
        
        civitas_info.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),CitivasPage.class);
				startActivity(i);
			}
        	
        });
	}
}
