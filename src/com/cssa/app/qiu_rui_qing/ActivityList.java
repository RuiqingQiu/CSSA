/*
 * Filename: ActivityList.java
 */
package com.cssa.app.qiu_rui_qing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

<<<<<<< HEAD
=======
import org.json.JSONException;

import module.recent_activity;
import module.simple_activity_detail;

import com.cssa.app.DAO;
>>>>>>> 3277adc44d987edb8aefeb7a72c35d816d872973
import com.cssa.app.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
<<<<<<< HEAD
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
=======
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
>>>>>>> 3277adc44d987edb8aefeb7a72c35d816d872973
import android.widget.ListView;
import android.widget.TextView;

public class ActivityList extends Activity {
  @Override
<<<<<<< HEAD
protected void onCreate(Bundle savedInstanceState) {
=======
  protected void onCreate(Bundle savedInstanceState) {
>>>>>>> 3277adc44d987edb8aefeb7a72c35d816d872973
    super.onCreate(savedInstanceState);
    setContentView(R.layout.qrq_activity_list);
    
    final ListView listview = (ListView)findViewById(R.id.listview);
    DAO d1 = new DAO();
    recent_activity r_a = null;
    try {
		r_a = d1.get_simple_activity_detail_by_index(1);
	} catch (JSONException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
   
    final StableArrayAdapter adapter = new StableArrayAdapter(this,
            R.layout.activity_list_item, r_a.getList());
    listview.setAdapter(adapter);

    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

          @Override
          public void onItemClick(AdapterView<?> parent, final View view,
              int position, long id) {
           
            Log.e("test",  parent.getItemAtPosition(position).toString());
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

