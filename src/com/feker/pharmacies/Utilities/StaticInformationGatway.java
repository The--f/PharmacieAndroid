package com.feker.pharmacies.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.util.Log;

public class StaticInformationGatway {

	private static StaticInformationGatway instance = null;
	private String[] govlist = { "Ariana", "Béja", "Ben Arous", "Bizerte",
			"Gabès", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kébili",
			"Kef", "Mahdia", "Manouba", "Médenine", "Monastir", "Nabeul",
			"Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur",
			"Tunis", "Zaghouan" };
	public int[] govid = { 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
			41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51 };
	public HashMap<Integer, String> govcache = new HashMap<Integer, String>();
	public HashMap<String,ArrayList<String>> localcach=  new HashMap<String,ArrayList<String>>();
	public boolean finishloading = false ;

	private int selectedgov = 0;
	private int selectedlocalite = 0;

	public int getSelectedgov() {
		return selectedgov;
	}

	public void setSelectedgov(String selectedgov) {
		for( Entry<Integer, String> thisentry : govcache.entrySet() ){
            if(selectedgov.equals(thisentry.getValue())){ //breaking :  Key found
            	this.selectedgov = (Integer) thisentry.getKey();
            	break; 
            }
		}
	}

	public int getSelectedlocalite() {
		return selectedlocalite;
	}

	public void setSelectedlocalite(int selectedlocalite) {
		this.selectedlocalite = selectedlocalite;
	}

	private StaticInformationGatway() {
////		this.govcache = new HashMap<Integer, String>();
//		this.localcach = new HashMap<Integer, String[]>();
		for (int i = 0; i < govlist.length; i++) {
			govcache.put(govid[i], govlist[i]);			
		}
//		this.selectedlocalite = 0;
		this.selectedgov = govid[1];
	}

	public static synchronized StaticInformationGatway getInstance() {
		if (instance == null) {
			instance = new StaticInformationGatway();
		}
		return instance;
	}
	

	public ArrayList<String> getListeGouvernorats() {
		ArrayList<String> glist = new ArrayList<String>();
		for (Entry<Integer, String> entry : this.govcache.entrySet()) {
			glist.add(entry.getValue());
			Log.i("myTag", entry.getValue());
		}
		return glist;

	}

	/*RC Client Usage :
	 * ArrayList<NameValuePair> header = new ArrayList<NameValuePair>();
	 * header.add(new BasicNameValuePair("accept", "xml")); RestClient RC = new
	 * RestClient(); RC.Execute(RestClient.RequestMethod.GET,
	 * "http://server.imsfirm.com/pharmacie/index.php/api/listegouvernerat/",
	 * header,null ); String E = RC.response;
	 */
	public void setListLocalite(String GovName,  ArrayList<String> local) {
		this.localcach.put(GovName, local);
	}
	
	public ArrayList<String> getListepharmacie(int Num) {
		ArrayList<String> glist = new ArrayList<String>();
		for (int i = 1; i < Num; i++) {
			glist.add(Integer.toString(i));
		}
		return glist;
	}
	

	public ArrayList<String> getListeLocalite() {					
		String nomGov = govcache.get(this.selectedgov);
		Boolean cashHit = localcach.containsKey(nomGov);
		if (cashHit)
		{ // cash Hit :)										
			return localcach.get(nomGov);
		}else // cash fail :(
		{			 
			Integer GovID = null ; 
	        for( Entry<Integer, String> thisentry : govcache.entrySet() ){
	            if(nomGov.equals( thisentry.getValue())){ //breaking :  Key found
	                GovID = (Integer) thisentry.getKey(); break; 
                }
	        }				
			new FillSinglelocaliteCacheTask().execute(GovID);										
			return localcach.get(nomGov);						
		}		
	}
	
	
	
	
}//END CLASS 

	


/**
 * if (nomGov.equals(new String("Loading"))) 
			{	
				
			        if( localcach.containsKey(nomGov)) 
			        {
			        	glist.clear();			        	
						 for (String string : this.localcach.get(nomGov) ){glist.add(string);}
						return glist;	
			        }else {
			        	
			        }
				}else {
					return glist ;
				}
				
*/
