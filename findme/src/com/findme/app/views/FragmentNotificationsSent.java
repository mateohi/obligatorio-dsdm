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

public class FragmentNotificationsSent extends Fragment {
	public FragmentNotificationsSent() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_notifications_sent,
				container, false);
		DatabaseHandler dh = new DatabaseHandler(getActivity()
				.getApplicationContext());

		Notificacion n1 = new Notificacion();
		n1.setNombreUsuario("Alberto");
		n1.setApellidoUsuario("Mostaza");
		n1.setNombreMascota("Lucy");
		n1.setCelular("094096444");
		n1.setCorreo("a.mostaza@outlook.com");
		n1.setFecha(new Date());
		n1.setLatitud("1");
		n1.setLongitud("2");
		n1.setFotoBase64("");
		n1.setPathFoto("");

		dh.addNotificacionEnviada(n1);

		List<Notificacion> notificacionesRecibidas = dh
				.getNotificacionesEnviadas();

		ArrayAdapter<Notificacion> adapter = new CustomNotificationsSentAdapter(
				getActivity(), R.layout.custom_listview_item,
				notificacionesRecibidas);
		ListView listView = (ListView) view.findViewById(R.id.list_sent);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				Notificacion selectedNotification = (Notificacion) parent
						.getItemAtPosition(position);
				MainActivity activity = (MainActivity) getActivity();
				activity.setDetailedNotificationsFragment(selectedNotification.getId(), false);
			}

		});
		
		return view;
	}
}
