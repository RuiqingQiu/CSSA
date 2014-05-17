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
    
    
    /*
    final ListView listview = (ListView)findViewById(R.id.listview);
    recent_activity r_a = manager.getManager().getNextPageRecentActivity(10);

    final StableArrayAdapter adapter = new StableArrayAdapter(this,
            R.layout.activity_list_item, r_a.getList());
    listview.setAdapter(adapter);
    final recent_activity r_a_inner = r_a;
    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

          @Override
          public void onItemClick(AdapterView<?> parent, final View view,
              int position, long id) {
        	Intent intent = new Intent(getApplicationContext(), ActivityDetailView.class);
        	Bundle bundle = new Bundle();
            bundle.putString("com.cssa.app.id", r_a_inner.getList().get(position).getId());
        	intent.putExtras(bundle);
            startActivity(intent); 
          }

        });
     */
}

class StableArrayAdapter extends ArrayAdapter<simple_activity_detail> {

  int layoutResourceId;
  List<simple_activity_detail> s_a_list;
  Context context;

  public StableArrayAdapter(Context context, int layoutResourceId,
	        List<simple_activity_detail> objects) {
	super(context, layoutResourceId, objects);
	this.layoutResourceId = layoutResourceId;
	s_a_list = objects;
	this.context =context;
  }
  
  
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
      View row = convertView;
      Simple_Activity_Holder holder = null;
      
      if(row == null)
      {
          LayoutInflater inflater = ((Activity)context).getLayoutInflater();
          row = inflater.inflate(layoutResourceId, parent, false);
          
          holder = new Simple_Activity_Holder();
          holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
          holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
          
          row.setTag(holder);
      }
      else
      {
          holder = (Simple_Activity_Holder)row.getTag();
      }
      
      simple_activity_detail detail = s_a_list.get(position);
      holder.txtTitle.setText(detail.toDisplayFormat());
      //holder.imgIcon.setImageResource();
      
      return row;
  }
  
  static class Simple_Activity_Holder
  {
	  TextView txtTitle;
	  ImageView imgIcon;
  }
}
