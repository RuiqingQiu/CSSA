/*
 * Filename: ActivityList.java
 */
package com.cssa.app.qiu_rui_qing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.json.JSONException;

import managerLayer.manager;
import module.recent_activity;
import module.simple_activity_detail;

import com.cssa.app.DAO;

import com.cssa.app.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityList extends Activity {
  @Override


  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.qrq_activity_list);
    
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
    }
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

