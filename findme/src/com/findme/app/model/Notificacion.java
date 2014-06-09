package com.findme.app.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Notificacion {
	
	public static final SimpleDateFormat FULL_DATE = new SimpleDateFormat("dd/MM/yyyy'-'HH:mm");
	public static final SimpleDateFormat DATE = new SimpleDateFormat("dd/MM/yyyy");
	public static final SimpleDateFormat TIME = new SimpleDateFormat("HH:mm");
	
	private int id;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String correo;
	private String celular;
	private String nombreMascota;
    private String pathFoto;
    private String fotoBase64;
	private Date fecha;
	private String longitud;
	private String latitud;
	private boolean estaVacunada;
	private boolean tenerCuidado;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getApellidoUsuario() {
		return apellidoUsuario;
	}
	
	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getCelular() {
		return celular;
	}
	
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public String getNombreMascota() {
		return nombreMascota;
	}
	
	public void setNombreMascota(String nombreMascota) {
		this.nombreMascota = nombreMascota;
	}
	
	public String getPathFoto() {
		return pathFoto;
	}
	
	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}
	
	public String getFotoBase64() {
		return fotoBase64;
	}
	
	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getLongitud() {
		return longitud;
	}
	
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	
	public String getLatitud() {
		return latitud;
	}
	
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	
	public boolean estaVacunada() {
		return estaVacunada;
	}
	
	public void setEstaVacunada(boolean estaVacunada) {
		this.estaVacunada = estaVacunada;
	}
	
	public boolean tenerCuidado() {
		return tenerCuidado;
	}
	
	public void setTenerCuidado(boolean tenerCuidado) {
		this.tenerCuidado = tenerCuidado;
	}

}
