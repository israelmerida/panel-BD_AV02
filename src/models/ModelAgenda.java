/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/** 
 *
 * @author LENOVO
 */
public class ModelAgenda {

    private Connection conexion;
    private Statement st;
    private ResultSet rs;

    private String nombre;
    private String email;
    private String telefono;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    

    /**
     * Método que realiza las siguietnes acciones: 1- Conecta con la base
     * agenda_mvc, 2- Consulta todo los registros de la tabla contactos, 3-
     * Obtiene el nombre y el email y los guarda en las variables globales
     * nombre y email.
     */
    public void conectarDB() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/agenda_mvc", "user_mvc", "pass_mvc.2018");
            st = conexion.createStatement();
            String sql = "SELECT * FROM contactos;";
            System.out.println(sql);
            rs = st.executeQuery(sql);
            rs.next();
            setValues();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 001: " + err.getMessage());
        }
    }

    /**
     * Lee los valores del registro seleccionado y los asigna a las variables
     * miembre nombre y email.
     */
    public void setValues() {
        try {
            nombre = rs.getString("nombre");
            email = rs.getString("email");
            telefono = rs.getString("telefono");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error model 102: " + err.getMessage());

        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al primer registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverPrimerRegistro() {
        try {
            rs.first();
            nombre = rs.getString("nombre");
            email = rs.getString("email");
            telefono =rs.getString("telefono");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 001" + ex.getMessage());
        }
        System.out.println("moverPrimerRegistro");
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al siguiente
     * registro 2.- obtener el valor del nombre de rs y guardarlo en la variable
     * nombre 3.- obtener el valor del email de rs y guardarlo en la variable
     * email
     */
    public void moverSiguienteRegistro() {
        try {
            if (!rs.isLast()) {
                rs.next();
                nombre = rs.getString("nombre");
                email = rs.getString("email");
                telefono =rs.getString("telefono");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 001" + ex.getMessage());
        }
        System.out.println("moverSiguienteRegistro");
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al anterior
     * registro 2.- obtener el valor del nombre de rs y guardarlo en la variable
     * nombre 3.- obtener el valor del email de rs y guardarlo en la variable
     * email
     */
    public void moverAnteriorRegistro() {
        try {
            if (!rs.isFirst()) {
                rs.previous();
                nombre = rs.getString("nombre");
                email = rs.getString("email");
                telefono =rs.getString("telefono");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 001" + ex.getMessage());
        }
        System.out.println("moverAnteriorRegistro");
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al ultimo registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la ariable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverUltimoRegistro() {
        try {
            rs.last();
            nombre = rs.getString("nombre");
            email = rs.getString("email");
            telefono =rs.getString("telefono");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error mover ultimo registro" + ex.getMessage());
        }
        System.out.println("moverUltimoRegistro");
    }
    public void modificar(){
        try {
            id = rs.getInt("id_contacto");
            nombre = this.getNombre();
            email = this.getEmail();
            telefono = this.getTelefono();
            st.executeUpdate("UPDATE contactos SET nombre = '"+ nombre +"', email = '"+ email +"', Telefono = '"+ telefono +"' WHERE id_contacto = "+ id +"; ");
            this.conectarDB();
            this.moverUltimoRegistro();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar" + ex.getMessage());
        }
   
        System.out.print("Programa modificar");
    }
    
    public void borrar(){
        try {
            id = rs.getInt("id_contacto");
            st.executeUpdate("DELETE FROM contactos WHERE id_contacto = "+ id +"; ");
            this.conectarDB();
            this.moverUltimoRegistro();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar" + ex.getMessage());
        }
   
        System.out.print("Programa borrar");
    }
    public void insertar(){
        try {
            nombre = this.getNombre();
            email = this.getEmail();
            telefono = this.getTelefono();
            st.executeUpdate("INSERT INTO contactos (nombre, email, Telefono)" + " VALUES ('"+ nombre +"','"+ email +"','"+ telefono +"');");
            this.conectarDB();
            this.moverUltimoRegistro();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar" + ex.getMessage());
        }
   
        System.out.print("Programa insertar");
    }
    
}
