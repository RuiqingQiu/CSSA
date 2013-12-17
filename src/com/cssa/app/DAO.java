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

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;



public class DAO 
{

  public DAO()
  {	
  }
  
  public void get_activity_scroller() throws JSONException
  {
	  String response="";
	  String url="http://hello-zhaoyang-udacity.appspot.com/CSSA";
	  try {
		  response= downloadUrl(url); 
	  } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
	  }
	  Log.e("message",response);
	  
	  JSONObject mainObject = new JSONObject(response);
	  JSONObject CSSAObject = mainObject.getJSONObject("CSSA");
	  JSONObject asObject = CSSAObject.getJSONObject("activity_scroller");
	  String uniURL = asObject.getString("url");
	  String uniImage = asObject.getString("image");
	  System.out.println(uniURL);
	  System.out.println(uniImage);
	  
  }
  
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
	        // Log.e("DEBUG", "The response is: " + response);
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

	        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
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
