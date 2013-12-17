package module;

public class activity_detail extends simple_activity_detail{

	private String title;
	private String text;
	
	//args: date, image ,introduction, title, text
	public activity_detail(String id,String date,String image, String introduction,String title,String text) {
		super(id, date, image,introduction);
		this.title=title;
		this.text=text;
		// TODO Auto-generated constructor stub
	}
	
	public String getTitle(){
		return title;
	}
	public String getText(){
		return text;
	}

}
