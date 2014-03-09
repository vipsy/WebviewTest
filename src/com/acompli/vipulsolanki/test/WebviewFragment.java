package com.acompli.vipulsolanki.test;

import com.acompli.vipulsolanki.test.WebAppInterface;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewFragment extends Fragment 
				implements WebAppInterface.Callback {
	
	private static final String TAG = "ACOMPLI";
	private DataChangeListener mDataChangeListener;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.webview_fragment, null);
		WebView webView = (WebView) v.findViewById(R.id.webview);
		webView.setWebViewClient(new MyWebViewClient()); //Set custom client
		webView.addJavascriptInterface(new WebAppInterface(getActivity(), this), "Native");
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setLoadWithOverviewMode(true);
		settings.setBuiltInZoomControls(true);
		
	
		webView.loadUrl("http://dl.dropboxusercontent.com/u/233148577/test.html");
		//webView.loadUrl("file:///android_asset/acompli.html"); 
		return v;
	}
	
	@Override
	public void onAttach(Activity activity) {
		mDataChangeListener = (DataChangeListener) activity;
		super.onAttach(activity);
	}
	
	public void setDataChangeListener(DataChangeListener listener) {
		mDataChangeListener = listener; 
	}

	//Implementing WebAppInterface.Callback
	@Override
	public void onDataStore() {
		mDataChangeListener.onDataChanged();
	}
			
	public 
	class MyWebViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String urlStr) {
			Log.d(TAG, "shouldOverrideUrlLoading() url=" + urlStr);			
			if(urlStr.startsWith("acompli")) {
				view.loadUrl(getScript());
				return true;
			}
			
			return super.shouldOverrideUrlLoading(view, urlStr);
		}

		private String getScript() {
			return  "javascript:" +
					"var inputs, index;" + 
					"var data = { array: [] };" +
					"inputs = document.getElementsByTagName('input');" +
					"for (index = 0; index < inputs.length; ++index) {" +
						"var field = inputs[index];" +
						""+
					   "data.array.push({\"name\": field.name ,\"value\": field.value});" +
					"}" + 
					"var json = JSON.stringify(data);" +
					"Native.storeData(json); ";   
		}
		
		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			Log.e(TAG, "WebviewClientError============");
			Log.e(TAG, "errorcode: "+errorCode);
			Log.e(TAG, "description: "+description);
			Log.e(TAG, "failingUrl "+failingUrl);
			
			super.onReceivedError(view, errorCode, description, failingUrl);
		}
		
	}
	
	public interface DataChangeListener {
		void onDataChanged();
	}
	
}
