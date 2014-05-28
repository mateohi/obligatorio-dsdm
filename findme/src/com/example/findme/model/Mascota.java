package com.example.findme.model;

public class Mascota {

    private String nombre;
    private String info;
    private String pathFoto;
    private boolean tenerCuidado;
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
