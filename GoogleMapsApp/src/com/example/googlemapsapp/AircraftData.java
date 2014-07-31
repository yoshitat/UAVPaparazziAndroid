package com.example.googlemapsapp;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

public class AircraftData {
	int buttonId;
    String AC_Color;
    int changed_id;
    boolean AC_Enabled = false;
    int AC_Id;
    String AC_Name;
    int wp_changed_id;
    double wp_changed_lat;
    double wp_changed_lng;
    double lat;
    double lng;
    boolean AC_Position_Changed = false;
    String AC_Type;
    String AirSpeed = "N/A";
    String Altitude;
    boolean Altitude_Changed = false;
    String Battery;
    String FlightTime;
    String Heading = "0";
    int NumbOfWps = 11;
    String Pitch = "0";
    LatLng Position;
    String Roll = "0";
    String Speed;
    String Throttle;
	
	public AircraftData(int buttonNo){
		buttonId=buttonNo;
	}
}



