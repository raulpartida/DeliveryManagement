package com.example.rulop_000.deliverym.Entidad;

import java.io.Serializable;

/**
 * Created by rulop_000 on 31/07/2016.
 */
public class EstatusEntregas implements Serializable {
    private int nentrega;
    private String nombre_negocio;
    private String nombre_cliente;
    private String apaterno;
    private String amaterno;

    public EstatusEntregas() {
    }

    public EstatusEntregas(int nentrega, String nombre_negocio, String amaterno, String nombre_cliente, String apaterno) {
        this.nentrega = nentrega;
        this.nombre_negocio = nombre_negocio;
        this.amaterno = amaterno;
        this.nombre_cliente = nombre_cliente;
        this.apaterno = apaterno;
    }

    public int getNentrega() {
        return nentrega;
    }

    public void setNentrega(int nentrega) {
        this.nentrega = nentrega;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getNombre_negocio() {
        return nombre_negocio;
    }

    public void setNombre_negocio(String nombre_negocio) {
        this.nombre_negocio = nombre_negocio;
    }
}
