/*
 * Filename: qiu_rui_qing.java
 * Author: Ruiqing Qiu
 * Date: Nov. 16, 2013
 */
package com.cssa.app.qiu_rui_qing;

import java.io.IOException;
import java.util.List;

import managerLayer.manager;
import module.recent_activity;
import module.simple_activity_detail;

import org.json.JSONException;


import org.json.JSONException;


import com.cssa.app.DAO;
import com.cssa.app.R;
import com.fima.cardsui.objects.CardStack;
//import com.cssa.app.qiu_rui_qing.StableArrayAdapter.Simple_Activity_Holder;
import com.fima.cardsui.views.CardUI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class qiu_rui_qing extends Activity {
	private CardUI mCardView;
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.qrq_activity_list);

	// init CardView
	mCardView = (CardUI) findViewById(R.id.cardsview);
	mCardView.setSwipeable(true);
	
	CardStack titleStack = new CardStack();
	titleStack.setTitle("Recent Activity");
	mCardView.addStack(titleStack, true);
    recent_activity r_a = manager.getManager().getNextPageRecentActivity(10);
    List<simple_activity_detail> lst = r_a.getList();
    for(int i = 0; i < lst.size(); i++){
    	MyPlayCard tmp = null;
    	
    	switch(i % 4){
    	    	case 0:
    	     		tmp = new MyPlayCard(
    	 				lst.get(i).getTitle(),
    	 				lst.get(i).getDate() + "\n" +
    	 				lst.get(i).getIntoduction(),
    	 				"#4ac925", "#222222", true, true);
    	     		break;
    	     	case 1:
    	     		tmp = new MyPlayCard(
    	     				lst.get(i).getTitle(),
    	     				lst.get(i).getIntoduction(),
    	     				"#f2a400", "#9d36d0", true, true);
    	    		break;
    	     	case 2:
    	     		tmp = new MyPlayCard(
    	     				lst.get(i).getTitle(),
    	     				lst.get(i).getIntoduction(),
    	     				"#e00707", "#e00707", true, true);
    	     		break;
    	     	case 3: 
    	     		tmp = new MyPlayCard(
    	     				lst.get(i).getTitle(),
    	     				lst.get(i).getIntoduction(),
    	     				"#33b6ea","#33b6ea", true, true);
    	     		break;
    	     	}
    	
    	/*switch(i % 7){
	    	case 0:
	    		tmp = new MyPlayCard(
					lst.get(i).getTitle(),
					lst.get(i).getIntoduction(),
					"red", "#1e1e1e", true, true);
	    		break;
	    	case 1:
	    		tmp = new MyPlayCard(
	    				lst.get(i).getTitle(),
	    				lst.get(i).getIntoduction(),
	    				"#ff6633", "#9d36d0", true, true);
	    		break;
	    	case 2:
	    		tmp = new MyPlayCard(
	    				lst.get(i).getTitle(),
	    				lst.get(i).getIntoduction(),
	    				"yellow", "#e00707", true, true);
	    		break;
	    	case 3: 
	    		tmp = new MyPlayCard(
	    				lst.get(i).getTitle(),
	    				lst.get(i).getIntoduction(),
	    				"green","#33b6ea", true, true);
	    		break;
	    	case 4: 
	    		tmp = new MyPlayCard(
	    				lst.get(i).getTitle(),
	    				lst.get(i).getIntoduction(),
	    				"cyan","#33b6ea", true, true);
	    		break;
	    	case 5: 
	    		tmp = new MyPlayCard(
	    				lst.get(i).getTitle(),
	    				lst.get(i).getIntoduction(),
	    				"blue","#33b6ea", true, true);
	    		break;
	    	case 6: 
	    		tmp = new MyPlayCard(
	    				lst.get(i).getTitle(),
	    				lst.get(i).getIntoduction(),
	    				"purple","#33b6ea", true, true);
	    		break;
    	}*/
    	final String id = lst.get(i).getId();
    	tmp.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), ActivityDetailView.class);
	        	Bundle bundle = new Bundle();
	            bundle.putString("com.cssa.app.id", id);
	        	intent.putExtras(bundle);
	            startActivity(intent); 
			}
    		
    	});
    	CardStack tempStack = new CardStack();
    	mCardView.addStack(tempStack, true);
    	mCardView.addCardToLastStack(tmp);
    }
	
	mCardView.refresh();
  }
}
