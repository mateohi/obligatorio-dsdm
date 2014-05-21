package com.findme.service.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Notificacion")
public class Notification extends Persistent {

    @Column(name = "Dueño")
    private Usuario usuarioDueno;
    @Column(name = "Informante")
    private Usuario usuarioInformante;
    @Column(name = "Mascota")
    private Mascota mascota;
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "Longitud")
    private String longitud;
    @Column(name = "Latitud")
    private String latitud;

    public Notification() {
    }

    public Usuario getUsuarioDueno() {
        return usuarioDueno;
    }

    public void setUsuarioDueno(Usuario usuarioDueno) {
        this.usuarioDueno = usuarioDueno;
    }

    public Usuario getUsuarioInformante() {
        return usuarioInformante;
    }

    public void setUsuarioInformante(Usuario usuarioInformante) {
        this.usuarioInformante = usuarioInformante;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
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
}
