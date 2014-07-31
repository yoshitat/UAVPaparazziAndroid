package com.example.googlemapsapp;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class UAVApplication extends Application {

	TCP_UDP_Connection b;
	Thread t;
	Handler h = new Handler();

	public TCP_UDP_Connection connect(int buttonNo,String ip, String portTCP, String portUDP) {
		try {
			b = new ConnectionAsync(this.getApplicationContext(), ip, portTCP,
					portUDP, buttonNo).execute().get();

		} catch (Exception e) {

		}
		return b;

	}

	public String messageSend() {
		return "";
	}

	public String messageReceive() {
		return "";
	}

}
