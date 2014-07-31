package com.example.googlemapsapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TCPConnect implements ConnectionType {
	 String ReceivedMsg = null;
	  public String SERVERIP;
	  public int SERVERPORT;
	  public boolean TCPConnected = false;
	  BufferedReader in;
	  PrintWriter out;
	  private InetAddress serverAddr = null;
	  String SendToTcp = null;
	  private Socket socket;
	 
	  public String readMessage()
	  {
	    if (!this.TCPConnected)
	    {
	    	setupConnection();
	    	return null;
	    }
	    try
	    {
	      //if (this.in.ready()) {}
	      this.ReceivedMsg = this.in.readLine();
	      socket.close();
	      return this.ReceivedMsg;
	     
	    }
	    catch (IOException localIOException)
	    {
	      //for (;;)
	      //{
	    	try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        this.TCPConnected = false;
	        //Log.d("PPRZ_info", "TCP comm problem");
	        return null;
	      //}
	    }
	  }
	  
	  public void sendMessage(String paramString)
	  {
	    if (!this.TCPConnected) {
	    	setupConnection();
	    }
	    if ((this.out != null) && (!this.out.checkError()))
	    {
	      this.out.println(paramString);
	      this.out.flush();
	    }
	  }
	  
	  public boolean setupConnection()
	  {
	    try
	    {  
	      this.serverAddr = InetAddress.getByName(this.SERVERIP);
	      this.socket = new Socket(this.serverAddr, this.SERVERPORT);
	      this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())), true);
	      this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
	      this.TCPConnected = true;
	      return this.TCPConnected;
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	      this.TCPConnected = false;
	    }
	    return this.TCPConnected;
	  }

}
