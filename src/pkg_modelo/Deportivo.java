/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_modelo;

/**
 *
 * @author anato
 */
public class Deportivo extends Auto {

    public Deportivo(int id, String tipo, String marca, String matricula, String color) {
        super(id, tipo, marca, matricula, color);
    }
    @Override
    public String detalles(){
        return this.tipo+"Frenos potenciados, motores potentes y diseño aerodinámico";
    }
}
