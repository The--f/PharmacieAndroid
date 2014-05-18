package com.feker.pharmacies.Utilities;



import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.feker.pharmacies.webservice.RestClient;


public class FillListGovCacheTask extends AsyncTask<Integer,Integer , String> {
	@Override
	protected String doInBackground(Integer... params) {		
		RestClient RC = new RestClient();
		ArrayList<NameValuePair> header = new ArrayList<NameValuePair>();
		header.add(new BasicNameValuePair("accept", "json"));					                       
			try {			
				RC.Execute(RestClient.RequestMethod.GET, "http://server.imsfirm.com/pharmacie/index.php/api/listegouvernerat/", header,  null );										
			} catch (Exception e) {
				Log.i("tag", "Here Is HTTP Request Exception");
				e.printStackTrace();
			}        	
            publishProgress((int) params[0]);
            return RC.response;		
	}
	
	@Override
	protected void onPostExecute(String result) {		
		super.onPostExecute(result);
	if (result.length()!= 0 ) {
		try {					
			JSONArray jObject = new JSONArray(result);
			for (int j = 0; j < jObject.length(); j++) {
				  JSONObject menuObject = jObject.getJSONObject(j);
				  	// 	{"idGouvernorat":29,"NomGouvernorat":"B\u00e9ja"},
				  	StaticInformationGatway.getInstance().govcache.put(menuObject.getInt("idGouvernorat"),menuObject.getString("NomGouvernorat"));
			}					
		} catch (JSONException e) {
			Log.i("tag", "here is JSON   parse  exception  at Fill listgov.PostExecute"); 
			e.printStackTrace();
		}
	}									
	}

	
}
