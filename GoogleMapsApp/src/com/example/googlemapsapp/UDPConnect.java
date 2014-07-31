package com.example.googlemapsapp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import android.util.Log;

public class UDPConnect implements ConnectionType{
	
	  private DatagramPacket packet;
	  private DatagramSocket socket = null;
	  public int UdpListenPort; 	
	  public boolean UDPConnedted=false;
	  public String receivedUDPData;
	  public String receivedUDPData_old=null;
	  public boolean isSet=false;
	  
	  public boolean setupConnection(){
		   try
		    {
		      socket = new DatagramSocket(this.UdpListenPort);
		     // socket.setSoTimeout(150);
		      byte[] arrayOfByte = new byte[1024];
		      packet = new DatagramPacket(arrayOfByte, arrayOfByte.length);
		      UDPConnedted = true;
		      
		    }
		    catch (SocketException localSocketException)
		    {
		    	UDPConnedted = false;		    	
		    	localSocketException.printStackTrace();

		    }
		   return this.UDPConnedted;
		   
	  }
	  
	  public String readMessage()
	  {
	      try
	      {
	      
	    	  //if(socket == null){
	    	//	  setupConnection();
	    	//  }
	      this.socket.receive(this.packet);
	      this.receivedUDPData = new String(packet.getData(), 0, packet.getLength());
	      //isSet=true;
	      if ((this.receivedUDPData != null) && (!this.receivedUDPData.equals(this.receivedUDPData_old)))
	      {
	        this.receivedUDPData_old = this.receivedUDPData;
	        //parse_udp_string(this.receivedUDPData);
	      }
	      return this.receivedUDPData;
	      //return "no exception";
	      
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	      return "in exception";
	    }
	    
	    
	    //return "after exception";
	  }
	
}
