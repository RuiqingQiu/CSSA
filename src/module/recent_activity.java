package module;

import java.util.ArrayList;

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
	
	
}
