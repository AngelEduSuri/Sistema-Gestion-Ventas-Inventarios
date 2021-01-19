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
        String sql = "SELECT * FROM vendedor";
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
    public int add(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
