package com.example.findme;

import java.util.ArrayList;
import java.util.List;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.findme.R.id;

public class MainActivity extends FragmentActivity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;

	private static final int REQUEST_SCAN = 0;

	private int actualPosition = 0;

	CustomDrawerAdapter adapter;
	List<DrawerItem> dataList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initializing
		dataList = new ArrayList<DrawerItem>();
		mTitle = mDrawerTitle = getTitle();
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
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			SelectItem(0);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void SelectItem(int position) {

		Fragment fragment = null;

		switch (position) {
		case 0:
			fragment = new FragmentScan();
			saveChanges(position);
			break;
		case 1:
			fragment = new FragmentPetProfile();
			saveChanges(position);
			break;
		case 2:
			fragment = new FragmentMyProfile();
			saveChanges(position);
			break;
		case 3:
			fragment = new FragmentConfiguration();
			saveChanges(position);
			break;
		default:
			break;
		}

		FragmentManager frgManager = getSupportFragmentManager();
		frgManager.beginTransaction().replace(R.id.content_frame, fragment)
				.commit();

		mDrawerList.setItemChecked(position, true);
		setTitle(dataList.get(position).getItemName());
		mDrawerLayout.closeDrawer(mDrawerList);

	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
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
		if (item.getItemId() == id.notification) {
			Fragment fragment = new FragmentNotifications();
			saveChanges(4);
			FragmentManager frgManager = getSupportFragmentManager();
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
					.commit();
			setTitle("Notifications");
			mDrawerLayout.closeDrawer(mDrawerList);

			return true;
		}

		return false;
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			SelectItem(position);

		}
	}

	public void scanQR(View view) {
		try {
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(intent, REQUEST_SCAN); 
		}
		catch (ActivityNotFoundException ex) {
			Toast.makeText(this, "Baje una aplicacion para leer QR.", Toast.LENGTH_LONG).show();
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == REQUEST_SCAN) {
			if (resultCode == RESULT_OK) {
				String contents = intent.getStringExtra("SCAN_RESULT");

				Toast.makeText(this, contents, Toast.LENGTH_SHORT)
						.show();
			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "Error al leer el QR", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	public void resendQR(View view) {
	}

	public void changeImage(View view) {

	}

	public void saveChanges(int position) {
		if (position != this.actualPosition) {
			if (this.actualPosition == 1) {
				Toast.makeText(this, "Se han guardado los datos de la mascota",
						Toast.LENGTH_SHORT).show();
			} else if (this.actualPosition == 2) {
				Toast.makeText(this, "Se han guardado los datos del usuario",
						Toast.LENGTH_SHORT).show();
			} else if (this.actualPosition == 3) {
				Toast.makeText(this,
						"Se han guardado los datos de la configuración",
						Toast.LENGTH_SHORT).show();
			}
			this.actualPosition = position;
		}
	}
}
