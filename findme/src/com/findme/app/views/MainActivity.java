package com.findme.app.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.findme.R;
import com.example.findme.R.id;
import com.findme.app.controller.DatabaseHandler;
import com.findme.app.controller.integration.tasks.PostNotificationTask;
import com.findme.app.controller.integration.tasks.PostPetTask;
import com.findme.app.controller.integration.tasks.PostUserTask;
import com.findme.app.controller.integration.tasks.ResendQrTask;
import com.findme.app.model.Mascota;
import com.findme.app.model.Notificacion;
import com.findme.app.model.Usuario;
import com.findme.app.utils.Base64Utils;
import com.findme.app.utils.EmailValidator;
import com.findme.app.utils.ImageUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class MainActivity extends FragmentActivity {

	/**
	 * Project number de la console API
	 */
	private static final String SENDER_ID = "163539247139";
	private static final String TAG = "MainActivity";
	private static final String PROPERTY_REG_ID = "registrationId";
	private static final String PROPERTY_APP_VERSION = "appVersion";
	private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

	public static final String PREFS_NAME = "FindMeConfig";
	private static final String SILENT = "silent";
	private static final String VIBRATE = "vibrate";

	private static final int REQUEST_SCAN = 0;
	private static final int REQUEST_SELECT_IMAGE = 1;

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CustomDrawerAdapter adapter;
	private List<DrawerItem> dataList;

	private GoogleCloudMessaging gcm;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initializing
		dataList = new ArrayList<DrawerItem>();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		// Add Drawer Item to dataList
		dataList.add(new DrawerItem("Escanear", R.drawable.ic_action_camera));
		dataList.add(new DrawerItem("Mi mascota", R.drawable.ic_action_labels));
		dataList.add(new DrawerItem("Mis datos", R.drawable.ic_action_person));
		dataList.add(new DrawerItem("Configuración",
				R.drawable.ic_action_settings));

		adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
				dataList);

		mDrawerList.setAdapter(adapter);

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}

		context = getApplicationContext();

		// Check device for Play Services APK. If check succeeds, proceed with
		// GCM registration.
		if (checkPlayServices()) {
			gcm = GoogleCloudMessaging.getInstance(this);
			String regid = getRegistrationId(context);

			if (regid.isEmpty()) {
				registerInBackground();
			}
		} else {
			Log.i(TAG, "No valid Google Play Services APK found.");
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void selectItem(int position) {

		Fragment fragment = null;

		switch (position) {
		case 0:
			fragment = new FragmentScan();
			break;
		case 1:
			fragment = new FragmentPetProfile(getMascota());
			break;
		case 2:
			fragment = new FragmentMyProfile(getUsuario());
			break;
		case 3:
			fragment = new FragmentConfiguration();
			break;
		default:
			break;
		}

		FragmentManager frgManager = getSupportFragmentManager();
		frgManager.beginTransaction().replace(R.id.content_frame, fragment)
				.commit();

		mDrawerList.setItemChecked(position, true);
		mDrawerLayout.closeDrawer(mDrawerList);

	}

	public void setDetailedNotificationsFragment(Notificacion notificacion,
			boolean isReceivedNotification) {
		Fragment fragment = new FragmentDetailedNotification(notificacion,
				isReceivedNotification);
		// Fragment fragment = new FragmentDetailedNotification();
		FragmentManager frgManager = getSupportFragmentManager();
		frgManager.beginTransaction().replace(R.id.content_frame, fragment)
				.commit();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		if (item.getItemId() == R.id.notification) {
			if (!hayUsuario()) {
				Toast.makeText(this, "Debe primero crear un usuario",
						Toast.LENGTH_LONG).show();
			} else if (!hayMascota()) {
				Toast.makeText(this, "Debe primero registrar una mascota",
						Toast.LENGTH_LONG).show();
			} else {
				Fragment fragment = new FragmentNotifications();
				FragmentManager frgManager = getSupportFragmentManager();
				frgManager.beginTransaction()
						.replace(R.id.content_frame, fragment).commit();
				mDrawerLayout.closeDrawer(mDrawerList);

				return true;
			}
		}

		return false;
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);

		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == REQUEST_SCAN) {
			if (resultCode == RESULT_OK) {
				String[] contents = intent.getStringExtra("SCAN_RESULT").split(
						"\\+");

				String gcmId = contents[0];
				String nombreMascota = contents[1];

				Toast.makeText(this, "Se encontro a " + nombreMascota,
						Toast.LENGTH_SHORT).show();

				new PostNotificationTask(this).execute(gcmId);
			}
		}

		if (requestCode == REQUEST_SELECT_IMAGE) {
			if (resultCode == RESULT_OK) {
				try {
					Uri selectedImage = intent.getData();
					Bitmap image = ImageUtils.getImageFromUri(
							getContentResolver(), selectedImage);
					Bitmap circleImage = ImageUtils.getCircleBitmap(image);

					ImageView imageView = (ImageView) findViewById(R.id.my_pet_image);
					imageView.setImageBitmap(circleImage);
				} catch (Exception ex) {
					Log.e(TAG, ex.getMessage(), ex);
				}
			}
		}
	}

	private boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(this);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				GooglePlayServicesUtil.getErrorDialog(resultCode, this,
						PLAY_SERVICES_RESOLUTION_REQUEST).show();
			} else {
				Log.i(TAG, "This device is not supported.");
				finish();
			}
			return false;
		}
		return true;
	}

	private String getRegistrationId(Context context) {
		final SharedPreferences prefs = getGcmPreferences(context);
		String registrationId = prefs.getString(PROPERTY_REG_ID, "");
		if (registrationId.isEmpty()) {
			Log.i(TAG, "Registration not found.");
			return "";
		}
		// Check if app was updated; if so, it must clear the registration ID
		// since the existing regID is not guaranteed to work with the new
		// app version.
		int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION,
				Integer.MIN_VALUE);
		int currentVersion = getAppVersion(context);
		if (registeredVersion != currentVersion) {
			Log.i(TAG, "App version changed.");
			return "";
		}
		return registrationId;
	}

	private SharedPreferences getGcmPreferences(Context context) {
		return getSharedPreferences(MainActivity.class.getSimpleName(),
				Context.MODE_PRIVATE);
	}

	private static int getAppVersion(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			// should never happen
			throw new RuntimeException("Could not get package name: " + e);
		}
	}

	private void registerInBackground() {
		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				String msg = "";
				try {
					if (gcm == null) {
						gcm = GoogleCloudMessaging.getInstance(context);
					}
					String regid = gcm.register(SENDER_ID);
					msg = "Device registered, registration ID=" + regid;
					storeRegistrationId(context, regid);
				} catch (IOException ex) {
					msg = "Error :" + ex.getMessage();
				}
				return msg;
			}

			@Override
			protected void onPostExecute(String msg) {
				Log.i(TAG, msg);
			}
		}.execute(null, null, null);
	}

	private void storeRegistrationId(Context context, String regId) {
		final SharedPreferences prefs = getGcmPreferences(context);
		int appVersion = getAppVersion(context);
		Log.i(TAG, "Saving regId on app version " + appVersion);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(PROPERTY_REG_ID, regId);
		editor.putInt(PROPERTY_APP_VERSION, appVersion);
		editor.commit();
	}

	// Configuration fragment methods

	public void scanQR(View view) {
		try {
			Intent intent = new Intent("com.google.zxing.client.android.SCAN");
			intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
			startActivityForResult(intent, REQUEST_SCAN);
		} catch (ActivityNotFoundException ex) {
			Toast.makeText(this, "Baje una aplicacion para leer QR.",
					Toast.LENGTH_LONG).show();
		}
	}

	// My pet fragment methods

	public void changePetImage(View v) {
		Intent i = new Intent(Intent.ACTION_PICK,
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(i, REQUEST_SELECT_IMAGE);
	}

	public void resendQR(View v) {
		if (hayMascota()) {
			String gcmId = getRegistrationId(getApplicationContext());
			new ResendQrTask(this).execute(gcmId);
		} else {
			Toast.makeText(this, "Cree una mascota antes", Toast.LENGTH_LONG)
					.show();
		}
	}

	public void savePetProfile(View v) {

		if (!hayUsuario()) {
			Toast.makeText(this, "Cree un usuario antes", Toast.LENGTH_LONG)
					.show();
			return;
		}

		String nombre = ((EditText) findViewById(id.my_pet_profile_name))
				.getText().toString().trim();
		boolean estaVacunada = ((ToggleButton) findViewById(id.switch_vacunada))
				.isChecked();
		boolean tenerCuidado = ((ToggleButton) findViewById(id.switch_cuidado))
				.isChecked();
		String info = ((EditText) findViewById(id.my_pet_extra_information))
				.getText().toString().trim();
		String fotoBase64 = getBase64FromImageView(id.my_pet_image);
		ImageView image = (ImageView) findViewById(id.my_pet_image);
		Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();

		Mascota mascota = new Mascota();
		mascota.setNombre(nombre);
		mascota.setEstaVacunada(estaVacunada);
		mascota.setTenerCuidado(tenerCuidado);
		mascota.setInfo(info);
		mascota.setFotoBase64(fotoBase64);
		mascota.setPathFoto(nombre);

		if (!nombre.isEmpty()) {
			try {
				guardarMascota(mascota);
				// Guardar imagen local
				ImageUtils.saveImageOnDevice(bitmap, mascota.getPathFoto(),
						getApplicationContext());
			} catch (Exception ex) {
				Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT)
						.show();
			}
		} else {
			Toast.makeText(this, "El nombre no puede ser vacio",
					Toast.LENGTH_LONG).show();
		}
	}

	private void guardarMascota(Mascota mascota) {
		// Guardar local
		DatabaseHandler handler = new DatabaseHandler(getApplicationContext());
		if (hayMascota()) {
			handler.updateMascota(mascota);
		} else {
			handler.addMascota(mascota);
		}

		// Guardar en el servidor
		String gcmId = getRegistrationId(getApplicationContext());
		new PostPetTask(this).execute(mascota, gcmId);
	}

	// My Profile fragment methods

	public void saveMyProfile(View v) {
		String nombre = ((EditText) findViewById(id.profile_name)).getText()
				.toString().trim();
		String apellido = ((EditText) findViewById(id.profile_last_name))
				.getText().toString().trim();
		String correo = ((EditText) findViewById(id.profile_mail)).getText()
				.toString().trim();
		String celular = ((EditText) findViewById(id.profile_mobile_phone))
				.getText().toString().trim();

		String validaciones = validarUsuario(nombre, apellido, correo, celular);

		if (validaciones.isEmpty()) {
			Usuario usuario = new Usuario();
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setCorreo(correo);
			usuario.setCelular(celular);
			usuario.setGcmId(this.getRegistrationId(context));

			try {
				guardarUsuario(usuario);
			} catch (Exception ex) {
				Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT)
						.show();
			}

		} else {
			Toast.makeText(this, validaciones, Toast.LENGTH_LONG).show();
		}
	}

	private void guardarUsuario(Usuario usuario) {
		// Guardar local
		DatabaseHandler handler = new DatabaseHandler(getApplicationContext());
		if (hayUsuario()) {
			handler.updateUsuario(usuario);
		} else {
			handler.addUsuario(usuario);
		}

		// Guardar en el servidor
		new PostUserTask(this).execute(usuario);
	}

	private String validarUsuario(String nombre, String apellido,
			String correo, String celular) {
		StringBuilder validaciones = new StringBuilder();

		if (nombre.equals("")) {
			validaciones.append("Nombre no puede ser vacio\n");
		}
		if (apellido.equals("")) {
			validaciones.append("Apellido no puede ser vacio\n");
		}
		if (correo.equals("")) {
			validaciones.append("Correo no puede ser vacio\n");
		} else if (!EmailValidator.valid(correo)) {
			validaciones.append("Correo no valido\n");
		}
		if (celular.equals("")) {
			validaciones.append("Celular no puede ser vacio\n");
		}

		if (validaciones.length() > 0) {
			validaciones.deleteCharAt(validaciones.length() - 1);
		}

		return validaciones.toString();
	}

	// Utility methods

	private Usuario getUsuario() {
		DatabaseHandler dh = new DatabaseHandler(getApplicationContext());

		if (dh.hayUsuario()) {
			return dh.getUsuario();
		} else {
			return null;
		}
	}

	private Mascota getMascota() {
		DatabaseHandler dh = new DatabaseHandler(getApplicationContext());

		if (dh.hayMascota()) {
			return dh.getMascota();
		} else {
			return null;
		}
	}

	private boolean hayUsuario() {
		DatabaseHandler dh = new DatabaseHandler(getApplicationContext());
		return dh.hayUsuario();
	}

	private boolean hayMascota() {
		DatabaseHandler dh = new DatabaseHandler(getApplicationContext());
		return dh.hayMascota();
	}

	private String getBase64FromImageView(int imageViewId) {
		ImageView image = (ImageView) findViewById(imageViewId);
		Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
		String fotoBase64 = Base64Utils.bytesToBase64String(ImageUtils
				.bitmapToBytes(bitmap));

		return fotoBase64;
	}
}
