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

    public Van(int id, String tipo, String marca, String matricula, String color) {
        super(id, tipo, marca, matricula, color);
    }
    @Override
    public String detalles(){
        return this.tipo+"Quince asientos con acceso fácil y seguro.";
    }
}
