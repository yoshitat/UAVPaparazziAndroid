package com.example.googlemapsapp;

import java.util.concurrent.ExecutionException;

import com.example.googlemapsapp.ConfigurationFragment.DisplayMapListener;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class ParseUdpString extends Thread {

	DisplayMapListener mcallback;
	AircraftData data;
	TCP_UDP_Connection tcpudpClient;
	Handler handle;
	Context c;
	public boolean msgrecieved = false;

	public ParseUdpString(Context c, Handler handler,
			TCP_UDP_Connection tcpudp, DisplayMapListener mcallbackIn) {
		tcpudpClient = tcpudp;
		handle = handler;
		this.c = c;
		data = new AircraftData(tcpudpClient.buttonId);
		data.lat=43.462230;
		data.lng=1.272890;
		data.wp_changed_id=0;
		mcallback = mcallbackIn;
	}

	public void run() {
		Runnable ui = new RunnableUI(c, mcallback,data);
		int i = 0;

		handle.post(ui);
		while (true) {
			tcpudpClient.udpclient.readMessage();
			parseMessage(tcpudpClient.udpclient.receivedUDPData);
			handle.post(ui);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void parseMessage(String msg) {
		AircraftData ac;
		String[] message = msg.split(" ");
		if (message[1].equals("FLIGHT_PARAM")) {
			data.AC_Id = Integer.parseInt(message[2]);
			data.Roll = message[3];
			data.Pitch = message[4];
			data.Heading = message[5];
			data.lat = Float.parseFloat(message[6]);
			data.lng = Float.parseFloat(message[7]);
			data.Speed = message[8];
			data.Altitude = message[10];
			data.AirSpeed = message[15];
			data.changed_id=0;
		}
		
	/*	else if(message[1].equals("WAYPOINT_MOVED"))
		{
			data.wp_changed_id=Integer.parseInt(message[3]);
			data.wp_changed_lat=Double.parseDouble(message[4]);
			data.wp_changed_lng=Double.parseDouble(message[5]);
			data.changed_id= 1;
		}*/
		else if(message[1].equals("ENGINE_STATUS"))
		{
			data.Throttle=message[3];
			data.Battery=message[7];
			data.changed_id=2;
		}
		else if(message[1].equals("AP_STATUS"))
		{
			data.FlightTime=message[9];
			data.changed_id=2;
		}
		else
			data.changed_id=0;	
	}
}
