package com.example.rulop_000.deliverym.Entidad;

import java.io.Serializable;

/**
 * Created by rulop_000 on 01/05/2016.
 */
public class Cliente implements Serializable{

    private String nombren;
    private  String nombrec;
    private  String apaterno;
    private  String amaterno;
    private  String direccion;
    private  String colonia;
    private int cP;
    private  String ciudad;
    private int telefono;
    private  String rFC;
    private  String correo;
    private  String fecha;
    private int id_empleado;

    public Cliente() {
    }

    public Cliente(String nombren, String nombrec, String apaterno, String amaterno, String direccion, String colonia, int cP, String ciudad, int telefono, String rFC, String correo, String fecha, int id_empleado) {
        this.nombren = nombren;
        this.nombrec = nombrec;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
        this.direccion = direccion;
        this.colonia = colonia;
        this.cP = cP;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.rFC = rFC;
        this.correo = correo;
        this.fecha = fecha;
        this.id_empleado = id_empleado;
    }

    public String getNombren() {
        return nombren;
    }

    public void setNombren(String nombren) {
        this.nombren = nombren;
    }

    public String getNombrec() {
        return nombrec;
    }

    public void setNombrec(String nombrec) {
        this.nombrec = nombrec;
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

    public int getcP() {
        return cP;
    }

    public void setcP(int cP) {
        this.cP = cP;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getrFC() {
        return rFC;
    }

    public void setrFC(String rFC) {
        this.rFC = rFC;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }
}
