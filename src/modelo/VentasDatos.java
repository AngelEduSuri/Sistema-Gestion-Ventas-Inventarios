package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Angel Eduardo Suriaga
 */
public class VentasDatos {

    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resultado = 0;

    public String numSerieVentas() {
        String serie = null;
        final String sql = "SELECT max(numeroVenta) FROM ventas";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                serie = rs.getString(1);
            }
        } catch (SQLException e) {
            System.err.println("Error en : " + e);
        }
        return serie;
    }

    public int guardarVentas(Ventas ven) {
        final String sql = "INSERT INTO ventas(idvendedo, nombreCliente, numeroVenta, fechaVenta, monto) VALUES (?,?,?,?,?)";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ven.getIdVendedor());
            ps.setString(2, ven.getCliente());
            ps.setString(3, ven.getSerie());
            ps.setString(4, ven.getFecha());
            ps.setDouble(5, ven.getMonto());
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en : " + e);
        }
        return resultado;
    }

    public int guardarDetalleVentas(DetalleVentas dellateVenta) {
        final String sql = "INSERT INTO detalle(ventasIdVentas, producIdProduc, nombreProd, cantidad, precio, total, fecha) VALUES (?,?,?,?,?,?,?)";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dellateVenta.getIdVentas());
            ps.setInt(2, dellateVenta.getIdProducto());
            ps.setString(3, dellateVenta.getNombrePro());
            ps.setInt(4, dellateVenta.getCantidad());
            ps.setDouble(5, dellateVenta.getPrecio());
            ps.setDouble(6, dellateVenta.getTotal());
            ps.setString(7, dellateVenta.getFecha());
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en : " + e);
        }
        return resultado;
    }
    
    public String idVentas() {
        String idVenta = null;
        final String sql = "SELECT max(idventas) FROM ventas";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                idVenta = rs.getString(1);
            }
        } catch (SQLException e) {
            System.err.println("Error en : " + e);
        }
        return idVenta;
    }
}
