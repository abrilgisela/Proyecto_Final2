/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_modelo;

/**
 *
 * @author Pau
 */
public abstract class Auto {
    protected int id;
    protected String tipo;
    protected String marca;
    protected String anio;
    protected String color; 

    public Auto(int id, String tipo, String marca, String anio, String color) {
        this.id = id;
        this.tipo = tipo;
        this.marca = marca;
        this.anio = anio;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    public String getAnio() {
        return anio;
    }

    public String getColor() {
        return color;
    }
    
    public abstract String detalles();
}
