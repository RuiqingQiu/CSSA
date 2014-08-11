package module;

public class simple_activity_detail {
	private String id;
	private String date;
	private String image;
	private String introduction;
	private String title;
	
	//args: date, image, introduction
	public simple_activity_detail(String id, String title, String date,String image, String introduction){
		this.id = id;
		this.date=date;
		this.image=image;
		this.introduction=introduction;
		this.title = title;
	}
	
	public String getTitle(){
		return title;
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
	
	public String toDisplayFormat(){
		return "Title: " + title + "\n" + "introduction: " + introduction +"\n" 
				+ "date: "+ date;
	}
	public String toString(){
		return "id=" + id + " date=" + date + " image=" + 
	image + " introduction=" + introduction;
	}

}
