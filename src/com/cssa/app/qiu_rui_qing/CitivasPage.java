/*
 * Filename: Citivas Page
 * Developer: Ruiqing Qiu
 * Date Created: Nov. 23, 2013
 * ;;;;;
 */
package com.cssa.app.qiu_rui_qing;

import com.cssa.app.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CitivasPage extends Activity{

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(com.cssa.app.R.layout.qiu_citivas_info);
      
	    }
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	    
}
