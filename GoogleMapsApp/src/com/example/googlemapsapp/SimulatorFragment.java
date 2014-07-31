package com.example.googlemapsapp;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Toast;

public class SimulatorFragment extends Fragment {

	public static TCPConnect client;
	public static double lat;
	public static double lng;
	public static SupportMapFragment Fmap;
	public static GoogleMap map;
	public static PolylineOptions opt0 = new PolylineOptions();
	public static PolylineOptions opt1 = new PolylineOptions();
	public static Marker[] marker=new Marker[2];

	 Marker[] waypoint= new Marker[11];
	 String[] wp_names=new String[11];
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_simulator,
				container, false);
		this.client = ConfigurationFragment.client;
		// Toast.makeText(getActivity(),
		// ConfigurationFragment.abc,Toast.LENGTH_LONG).show();
		if (this.client != null)
			Toast.makeText(getActivity(), this.client.SERVERIP,
					Toast.LENGTH_LONG).show();
		FragmentManager myFM = getActivity().getSupportFragmentManager();

		Fmap = (SupportMapFragment) myFM.findFragmentById(R.id.map1);
		map = Fmap.getMap();
		Marker wp_Climb = map.addMarker(new MarkerOptions()
		.position(new LatLng(43.463669, 1.271434))
		.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.waypoint_ic)).flat(true).title("Climb"));
		wp_Climb.showInfoWindow();
		Marker wp_s2 = map.addMarker(new MarkerOptions()
		.position(new LatLng(43.464167, 1.276227))
		.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.waypoint_ic)).flat(true).title("S2"));
		
		wp_s2.showInfoWindow();
		
		Marker wp_s1 = map.addMarker(new MarkerOptions()
		.position(new LatLng(43.462834, 1.271399))
		.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.waypoint_ic)).flat(true).title("S1"));
		wp_s1.showInfoWindow();
		
		Marker STBY = map.addMarker(new MarkerOptions()
		.position(new LatLng(43.463140, 1.273476))
		.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.waypoint_ic)).flat(true).title("STBY"));
		STBY.showInfoWindow();
		
		Marker Home = map.addMarker(new MarkerOptions()
		.position(new LatLng(43.462230, 1.272890))
		.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.waypoint_ic)).flat(true).title("Home"));
		
		Home.showInfoWindow();
		Marker TD = map.addMarker(new MarkerOptions()
		.position(new LatLng(43.462748, 1.273231))
		.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.waypoint_ic)).flat(true).title("TD"));
		
		TD.showInfoWindow();
		
		Marker MOB = map.addMarker(new MarkerOptions()
		.position(new LatLng(43.462151, 1.274586))
		.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.waypoint_ic)).flat(true).title("MOB"));
		MOB.showInfoWindow();
		Marker wp_1 = map.addMarker(new MarkerOptions()
		.position(new LatLng(43.463941, 1.272966))
		.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.waypoint_ic)).flat(true).title("1"));
		wp_1.showInfoWindow();
		
		Marker wp_2 = map.addMarker(new MarkerOptions()
		.position(new LatLng(43.463507, 1.274489))
		.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.waypoint_ic)).flat(true).title("2"));
		
		wp_2.showInfoWindow();
		Marker AF = map.addMarker(new MarkerOptions()
		.position(new LatLng(43.462669, 1.275070))
		.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.waypoint_ic)).flat(true).title("AF"));
		AF.showInfoWindow();
		Marker[] wp= {Home,STBY,wp_1,wp_2,MOB,wp_s1,wp_s2,wp_Climb};
		
		waypoint[0]=Home;
		waypoint[1]=STBY;
		waypoint[2]=wp_1;
		waypoint[3]=wp_2;
		waypoint[4]=MOB;
		waypoint[5]=wp_s1;
		waypoint[6]=wp_s2;
		waypoint[7]=AF;
		waypoint[8]=TD;
		waypoint[10]=wp_Climb;
		wp_names[0]="Home";
		wp_names[1]="STBY";
		wp_names[2]="1";
		wp_names[3]="2";
		wp_names[4]="MOB";
		wp_names[5]="s1";
		wp_names[6]="s2";
		wp_names[7]="AF";
		wp_names[8]="TD";
		wp_names[10]="Climb";
		
		marker[0] = map.addMarker(new MarkerOptions()
		.position(new LatLng(43.462230, 1.272890))
		.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.ic_aircraft)).flat(true));
		Polyline line = map.addPolyline(opt0.add(new LatLng(43.462230, 1.272890)).width(5)
				.color(Color.BLUE).geodesic(true));
		
		marker[1] = map.addMarker(new MarkerOptions()
		.position(new LatLng(43.462230, 1.272890))
		.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.ic_aircraft)).flat(true));
		Polyline line1 = map.addPolyline(opt1.add(new LatLng(43.462230, 1.272890)).width(5)
				.color(Color.GREEN).geodesic(true));
		return rootView;

	}

	//float latIn, float lngIn, String speed
	public synchronized void Trace(AircraftData data) {
		if(data.changed_id==0)
		{
		//Toast.makeText(getActivity(), "id0: "+String.valueOf(data.lat), Toast.LENGTH_SHORT)
				//.show();
		if (marker[data.buttonId] != null)
			marker[data.buttonId].remove();
		lat = data.lat;
		lng = data.lng;
		marker[data.buttonId] = map.addMarker(new MarkerOptions()
				.position(new LatLng(lat, lng))
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.ic_aircraft)).flat(true));

		CameraPosition camPos = new CameraPosition.Builder()
				.target(new LatLng(lat, lng)).zoom(17)
				.build();

		CameraUpdate cu = CameraUpdateFactory.newCameraPosition(camPos);
		map.animateCamera(cu);
		if(data.buttonId==0)
		{
		Polyline line = map.addPolyline(opt0.add(new LatLng(lat, lng)).width(5)
				.color(Color.BLUE).geodesic(true));
		}else
		{
			Polyline line = map.addPolyline(opt1.add(new LatLng(lat, lng)).width(5)
					.color(Color.GREEN).geodesic(true));
				
		}
		}
		else if(data.changed_id==1)
		{
		//	Toast.makeText(getActivity(), "moved_wp: " + String.valueOf(data.wp_changed_id), Toast.LENGTH_SHORT);
			if(data.wp_changed_id<11 && data.wp_changed_id!=9 )
			{
				waypoint[data.wp_changed_id].remove();
			waypoint[data.wp_changed_id]=map.addMarker(new MarkerOptions()
			.position(new LatLng(data.wp_changed_lat,data.wp_changed_lng))
			.icon(BitmapDescriptorFactory
					.fromResource(R.drawable.waypoint_ic)).flat(true).title(wp_names[data.wp_changed_id]));
			
			waypoint[data.wp_changed_id].showInfoWindow();
			}
		}
	}
}
