package com.feker.pharmacies;



import java.lang.reflect.Field;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.feker.pharmacies.Utilities.StaticInformationGatway;
import com.feker.pharmacies.webservice.RestClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends SupportMapFragment  {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	 private GoogleMap mMap;

	public MapFragment() {		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View rootView  = (View) inflater.inflate(R.layout.fragment_map, container, false);	   					
        	rootView = setUpMapIfNeeded(rootView);		
		return rootView;
	}
	@Override
	public void onResume() {

		super.onResume();
		setUpMapIfNeeded(this.getView());
	}
	
	private View setUpMapIfNeeded(View V ) {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
         //   ( getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        	 mMap =	((SupportMapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
            	mMap.setMyLocationEnabled(true);
                setUpMap();
            }
        }
		return V;
    }
	
	 private void setUpMap() {
		  	mMap.addMarker(new MarkerOptions()
	  		.position(new LatLng(
	  					0.0
  						,0.0
	  					)
	  				).title("NomPharmacien") 
	  			);
		 	RestClient RC = new RestClient();
			ArrayList<NameValuePair> header = new ArrayList<NameValuePair>();
			header.add(new BasicNameValuePair("accept", "json"));					                       
				try {			
					RC.Execute(RestClient.RequestMethod.GET, "http://server.imsfirm.com/pharmacie/index.php/api/getgpspharmacie/", header,  null );										
				} catch (Exception e) {
					Log.i("tag", "Here Is HTTP Request Exception");
					e.printStackTrace();
				} 
			mMap.clear();
			String result = RC.response;
			//result ="[{\"NomPharmacien\":\"Bounsnina Salah\",\"longitute\":\"10.1689624786377\",\"lattitude\":\"36.8901928679271\"},{\"NomPharmacien\":\"Hela Chahed\",\"longitute\":\"10.5914640426636\",\"lattitude\":\"36.6494411163734\"},{\"NomPharmacien\":\"Boubaya Ali \",\"longitute\":\"10.1795196533203\",\"lattitude\":\"36.7900925187546\"},{\"NomPharmacien\":\"Ghileb Semi\",\"longitute\":\"10.1858711242676\",\"lattitude\":\"36.9000687161051\"},{\"NomPharmacien\":\"Ayari Nejib \",\"longitute\":\"10.5921936035156\",\"lattitude\":\"36.6447325200542\"},{\"NomPharmacien\":\"mohamed ali jemaa\",\"longitute\":\"10.1227426528931\",\"lattitude\":\"36.8271066158298\"}]";
			if (result.length()!= 0 ) {
					try {					
						JSONArray jObject = new JSONArray(result);
						for (int j = 0; j < jObject.length(); j++) {
							  JSONObject menuObject = jObject.getJSONObject(j);
							  	// {"NomPharmacien":"Bounsnina Salah","longitute":"10.1689624786377","lattitude":"36.8901928679271"}							  	
							  	mMap.addMarker(new MarkerOptions()
							  		.position(new LatLng(
							  				menuObject.getDouble("lattitude") 
						  						, menuObject.getDouble("longitute")
							  					)
							  				).title(menuObject.getString("NomPharmacien")) 
							  			);							  	
						}					
					} catch (JSONException e) {
						Log.i("tag", "here is JSON   parse  exception  at Fill listgov.PostExecute"); 
						e.printStackTrace();
					}
			}
			
		 	//mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36,
//		             10), 12.0f));	
		 	 		 	
	    }
	

	@Override
	 public void onDetach() {
	     super.onDetach();
	     try {
	         Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
	         childFragmentManager.setAccessible(true);
	         childFragmentManager.set(this, null);
	     } catch (NoSuchFieldException e) {
	         throw new RuntimeException(e);
	     } catch (IllegalAccessException e) {
	         throw new RuntimeException(e);
	     }
	 }
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {	
		super.onActivityCreated(savedInstanceState);
		setUpMap();
	}
}
	
