/*
 * Author: Taokang
 */
package com.cssa.app.tao_kang;

import com.cssa.app.R;
<<<<<<< HEAD
import com.cssa.app.R.layout;
=======
>>>>>>> 37fe9b74a4e15d4c1b3d7850782bfce97798498c
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
<<<<<<< HEAD
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.TabSpec;
=======
>>>>>>> 37fe9b74a4e15d4c1b3d7850782bfce97798498c

public class tao_kang extends TabActivity {
	private RadioGroup group;
	private TabHost tabHost;

<<<<<<< HEAD
    @Override
    protected void onCreate(Bundle savedInstanceState) {
=======
public class tao_kang extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
>>>>>>> 37fe9b74a4e15d4c1b3d7850782bfce97798498c
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        
        group = (RadioGroup)findViewById(R.id.main_radio);
		tabHost = getTabHost();

		tabHost.addTab(tabHost.newTabSpec("TAB1")
                .setIndicator("TAB1")
                .setContent(new Intent(this,chapter1.class)));
		tabHost.addTab(tabHost.newTabSpec("TAB2")
                .setIndicator("TAB2")
                .setContent(new Intent(this,chapter2.class)));
		tabHost.addTab(tabHost.newTabSpec("TAB3")
                .setIndicator("TAB3")
                .setContent(new Intent(this,chapter3.class)));
		tabHost.addTab(tabHost.newTabSpec("TAB4")
                .setIndicator("TAB4")
                .setContent(new Intent(this,chapter4.class)));
	    group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.radio_button1:

					tabHost.setCurrentTabByTag("TAB1");
					
					break;
				case R.id.radio_button2:
					tabHost.setCurrentTabByTag("TAB2");
					break;
				case R.id.radio_button3:
					tabHost.setCurrentTabByTag("TAB3");
					break;
				case R.id.radio_button4:
					tabHost.setCurrentTabByTag("TAB4");
					break;
			    default:
					break;
				}
			}
		});
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
}

