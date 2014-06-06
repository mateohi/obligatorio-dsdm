package com.findme.app.model;

import java.util.Calendar;

public class Notificacion {
	private int id;
	private Usuario usuarioDueno;
	private Usuario usarioInformante;
	private Mascota mascota;
	private Calendar fecha;
	private String longitud;
	private String latitud;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario getUsuarioDueno() {
		return usuarioDueno;
	}
	public void setUsuarioDueno(Usuario usuarioDueno) {
		this.usuarioDueno = usuarioDueno;
	}
	public Usuario getUsarioInformante() {
		return usarioInformante;
	}
	public void setUsarioInformante(Usuario usarioInformante) {
		this.usarioInformante = usarioInformante;
	}
	public Mascota getMascota() {
		return mascota;
	}
	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}
	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {
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
}
