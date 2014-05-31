package com.findme.app.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.findme.R;
import com.example.findme.R.id;
import com.findme.app.controller.DatabaseHandler;
import com.findme.app.controller.integration.PetServiceClient;
import com.findme.app.model.Mascota;

public class FragmentPetProfile extends Fragment {
	public FragmentPetProfile() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater
				.inflate(R.layout.fragment_my_pet, container, false);

		return view;
	}

	public void saveProfile(View view) {
		String nombre = ((EditText) view.findViewById(id.my_pet_name))
				.getText().toString().trim();
		boolean estaVacunada = ((Switch) view.findViewById(id.switch_vacunada))
				.isChecked();
		boolean tenerCuidado = ((Switch) view.findViewById(id.switch_cuidado))
				.isChecked();

		Mascota mascota = new Mascota();
		mascota.setNombre(nombre);
		mascota.setEstaVacunada(estaVacunada);
		mascota.setTenerCuidado(tenerCuidado);

		DatabaseHandler handler = new DatabaseHandler(this.getActivity()
				.getApplicationContext());
		handler.agregarMascota(mascota);

		String serviceResponse = PetServiceClient.instance().postPet(mascota);

		if (serviceResponse.isEmpty()) {
			Toast.makeText(null, "Mascota guardada", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(null, serviceResponse, Toast.LENGTH_SHORT).show();
		}

		Toast.makeText(null, "Usuario guardado", Toast.LENGTH_SHORT).show();
	}
}
