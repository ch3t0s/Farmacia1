package com.example.appws.modeloVO;

public class Medicamento {
    private int id;
    private String nombre_medicamento;
    private int cantidad;
    private double precio;
    private String fecha_vencimiento;

    public Medicamento (){

    }

    public Medicamento(int id, String nombre_medicamento, int cantidad, double precio, String fecha_vencimiento) {
        this.id = id;
        this.nombre_medicamento = nombre_medicamento;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_medicamento() {
        return nombre_medicamento;
    }

    public void setNombre_medicamento(String nombre_medicamento) {
        this.nombre_medicamento = nombre_medicamento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }
}
