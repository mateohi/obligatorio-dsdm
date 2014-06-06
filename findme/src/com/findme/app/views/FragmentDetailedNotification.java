package com.findme.app.views;

import com.example.findme.R;
import android.support.v4.app.Fragment;
import android.app.Notification;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentDetailedNotification extends Fragment {

	public FragmentDetailedNotification() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_detailed_notification,
				container, false);

		return view;
	}
}
