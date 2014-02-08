/*
 * Author: Taokang
 */
package com.cssa.app.tao_kang;

import com.cssa.app.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class tao_kang extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tao_kang);
        
	}
	public void clickChapter1(View view )
    {
    	Intent i = new Intent(this, chapter1.class);
    	startActivity(i);
    }
    
    public void clickChapter2(View view )
    {
    	Intent i = new Intent(this, chapter2.class);
    	startActivity(i);
    }
    
    public void clickChapter3(View view )
    {
    	Intent i = new Intent(this, chapter3.class);
    	startActivity(i);
    }
    
    public void clickChapter4(View view )
    {
    	Intent i = new Intent(this, chapter4.class);
    	startActivity(i);
    }
    public void clickChapter5(View view )
    {
    	Intent i = new Intent(this, chapter5.class);
    	startActivity(i);
    }
}


