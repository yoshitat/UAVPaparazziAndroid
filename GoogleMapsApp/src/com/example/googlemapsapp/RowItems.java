package com.example.googlemapsapp;

public class RowItems {

	String descripter=null;
	String ac1=null;
	String ac2=null;
	
	public String getAc1() {
		return ac1;
	}

	public void setAc1(String ac1) {
		this.ac1 = ac1;
	}

	public String getAc2() {
		return ac2;
	}

	public void setAc2(String ac2) {
		this.ac2 = ac2;
	}

	public RowItems(String desp, String a1, String a2){
		descripter=desp;
		ac1=a1;
		ac2=a2;
	}
	
	@Override
	public String toString(){
		
		return "\n"+descripter+"\n"+"aircraft 1: " +ac1+"\naircraft 2: "+ac2;
	}
}
