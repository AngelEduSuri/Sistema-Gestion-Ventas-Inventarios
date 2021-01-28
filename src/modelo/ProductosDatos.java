package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Karla Minga Herrera
 */
public class ProductosDatos implements Crud {

    PreparedStatement ps;
    ResultSet rs;

    Conexion conexion = new Conexion();
    Connection con;

    @Override
    public List listar() {
        List<Producto> listaProducto = new ArrayList<>();
        final String sql = "SELECT * FROM productos";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto productos = new Producto();
                productos.setIdProd(rs.getInt(1));
                productos.setNombreProd(rs.getString(2));
                productos.setPrecio(rs.getDouble(3));
                productos.setCantidad(rs.getInt(4));
                listaProducto.add(productos);
            }
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
        return listaProducto;
    }

    @Override
    public int add(Object[] obj) {
        int respuesta = 0;
        final String sql = "INSERT INTO productos(nombre_pro, precio, cantidad) VALUES (?,?,?)";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, obj[0]);
            ps.setObject(2, obj[1]);
            ps.setObject(3, obj[2]);
            respuesta = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
        return respuesta;
    }

    @Override
    public int actualizar(Object[] obj) {
        int respuesta = 0;
        final String sql = "UPDATE productos SET nombre_pro = ?, precio = ?, cantidad = ? WHERE idproductos = ?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, obj[0]);
            ps.setObject(2, obj[1]);
            ps.setObject(3, obj[2]);
            ps.setObject(4, obj[3]);
            respuesta = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
        return respuesta;
    }

    @Override
    public void eliminar(int idProducto) {
        final String sql = "DELETE FROM productos WHERE idproductos = ?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProducto);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
    }

    public Producto BuscarProductoPorID(int idProducto) {
        Producto producto = new Producto();
        final String sql = "SELECT *FROM productos WHERE idproductos=?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();
            while (rs.next()) {
                producto.setIdProd(rs.getInt(1));
                producto.setNombreProd(rs.getString(2));
                producto.setPrecio(rs.getDouble(3));
                producto.setCantidad(rs.getInt(4));
            }
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
        return producto;
    }

    public int actualizarStock(int cantidad, int idProducto) {
        int resultado = 0;
        final String sql = "UPDATE productos SET cantidad=? WHERE idproductos=?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cantidad);
            ps.setInt(2, idProducto);
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
        return resultado;
    }
}
