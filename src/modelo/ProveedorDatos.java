package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Angel Suriaga
 */
public class ProveedorDatos implements Crud {

    PreparedStatement ps;
    ResultSet rs;

    Conexion conexion = new Conexion();
    Connection con;

    @Override
    public List listar() {
        List<Proveedor> listaProveedor = new ArrayList<>();
        final String sql = "SELECT * FROM proveedor";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt(1));
                proveedor.setCedula(rs.getString(2));
                proveedor.setNombre(rs.getString(3));
                proveedor.setTelefono(rs.getString(4));
                proveedor.setProductos(rs.getString(5));
                listaProveedor.add(proveedor);
            }
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
        return listaProveedor;
    }

    @Override
    public int add(Object[] o) {
        int resultado = 0;
        final String sql = "INSERT INTO proveedor (cedula_prov, nombre_prov, telefono_prov, productos_prov) VALUES (?,?,?,?)";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
        return resultado;
    }

    @Override
    public int actualizar(Object[] obj) {
        int resultado = 0;
        final String sql = "UPDATE proveedor SET cedula_prov = ?, nombre_prov = ?, telefono_prov = ?, productos_prov = ? WHERE id_proveedor = ?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, obj[0]);
            ps.setObject(2, obj[1]);
            ps.setObject(3, obj[2]);
            ps.setObject(4, obj[3]);
            ps.setObject(5, obj[4]);
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
        return resultado;
    }

    @Override
    public void eliminar(int idProveedor) {
        final String sql = "DELETE FROM proveedor WHERE id_proveedor = ?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProveedor);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
    }

}
