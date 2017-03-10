package com.example.rulop_000.deliverym.Entidad;

import java.io.Serializable;

/**
 * Created by rulop_000 on 31/07/2016.
 */
public class PerfilRepartidor implements Serializable {
    private int id_empleado;
    private String nombre;
    private String apaterno;
    private String amaterno;
    private int id_ruta;
    private String id_vehiculo;

    public PerfilRepartidor() {
    }

    public PerfilRepartidor(int id_empleado, String nombre, String amaterno, String apaterno, int id_ruta, String id_vehiculo) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.amaterno = amaterno;
        this.apaterno = apaterno;
        this.id_ruta = id_ruta;
        this.id_vehiculo = id_vehiculo;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(int id_ruta) {
        this.id_ruta = id_ruta;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(String id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }
}
