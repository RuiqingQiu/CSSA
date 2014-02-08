package module;

import java.util.ArrayList;

public class activity_scroller {
	private String[] mimgs;
	private ArrayList<String> imgs = new ArrayList<String>();
	private String image;
	private String url;
	private int index=0;
	public activity_scroller(String image, String url){
		this.image=image;
		this.url=url;
		mimgs=image.split("\\|");
		for (String item : mimgs)
	    {
	        //Log.e("E",item);
	        imgs.add(item);
	    }
	}
	public boolean hasImg(){
		if (index==(mimgs.length)){
			return false;
		}
		else{
			return true;
		}
	}
	public String nextImg(){
		String img = imgs.get(index);
		index++;
		//Log.e("e1",img);
		return img;
	}
	public String getImage(){
		return image;
	}
	
	public String getUrl(){
		return url;
	}
}
