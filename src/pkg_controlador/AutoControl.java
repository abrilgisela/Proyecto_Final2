/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_controlador;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import pkg_modelo.*;
import pkg_modelo.DAOAuto;
import pkg_vista.Vista;

/**
 *
 * @author Pau
 */
public class AutoControl {
    private Vista vista;
    private DAOAuto dao;

    public AutoControl(Vista vista, DAOAuto dao) {
        this.vista = vista;
        this.dao = dao;
        
        //Poner escuchador al boton
        this.vista.btnGuardar.addActionListener(e->guardarDatos());
        this.vista.btnConsultar.addActionListener(e->consultarDatos());
        this.vista.btnEliminar.addActionListener(e->eliminarDatos());
        this.vista.btnActualizar.addActionListener(e->actualizarDatos());
        this.vista.tabla.getSelectionModel().addListSelectionListener(e->seleccionarDatos());
        
    }
    
    public void guardarDatos(){
        //try
        try{
        //recuperar datos de la vista
        String tipo=vista.comboTipo.getSelectedItem().toString();
        String marca=vista.txtMarca.getText().trim();
        String matricula=vista.txtMatricula.getText().trim();
        String color=vista.txtColor.getText().trim();
        
        //llenar objeto
        Auto a=switch(tipo){//Polimorfismo, asi un mismo objeto, metodo puede comportarse de maneras difernetes segun lo que se utilice
            case "Sedan" -> new Sedan(0,tipo,marca,matricula,color);
            case "Van" -> new Van(0,tipo,marca,matricula,color);
            case "Deportivo" -> new Deportivo(0,tipo,marca,matricula,color);
            case "Convertible" -> new Convertible(0,tipo,marca,matricula,color);
            default -> null;
        };
        if(a==null){
            throw new NoSeleccionado("Selecciona un tipo de auto");
        }
        dao.insertar(a);
        //limpiar datos
        limpiarDatos();
        //mensaje de confirmacion al usuario
        JOptionPane.showMessageDialog(vista,"Auto registrado: "+ tipo);
        this.consultarDatos();
        //simular clic en consultar para refrescar la tabla
        }catch(NoSeleccionado e){
            JOptionPane.showMessageDialog(vista,e.getMessage());
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(vista, "Error al registrar: " + ex.getMessage());
        }
    }
    public void limpiarDatos(){
        vista.comboTipo.setSelectedIndex(0);
        vista.txtMarca.setText("");
        vista.txtMatricula.setText("");
        vista.txtColor.setText("");
    }
    
    public void consultarDatos(){
        try{
            vista.modeloTabla.setRowCount(0); //limpiar tabla
            String tipo=vista.comboTipo.getSelectedItem().toString();
            for(Auto a: dao.obtenerPorTipo(tipo)){
                vista.modeloTabla.addRow(new Object[]{
                //Arregar acceso
                a.getMatricula(),
                tipo,
                a.getMarca(),
                a.getColor()
                });
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(vista,"Error al guardar"+ex.getMessage());
        }
    }
    
    public void eliminarDatos(){
        try{
            if (vista.nombreSeleccionado == null){
                JOptionPane.showMessageDialog(vista, "Selecciona un auto para eliminar");
                return;
            }
            int confirmacion = JOptionPane.showConfirmDialog(vista,
                    "Estás seguro de eliminar "+vista.nombreSeleccionado +
                            "?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION){
                dao.eliminar(vista.nombreSeleccionado);
                vista.nombreSeleccionado=null;
                limpiarDatos();
                JOptionPane.showMessageDialog(vista, "Auto eliminado.");
                vista.btnConsultar.doClick();
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(vista, "Error al eliminar: "+ex.getMessage());
        }
    }
    
    //selecionar el registro de la tabla
    public void seleccionarDatos(){
        int fila=vista.tabla.getSelectedRow();
        if (fila>=0){
        vista.nombreSeleccionado=vista.tabla.getValueAt(fila, 0).toString();
        
        vista.comboTipo.setSelectedItem(vista.tabla.getValueAt(fila, 1).toString());
        vista.txtMarca.setText(vista.tabla.getValueAt(fila, 2).toString());
        vista.txtMatricula.setText(vista.tabla.getValueAt(fila, 0).toString());
        vista.txtColor.setText(vista.tabla.getValueAt(fila, 3).toString());
        }
    }
    
    public void actualizarDatos(){
        //try
        try{
        //recuperar datos de la vista
        String tipo=vista.comboTipo.getSelectedItem().toString();
        String marca=vista.txtMarca.getText().trim();
        String matricula=vista.txtMatricula.getText().trim();
        String color = vista.txtColor.getText().trim();
        
        if(vista.nombreSeleccionado==null){
            JOptionPane.showMessageDialog(vista,"Selcciona un auto");
            return;
        }
        
        //Validar el tipo de auto y llenar objeto
        Auto a=switch(tipo){
            case "Sedan" -> new Sedan(0,tipo,marca,matricula,color);
            case "Van" -> new Van(0,tipo,marca,matricula,color);
            case "Deportivo" -> new Deportivo(0,tipo,marca,matricula,color);
            case "Convertible" -> new Convertible(0,tipo,marca,matricula,color);
            default -> null;
        };
        dao.actualizar(a, tipo,vista.nombreSeleccionado);
        vista.nombreSeleccionado=null;
        //limpiar datos
        limpiarDatos();
        //mensaje de confirmacion al usuario
        JOptionPane.showMessageDialog(vista,"Auto actualizado: "+ tipo);
        this.consultarDatos();
        //simular clic en consultar para refrescar la tabla
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(vista,"Error al actualizar");
        }
    }
}