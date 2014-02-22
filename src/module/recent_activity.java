package module;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class recent_activity {
	ArrayList<simple_activity_detail> activities;
	public recent_activity(){
		activities= new ArrayList<simple_activity_detail>();
	}
	
	public boolean addActivity(simple_activity_detail sad){
		return activities.add(sad);
	}

	public simple_activity_detail getSimpleActivityFromIndex(int index){
		return activities.get(index);
	}
	public List<simple_activity_detail> getList(){
		return this.activities;
	}
	
	/**
	 * tester function for printing out everything in the list using log
	 */
	public void listEntries(){
		for(simple_activity_detail item : activities){
			Log.e("test",item.toString());
		}
	}
	
	
}
