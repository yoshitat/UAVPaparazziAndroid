package com.example.googlemapsapp;

import com.example.googlemapsapp.ConfigurationFragment.DisplayMapListener;

import android.content.Context;
import android.widget.Toast;

public class RunnableUI implements Runnable {

	float lat;
	float lng;
	String speed;
	int ac_id;
	String alti;
	String throttle;
	int flag=0;
	String msg;
	Context c;
	String s;
	AircraftData data;
	DisplayMapListener mcallback;
	
	public RunnableUI(Context cIn,DisplayMapListener mcallbackIn, AircraftData dataIn)
	{
		c=cIn;
		mcallback=mcallbackIn;
		data=dataIn;
	}
	
	public void setString(String str)
	{
		s=str;
	}
	/*public void setCoord(String str,float latIn, float latLng, String speedIn, int acidIn, String altiIn, String throttleIn)
	{
		
		msg=str;
		lat=latIn;
		lng=latLng;
		speed = speedIn;
		ac_id = acidIn;
		alti = altiIn;
		throttle = throttleIn;
	}*/
	@Override
	public void run() {
		//Toast.makeText(c,msg, Toast.LENGTH_SHORT).show();
			if(mcallback!=null)
    		{
				mcallback.displaymap(data);
    		}
    }
}

