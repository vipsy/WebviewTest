package com.acompli.vipulsolanki.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.acompli.vipulsolanki.test.db.Input;
import com.acompli.vipulsolanki.test.db.dao.InputDao;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class WebAppInterface {
	
	private static final String TAG = "ACOMPLI";
	private Context mContext;
	private Callback mCallback;

	public WebAppInterface(Context c, Callback callback) {
		mContext = c;
		mCallback = callback;
	}
	
	@JavascriptInterface
	public void storeData(String jsonData) {
		Log.d(TAG, jsonData);
		//ContentValues cv = new ContentValues();
		try {
			JSONObject obj = new JSONObject(jsonData);
			JSONArray jsonArray = obj.getJSONArray("array");
			for (int i=0; i<jsonArray.length(); i++) {
				JSONObject item = jsonArray.getJSONObject(i);
				String name = item.getString("name");
				String value = item.getString("value");
				//cv.put(name, value);
				Input input = new Input();
				input.setName(name);
				input.setValue(value); 
				InputDao dao = new InputDao(mContext);
				dao.insert(input);
				if (mCallback != null) {
					mCallback.onDataStore();
				}
				Log.d(TAG, "name: "+name+" value: "+value);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public interface Callback {
		void onDataStore();
	}
	
}
