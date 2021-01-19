package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MATHEO-PC
 */
public class VendedorDatos implements Crud {

    PreparedStatement ps;
    ResultSet rs;

    Conexion conexion = new Conexion();
    Connection con;

    @Override
    public List listar() {
        List<Vendedor> listaVendedores = new ArrayList<>();
        final String sql = "SELECT * FROM vendedor";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setId(rs.getInt(1));
                vendedor.setUsuario(rs.getString(2));
                vendedor.setCedula(rs.getString(3));
                vendedor.setNombre(rs.getString(4));
                vendedor.setTelefono(rs.getString(5));
                listaVendedores.add(vendedor);
            }
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
        return listaVendedores;
    }

    @Override
    public int add(Object[] obj) {
        int resultado = 0;
        final String sql = "INSERT INTO vendedor(usuario, cedula, nombre, telefono) VALUES (?,?,?,?)";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, obj[0]);
            ps.setObject(2, obj[1]);
            ps.setObject(3, obj[2]);
            ps.setObject(4, obj[3]);
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
        return resultado;
    }

    @Override
    public int actualizar(Object[] obj) {
        int respuesta = 0;
        final String sql = "UPDATE vendedor SET usuario=?, cedula=?, nombre=?, telefono=? WHERE idvendedor=?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, obj[0]);
            ps.setObject(2, obj[1]);
            ps.setObject(3, obj[2]);
            ps.setObject(4, obj[3]);
            ps.setObject(5, obj[4]);
            respuesta = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
        return respuesta;
    }

    @Override
    public void eliminar(int idVendedor) {
        final String sql = "DELETE FROM vendedor WHERE idvendedor=?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVendedor);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
    }
}
