package module;

public class activity_scroller {
	private String image;
	private String url;
	public activity_scroller(String image, String url){
		this.image=image;
		this.url=url;
	}

	public String getImage(){
		return image;
	}
	
	public String getUrl(){
		return url;
	}
}
