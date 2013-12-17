package module;

public class activity_detail extends simple_activity_detail{

	private String title;
	private String text;
	
	//args: date, image ,introduction, title, text
	public activity_detail(String arg0, String arg1, String arg2,String arg3,String arg4) {
		super(arg0, arg1, arg2);
		this.title=arg3;
		this.text=arg4;
		// TODO Auto-generated constructor stub
	}
	
	public String getTitle(){
		return title;
	}
	public String getText(){
		return text;
	}

}
