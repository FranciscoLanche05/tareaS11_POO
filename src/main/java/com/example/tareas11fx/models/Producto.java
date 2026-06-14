package com.example.tareas11fx.models;

import javafx.beans.property.*;

public class Producto {

    private final StringProperty codigo;
    private final StringProperty nombre;
    private final StringProperty categoria;
    private final DoubleProperty precio;
    private final IntegerProperty stock;
    private final StringProperty estado;

    public Producto(String codigo, String nombre, String categoria, double precio, int stock, String estado) {
        this.codigo = new SimpleStringProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.categoria = new SimpleStringProperty(categoria);
        this.precio = new SimpleDoubleProperty(precio);
        this.stock = new SimpleIntegerProperty(stock);
        this.estado = new SimpleStringProperty(estado);
    }

    public String getCodigo() { return codigo.get(); }
    public void setCodigo(String value) { codigo.set(value); }
    public StringProperty codigoProperty() { return codigo; }

    public String getNombre() { return nombre.get(); }
    public void setNombre(String value) { nombre.set(value); }
    public StringProperty nombreProperty() { return nombre; }

    public String getCategoria() { return categoria.get(); }
    public void setCategoria(String value) { categoria.set(value); }
    public StringProperty categoriaProperty() { return categoria; }

    public double getPrecio() { return precio.get(); }
    public void setPrecio(double value) { precio.set(value); }
    public DoubleProperty precioProperty() { return precio; }

    public int getStock() { return stock.get(); }
    public void setStock(int value) { stock.set(value); }
    public IntegerProperty stockProperty() { return stock; }

    public String getEstado() { return estado.get(); }
    public void setEstado(String value) { estado.set(value); }
    public StringProperty estadoProperty() { return estado; }
}