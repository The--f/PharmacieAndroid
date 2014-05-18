package com.feker.pharmacies;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.feker.pharmacies.Utilities.StaticInformationGatway;

/**
 * @author Feker
 *
 */
public class LocalitelistFragment extends ListFragment {

	/**
	 * The fragment argument representing the governerat (number or name) for this
	 * fragment.
	 */			
	
	public LocalitelistFragment( ) {
	}	


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
    	 View rootView = inflater.inflate(R.layout.fragment_locallist,container, false);    	 
//    	 String g = StaticInformationGatway.getInstance().govcache.get(StaticInformationGatway.getInstance().getSelectedgov());
//		 Toast.makeText(getActivity(),"oncreate"+g, 3).show();			 		 
		 ArrayList<String> glist = new ArrayList<String>() ;//  StaticInformationGatway.getInstance().getListeLocalite(g);		 
		 ListView ListLoc = (ListView)rootView.findViewById(android.R.id.list);			 
		 ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getActivity().getApplicationContext(), android.R.layout.simple_list_item_activated_1,glist );
         ListLoc.setAdapter(arrayAdapter);         
		return rootView;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);		
		
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		ListView ListLoc = this.getListView();
//		ArrayList<String> glist = StaticInformationGatway.getInstance().getListeLocalite();
//		if (glist.isEmpty()) {
//			setEmptyText("Still Loading ");			
//		}else {
//		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getActivity().getApplicationContext(), android.R.layout.simple_list_item_activated_1,glist );
//		ListLoc.setAdapter(arrayAdapter);		 
//		arrayAdapter.notifyDataSetChanged();
//		}
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);		 
	}
	
	
	
}
