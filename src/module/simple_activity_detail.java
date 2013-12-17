package module;

public class simple_activity_detail {
	private String date;
	private String image;
	private String introduction;
	
	//args: date, image, introduction
	public simple_activity_detail(String arg0,String arg1,String arg2){
		date=arg0;
		image=arg1;
		introduction=arg2;
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
