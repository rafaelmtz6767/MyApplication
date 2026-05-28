package com.example.myapplication;

public class Producto {
    private boolean disponibilidad;
    private int id;
    private String nombre;
    private int precio;
    private int stock;
    public Producto(){
    }

    public Producto(boolean disponibilidad, int id, String nombre, int precio, int stock){
        this.disponibilidad=disponibilidad;
        this.id=id;
        this.nombre=nombre;
        this.precio=precio;
        this.stock=stock;
    }
    public boolean isDisponibilidad(){
        return disponibilidad;
    }
    public void setDisponibilidad(boolean disponibilidad){
        this.disponibilidad=disponibilidad;
    }
    public int isId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public int isPrecio(){
        return precio;
    }
    public void setPrecio(int precio){
        this.precio=precio;
    }
    public int isStock(){
        return stock;
    }
    public void setStock(int stock){
        this.stock=stock;
    }
    public String isNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

}
