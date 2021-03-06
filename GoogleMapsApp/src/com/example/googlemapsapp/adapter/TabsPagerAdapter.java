package com.example.googlemapsapp.adapter;

import com.example.googlemapsapp.ConfigurationFragment;
import com.example.googlemapsapp.ExtraFragment;
import com.example.googlemapsapp.SimulatorFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter{

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int index) {
		// TODO Auto-generated method stub
		  switch (index) {
	        case 0:
	            // Top Rated fragment activity
	            return new SimulatorFragment();
	        case 1:
	            // Games fragment activity
	            return new ConfigurationFragment();
	      /*  case 2:
	            // Movies fragment activity
	            return new ExtraFragment();*/
	        }
	 
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

}
