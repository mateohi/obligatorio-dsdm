package com.findme.app.views;

import com.example.findme.R;
import com.example.findme.R.id;
import com.google.android.gms.analytics.Logger;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

public class FragmentConfiguration extends Fragment {

	public static final String PREFS_NAME = "FindMeConfig";
	private static final String SILENT = "silent";
	private static final String VIBRATE = "vibrate";
	private static final String TAG = "FragmentConfiguration";

	private View parentView;

	public FragmentConfiguration() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_configuration,
				container, false);
		parentView = view;
		restorePreferences();
		return view;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		savePreferences();
	}

	private void restorePreferences() {
		try {
			SharedPreferences settings = getActivity().getSharedPreferences(
					PREFS_NAME, 0);

			boolean silent = settings.getBoolean(SILENT, false);
			boolean vibrate = settings.getBoolean(VIBRATE, false);

			((Switch) parentView.findViewById(id.switch_sound))
					.setChecked(silent);
			((Switch) parentView.findViewById(id.switch_vibrate))
					.setChecked(vibrate);
		} catch (Exception ex) {
			Log.e(TAG, "Error al recuperar las configuraciones", ex);
			setDefaultPreferences();
		}

	}

	private void setDefaultPreferences() {
		((Switch) parentView.findViewById(id.switch_sound)).setChecked(true);
		((Switch) parentView.findViewById(id.switch_vibrate)).setChecked(true);
	}

	private void savePreferences() {
		try {
			boolean silent = ((Switch) parentView.findViewById(id.switch_sound))
					.isChecked();
			boolean vibrate = ((Switch) parentView
					.findViewById(id.switch_vibrate)).isChecked();

			SharedPreferences settings = getActivity().getSharedPreferences(
					PREFS_NAME, 0);
			SharedPreferences.Editor editor = settings.edit();

			editor.putBoolean(SILENT, silent);
			editor.putBoolean(VIBRATE, vibrate);
			editor.commit();
		} catch (Exception ex) {
			Log.e(TAG, "Error al guardar las configuraciones", ex);
		}
	}
}
