package managerLayer;

import java.util.ArrayList;

import org.json.JSONException;

import module.activity_detail;
import module.activity_scroller;
import module.food2cssa;
import module.freshman101;
import module.recent_activity;
import module.simple_activity_detail;
import module.sponsor;

import com.cssa.app.DAO;

public class manager {
	public static manager M;
	private DAO dao;
	private int cursorOfActivity;
	private manager(){
		dao=new DAO();
		cursorOfActivity=0;
	}
	public static manager getManager(){
		if(M==null){
			M=new manager();
		}
		return M;
	}
	public activity_scroller getActivityScroller(){
		try {
			return dao.get_activity_scroller();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public recent_activity getNextPageRecentActivity(int count){
		recent_activity r_a = null;
		try {
			r_a = dao.get_simple_activity_detail_by_index(1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r_a;
	}
	
	public freshman101 getFreshman101(){
		try {
			return dao.get_freshman101();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public food2cssa getFood2cssa(){
		try {
			return dao.get_food2cssa();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<sponsor> getSponsors(int index){
		ArrayList<sponsor> sps = new ArrayList<sponsor>();
		index=0;
		while(true){
			try{
				sponsor sp=dao.get_sponsor_by_index(index);
				sps.add(sp);
			}catch(JSONException e){
				return sps;
			}
			index++;
		}
	}
	public activity_detail getActivityDetailById(String ID){
		try {
			return dao.get_activity_detail_by_id(ID);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
