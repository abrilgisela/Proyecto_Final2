/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author anato
 */
public class DAOAuto {
    private final String URL = "jdbc:mysql://localhost:3306/autos";
    private final String USER = "root";
    private final String PASS = "070807";

    // INSERTAR
    public void insertar(Auto a) throws SQLException {
        String sql = "INSERT INTO libro(tipo, marca, anio, color) VALUES(?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, a.getTipo());
            stmt.setString(2, a.getMarca());
            stmt.setString(3, a.getAnio());
            stmt.setString(4, a.getColor());
            stmt.executeUpdate();
        }
    }
    
    public ArrayList<Auto> consultar() throws SQLException {
        ArrayList<Auto> lista = new ArrayList<>();
        String sql = "SELECT * FROM auto";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Auto auto = new Auto(
                        rs.getInt("id"),
                        rs.getString("tipo"),
                        rs.getString("marca"),
                        rs.getString("anio"),
                        rs.getString("color")
                ) {
                    @Override
                    public String detalles() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }
                };
                lista.add(auto);
            }
        }
        return lista;
    }
    
    public void eliminar(String tipo, String marca) throws SQLException {
        String sql = "DELETE FROM auto WHERE tipo = ? and marca=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipo);
            stmt.setString(2, marca);
            stmt.executeUpdate();
        }
    }
    
    //actualizar
    public void actualizar(Auto a, String tipo, String tipoOriginal) throws SQLException{//throws para capturar errores
        String sql="UPDATE instrumento SET tipo=?, marca=?, anio=?, color=? WHERE tipo=? and marca=?";
        try(Connection conn=DriverManager.getConnection(URL,USER,PASS);//Para manejar errores
                PreparedStatement stmt=conn.prepareStatement(sql)){
                stmt.setString(1, a.tipo);
                stmt.setString(2, a.marca);
                stmt.setString(3, a.anio);
                stmt.setString(4, a.color);
                stmt.setString(5, tipoOriginal);
                stmt.executeUpdate();
                
        }
    }
}
