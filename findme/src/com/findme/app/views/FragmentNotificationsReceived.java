package com.findme.app.views;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.findme.R;
import com.findme.app.model.Mascota;
import com.findme.app.model.Notificacion;
import com.findme.app.model.Usuario;

public class FragmentNotificationsReceived extends Fragment {
	public FragmentNotificationsReceived() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_notifications_received,
				container, false);

		Usuario informante = new Usuario();
		informante.setNombre("Mario");

		Mascota mascota = new Mascota();
		mascota.setNombre("Lucy");
		mascota.setPathFoto("");

		Notificacion n1 = new Notificacion();
		n1.setMascota(mascota);
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.HOUR, 10);
		calendar.set(Calendar.MINUTE, 30);
		n1.setFecha(calendar);
		n1.setUsarioInformante(informante);

		Usuario informante2 = new Usuario();
		informante2.setNombre("Juan");

		Mascota mascota2 = new Mascota();
		mascota2.setNombre("Lucy");
		mascota2.setPathFoto("");

		Notificacion n2 = new Notificacion();
		n2.setMascota(mascota2);
		n2.setFecha(calendar);
		n2.setUsarioInformante(informante2);

		List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		notificaciones.add(n1);
		notificaciones.add(n2);

		ArrayAdapter<Notificacion> codeLearnArrayAdapter = new CustomNotificationsReceivedAdapter(
				getActivity(), R.layout.custom_listview_item, notificaciones);
		ListView listView = (ListView) view.findViewById(R.id.list_received);
		listView.setAdapter(codeLearnArrayAdapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				Notificacion selectedNotification = (Notificacion) parent.getItemAtPosition(position);
				MainActivity activity = (MainActivity)getActivity();
				activity.setDetailedNotificationsFragment(selectedNotification, true);
			}

		});

		return view;
	}
}
