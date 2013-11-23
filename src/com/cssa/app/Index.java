/*
 * Filename: Index.java
 */
package com.cssa.app;

import java.util.ArrayList;

import com.cssa.app.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Index extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClickGo(View v){
		Intent i = new Intent(this, MainActivity.class);  
		Bundle mBundle = new Bundle();
		EditText mEdit   = (EditText)findViewById(R.id.editText1);
		mBundle.putString("Name", mEdit.getText().toString());
		i.putExtras(mBundle);
		startActivity(i);
	}
	
		
}
