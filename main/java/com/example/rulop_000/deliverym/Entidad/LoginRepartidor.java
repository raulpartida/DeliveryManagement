package com.example.rulop_000.deliverym.Entidad;

import java.io.Serializable;

/**
 * Created by rulop_000 on 01/05/2016.
 */
public class LoginRepartidor implements Serializable{
    private Integer id;
    private String contrasena;

    public LoginRepartidor() {
    }

    public LoginRepartidor(Integer id, String contrasena) {
        this.id = id;
        this.contrasena = contrasena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
