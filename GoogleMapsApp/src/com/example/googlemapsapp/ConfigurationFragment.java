package com.example.googlemapsapp;

import java.util.ArrayList;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigurationFragment extends Fragment {

	DisplayMapListener mcallback;
	public static TCPConnect client;
	public static String abc = "abc";
	Thread t;
	Handler h = new Handler();
	View rootView;
	ArrayAdapter<RowItems> adapter;
	ArrayList<RowItems> menu;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_config, container,
				false);
		ListView lv = (ListView) rootView.findViewById(R.id.listView1);
		//ListAdapter adp = lv.getAdapter();
		//adp.
		menu = new ArrayList<RowItems>();
		 //ArrayList<RowItems> menu = new ArrayList<RowItems>();
	        RowItems bat=new RowItems("BATTERY", null, null);
	        RowItems speed=new RowItems("SPEED", null, null);
	        RowItems acid=new RowItems("AIRCRAFT ID", null, null);
	        RowItems throttle=new RowItems("THROTTLE", null, null);
	        RowItems altitude=new RowItems("ALTITUDE", null, null);
	        RowItems flighttime=new RowItems("FLIGHT TIME", null, null);
	        menu.add(acid);
	        menu.add(speed);
	        menu.add(altitude);
	        menu.add(bat);
	        menu.add(throttle);
	        menu.add(flighttime);	 
	        adapter = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1, menu);
	        lv.setAdapter(adapter);
		final Button button1 = (Button) rootView.findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				SharedPreferences sharedPref = PreferenceManager
						.getDefaultSharedPreferences(getActivity());
				String ip = sharedPref
						.getString("pref_ipaddress_1", "notfound");
				String portTCP = sharedPref.getString("pref_portTCP_1",
						"notfound");
				String portUDP = sharedPref.getString("pref_portUDP_1",
						"notfound");
				UAVApplication u = (UAVApplication) getActivity()
						.getApplication();
				TCP_UDP_Connection b = u.connect(0,ip, portTCP, portUDP);
				ParseUdpString parseUdp = new ParseUdpString(getActivity(), h,
						b, mcallback);
				t = new Thread(parseUdp);
				t.start();

			}
		});

		final Button button2 = (Button) rootView.findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				SharedPreferences sharedPref = PreferenceManager
						.getDefaultSharedPreferences(getActivity());
				String ip = sharedPref
						.getString("pref_ipaddress_2", "notfound");
				String portTCP = sharedPref.getString("pref_portTCP_2",
						"notfound");
				String portUDP = sharedPref.getString("pref_portUDP_2",
						"notfound");
				UAVApplication u = (UAVApplication) getActivity()
						.getApplication();
				TCP_UDP_Connection b = u.connect(1,ip, portTCP, portUDP);
				ParseUdpString parseUdp = new ParseUdpString(getActivity(), h,
						b, mcallback);
				t = new Thread(parseUdp);
				t.start();

			}
		});
		return rootView;

	}

	public interface DisplayMapListener {
		//public void displaymap(float lat, float lng,String speed,int Ac_id, String alti, String throttle);
		
		public void displaymap(AircraftData data);
	}

	@Override
	public void onResume() {
		super.onResume();

		h.removeCallbacks(t);
		h.postDelayed(t, 0);

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mcallback = (DisplayMapListener) activity;
	}

	@Override
	public void onPause() {
		super.onPause();

		h.removeCallbacks(t);

	}

	public synchronized void ConfigTrace(AircraftData data) {
			//	.show();
		if(data.buttonId==0)
		{
			menu.get(1).setAc1(data.Speed+"m/s");
			menu.get(0).setAc1(String.valueOf(data.AC_Id));
			menu.get(4).setAc1(data.Throttle);
			menu.get(2).setAc1(data.Altitude);
			menu.get(3).setAc1(data.Battery+"V");
			menu.get(5).setAc1(data.FlightTime+"s");
		}
		else if(data.buttonId==1)
		{
			menu.get(1).setAc2(data.Speed+"m/s");
			menu.get(0).setAc2(String.valueOf(data.AC_Id));
			menu.get(4).setAc2(data.Throttle);
			menu.get(2).setAc2(data.Altitude);
			menu.get(3).setAc2(data.Battery+"V");
			menu.get(5).setAc2(data.FlightTime+"s");
		}
	     adapter.notifyDataSetChanged();
	     
		//TextView speedtxt = (TextView) rootView.findViewById(R.id.ac_speed_txt);
		//speedtxt.setText(speed);
		
	}
}
