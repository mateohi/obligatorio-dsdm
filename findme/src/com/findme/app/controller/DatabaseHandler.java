package com.findme.app.controller;

import java.util.ArrayList;

import com.findme.app.model.Mascota;
import com.findme.app.model.Usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_NAME = "findmeDatabase";

	private static final String TABLA_USUARIO = "usuario";

	private static final String COLUMNA_USUARIO_ID = "id";
	private static final String COLUMNA_USUARIO_NOMBRE = "nombre";
	private static final String COLUMNA_USUARIO_APELLIDO = "apellido";
	private static final String COLUMNA_USUARIO_EMAIL = "correo";
	private static final String COLUMNA_USUARIO_CELULAR = "celular";
	private static final String COLUMNA_USUARIO_FK_MASCOTA_ID = "id_pet";

	private static final String TABLA_MASCOTA = "mascota";

	private static final String COLUMNA_MASCOTA_ID = "id";
	private static final String COLUMNA_MASCOTA_NOMBRE = "nombre";
	private static final String COLUMNA_MASCOTA_INFO = "info";
	private static final String COLUMNA_MASCOTA_FOTO = "foto";
	private static final String COLUMNA_MASCOTA_CUIDADO = "tener_cuidado";
	private static final String COLUMNA_MASCOTA_VACUNAS = "tiene_vacunas";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREAR_TABLA_MASCOTAS = "CREATE TABLE " + TABLA_MASCOTA + " ( "
				+ COLUMNA_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COLUMNA_MASCOTA_NOMBRE + " TEXT, " + COLUMNA_MASCOTA_INFO
				+ " TEXT, " + COLUMNA_MASCOTA_FOTO + " TEXT, "
				+ COLUMNA_MASCOTA_CUIDADO + " INTEGER, "
				+ COLUMNA_MASCOTA_VACUNAS + " INTEGER )";

		db.execSQL(CREAR_TABLA_MASCOTAS);

		String CREAR_TABLA_USUARIOS = "CREATE TABLE " + TABLA_USUARIO + " ( "
				+ COLUMNA_USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COLUMNA_USUARIO_NOMBRE + " TEXT, " + COLUMNA_USUARIO_APELLIDO
				+ " TEXT, " + COLUMNA_USUARIO_EMAIL + " TEXT, "
				+ COLUMNA_USUARIO_CELULAR + " TEXT, "
				+ COLUMNA_USUARIO_FK_MASCOTA_ID
				+ " INTEGER,FOREIGN  KEY ("
				+ COLUMNA_USUARIO_FK_MASCOTA_ID + ") REFERENCES "
				+ TABLA_MASCOTA + " (" + COLUMNA_MASCOTA_ID + "));)";
		db.execSQL(CREAR_TABLA_USUARIOS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIO);
		db.execSQL("DROP TABLE IF EXISTS " + TABLA_MASCOTA);
	}

	public void agregarUsuario(Usuario usuario) {
//		agregarMascota(usuario.getMascota());
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(COLUMNA_USUARIO_NOMBRE, usuario.getNombre());
		values.put(COLUMNA_USUARIO_APELLIDO, usuario.getApellido());
		values.put(COLUMNA_USUARIO_EMAIL, usuario.getCorreo());
		values.put(COLUMNA_USUARIO_CELULAR, usuario.getCelular());
//		values.put(COLUMNA_USUARIO_FK_MASCOTA_ID, recuperarMascota().getId());

		db.insert(TABLA_USUARIO, COLUMNA_USUARIO_NOMBRE, values);
		db.close();
	}

	public boolean actualizarUsuario(Usuario usuario) {
		boolean pudoMascota = actualizarMascota(usuario.getMascota());

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMNA_USUARIO_NOMBRE, usuario.getNombre());
		values.put(COLUMNA_USUARIO_APELLIDO, usuario.getApellido());
		values.put(COLUMNA_USUARIO_EMAIL, usuario.getCorreo());
		values.put(COLUMNA_USUARIO_CELULAR, usuario.getCelular());

		int i = db.update(TABLA_USUARIO, values, COLUMNA_USUARIO_ID + " = ?",
				new String[] { String.valueOf(usuario.getId()) });
		db.close();

		return i > 0 && pudoMascota;
	}

	public Usuario getUsuario() {
		Usuario usuario = new Usuario();
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_USUARIO, null);

		if (cursor != null) {
			if (cursor.moveToFirst()) {
				usuario = new Usuario();
				usuario.setId(Integer.parseInt(cursor.getString(0)));
				usuario.setNombre(cursor.getString(1));
				usuario.setApellido(cursor.getString(2));
				usuario.setCorreo(cursor.getString(3));
				usuario.setCelular(cursor.getString(4));
				usuario.setMascota(recuperarMascota(Integer.parseInt(cursor
						.getString(5))));
			}
		}

		return usuario;
	}

	public void agregarMascota(Mascota mascota) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMNA_MASCOTA_NOMBRE, mascota.getNombre());
		values.put(COLUMNA_MASCOTA_INFO, mascota.getInfo());
		values.put(COLUMNA_MASCOTA_FOTO, mascota.getPathFoto());
		values.put(COLUMNA_MASCOTA_CUIDADO, mascota.tenerCuidado() ? 1 : 0);
		values.put(COLUMNA_MASCOTA_VACUNAS, mascota.estaVacunada() ? 1 : 0);

		db.insert(TABLA_MASCOTA, COLUMNA_MASCOTA_INFO, values);
		db.close();
	}

	public boolean actualizarMascota(Mascota mascota) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMNA_MASCOTA_NOMBRE, mascota.getNombre());
		values.put(COLUMNA_MASCOTA_INFO, mascota.getInfo());
		values.put(COLUMNA_MASCOTA_FOTO, mascota.getPathFoto());
		values.put(COLUMNA_MASCOTA_CUIDADO, mascota.tenerCuidado() ? 1 : 0);
		values.put(COLUMNA_MASCOTA_VACUNAS, mascota.estaVacunada() ? 1 : 0);

		int i = db.update(TABLA_MASCOTA, values, COLUMNA_MASCOTA_ID + " = ?",
				new String[] { String.valueOf(mascota.getId()) });
		db.close();

		return i > 0;
	}

	public Mascota recuperarMascota(int id) {
		Mascota mascota = new Mascota();
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_MASCOTA
				+ " WHERE " + COLUMNA_MASCOTA_ID + "=?",
				new String[] { String.valueOf(id) });
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				mascota.setId(Integer.parseInt(cursor.getString(0)));
				mascota.setNombre(cursor.getString(1));
				mascota.setInfo(cursor.getString(2));
				mascota.setPathFoto(cursor.getString(3));
				mascota.setTenerCuidado(Integer.parseInt(cursor.getString(4)) == 1 ? true
						: false);
				mascota.setEstaVacunada(Integer.parseInt(cursor.getString(5)) == 1 ? true
						: false);
			}
		}
		return mascota;
	}

	public Mascota recuperarMascota() {
		Mascota mascota = new Mascota();
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_MASCOTA, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				mascota.setId(Integer.parseInt(cursor.getString(0)));
				mascota.setNombre(cursor.getString(1));
				mascota.setInfo(cursor.getString(2));
				mascota.setPathFoto(cursor.getString(3));
				mascota.setTenerCuidado(Integer.parseInt(cursor.getString(4)) == 1 ? true
						: false);
				mascota.setEstaVacunada(Integer.parseInt(cursor.getString(5)) == 1 ? true
						: false);
			}
		}
		return mascota;
	}
}
