
package com.aesuriagasalazar.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Karla Minga Herrera
 */
public class Conexion {
    
    Connection con;
    String url = "jdbc:mysql://localhost:3306/papeleria?serverTimezone=UTC";
    String user = "root";
    String password = "abc123";

    public Connection conectar() { //Metodo que retorna la conexion a la base de datos
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion correcta");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return con;
    }
}
