package managerLayer;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.LruCache;

import com.cssa.app.ActivityImageFetchMachine;
import com.cssa.app.DAO;
import com.cssa.app.MainActivity;

public class manager {
	public static manager M;
	private DAO dao;
	
	public final static String VERSION_FILENAME = "version_file";
	private int cursorOfActivity;
	//Print 14 numbers of images. offset 1
	public int IMAGE_NUMBERS = 5;

    
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
/** Need to refactor **/	
	public void addVersionToFile(String version) {
		try{
			FileOutputStream fos = MainActivity.mainActivity.openFileOutput(VERSION_FILENAME, Context.MODE_PRIVATE);
			fos.write(version.getBytes());
			fos.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@SuppressLint("NewApi")
	public String getVersionFromFile(){
		String line="";
		try{
			FileInputStream fis = MainActivity.mainActivity.openFileInput(VERSION_FILENAME);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader reader = new BufferedReader(isr);
			line = reader.readLine();
		}catch(IOException e){
			e.printStackTrace();
		}
		return line;
	}
	
	public Bitmap getImagesByName(String name){
		 FileInputStream fis;
		 Bitmap image = null;
		 try {
			fis = MainActivity.mainActivity.openFileInput(name);
			image= BitmapFactory.decodeStream(fis);
			fis.close();
		 } catch (Exception e) {
			e.printStackTrace();
		 }
		 //ByteArrayOutputStream out = new ByteArrayOutputStream();
		 //image.compress(Bitmap.CompressFormat.PNG, 100, out);
		 //Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
		 return image;
	}
	public void addImagesToFiles(List<Bitmap> imgs){
		int number = 1;
		for(Bitmap b : imgs){
			if(b == null){
				number++;
				continue;
			}
			else{
				try{
					FileOutputStream fos = MainActivity.mainActivity.openFileOutput(""+number+".png", Context.MODE_PRIVATE);
					// Writing the bitmap to the output stream
					b.compress(Bitmap.CompressFormat.PNG, 100, fos);
					fos.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}
				number++;
			}
		}
	}
/** File input and output **/
	public List<Bitmap> getImageList(){
		List<Bitmap> imgs = new ArrayList<Bitmap>();
		//Get version from online
		String version = dao.getVersion();
		//Local version
		String localVersion = this.getVersionFromFile();
		String[] splited = version.split("\\s+");
		String[] splited2 = localVersion.split("\\s+");
		Log.e("cache",  splited[0]);
		Log.e("cache",  splited2[0]);
		//The version is same

		if(splited[0].equals(splited2[0])){
			//TODO load the image from cache
			Log.e("cache", "Entering if");
			for(int i = 1; i < IMAGE_NUMBERS; i++){
				Log.e("load", ""+i);
				imgs.add(getImagesByName("" + i + ".png"));
			}
		}
		//Local version is null or older, downloaded new images
		else{
			this.addVersionToFile(version);
			Log.e("cache", "Entering else");
			//Download image and put into list
			//create a new fetchmachine for picture fetching
			ActivityImageFetchMachine imgMachine = new ActivityImageFetchMachine();
			//use it like iterator, if it has next, then add it.
			while(imgMachine.hasNext()){
				imgs.add(imgMachine.next());
			}
			//Adding images to files
			addImagesToFiles(imgs);
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
