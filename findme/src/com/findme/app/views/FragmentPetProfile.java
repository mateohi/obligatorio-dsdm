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
}
