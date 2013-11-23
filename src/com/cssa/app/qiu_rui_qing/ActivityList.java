package com.cssa.app.qiu_rui_qing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cssa.app.R;
import com.cssa.app.R.id;
import com.cssa.app.R.layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ActivityList extends Activity {
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.qrq_activity_list);
    
    final ListView listview = (ListView)findViewById(R.id.listview);
    String[] values = new String[]{"Android","iPhone","WindowsMobile",
    		"Blackberry","WebOS","Ubuntu","Windows7","Max OS X",
            "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
            "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
            "Android", "iPhone", "WindowsMobile" };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
          list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
            android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

          @Override
          public void onItemClick(AdapterView<?> parent, final View view,
              int position, long id) {
            final String item = (String) parent.getItemAtPosition(position);
            view.animate().setDuration(2000).alpha(0)
                .withEndAction(new Runnable() {
                  @Override
                  public void run() {
                    list.remove(item);
                    adapter.notifyDataSetChanged();
                    view.setAlpha(1);
                  }
                });
          }

        });
    }
}
class StableArrayAdapter extends ArrayAdapter<String> {

  HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

  public StableArrayAdapter(Context context, int textViewResourceId,
	        List<String> objects) {
	super(context, textViewResourceId, objects);
	for (int i = 0; i < objects.size(); ++i) {
	  mIdMap.put(objects.get(i), i);
	}
  }
  @Override
  public long getItemId(int position) {
	String item = getItem(position);
	return mIdMap.get(item);
  }
  @Override
  public boolean hasStableIds() {
	return true;
  }
}

