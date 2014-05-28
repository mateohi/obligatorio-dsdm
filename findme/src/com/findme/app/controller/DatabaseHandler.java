package com.findme.app.controller;

import java.util.ArrayList;

import com.findme.app.model.Mascota;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler  extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_NAME = "findmeDatabase";

	private static final String TABLE_PERSON = "persona";

	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "nombre";
	private static final String KEY_SURNAME = "apellido";
	private static final String KEY_EMAIL = "correo";
	private static final String KEY_PHONE = "celular";
	private static final String KEY_FK_PET_ID = "id_pet";
	
	private static final String TABLE_PET= "mascota";
	
	private static final String KEY_PET_ID = "id";
	private static final String KEY_INFO = "info";
	private static final String KEY_PHOTO = "foto";
	private static final String KEY_BE_CAREFUL = "tener_cuidado";
	private static final String KEY_HAS_VACCINES = "tiene_vacunas";
	
	private final ArrayList<Mascota> pet_list = new ArrayList<Mascota>();

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
