package com.findme.app.views;

import com.example.findme.R;
import com.example.findme.R.id;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

public class FragmentConfiguration extends Fragment {

	public static final String PREFS_NAME = "FindMeConfig";
	private static final String SILENT = "silent";
	private static final String VIBRATE = "vibrate";

	public FragmentConfiguration() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_configuration,
				container, false);
		restorePreferences();
		return view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		savePreferences();
	}
	
	private void restorePreferences() {
		SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);

		boolean silent = settings.getBoolean(SILENT, false);
		boolean vibrate = settings.getBoolean(VIBRATE, false);

		((Switch) getView().findViewById(id.switch_sound)).setChecked(silent);
		((Switch) getView().findViewById(id.switch_vibrate))
				.setChecked(vibrate);

	}

	private void savePreferences() {
		boolean silent = ((Switch) getView().findViewById(id.switch_sound))
				.isChecked();
		boolean vibrate = ((Switch) getView().findViewById(id.switch_vibrate))
				.isChecked();

		SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		
		editor.putBoolean(SILENT, silent);
		editor.putBoolean(VIBRATE, vibrate);
		editor.commit();
	}
}
