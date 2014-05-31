package com.findme.app.views;

import utils.EmailValidator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findme.R;
import com.example.findme.R.id;
import com.findme.app.controller.DatabaseHandler;
import com.findme.app.controller.integration.UserServiceClient;
import com.findme.app.model.Usuario;

public class FragmentMyProfile extends Fragment {
	public FragmentMyProfile() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_my_profile, container,
				false);

		return view;
	}

	public void saveProfile(View view) {
		String nombre = ((EditText) view.findViewById(id.my_profile_name))
				.getText().toString().trim();
		String apellido = ((EditText) view
				.findViewById(id.my_profile_last_name)).getText().toString().trim();
		String correo = ((EditText) view.findViewById(id.my_profile_mail))
				.getText().toString().trim();
		String celular = ((EditText) view
				.findViewById(id.my_profile_mobile_phone)).getText().toString().trim();

		String validaciones = validar(nombre, apellido, correo, celular);
		if (validaciones.isEmpty()) {
			Usuario usuario = new Usuario();
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setCorreo(correo);
			usuario.setCelular(celular);

			DatabaseHandler handler = new DatabaseHandler(this.getActivity()
					.getApplicationContext());
			handler.agregarUsuario(usuario);

			String serviceResponse = UserServiceClient.instance().postUser(usuario);
			
			if (serviceResponse.isEmpty()) {
				Toast.makeText(null, "Usuario guardado", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(null, serviceResponse, Toast.LENGTH_SHORT).show();
			}

			
		} else {
			Toast.makeText(null, validaciones, Toast.LENGTH_SHORT).show();
		}
	}

	private String validar(String nombre, String apellido, String correo,
			String celular) {
		StringBuilder validaciones = new StringBuilder();
		
		if (nombre == "") {
			validaciones.append("Nombre no puede ser vacio\n");
		}
		if (apellido == "") {
			validaciones.append("Apellido no puede ser vacio\n");
		}
		if (correo == "") {
			validaciones.append("Nombre no puede ser vacio\n");
		}
		else if (!EmailValidator.valid(correo)){
			validaciones.append("Correo no valido\n");
		}
		if (celular == "") {
			validaciones.append("Nombre no puede ser vacio\n");
		}
		
		if (validaciones.length() > 0) {
			validaciones.deleteCharAt(validaciones.length());
		}
		
		return validaciones.toString();
	}
}