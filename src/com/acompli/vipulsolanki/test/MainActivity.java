package com.acompli.vipulsolanki.test;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class MainActivity extends FragmentActivity implements 
			TabListener, ViewPager.OnPageChangeListener, WebviewFragment.DataChangeListener {

	private static final String TAG = "ACOMPLI";
	
	WebviewFragment mWebviewFragment;
	ListFragment mListFragment;
	
	ViewPager mViewPager;
	ActionBar mActionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mWebviewFragment = new WebviewFragment();
		mListFragment = new ListFragment(); 
		mWebviewFragment.setDataChangeListener(this);
		
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));
		mViewPager.setOnPageChangeListener(this);
	    
		mActionBar = getActionBar();
	    mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    mActionBar.addTab(mActionBar.newTab().setText("Webview").setTabListener(this));
	    mActionBar.addTab(mActionBar.newTab().setText("List").setTabListener(this));
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int page) {
		mActionBar.selectTab(mActionBar.getTabAt(page));
		
	}

	@Override
	public void onDataChanged() {
		if (mListFragment != null) {
			mListFragment.onDataChanged();
		}
		
	}


	class TabAdapter extends FragmentPagerAdapter {

		public TabAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return mWebviewFragment;
			case 1:
				return mListFragment;

			default:
				Log.wtf(TAG, "Which fragment to create? Should never happen.");
				break;
			}
			
			return null;
		}

		@Override
		public int getCount() {
			
			return 2;
		}
		
	}


}
