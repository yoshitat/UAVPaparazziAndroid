package com.example.googlemapsapp;

import android.os.AsyncTask;

public class ReadMsgAsync extends AsyncTask<Void, Float, String> {

	UDPConnect udpClient;

	public ReadMsgAsync(UDPConnect udpClientIn) {
		udpClient = udpClientIn;
	}

	@Override
	protected String doInBackground(Void... params) {

		udpClient.readMessage();
		return udpClient.receivedUDPData;
	}

}
