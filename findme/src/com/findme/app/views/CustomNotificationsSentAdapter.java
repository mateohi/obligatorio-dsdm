package com.findme.app.views;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findme.R;
import com.findme.app.model.Notificacion;

public class CustomNotificationsSentAdapter extends ArrayAdapter<Notificacion> {

	Context contexto;
	List<Notificacion> notificaciones;
	int idDelLayout;

	public CustomNotificationsSentAdapter(Context pContexto, int pIdDelLayout,
			List<Notificacion> pNotificaciones) {
		super(pContexto, pIdDelLayout, pNotificaciones);
		this.contexto = pContexto;
		this.notificaciones = pNotificaciones;
		this.idDelLayout = pIdDelLayout;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ListViewItemHolder listViewHolder;
		View view = convertView;

		if (view == null) {
			LayoutInflater inflater = ((Activity) contexto).getLayoutInflater();
			listViewHolder = new ListViewItemHolder();

			view = inflater.inflate(idDelLayout, parent, false);
			listViewHolder.nombreMascota = (TextView) view
					.findViewById(R.id.pet_name);
			listViewHolder.nombreUsuarioInformante = (TextView) view
					.findViewById(R.id.user_found_name);
			listViewHolder.horaEncontrada = (TextView) view
					.findViewById(R.id.date_found);

			listViewHolder.fotoMascota = (ImageView) view
					.findViewById(R.id.foto_mascota);

			view.setTag(listViewHolder);

		} else {
			listViewHolder = (ListViewItemHolder) view.getTag();

		}

		Notificacion listItem = this.notificaciones.get(position);

		listViewHolder.nombreMascota.setText("Encontraste a "+ listItem.getMascota().getNombre());
		listViewHolder.nombreUsuarioInformante.setText("Has encontrado una mascota!");
		
		listViewHolder.horaEncontrada.setText(String.valueOf(listItem.getFecha().get(Calendar.HOUR))+":"+ listItem.getFecha().get(Calendar.MINUTE)+ " hs");
		//listViewHolder.fotoMascota.setImageDrawable(view.getResources()
		//		.getDrawable(
		//				Integer.parseInt(listItem.getMascota().getPathFoto())));
		return view;
	}

	private static class ListViewItemHolder {
		TextView nombreMascota;
		TextView nombreUsuarioInformante;
		TextView horaEncontrada;
		ImageView fotoMascota;
	}
}