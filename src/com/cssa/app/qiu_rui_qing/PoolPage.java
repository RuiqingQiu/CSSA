/*
 * Filenam: PoolPage.java
 * Developer: Ruiqing Qiu
 * Created: Nov. 23, 2013
 */
package com.cssa.app.qiu_rui_qing;

import com.cssa.app.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PoolPage extends Activity{

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.qrq_pool_info);
      
	    }


	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	    
}
