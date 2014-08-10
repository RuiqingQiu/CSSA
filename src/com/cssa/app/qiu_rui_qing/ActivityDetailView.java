package com.cssa.app.qiu_rui_qing;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import managerLayer.manager;
import module.activity_detail;

import com.cssa.app.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityDetailView extends Activity {
	private TextView title;
	private TextView date;
	private ImageView image;
	private TextView paragraph;
	private WebView wv;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		//title = (TextView)findViewById(R.id.detail_title);
		//date = (TextView)findViewById(R.id.detail_date);
		//image = (ImageView)findViewById(R.id.detail_image);
		//paragraph = (TextView)findViewById(R.id.detail_paragraph);
		wv = (WebView)findViewById(R.id.webView1);
		
		Intent i = this.getIntent();
		Bundle extras = i.getExtras();
		String id = extras.getString("com.cssa.app.id");
		activity_detail a_d = manager.getManager().getActivityDetailById(id);
		
		
		/**
		title.setText(a_d.getTitle());
		date.setText(a_d.getDate());
		//Uri uri = Uri.parse(a_d.getImage());
		//image.setImageURI(uri);
		
		URL newurl = null;
		try {
			newurl = new URL(a_d.getImage());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		image.setImageBitmap(bitmap);
		
		
		
		paragraph.setText(a_d.getText());**/

	    wv.setWebViewClient(new WebViewClient(){
	    	public boolean shouldOverrideUrlLoading(WebView view, String url){
	    		view.loadUrl(url);
	    		return false;
	    	}
	    });
	    wv.loadUrl(a_d.getText());
	}
}
