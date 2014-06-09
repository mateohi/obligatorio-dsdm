package com.findme.app.views;

import com.findme.app.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentNotifications extends Fragment {
	public FragmentNotifications() {

	}

	private FragmentTabHost tabHost;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		tabHost = new FragmentTabHost(getActivity());
		tabHost.setup(getActivity(), getChildFragmentManager(), R.layout.fragment_main_notifications);

		Bundle arg1 = new Bundle();
		arg1.putInt("Arg for Frag1", 1);
		tabHost.addTab(tabHost.newTabSpec("Tab1").setIndicator("Recibidas"),
				FragmentNotificationsReceived.class, arg1);

		Bundle arg2 = new Bundle();
		arg2.putInt("Arg for Frag2", 2);
		tabHost.addTab(tabHost.newTabSpec("Tab2").setIndicator("Enviadas"),
				FragmentNotificationsSent.class, arg2);

		return tabHost;
		}
}
