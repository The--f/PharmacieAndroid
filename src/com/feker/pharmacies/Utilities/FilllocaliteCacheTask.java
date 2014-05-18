//package com.feker.pharmacies.Utilities;
//
//
//
//import java.util.ArrayList;
//
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import com.feker.pharmacies.webservice.RestClient;
//
//
//public class FilllocaliteCacheTask extends AsyncTask<Integer,Integer , String> {
//	
//	
//
//	@Override
//	protected String doInBackground(Integer... params) {
//		
//		;
//		ArrayList<NameValuePair> header = new ArrayList<NameValuePair>();
//		header.add(new BasicNameValuePair("accept", "json"));			
//		int count = 24;        
//        for (int i = 0; i < count; i++) {  //  1-->24        	
//			try {			
//			//String response = new RestClient().Execute(RestClient.RequestMethod.POST, "http://server.imsfirm.com/pharmacie/index.php/api/getlocalitesbygouvernorat/"+StaticInformationGatway.getInstance().govid[i], header,  null );
//				String tab[] = new String[50] ;
//				try {
//					JSONArray jObject = new JSONArray("");
//					for (int j = 0; j < jObject.length(); j++) {
//						  JSONObject menuObject = jObject.getJSONObject(j);
//						  	tab[j] = menuObject.getString("Nomlocalite");
//					}
//					StaticInformationGatway.getInstance().addListLocalite("", tab);		
//				} catch (JSONException e) {
//					Log.i("tag", "here is parse  exception"); 
//					e.printStackTrace();
//				}								
//				//return response;								
//			} catch (Exception e) {
//				Log.i("tag", "here is  execute  exception");
//				e.printStackTrace();
//			}        	
//            publishProgress((int) i);
//            // Escape early if cancel() is called
//            if (isCancelled()) break;
//        }        
//		return null;
//	}
//		
//	@Override
//	protected void onPostExecute(String result) {
//		super.onPostExecute(result);
//		StaticInformationGatway.getInstance().finishloading = true;
//	}
//	
//
//	
//}
