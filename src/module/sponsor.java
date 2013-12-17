package module;



public class sponsor {
	private String images;
	private String urls;
	private String text;
	
	public sponsor(String images,String urls,String text){
		this.images=images;
		this.urls=urls;
		this.text = text;
	}
	
	public String getImages(){
		return images;
	}
	
	public String getUrls(){
		return urls;
	}
	
	public String getText(){
		return text;
	}

}
