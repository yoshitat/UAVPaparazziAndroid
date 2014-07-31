package com.example.googlemapsapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Toast;

public class ConnectionAsync extends AsyncTask<Void, String, TCP_UDP_Connection> {

	TCPConnect tcpClient;
	UDPConnect udpClient;
	String ip;
	String TCPport;
	String UDPport;
	Context c;
	Boolean b1,b;
	String str;
	AircraftData data;
	int index;
	public ConnectionAsync(Context c,String ip, String tcpport, String udpport, int ind)
	{
		this.ip=ip;
		this.TCPport=tcpport;
		UDPport=udpport;
		this.c=c;
		//data=new AircraftData(ind);
		index=ind;
	}
	@Override
	protected TCP_UDP_Connection doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		 tcpClient = new TCPConnect();
		 udpClient = new UDPConnect();
	    tcpClient.SERVERIP =ip;//"149.125.101.9";
	    tcpClient.SERVERPORT =Integer.parseInt(TCPport);// 5010;
	    udpClient.UdpListenPort=Integer.parseInt(UDPport);
	    b = tcpClient.setupConnection();
	    b1 = udpClient.setupConnection();
	    TCP_UDP_Connection tcpudp=new TCP_UDP_Connection(tcpClient,udpClient, index);
	    return tcpudp;
	}
	protected void onProgressUpdate(String... params)
	{
		//Toast.makeText(c,"tcp"+params[0], Toast.LENGTH_SHORT).show();

	}
	protected void onPostExecute (UDPConnect result)
	{
		//Toast.makeText(c,"connected "+ b.toString() +index, Toast.LENGTH_SHORT).show();
	}
	
	public void parseMessage(String msg){
		AircraftData ac;
		String[] message = msg.split(" ");
		if(message[1].equals("FLIGHT_PARAM")){
			data.AC_Id = Integer.parseInt(message[2]);
			data.Roll = message[3];
			data.Pitch = message[4];
			data.Heading = message[5];
			data.lat = Float.parseFloat(message[6]);
			data.lng = Float.parseFloat(message[7]);
			data.Speed = message[8];
			data.Altitude = message[10];
			data.AirSpeed = message[15];
			
		}
		
	}
}