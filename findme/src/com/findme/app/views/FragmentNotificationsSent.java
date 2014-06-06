package com.findme.app.views;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.example.findme.R;
import com.findme.app.model.Mascota;
import com.findme.app.model.Notificacion;
import com.findme.app.model.Usuario;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentNotificationsSent extends Fragment {
	public FragmentNotificationsSent() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_notifications_sent,
				container, false);

		Usuario informante = new Usuario();
		informante.setNombre("Mario");

		Mascota mascota = new Mascota();
		mascota.setNombre("Tornado");
		mascota.setPathFoto("");

		Notificacion n1 = new Notificacion();
		n1.setMascota(mascota);
		Calendar calendar = new GregorianCalendar(2013, 1, 28, 13, 24, 56);
		n1.setFecha(calendar);
		n1.setUsarioInformante(informante);

		Usuario informante2 = new Usuario();
		informante2.setNombre("Juan");

		Mascota mascota2 = new Mascota();
		mascota2.setNombre("Juancho");
		mascota2.setPathFoto("");

		Notificacion n2 = new Notificacion();
		n2.setMascota(mascota2);
		n2.setFecha(calendar);
		n2.setUsarioInformante(informante2);

		List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		notificaciones.add(n1);
		notificaciones.add(n2);

		ArrayAdapter<Notificacion> codeLearnArrayAdapter = new CustomNotificationsSentAdapter(
				getActivity(), R.layout.custom_listview_item, notificaciones);
		ListView lv = (ListView) view.findViewById(R.id.list_sent);
		lv.setAdapter(codeLearnArrayAdapter);
		
		return view;
	}
}
