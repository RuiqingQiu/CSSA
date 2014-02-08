package com.cssa.app;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import managerLayer.manager;
import module.activity_scroller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ActivityImageFetchMachine {
	int index=0;
	//private Dictionary imgDic = new Dictionary();
	private ArrayList<Bitmap> imgs =new ArrayList<Bitmap>();
	static ActivityImageFetchMachine InstanceAM;
	public ActivityImageFetchMachine(){
		activity_scroller as = manager.getManager().getActivityScroller();
		while(as.hasImg()){
			imgs.add(loadBitmap(as.nextImg()));
		}
		//imgs.add(R.drawable.gif2);
		//imgs.add(R.drawable.gif3);
		//imgs.add(R.drawable.gif4);
		//imgs.add(R.drawable.gif0);			
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
	
	public Bitmap next(){
		Bitmap currentImg = imgs.get(index);
		index++;
		return currentImg;
	}

	public static Bitmap loadBitmap(String url) {
		URL newurl = null;
		try {
			newurl = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//profile_photo.setImageBitmap(mIcon_val);
	    return bitmap;
	}
}
