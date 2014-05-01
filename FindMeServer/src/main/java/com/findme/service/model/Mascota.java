package com.findme.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Mascota")
public class Mascota extends Persistent {

    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Info")
    private String info;
    @Column(name = "Foto")
    private String pathFoto;
    @Column(name = "Cuidado")
    private boolean tenerCuidado;
    @Column(name = "Vacunada")
    private boolean estaVacunada;

    public Mascota() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    public boolean tenerCuidado() {
        return tenerCuidado;
    }

    public void setTenerCuidado(boolean tenerCuidado) {
        this.tenerCuidado = tenerCuidado;
    }

    public boolean estaVacunada() {
        return estaVacunada;
    }

    public void setEstaVacunada(boolean estaVacunada) {
        this.estaVacunada = estaVacunada;
    }
}
