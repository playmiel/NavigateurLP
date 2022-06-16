package com.ngppliz;

import com.ngppliz.AdvancedWebView;
import android.webkit.WebChromeClient;
import android.widget.Toast;
import android.webkit.WebView;
import android.view.View;
import android.webkit.WebViewClient;
import android.graphics.Bitmap;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Activity;
import android.net.*;
import android.view.*;
import android.widget.*;
import android.view.View.*;
import org.apache.http.util.*;
import java.net.*;
import android.content.*;
import android.support.v4.widget.*;
import android.support.v7.app.AppCompatActivity;
import android.webkit.*;

public class MainActivity extends Activity implements AdvancedWebView.Listener {
    
    WebView view;

	   private static final String TEST_PAGE_URL = "https://www.google.fr/";
	private AdvancedWebView mWebView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	
	
        if (savedInstanceState == null)
            ((WebView)findViewById(R.id.webview)).restoreState(savedInstanceState);
		
		
			mWebView = (AdvancedWebView) findViewById(R.id.webview);
		mWebView.setListener(this, this);
		mWebView.setGeolocationEnabled(true);
		mWebView.setMixedContentAllowed(true);
		mWebView.setCookiesEnabled(true);
		mWebView.setThirdPartyCookiesEnabled(true);
		mWebView.getSettings().setLoadsImagesAutomatically(true);      
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
		mWebView.getSettings().setAppCacheEnabled(true);
		mWebView.getSettings().setSupportMultipleWindows(true);
		mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		mWebView.setWebViewClient(new WebViewClient() {
			
				
			@Override
				public void onPageFinished(WebView view, String url) {				
					Toast.makeText(MainActivity.this, "chargement terminer", Toast.LENGTH_SHORT).show();
				}

			});
		mWebView.setWebChromeClient(new WebChromeClient() {

				@Override
				public void onReceivedTitle(WebView view, String title) {
					super.onReceivedTitle(view, title);
					Toast.makeText(MainActivity.this, title, Toast.LENGTH_LONG).show();
				}

			});
		mWebView.addHttpHeader("X-Requested-With", "");
		mWebView.loadUrl(TEST_PAGE_URL);
	}

	@SuppressLint("NewApi")
	@Override
	protected void onResume() {
		super.onResume();
		mWebView.onResume();
		// ...
	}

	@SuppressLint("NewApi")
	@Override
	protected void onPause() {
		mWebView.onPause();
		// ...
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		mWebView.onDestroy();
		super.onDestroy();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		mWebView.onActivityResult(requestCode, resultCode, intent);
		// ...
	}

	@Override
	public void onBackPressed() {
		if (!mWebView.onBackPressed()) { return; }
		// ...
		super.onBackPressed();
	}
	
	@Override
	public void onPageStarted(String url, Bitmap favicon) {
		checkInternetConenction();
		findViewById(R.id.progress1).setVisibility(View.VISIBLE);
		mWebView.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onPageFinished(String url) {
		findViewById(R.id.progress1).setVisibility(View.GONE);
		mWebView.setVisibility(View.VISIBLE);
	}

	@Override
	public void onPageError(int errorCode, String description, String failingUrl) {
		Toast.makeText(MainActivity.this, "erreur(errorCode = "+errorCode+",  description = "+description+",  failingUrl = "+failingUrl+")", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
		Toast.makeText(MainActivity.this, "telechargement requis(url = "+url+",  suggestedFilename = "+suggestedFilename+",  mimeType = "+mimeType+",  contentLength = "+contentLength+",  contentDisposition = "+contentDisposition+",  userAgent = "+userAgent+")", Toast.LENGTH_LONG).show();

//		if (AdvancedWebView.handleDownload(this, url, suggestedFilename)) {
//		 // download successfully handled
//		 }
//		 else {
//		 // download couldn't be handled because user has disabled download manager app on the device
//		 }
	}

	@Override
	public void onExternalPageRequest(String url) {
		Toast.makeText(MainActivity.this, "page externe requis(url = "+url+")", Toast.LENGTH_SHORT).show();
	}

	private boolean checkInternetConenction() {

      ConnectivityManager connec
         =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

      if ( connec.getNetworkInfo(0).getState() == 
         android.net.NetworkInfo.State.CONNECTED ||
         connec.getNetworkInfo(0).getState() == 
         android.net.NetworkInfo.State.CONNECTING ||
         connec.getNetworkInfo(1).getState() == 
         android.net.NetworkInfo.State.CONNECTING ||
         connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
            Toast.makeText(this, " Connecte ", Toast.LENGTH_SHORT).show();
            return true;
         }else if (
            connec.getNetworkInfo(0).getState() == 
            android.net.NetworkInfo.State.DISCONNECTED ||
            connec.getNetworkInfo(1).getState() == 
            android.net.NetworkInfo.State.DISCONNECTED  ) {
               Toast.makeText(this, " non connecte ", Toast.LENGTH_SHORT).show();
               return false;
			   
			  
               }
   
		
        return false;
    
        }}
		 
		 
		
				
		

	
		 
