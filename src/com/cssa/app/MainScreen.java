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
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.cssa.app.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainScreen extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen);
		//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		//StrictMode.setThreadPolicy(policy);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClickGo(View v) throws IOException{
		Intent i = new Intent(this, MainActivity.class);  
		Bundle mBundle = new Bundle();
		EditText mEdit   = (EditText)findViewById(R.id.editText1);
		String name = mEdit.getText().toString();
		Log.e("name",name);
		if (name.equals("hi")){
			String response="";
			String url="http://hello-zhaoyang-udacity.appspot.com/CSSA";
			 try {
                 response= downloadUrl(url);
                 
	         } catch (IOException e) {
	                 // TODO Auto-generated catch block
	                 e.printStackTrace();
	         }
			 //String response = downloadUrl("http://hello-zhaoyang-udacity.com/CSSA?message=hi");
			 Log.e("message",response);
		}else{
			Log.e("error","false");
			mBundle.putString("Name", mEdit.getText().toString());
			i.putExtras(mBundle);
			//startActivity(i);
		}
	}
	
	private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        try {
                URL url = new URL(myurl);
                URI uri = null;
                try {
                        uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                } catch (URISyntaxException e) {
                        // TODO Auto-generated catch block
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