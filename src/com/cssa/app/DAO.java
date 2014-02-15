package com.cssa.app;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import module.activity_detail;
import module.activity_scroller;
import module.food2cssa;
import module.freshman101;
import module.simple_activity_detail;
import module.sponsor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class DAO 
{
  public static final String SERVERURL = "http://hello-zhaoyang-udacity.appspot.com/CSSA";
  public static final String URL = "http://ucsdcssaapp.appspot.com/";
  public static final String IMAGEURL = "http://z.bvcx.org/cssa/geturl.txt";
  /**
   * Public constructor for DAO
   */
  public DAO()
  {	
  }
  
  
  /**
   * Method name: get_activity_scroller
   * @return activity_scroller object containing the decoded string of 
   * image and url
   * @throws JSONException
   */
  public activity_scroller get_activity_scroller() throws JSONException
  {
	  String response="";
	  try {
		  response= downloadUrl(IMAGEURL); 
	  } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
	  }
	  Log.e("hello",response);
	  
	  return new activity_scroller(response,"");
	  
	  /*try {
		  response= downloadUrl(SERVERURL); 
	  } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
	  }
	  Log.e("hello",response);
	  
	  JSONObject mainObject = new JSONObject(response);
	  JSONObject CSSAObject = mainObject.getJSONObject("CSSA");
	  JSONObject asObject = CSSAObject.getJSONObject("activity_scroller");
	  String uniURL = asObject.getString("url");
	  String uniImage = asObject.getString("image");
	  return new activity_scroller(uniImage,uniURL);*/
  }
  
  /**
   * Method name: get_activity_detail
   * @return activity_detail object
   * @throws JSONException
   */
  public activity_detail get_activity_detail_by_id(int ID) throws JSONException{
	  String response="";
	  String urlarg = "app";
	  try {
		  response= downloadUrl(URL+urlarg); 
	  } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
	  }
	  Log.e("message",response);
	  
	  JSONObject mainObject = new JSONObject(response);
	  //JSONObject CSSAObject = mainObject.getJSONObject("CSSA");
	  //JSONObject adObject = CSSAObject.getJSONObject("activity_detail");
	  //JSONObject acObject = mainObject.getJSONObject("activity");
	  JSONArray jarray = mainObject.getJSONArray("activity");
	  
	 /* String id = adObject.getString("id");
	  String postDate = adObject.getString("postDate");
	  String eventDate = adObject.getString("activityDate");
	  String image = adObject.getString("image");
	  String introduction = adObject.getString("intro");
	  String title = adObject.getString("title");
	  String text = adObject.getString("text");*/
	  for(int i = 0; i < jarray.length(); i++){
		  JSONObject o = jarray.getJSONObject(i);
		  String id = o.getString("id");
		  String eventDate = o.getString("activityDate");
		  String image = o.getString("image");
		  String introduction = o.getString("intro");
		  String title = o.getString("title");
		  String text = o.getString("text");
		  String postDate = o.getString("postDate");
		  Log.e("message",postDate+eventDate);
		  
	  }
	  return null;
  }
 
  /**
   * 
   * @return
   * @throws JSONException
   */
  public food2cssa get_food2cssa() throws JSONException
  {
	  String response="";
	  try {
		  response= downloadUrl(SERVERURL); 
	  } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
	  }
	  Log.e("message",response);
	  
	  JSONObject mainObject = new JSONObject(response);
	  JSONObject CSSAObject = mainObject.getJSONObject("CSSA");
	  JSONObject fcObject = CSSAObject.getJSONObject("food2cssa");
	  
	  String url = fcObject.getString("id");
	  
	  
	  return new food2cssa(url);
  }
  
  /**
   * 
   * @return freshman101 object
   * @throws JSONException
   */
  public freshman101 get_freshman101() throws JSONException
  {
	  String response="";
	  try {
		  response= downloadUrl(SERVERURL); 
	  } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
	  }
	  Log.e("message",response);
	  
	  JSONObject mainObject = new JSONObject(response);
	  JSONObject CSSAObject = mainObject.getJSONObject("CSSA");
	  JSONObject f1Object = CSSAObject.getJSONObject("freshman101");
	  
	  String rawData = f1Object.getString("rawData");
	   
	  return new freshman101(rawData);
  }
  
 
  /**
   * 
   * @param ID
   * @return
   * @throws JSONException
   */
  public simple_activity_detail get_simple_activity_detail_by_index(int index) throws JSONException
  {
	  String response="";
	  String urlarg = "?index=" + Integer.toString(index);
	  try {
		  response= downloadUrl(SERVERURL+urlarg); 
	  } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
	  }
	  Log.e("message",response);
	  
	  JSONObject mainObject = new JSONObject(response);
	  JSONObject CSSAObject = mainObject.getJSONObject("CSSA");
	  JSONObject sadObject = CSSAObject.getJSONObject("simple_activity_detail");
	  
	  String id = sadObject.getString("id");
	  String image = sadObject.getString("image");
	  String date = sadObject.getString("date");
	  String introduction = sadObject.getString("introduction");
	  
	  return new simple_activity_detail(id,date,image,introduction);
  }
  
  /**
   * 
   * @return
   * @throws JSONException
   */
  public sponsor get_sponsor_by_index(int index) throws JSONException
  {
	  String response="";
	  String urlarg = "?index=" + Integer.toString(index);
	  try {
		  response= downloadUrl(SERVERURL+urlarg); 
	  } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
	  }
	  Log.e("message",response);
	  
	  JSONObject mainObject = new JSONObject(response);
	  JSONObject CSSAObject = mainObject.getJSONObject("CSSA");
	  JSONObject sadObject = CSSAObject.getJSONObject("sponsor");
	  
	  String image = sadObject.getString("image");
	  String url = sadObject.getString("url");
	  String text = sadObject.getString("text");
	  
	  return new sponsor(image,url,text);
  }
  
  /**
   * Private helper function for downloading the information
   * @param myurl
   * @return
   * @throws IOException
   */
  private String downloadUrl(String myurl) throws IOException {
	    InputStream is = null;
	    try 
	    {
	    	URL url = new URL(myurl);
	        URI uri = null;
	        try {
	                    uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
	        } catch (URISyntaxException e) {
	                    e.printStackTrace();
	        }
	        url = uri.toURL();
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setReadTimeout(10000 /* milliseconds */);
	        conn.setConnectTimeout(15000 /* milliseconds */);
	        conn.setRequestMethod("GET");
	        conn.setDoInput(true);
	        conn.connect();
	        int response = conn.getResponseCode();
	         Log.e("DEBUG", "The response is: " + response);
	        is = conn.getInputStream();

	        String contentAsString = new DownloadWebpageText().readIt(is);
	        return contentAsString;

	    } finally {if (is != null) {is.close();} }
	    
	    
	}

	private class DownloadWebpageText {
	    private static final String DEBUG_TAG = "HttpExample";

	    protected String execute(String... urls) {
	            String fetchResult = "";
	        try {
	            fetchResult = downloadUrl(urls[0]);
	                //return fetchResult;

	        } catch (IOException e) {
	            Log.e("DEBUG", "Unable to retrieve web page. URL may be invalid.");
	            fetchResult = "Unable to retrieve web page. URL may be invalid.";
	        }
	        finally{
	                return fetchResult;
	        }
	    }

	    private String downloadUrl(String myurl) throws IOException {
	        InputStream is = null;
	        String fetchResult = "";
	        try {
	            URL url = new URL(myurl);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setReadTimeout(10000 /* milliseconds */);
	            conn.setConnectTimeout(15000 /* milliseconds */);
	            conn.setRequestMethod("GET");
	            conn.setDoInput(true);
	            conn.connect();
	            int response = conn.getResponseCode();
	            Log.d(DEBUG_TAG, "The response is: " + response);
	            is = conn.getInputStream();

	            fetchResult = readIt(is);

	        } finally {
	            if (is != null) {
	                is.close();
	            }
	            
	            return fetchResult;
	        }
	    }

	    public String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {

	        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "Unicode"));
	        StringBuilder sb = new StringBuilder();
	        String line;

	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	        reader.close();
	        String result = sb.toString();
	        return result;
	    }
	}
}
