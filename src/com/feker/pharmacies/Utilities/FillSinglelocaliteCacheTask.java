package com.feker.pharmacies.Utilities;



import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.feker.pharmacies.webservice.RestClient;


public class FillSinglelocaliteCacheTask extends AsyncTask<Integer,Integer , String> {
	

	@Override
	protected String    doInBackground(Integer... params) {
		RestClient RC = new RestClient();
		ArrayList<NameValuePair> header = new ArrayList<NameValuePair>();
		header.add(new BasicNameValuePair("accept", "json"));					                       
			try {			
				RC.Execute(RestClient.RequestMethod.POST, "http://server.imsfirm.com/pharmacie/index.php/api/getlocalitesbygouvernorat/"+params[0], header,  null );
			} catch (Exception e) {
				Log.i("tag", "HERE IS HTTP REQUEST EXCEPTION @ FILL SINGLE LOCALITE ");
				e.printStackTrace();
			}        	            
            return RC.response;		
	}
	protected void onPostExecute(String result) {
	if (result.length() != 0 ) 
	{
		ArrayList<String> tab = new ArrayList<String>() ;
		try {
			JSONArray jObject = new JSONArray(result);
			for (int j = 0; j < jObject.length(); j++) {
				  JSONObject menuObject = jObject.getJSONObject(j);
				  	tab.add(menuObject.getString("Nomlocalite"));
				  	publishProgress((int)j);
			}
		StaticInformationGatway.getInstance().setListLocalite(
				StaticInformationGatway.getInstance().govcache.get(
						StaticInformationGatway.getInstance().getSelectedgov()  ), tab);
	
		} catch (JSONException e) {
			Log.i("tag", "HERE IS JSON EXCEPTION @ FILL SINGLE LOCALITE "); 
			e.printStackTrace();
		}
	}	
		
		
	}

}
