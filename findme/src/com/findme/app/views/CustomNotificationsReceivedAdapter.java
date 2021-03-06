package com.findme.app.views;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.findme.app.R;
import com.findme.app.controller.DatabaseHandler;
import com.findme.app.model.Notificacion;
import com.findme.app.utils.ImageUtils;

public class CustomNotificationsReceivedAdapter extends
		ArrayAdapter<Notificacion> {

	Context contexto;
	List<Notificacion> notificaciones;
	int idDelLayout;

	public CustomNotificationsReceivedAdapter(Context pContexto,
			int pIdDelLayout, List<Notificacion> pNotificaciones) {
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

		listViewHolder.nombreMascota.setText("Encontraron a "
				+ listItem.getNombreMascota());
		listViewHolder.nombreUsuarioInformante.setText(listItem
				.getNombreUsuario() + " ha encontrado a la mascota");
		listViewHolder.horaEncontrada.setText(setTime(listItem));
		Bitmap fotoMascota = devolverIconoGrande(listItem.getNombreMascota());
		listViewHolder.fotoMascota.setImageBitmap(fotoMascota);
		return view;
	}

	public String setTime(Notificacion notificacion) {
		String date = "";
		Date fechaNotificacion = notificacion.getFecha();
		Date fechaHoy = new Date();
		if (Notificacion.DATE.format(fechaNotificacion).equals(
				Notificacion.DATE.format(fechaHoy))) {
			date = Notificacion.TIME.format(fechaNotificacion);
		} else {
			date = Notificacion.DATE.format(fechaNotificacion);
		}

		return date;
	}

	private static class ListViewItemHolder {
		TextView nombreMascota;
		TextView nombreUsuarioInformante;
		TextView horaEncontrada;
		ImageView fotoMascota;
	}

	private Bitmap devolverIconoGrande(String path) {

		try {
			Bitmap fotoMascota = ImageUtils
					.getCircleBitmapFromDevice(path, this.contexto);
			return fotoMascota;
		} catch (FileNotFoundException e) {
			Bitmap iconoFindMe = BitmapFactory.decodeResource(
					this.contexto.getResources(), R.drawable.ic_launcher);
			return iconoFindMe;
		}
	}
}
