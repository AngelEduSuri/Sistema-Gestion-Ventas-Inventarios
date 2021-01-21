package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Angel Suriaga
 */
public class VentasDatos {

    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resultado = 0;

    public String numSerieVentas() {
        String serie = null;
        String sql = "SELECT max(numeroVenta) FROM ventas";
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
        Ventas ventas = new Ventas();
        String sql = "INSERT INTO ventas(idvendedo, nombreCliente, numeroVenta, fechaVenta, monto) VALUES (?,?,?,?,?)";
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

    public int guardarDetalleVentas(DetalleVentas dv) {
        String sql = "INSERT INTO detalle(ventasIdVentas, producIdProduc, nombreProd, cantidad, precio, total, fecha) VALUES (?,?,?,?,?,?,?)";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dv.getIdVentas());
            ps.setInt(2, dv.getIdProducto());
            ps.setString(3, dv.getNombrePro());
            ps.setInt(4, dv.getCantidad());
            ps.setDouble(5, dv.getPrecio());
            ps.setDouble(6, dv.getTotal());
            ps.setString(7, dv.getFecha());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en : " + e);
        }
        return resultado;
    }
}
