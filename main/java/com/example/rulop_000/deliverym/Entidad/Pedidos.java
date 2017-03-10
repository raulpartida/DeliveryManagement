package com.example.rulop_000.deliverym.Entidad;

import java.io.Serializable;

/**
 * Created by rulop_000 on 31/07/2016.
 */
public class Pedidos implements Serializable {
   private int nentrega;
   private  String direccion;
    private  String colonia;
    private  String nombre_negocio;
    private  String nombre_cliente;
    private  String apaterno;
    private  String amaterno;
    private  String estatus;
    private String comentarios;

    public Pedidos() {
    }

    public Pedidos(int nentrega, String direccion, String colonia, String nombre_negocio, String nombre_cliente, String apaterno, String amaterno, String estatus, String comentarios) {
        this.nentrega = nentrega;
        this.direccion = direccion;
        this.colonia = colonia;
        this.nombre_negocio = nombre_negocio;
        this.nombre_cliente = nombre_cliente;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
        this.estatus = estatus;
        this.comentarios = comentarios;
    }

    public int getNentrega() {
        return nentrega;
    }

    public void setNentrega(int nentrega) {
        this.nentrega = nentrega;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getNombre_negocio() {
        return nombre_negocio;
    }

    public void setNombre_negocio(String nombre_negocio) {
        this.nombre_negocio = nombre_negocio;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
