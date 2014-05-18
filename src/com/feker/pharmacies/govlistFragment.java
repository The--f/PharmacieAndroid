package com.feker.pharmacies;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
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
public class govlistFragment extends ListFragment {

	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */		
	public govlistFragment() {		
	}

	public boolean onCreateOptionsMenu(Menu menu){
		
		this.getActivity().getMenuInflater().inflate(R.menu.menu_tab, menu);
		return true ;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_locallist,
				container, false);	
		 ListView LVgov = (ListView)rootView.findViewById(android.R.id.list);			 		
		 ArrayList<String> glist = StaticInformationGatway.getInstance().getListeGouvernorats();		 
		 ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getActivity().getApplicationContext(), android.R.layout.simple_list_item_activated_1 ,glist);
         LVgov.setAdapter(arrayAdapter); 
         arrayAdapter.notifyDataSetChanged();
		 return rootView;
	}
	
	@Override
	public void onListItemClick (ListView l, View v, int position, long id){
		
		StaticInformationGatway.getInstance().setSelectedgov(l.getItemAtPosition(position).toString());		
		StaticInformationGatway.getInstance().getListeLocalite();
		//Toast.makeText(v.getContext(), "this id = "+ id + " position "+ position + "name"+ l.getItemAtPosition(position).toString(), 3).show();
	    (this.getActivity()).getActionBar().setSelectedNavigationItem(2);
//	    ListFragment localfrag = (ListFragment) this.getFragmentManager().findFragmentById(R.id.LocalListFragment);
//	    localfrag.getListView().invalidateViews();
	}
	
	
	@Override
	public void onResume( ) {
		// TODO Auto-generated method stub
		super.onResume();
		Toast.makeText(getActivity(), "RESUME Listgov", 2).show();	
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		Toast.makeText(getActivity(), "On view ceated Listgov", 2).show();
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		Toast.makeText(getActivity(), "On view Attach Listgov", 2).show();
	}
		
}

