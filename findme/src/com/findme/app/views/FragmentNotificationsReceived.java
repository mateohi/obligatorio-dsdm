package com.findme.app.views;

import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.findme.R;
import com.findme.app.controller.DatabaseHandler;
import com.findme.app.model.Notificacion;

public class FragmentNotificationsReceived extends Fragment {
	public FragmentNotificationsReceived() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_notifications_received,
				container, false);

		DatabaseHandler dh = new DatabaseHandler(getActivity()
				.getApplicationContext());
		
		List<Notificacion> notificacionesRecibidas = dh
				.getNotificacionesRecibidas();

		
		
		ArrayAdapter<Notificacion> codeLearnArrayAdapter = new CustomNotificationsReceivedAdapter(
				getActivity(), R.layout.custom_listview_item,
				notificacionesRecibidas);
		ListView listView = (ListView) view.findViewById(R.id.list_received);
		listView.setAdapter(codeLearnArrayAdapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				Notificacion selectedNotification = (Notificacion) parent
						.getItemAtPosition(position);
				MainActivity activity = (MainActivity) getActivity();
				activity.setDetailedNotificationsFragment(selectedNotification.getId(), true);
			}

		});

		return view;
	}
}
