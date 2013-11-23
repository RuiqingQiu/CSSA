package com.cssa.app;


import java.util.ArrayList;
import java.util.Dictionary;

public class ActivityImageFetchMachine {
	int index=0;
	//private Dictionary imgDic = new Dictionary();
	private ArrayList<Integer> imgs =new ArrayList<Integer>();
	static ActivityImageFetchMachine InstanceAM;
	public ActivityImageFetchMachine(){
		imgs.add(R.drawable.gif1);
		imgs.add(R.drawable.gif2);
		imgs.add(R.drawable.gif3);
		imgs.add(R.drawable.gif4);
		imgs.add(R.drawable.gif0);			
	}
	public static ActivityImageFetchMachine getInstance(){
		if (InstanceAM != null){
		    return InstanceAM;
		}else{
			InstanceAM= new ActivityImageFetchMachine();
			return InstanceAM;
		}
	}
	public boolean hasNext(){
		if (index < imgs.size()){
			return true;
		}else{
			return false;
		}
	}
	
	public Integer next(){
		int currentImg = imgs.get(index);
		index++;
		return currentImg;
	}

}
