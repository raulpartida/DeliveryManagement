package com.example.rulop_000.deliverym.Entidad;

import java.io.Serializable;

/**
 * Created by rulop_000 on 28/07/2016.
 */
public class OlvidoContrasena implements Serializable {
    Integer id,estado;
    String palabra,password;

    public OlvidoContrasena() {
    }

    public OlvidoContrasena(Integer id, Integer estado, String palabra, String password) {
        this.id = id;
        this.estado = estado;
        this.palabra = palabra;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
