/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg18_rentalcar2;
import pkg_controlador.AutoControl;
import pkg_modelo.DAOAuto;
import pkg_vista.Vista;

/**
 *
 * @author Pau
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Vista vista = new Vista();
        DAOAuto daoAuto = new DAOAuto();
        AutoControl control=new AutoControl(vista,daoAuto);
        vista.setVisible(true);
    }
    
}
