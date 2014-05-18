package com.feker.pharmacies;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends SupportMapFragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	 private GoogleMap mMap;

	public MapFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View rootView = this.getView();  
					 
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
         //   mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        	mMap = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)   ).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
		return V;
        
        
    }
	
	 private void setUpMap() {
		 
	        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker").flat(false));
	    }
	
}
