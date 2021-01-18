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
    public int add(Object[] o) {
        return 0;

    }

    @Override
    public int actualizar(Object[] o) {
        return 0;

    }

    @Override
    public void eliminar(int id) {

    }

}
