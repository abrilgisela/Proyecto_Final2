/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_modelo;

/**
 *
 * @author anato
 */
public class Van extends Auto{

    public Van(int id, String tipo, String marca, String anio, String color) {
        super(id, tipo, marca, anio, color);
    }
    @Override
    public String detalles(){
        return this.tipo+"Cuatro asiento, mucha comodidad.";
    }
}
