package managerLayer;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import module.activity_detail;
import module.activity_scroller;
import module.food2cssa;
import module.freshman101;
import module.recent_activity;
import module.simple_activity_detail;
import module.sponsor;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.cssa.app.ActivityImageFetchMachine;
import com.cssa.app.DAO;

public class manager {
	public static manager M;
	private DAO dao;
	
	
	private int cursorOfActivity;
	
	private LruCache<String, List<Bitmap>> mMemoryCache;
	private LruCache<String, String> versionCache;
	
	// Get max available VM memory, exceeding this amount will throw an
    // OutOfMemory exception. Stored in kilobytes as LruCache takes an
    // int in its constructor.
    final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    final String VERSION_KEY = "com.cssa.app.versionkey";

    // Use 1/8th of the available memory for this memory cache.
    final int cacheSize = maxMemory / 8;
    
	private manager(){
		dao=new DAO();
		cursorOfActivity=0;
		
		 mMemoryCache = new LruCache<String, List <Bitmap>>(cacheSize) {
		        @Override
		        protected int sizeOf(String key, List<Bitmap> bitmap) {
		            // The cache size will be measured in kilobytes rather than
		            // number of items.
		        	int totalSize = 0;
		        	for(Bitmap b : bitmap){
		        		totalSize += b.getByteCount()/1024;
		        	}
		            return totalSize;
		        }
		    };
		   versionCache = new LruCache<String, String>(cacheSize) {
		        @Override
		        protected int sizeOf(String key, String version) {
		            // The cache size will be measured in kilobytes rather than
		            // number of items.
		        	
		            return 1;
		        }
		    };
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
	
	public void addVersionCache(String key, String version) {
	    if (getCacheVersion() == null) {
	        versionCache.put(VERSION_KEY, version);
	    }
	}
	
	@SuppressLint("NewApi")
	public String getCacheVersion(){
		 return versionCache.get(VERSION_KEY);
	}

	public List<Bitmap> getImageList(){
		List<Bitmap> imgs = new ArrayList<Bitmap>();
		String version = dao.getVersion();
		String cacheVersion = this.getCacheVersion();

		if(cacheVersion == null)
			cacheVersion = "";
		Log.e("cache", "from web: " + version);
		Log.e("cache", "from local: " + cacheVersion);
		//The version is same
		if(cacheVersion.equals(version)){
			//TODO load the image from cache
			Log.e("cache", "Entering if");
		}else{
			this.addVersionCache(VERSION_KEY, version);
			Log.e("cache", "Entering else");
		}
		
		//create a new fetchmachine for picture fetching
		ActivityImageFetchMachine imgMachine = new ActivityImageFetchMachine();
		//use it like iterator, if it has next, then add it.
		while(imgMachine.hasNext()){
			imgs.add(imgMachine.next());
		}
		return imgs;
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
