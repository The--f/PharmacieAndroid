package com.feker.pharmacies;

import java.util.ArrayList;

import com.feker.pharmacies.Utilities.StaticInformationGatway;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author Feker
 *
 */
public class PharmacielistFragment extends Fragment {

	/**
	 * The fragment argument representing the governerat (number or name) for this
	 * fragment.
	 */		
	public static final String ARG_localite_NUMBER = "0";
	
	public PharmacielistFragment() {		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_locallist,container, false);
		int nb  =( Integer.parseInt(ARG_localite_NUMBER) == 0 ? 0 : Integer.parseInt(ARG_localite_NUMBER));
		 ArrayList<String> glist = StaticInformationGatway.getInstance().getListepharmacie(4);		 
		 ListView LVgov = (ListView)rootView.findViewById(android.R.id.list);			 
		 ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getActivity().getApplicationContext(), android.R.layout.simple_list_item_1,glist );
         LVgov.setAdapter(arrayAdapter); 	
		return rootView;
	}
}
