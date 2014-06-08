package com.findme.app.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.findme.app.model.Mascota;
import com.findme.app.model.Notificacion;
import com.findme.app.model.Usuario;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_NAME = "findme";

	private static final String TABLA_USUARIO = "usuario";

	private static final String COLUMNA_USUARIO_ID = "id";
	private static final String COLUMNA_USUARIO_NOMBRE = "nombre";
	private static final String COLUMNA_USUARIO_APELLIDO = "apellido";
	private static final String COLUMNA_USUARIO_EMAIL = "correo";
	private static final String COLUMNA_USUARIO_CELULAR = "celular";
	private static final String COLUMNA_USUARIO_GCM_ID = "gcm_id";

	private static final String TABLA_MASCOTA = "mascota";

	private static final String COLUMNA_MASCOTA_ID = "id";
	private static final String COLUMNA_MASCOTA_NOMBRE = "nombre";
	private static final String COLUMNA_MASCOTA_INFO = "info";
	private static final String COLUMNA_MASCOTA_FOTO = "foto";
	private static final String COLUMNA_MASCOTA_CUIDADO = "tener_cuidado";
	private static final String COLUMNA_MASCOTA_VACUNAS = "tiene_vacunas";

	private static final String TABLA_NOTIFICACIONES_RECIBIDAS = "notificaciones_recibidas";

	private static final String COLUMNA_NOTIFICACION_RECIBIDA_ID = "id";
	private static final String COLUMNA_NOTIFICACION_RECIBIDA_FECHA = "fecha";
	private static final String COLUMNA_NOTIFICACION_RECIBIDA_NOMBRE_INFORMANTE = "nombre_informante";
	private static final String COLUMNA_NOTIFICACION_RECIBIDA_APELLIDO_INFORMANTE = "apellido_informante";
	private static final String COLUMNA_NOTIFICACION_RECIBIDA_CELULAR_INFORMANTE = "celular_informante";
	private static final String COLUMNA_NOTIFICACION_RECIBIDA_CORREO_INFORMANTE = "correo_informante";
	private static final String COLUMNA_NOTIFICACION_RECIBIDA_LONGITUD = "longitud";
	private static final String COLUMNA_NOTIFICACION_RECIBIDA_LATITUD = "latitud";
	private static final String COLUMNA_NOTIFICACION_RECIBIDA_NOMBRE_MASCOTA = "nombre_mascota";

	private static final String TABLA_NOTIFICACIONES_ENVIADAS = "notificaciones_enviadas";

	private static final String COLUMNA_NOTIFICACION_ENVIADA_ID = "id";
	private static final String COLUMNA_NOTIFICACION_ENVIADA_FECHA = "fecha";
	private static final String COLUMNA_NOTIFICACION_ENVIADA_NOMBRE_DUENO = "nombre_dueno";
	private static final String COLUMNA_NOTIFICACION_ENVIADA_APELLIDO_DUENO = "apellido_dueno";
	private static final String COLUMNA_NOTIFICACION_ENVIADA_CELULAR_DUENO = "celular_dueno";
	private static final String COLUMNA_NOTIFICACION_ENVIADA_CORREO_DUENO = "correo_dueno";
	private static final String COLUMNA_NOTIFICACION_ENVIADA_NOMBRE_MASCOTA = "nombre_mascota";

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
				+ COLUMNA_USUARIO_CELULAR + " TEXT, " + COLUMNA_USUARIO_GCM_ID
				+ " TEXT )";
		db.execSQL(CREAR_TABLA_USUARIOS);

		String CREAR_TABLA_NOTIFICACIONES_RECIBIDAS = "CREATE TABLE "
				+ TABLA_NOTIFICACIONES_RECIBIDAS + " ( "
				+ COLUMNA_NOTIFICACION_RECIBIDA_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COLUMNA_NOTIFICACION_RECIBIDA_NOMBRE_INFORMANTE + " TEXT, "
				+ COLUMNA_NOTIFICACION_RECIBIDA_APELLIDO_INFORMANTE + " TEXT, "
				+ COLUMNA_NOTIFICACION_RECIBIDA_CORREO_INFORMANTE + " TEXT, "
				+ COLUMNA_NOTIFICACION_RECIBIDA_CELULAR_INFORMANTE + " TEXT, "
				+ COLUMNA_NOTIFICACION_RECIBIDA_LONGITUD + " TEXT, "
				+ COLUMNA_NOTIFICACION_RECIBIDA_FECHA + " TEXT, "
				+ COLUMNA_NOTIFICACION_RECIBIDA_LATITUD + " TEXT, "
				+ COLUMNA_NOTIFICACION_RECIBIDA_NOMBRE_MASCOTA + " TEXT )";
		db.execSQL(CREAR_TABLA_NOTIFICACIONES_RECIBIDAS);

		String CREAR_TABLA_NOTIFICACIONES_ENVIADAS = "CREATE TABLE "
				+ TABLA_NOTIFICACIONES_ENVIADAS + " ( "
				+ COLUMNA_NOTIFICACION_ENVIADA_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COLUMNA_NOTIFICACION_ENVIADA_NOMBRE_DUENO + " TEXT, "
				+ COLUMNA_NOTIFICACION_ENVIADA_APELLIDO_DUENO + " TEXT, "
				+ COLUMNA_NOTIFICACION_ENVIADA_CELULAR_DUENO + " TEXT, "
				+ COLUMNA_NOTIFICACION_ENVIADA_CORREO_DUENO + " TEXT, "
				+ COLUMNA_NOTIFICACION_ENVIADA_FECHA + " TEXT, "
				+ COLUMNA_NOTIFICACION_ENVIADA_NOMBRE_MASCOTA + " TEXT )";
		db.execSQL(CREAR_TABLA_NOTIFICACIONES_ENVIADAS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIO);
		db.execSQL("DROP TABLE IF EXISTS " + TABLA_MASCOTA);
		db.execSQL("DROP TABLE IF EXISTS " + TABLA_NOTIFICACIONES_ENVIADAS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLA_NOTIFICACIONES_RECIBIDAS);

		onCreate(db);
	}

	public void addUsuario(Usuario usuario) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(COLUMNA_USUARIO_NOMBRE, usuario.getNombre());
		values.put(COLUMNA_USUARIO_APELLIDO, usuario.getApellido());
		values.put(COLUMNA_USUARIO_EMAIL, usuario.getCorreo());
		values.put(COLUMNA_USUARIO_CELULAR, usuario.getCelular());
		values.put(COLUMNA_USUARIO_GCM_ID, usuario.getGcmId());

		db.insert(TABLA_USUARIO, COLUMNA_USUARIO_NOMBRE, values);
		db.close();
	}

	public boolean updateUsuario(Usuario usuario) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMNA_USUARIO_NOMBRE, usuario.getNombre());
		values.put(COLUMNA_USUARIO_APELLIDO, usuario.getApellido());
		values.put(COLUMNA_USUARIO_EMAIL, usuario.getCorreo());
		values.put(COLUMNA_USUARIO_CELULAR, usuario.getCelular());
		values.put(COLUMNA_USUARIO_GCM_ID, usuario.getGcmId());

		int id = getUsuario().getId();
		int i = db.update(TABLA_USUARIO, values, COLUMNA_USUARIO_ID + " = ?",
				new String[] { String.valueOf(id) });
		db.close();

		return i > 0;
	}

	public Usuario getUsuario() {
		Usuario usuario = null;
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_USUARIO, null);

		if (cursor.getCount() > 0) {
			if (cursor.moveToFirst()) {
				usuario = new Usuario();
				usuario.setId(Integer.parseInt(cursor.getString(0)));
				usuario.setNombre(cursor.getString(1));
				usuario.setApellido(cursor.getString(2));
				usuario.setCorreo(cursor.getString(3));
				usuario.setCelular(cursor.getString(4));
				usuario.setGcmId(cursor.getString(5));
			}
		}

		return usuario;
	}

	public void addMascota(Mascota mascota) {
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

	public boolean updateMascota(Mascota mascota) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMNA_MASCOTA_NOMBRE, mascota.getNombre());
		values.put(COLUMNA_MASCOTA_INFO, mascota.getInfo());
		values.put(COLUMNA_MASCOTA_FOTO, mascota.getPathFoto());
		values.put(COLUMNA_MASCOTA_CUIDADO, mascota.tenerCuidado() ? 1 : 0);
		values.put(COLUMNA_MASCOTA_VACUNAS, mascota.estaVacunada() ? 1 : 0);

		int id = getMascota().getId();
		int i = db.update(TABLA_MASCOTA, values, COLUMNA_MASCOTA_ID + " = ?",
				new String[] { String.valueOf(id) });
		db.close();

		return i > 0;
	}

	public Mascota getMascota() {
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

	public void addNotificacionRecibida(Notificacion notificacion) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMNA_NOTIFICACION_RECIBIDA_NOMBRE_INFORMANTE,
				notificacion.getNombreUsuario());
		values.put(COLUMNA_NOTIFICACION_RECIBIDA_APELLIDO_INFORMANTE,
				notificacion.getApellidoUsuario());
		values.put(COLUMNA_NOTIFICACION_RECIBIDA_CELULAR_INFORMANTE,
				notificacion.getCelular());
		values.put(COLUMNA_NOTIFICACION_RECIBIDA_CORREO_INFORMANTE,
				notificacion.getCorreo());
		values.put(COLUMNA_NOTIFICACION_RECIBIDA_LONGITUD,
				notificacion.getLongitud());
		values.put(COLUMNA_NOTIFICACION_RECIBIDA_LATITUD,
				notificacion.getLatitud());
		values.put(COLUMNA_NOTIFICACION_RECIBIDA_FECHA,
				Notificacion.FULL_DATE.format(notificacion.getFecha()));
		values.put(COLUMNA_NOTIFICACION_RECIBIDA_NOMBRE_MASCOTA,
				notificacion.getNombreMascota());

		db.insert(TABLA_NOTIFICACIONES_RECIBIDAS,
				COLUMNA_NOTIFICACION_RECIBIDA_LATITUD, values);
		db.close();
	}

	public List<Notificacion> getNotificacionesRecibidas() {
		List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery("SELECT * FROM "
				+ TABLA_NOTIFICACIONES_RECIBIDAS + " ORDER BY "
				+ COLUMNA_NOTIFICACION_RECIBIDA_ID + " DESC", null);
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				if (cursor.moveToFirst()) {
					while (cursor.isAfterLast() == false) {
						Notificacion notificacion = new Notificacion();
						notificacion
								.setId(Integer.parseInt(cursor.getString(0)));

						notificacion.setNombreUsuario(cursor.getString(1));
						notificacion.setApellidoUsuario(cursor.getString(2));
						notificacion.setCorreo(cursor.getString(3));
						notificacion.setCelular(cursor.getString(4));
						notificacion.setLongitud(cursor.getString(5));
						try {
							notificacion.setFecha(Notificacion.FULL_DATE
									.parse(cursor.getString(6)));
						} catch (ParseException e) {
							// queda en null
						}
						notificacion.setLatitud(cursor.getString(7));
						notificacion.setNombreMascota(cursor.getString(8));

						notificaciones.add(notificacion);
						cursor.moveToNext();
					}
				}
			}
		}
		return notificaciones;

	}

	public void addNotificacionEnviada(Notificacion notificacion) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMNA_NOTIFICACION_ENVIADA_NOMBRE_DUENO,
				notificacion.getNombreUsuario());
		values.put(COLUMNA_NOTIFICACION_ENVIADA_APELLIDO_DUENO,
				notificacion.getApellidoUsuario());
		values.put(COLUMNA_NOTIFICACION_ENVIADA_CELULAR_DUENO,
				notificacion.getCelular());
		values.put(COLUMNA_NOTIFICACION_ENVIADA_CORREO_DUENO,
				notificacion.getCorreo());
		values.put(COLUMNA_NOTIFICACION_ENVIADA_FECHA,
				Notificacion.FULL_DATE.format(notificacion.getFecha()));
		values.put(COLUMNA_NOTIFICACION_ENVIADA_NOMBRE_MASCOTA,
				notificacion.getNombreMascota());

		db.insert(TABLA_NOTIFICACIONES_ENVIADAS,
				COLUMNA_NOTIFICACION_ENVIADA_FECHA, values);
		db.close();

	}

	public List<Notificacion> getNotificacionesEnviadas() {
		List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery("SELECT * FROM "
				+ TABLA_NOTIFICACIONES_ENVIADAS + " ORDER BY "
				+ COLUMNA_NOTIFICACION_ENVIADA_ID + " DESC", null);
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				if (cursor.moveToFirst()) {
					while (cursor.isAfterLast() == false) {
						Notificacion notificacion = new Notificacion();
						notificacion
								.setId(Integer.parseInt(cursor.getString(0)));

						notificacion.setNombreUsuario(cursor.getString(1));
						notificacion.setApellidoUsuario(cursor.getString(2));
						notificacion.setCelular(cursor.getString(3));
						notificacion.setCorreo(cursor.getString(4));
						try {
							notificacion.setFecha(Notificacion.FULL_DATE
									.parse(cursor.getString(5)));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						notificacion.setNombreMascota(cursor.getString(6));

						notificaciones.add(notificacion);
						cursor.moveToNext();
					}
				}
			}
		}
		return notificaciones;
	}

	public Notificacion getNotificacionRecibida(int pId) {
		Notificacion notificacion = null;
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery("SELECT * FROM "
				+ TABLA_NOTIFICACIONES_RECIBIDAS + " WHERE "
				+ COLUMNA_NOTIFICACION_RECIBIDA_ID + "=?",
				new String[] { String.valueOf(pId) + "" });
		if (cursor.getCount() > 0) {
			if (cursor.moveToFirst()) {
				notificacion = new Notificacion();
				notificacion.setId(Integer.parseInt(cursor.getString(0)));

				notificacion.setNombreUsuario(cursor.getString(1));
				notificacion.setApellidoUsuario(cursor.getString(2));
				notificacion.setCorreo(cursor.getString(3));
				notificacion.setCelular(cursor.getString(4));
				notificacion.setLongitud(cursor.getString(5));
				try {
					notificacion.setFecha(Notificacion.FULL_DATE.parse(cursor
							.getString(6)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				notificacion.setLatitud(cursor.getString(7));
				notificacion.setNombreMascota(cursor.getString(8));
			}
		}
		return notificacion;
	}

	public Notificacion getNotificacionEnviada(int pId) {
		Notificacion notificacion = null;
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery("SELECT * FROM "
				+ TABLA_NOTIFICACIONES_ENVIADAS + " WHERE "
				+ COLUMNA_NOTIFICACION_ENVIADA_ID + "=?",
				new String[] { String.valueOf(pId) + "" });
		if (cursor.getCount() > 0) {
			if (cursor.moveToFirst()) {
				notificacion = new Notificacion();
				notificacion.setId(Integer.parseInt(cursor.getString(0)));

				notificacion.setNombreUsuario(cursor.getString(1));
				notificacion.setApellidoUsuario(cursor.getString(2));
				notificacion.setCelular(cursor.getString(3));
				notificacion.setCorreo(cursor.getString(4));
				try {
					notificacion.setFecha(Notificacion.FULL_DATE.parse(cursor
							.getString(5)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				notificacion.setNombreMascota(cursor.getString(6));
			}
		}
		return notificacion;
	}

	public boolean hayUsuario() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_USUARIO, null);

		return cursor.getCount() > 0;
	}

	public boolean hayMascota() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_MASCOTA, null);

		return cursor.getCount() > 0;
	}
}
