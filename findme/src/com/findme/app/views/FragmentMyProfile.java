package com.findme.app.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.findme.R;
import com.example.findme.R.id;
import com.findme.app.model.Usuario;

public class FragmentMyProfile extends Fragment {

	private View parentView;
	private Usuario usuario;

	public FragmentMyProfile(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
		this.parentView = view;
		cargarDatos();
		return view;
	}

	private void cargarDatos() {
		if (usuario != null) {
			String nombre = usuario.getNombre();
			String apellido = usuario.getApellido();
			String correo = usuario.getCorreo();
			String celular = usuario.getCelular();

			((EditText) parentView.findViewById(id.profile_name)).setText(nombre);
			((EditText) parentView.findViewById(id.profile_last_name)).setText(apellido);
			((EditText) parentView.findViewById(id.profile_mail)).setText(correo);
			((EditText) parentView.findViewById(id.profile_mobile_phone)).setText(celular);
		}
	}
}