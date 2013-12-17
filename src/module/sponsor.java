package module;

import java.util.ArrayList;

public class sponsor {
	private ArrayList<String> images;
	private ArrayList<String> urls;
	
	public sponsor(ArrayList<String> arg0,ArrayList<String> arg1){
		images=arg0;
		urls=arg1;
	}
	
	public String getSponsorImgFromIndex(int index){
		return images.get(index);
	}
	
	public String getSponsorUrlFromIndex(int index){
		return urls.get(index);
	}

}
