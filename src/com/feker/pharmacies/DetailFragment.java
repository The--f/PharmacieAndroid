package com.feker.pharmacies;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.feker.pharmacies.webservice.RestClient;
import com.mustafaferhan.MFCalendarView;
import com.mustafaferhan.Util;

public class DetailFragment extends Fragment {
	
	

	private MFCalendarView mf;

	public DetailFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		RestClient RC = new RestClient();
		View rootView = inflater.inflate(R.layout.fragment_detail,
				container, false);
		mf = (MFCalendarView) rootView.findViewById(R.id.mFCalendarView);
		ArrayList<String> eventDays = new ArrayList<String>();
		ArrayList<NameValuePair> header = new ArrayList<NameValuePair>();
		header.add(new BasicNameValuePair("accept", "json"));
		try {
			RC.Execute(RestClient.RequestMethod.POST, "http://server.imsfirm.com/pharmacie/index.php/api/gardes/7", header,  null );		
			JSONArray jObject = new JSONArray(RC.response);
			for (int j = 0; j < jObject.length(); j++) {
				  JSONObject menuObject = jObject.getJSONObject(j);
				  eventDays.add(menuObject.getString("date"));
				  Toast.makeText(rootView.getContext(), menuObject.getString("date"), 2).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		mf.setEvents(eventDays);
		
		
		
		
		return rootView;
	}
}
