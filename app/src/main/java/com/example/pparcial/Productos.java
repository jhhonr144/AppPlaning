package com.example.pparcial;

import java.io.Serializable;

public class Productos implements Serializable {

    private  String nombre;
    private  String precio;
    private  String id;
    private  String imei;

    public Productos() {
    }

    public Productos(String nombre, String precio, String id,String imei) {
        this.nombre = nombre;
        this.precio = precio;
        this.id = id;
        this.imei = imei;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
