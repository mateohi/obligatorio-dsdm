package com.findme.app.views;

import java.io.FileNotFoundException;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.findme.app.R;
import com.findme.app.controller.DatabaseHandler;
import com.findme.app.model.Notificacion;
import com.findme.app.utils.ImageUtils;

public class FragmentDetailedNotification extends Fragment {

	private View parentView;
	private Notificacion notificacion;
	private boolean isReceivedNotification;

	public FragmentDetailedNotification() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		DatabaseHandler dh = new DatabaseHandler(getActivity()
				.getApplicationContext());
		isReceivedNotification = getArguments()
				.getInt("isReceivedNotification") == 1 ? true : false;
		if (isReceivedNotification) {
			notificacion = dh.getNotificacionRecibida(getArguments().getInt(
					"notificationId"));
		} else {
			notificacion = dh.getNotificacionEnviada(getArguments().getInt(
					"notificationId"));
		}

		View view = inflater.inflate(R.layout.fragment_detailed_notification,
				container, false);
		parentView = view;
		cargarDatos();
		setUpView();
		setButtonListener();
		return view;
	}

	private void setButtonListener() {
		Button buttonLocation = (Button) parentView
				.findViewById(R.id.button_location);
		buttonLocation.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				if (notificacion != null) {
					Uri geoLocationUri = Uri.parse("geo:0,0?q="
							+ notificacion.getLatitud() + ","
							+ notificacion.getLongitud());
					intent.setData(geoLocationUri);
				}

				if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
					startActivity(intent);
				}

			}
		});

	}

	private void setUpView() {
		if (!isReceivedNotification) {
			((Button) parentView.findViewById(R.id.button_location))
					.setVisibility(View.GONE);
			((TextView) parentView
					.findViewById(R.id.detailed_notification_info))
					.setText("Datos del dueño            ");
		}
	}

	public void cargarDatos() {
		if (notificacion != null) {
			Bitmap bm = devolverIconoGrande(notificacion.getNombreMascota());
			((ImageView) parentView.findViewById(R.id.my_pet_image_notification)).setImageBitmap(bm);
			((TextView) parentView
					.findViewById(R.id.my_pet_profile_name_notification))
					.setText("Se encontró a " + notificacion.getNombreMascota());
			((TextView) parentView
					.findViewById(R.id.detailed_notification_full_name))
					.setText(notificacion.getNombreUsuario() + " " + notificacion.getApellidoUsuario());
			((EditText) parentView.findViewById(R.id.detailed_notification_phone))
					.setText(notificacion.getCelular());
			((EditText) parentView.findViewById(R.id.detailed_notification_email))
					.setText(notificacion.getCorreo());
			((CheckBox) parentView.findViewById(R.id.checkBoxVacunada))
			.setChecked(notificacion.estaVacunada());
			((CheckBox) parentView.findViewById(R.id.checkBoxCuidado))
			.setChecked(notificacion.estaVacunada());

			((EditText) parentView.findViewById(R.id.detailed_notification_phone))
					.setKeyListener(null);
			((EditText) parentView.findViewById(R.id.detailed_notification_email))
					.setKeyListener(null);

		}
	}

	private Bitmap devolverIconoGrande(String nombreMascota) {

		String path = (this.isReceivedNotification ? "" : "N-") + nombreMascota;

		try {
			Bitmap fotoMascota = ImageUtils.getCircleBitmapFromDevice(path,
					this.parentView.getContext());
			return fotoMascota;
		} catch (FileNotFoundException e) {
			Bitmap iconoFindMe = BitmapFactory.decodeResource(this.parentView
					.getContext().getResources(), R.drawable.ic_launcher);
			return iconoFindMe;
		}
	}
}
