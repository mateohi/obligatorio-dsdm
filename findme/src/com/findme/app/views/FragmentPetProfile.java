package com.findme.app.views;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.findme.app.R;
import com.findme.app.R.id;
import com.findme.app.model.Mascota;
import com.findme.app.utils.ImageUtils;

public class FragmentPetProfile extends Fragment {
	
	private View parentView;
	private Mascota mascota;
	
	public FragmentPetProfile(Mascota mascota) {
		this.mascota = mascota;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater
				.inflate(R.layout.fragment_my_pet, container, false);
		this.parentView = view;
		cargarDatos();
		return view;
	}

	private void cargarDatos() {
		if (mascota != null) {			
			String nombre = mascota.getNombre();
			boolean cuidado = mascota.tenerCuidado();
			boolean vacunada = mascota.estaVacunada();
			String info = mascota.getInfo();

			((EditText) this.parentView.findViewById(id.my_pet_profile_name)).setText(nombre);
			((ToggleButton) this.parentView.findViewById(id.switch_vacunada)).setChecked(vacunada);
			((ToggleButton) this.parentView.findViewById(id.switch_cuidado)).setChecked(cuidado);
			((EditText) this.parentView.findViewById(id.my_pet_extra_information)).setText(info);
			
			try {
				Bitmap foto = ImageUtils.getImageFromDevice(mascota.getPathFoto(), parentView.getContext());
				((ImageView) this.parentView.findViewById(id.my_pet_image)).setImageBitmap(foto);
			} 
			catch (Exception ex) {
				// Queda la default
			}
		}
	}
}
