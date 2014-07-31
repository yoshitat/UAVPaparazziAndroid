package com.example.googlemapsapp;

public class TCP_UDP_Connection {
	
	public TCPConnect tcpclient;
	public UDPConnect udpclient;
	public int buttonId;
	
	public TCP_UDP_Connection(TCPConnect tcpclientIn,UDPConnect udpclientIn, int buttonNo)
	{
		tcpclient=tcpclientIn;
		udpclient=udpclientIn;
		buttonId=buttonNo;
	}

}
