package module;

public class simple_activity_detail {
	private String id;
	private String date;
	private String image;
	private String introduction;
	
	//args: date, image, introduction
	public simple_activity_detail(String id,String date,String image, String introduction){
		this.id = id;
		this.date=date;
		this.image=image;
		this.introduction=introduction;
	}
	
	public String getId(){
		return id;
	}
	
	public String getDate(){
		return date;
	}
	
	public String getImage(){
		return image;
	}
	
	public String getIntoduction(){
		return introduction;
	}

}
