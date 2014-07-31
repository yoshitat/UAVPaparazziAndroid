package com.example.googlemapsapp;

import java.util.concurrent.ExecutionException;

import com.example.googlemapsapp.adapter.TabsPagerAdapter;

//import com.example.tabtest.adapter.TabsPagerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener, ConfigurationFragment.DisplayMapListener {

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	public final static String EXTRA_IPAddress = "com.example.googlemapsapp.IPAdress";
	public final static String EXTRA_PortNo = "com.example.googlemapsapp.PortNo";
	private String[] tabs = { "Simulator", "Configurations" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@SuppressLint("NewApi")
			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_setting:
			openSetting();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void openSetting() {

		Intent intent = new Intent(this, SettingActivity.class);
		startActivity(intent);
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {

	}

	//float latIn, float lngIn, String speed, int Ac_id, String alti, String throttle
	@Override
	public void displaymap(AircraftData data) {
		// Toast.makeText(this,"in displaymap", Toast.LENGTH_SHORT).show();
		// SimulatorFragment simulator =
		// (SimulatorFragment)getSupportFragmentManager().findFragmentById(R.id.simu);
		SimulatorFragment simulator = (SimulatorFragment) getSupportFragmentManager()
				.getFragments().get(0);
		ConfigurationFragment config = (ConfigurationFragment) getSupportFragmentManager()
				.getFragments().get(1);
		if(data.wp_changed_id!=2)
			if(simulator!=null)
				simulator.Trace(data);
			if(config!=null)
				config.ConfigTrace(data);
		
		

	}

}