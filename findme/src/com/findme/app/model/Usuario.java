package com.findme.app.model;

public class Usuario {

	private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private String celular;
    private String gcmId;

    public Usuario() {
    }

    public int getId(){
    	return id;
    }
        
    public void setId(int pId){
    	this.id = pId;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

	public String getGcmId() {
		return gcmId;
	}

	public void setGcmId(String gcmId) {
		this.gcmId = gcmId;
	}
}