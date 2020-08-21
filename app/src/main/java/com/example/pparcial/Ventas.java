package com.example.pparcial;

import java.io.Serializable;

public class Ventas implements Serializable {
    private String idventa;
    private String nombreProducto;
    private String nombreCliente;
    private String TotalPagar;
    private String TotalDebe;
    private String TotalAbono;
    private String anotaciones;
    private String Hora;
    private String fecha;
    private String titulo;
    private String mensaje;

    public Ventas() {
    }

    public Ventas(String nombreProducto, String nombreCliente, String totalPagar, String totalDebe, String totalAbono, String anotaciones, String hora, String fecha, String titulo, String mensaje,String idventa) {
        this.nombreProducto = nombreProducto;
        this.nombreCliente = nombreCliente;
        TotalPagar = totalPagar;
        TotalDebe = totalDebe;
        TotalAbono = totalAbono;
        this.anotaciones = anotaciones;
        Hora = hora;
        this.fecha = fecha;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.idventa = idventa;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTotalPagar() {
        return TotalPagar;
    }

    public void setTotalPagar(String totalPagar) {
        TotalPagar = totalPagar;
    }

    public String getTotalDebe() {
        return TotalDebe;
    }

    public void setTotalDebe(String totalDebe) {
        TotalDebe = totalDebe;
    }

    public String getTotalAbono() {
        return TotalAbono;
    }

    public void setTotalAbono(String totalAbono) {
        TotalAbono = totalAbono;
    }

    public String getAnotaciones() {
        return anotaciones;
    }

    public void setAnotaciones(String anotaciones) {
        this.anotaciones = anotaciones;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getIdventa() {
        return idventa;
    }

    public void setIdventa(String idventa) {
        this.idventa = idventa;
    }
}
