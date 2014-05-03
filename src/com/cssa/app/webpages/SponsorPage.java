package com.cssa.app.webpages;

import com.cssa.app.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SponsorPage extends Activity {
	private WebView wv;
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_screen);
	    wv = (WebView)findViewById(R.id.webView);
	    wv.setWebViewClient(new WebViewClient(){
	    	public boolean shouldOverrideUrlLoading(WebView view, String url){
	    		view.loadUrl(url);
	    		return false;
	    	}
	    });
	    wv.getSettings().setLoadWithOverviewMode(true);
	    wv.getSettings().setUseWideViewPort(true);
	    wv.loadUrl("http://www.ucsdcssa.org/sponsors/");
	}
}
