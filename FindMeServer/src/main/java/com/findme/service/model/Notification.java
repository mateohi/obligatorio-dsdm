package com.findme.service.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "Notificacion")
public class Notification extends Persistent {

    private String gcmIdDenunciante;
    private String gcmIdDueno;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String correo;
    private String celular;
    private String nombreMascota;
    private String pathFoto;
    private String fotoBase64;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private String longitud;
    private String latitud;
    private boolean estaVacunada;
    private boolean tenerCuidado;

    public String getGcmIdDenunciante() {
        return gcmIdDenunciante;
    }

    public void setGcmIdDenunciante(String gcmIdDenunciante) {
        this.gcmIdDenunciante = gcmIdDenunciante;
    }

    public String getGcmIdDueno() {
        return gcmIdDueno;
    }

    public void setGcmIdDueno(String gcmIdDueno) {
        this.gcmIdDueno = gcmIdDueno;
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
