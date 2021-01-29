package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
    public List listar() { //Metodo para listar todos los productos de la base de datos
        List<Producto> listaProducto = new ArrayList<>(); //Un arrayList para almacenar los productos
        final String sql = "SELECT * FROM productos"; //Sentencia sql para consultar la base de datos
        try {
            con = conexion.conectar(); //Conexion  con la base de datos
            ps = con.prepareStatement(sql); //Enviamos la consulta sql al prepared
            rs = ps.executeQuery(); //Ejecutamos la consulta sql y en el rs guardamos los datos traidos de la base de datos
            while (rs.next()) { //Recorre todos los campos de la base de datos hasta que devuelva un null
                Producto productos = new Producto(); //Creamos un objeto producto con cada campo encontrado en la base de datos
                //Guardamos en el objeto producto cada columna de los datos de la BD
                productos.setIdProd(rs.getInt(1));
                productos.setNombreProd(rs.getString(2));
                productos.setPrecio(rs.getDouble(3));
                productos.setCantidad(rs.getInt(4));
                //Agregamos cada objeto producto al arrayList de productos
                listaProducto.add(productos);
            }
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
        return listaProducto; //Devolvemos ese arrayList de productos
    }

    @Override
    public int add(Object[] obj) { //Metodo que agrega productos a la base de datos recibiendo un arreglo de objetos productos
        int respuesta = 0; //Variable para verificar que se inserto datos a la BD
        final String sql = "INSERT INTO productos(nombre_pro, precio, cantidad) VALUES (?,?,?)"; //Sentencia sql para la BD
        try {
            con = conexion.conectar(); //Conexion con la base de datos
            ps = con.prepareStatement(sql); //Envio de la consulta sql
            //Agino a cada columna de la BD obteniendo los valores del arreglo de ojeto recibido
            ps.setObject(1, obj[0]);
            ps.setObject(2, obj[1]);
            ps.setObject(3, obj[2]);
            respuesta = ps.executeUpdate(); //Respueata recibe un entero mayor a 0 si se inserto datos en la BD
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
        return respuesta; //Retornamos la respuesta 
    }

    @Override
    public int actualizar(Object[] obj) {
        int respuesta = 0; //Respuesta sql
        final String sql = "UPDATE productos SET nombre_pro = ?, precio = ?, cantidad = ? WHERE idproductos = ?"; //Sentencia SQL
        try {
            con = conexion.conectar(); //Obtenemos la conexion 
            ps = con.prepareStatement(sql); //Enviamos la consulta sql
            //Enviamos los datos a la columna de cada areglo de objetos para actualizar
            ps.setObject(1, obj[0]);
            ps.setObject(2, obj[1]);
            ps.setObject(3, obj[2]);
            ps.setObject(4, obj[3]);
            respuesta = ps.executeUpdate(); //Respuesta de confirmacion de la actualizacion
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
        return respuesta; //Retorno de la respuesta de confirmacion
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

    public Vector<Producto> buscarProductoNombre(String buscarProducto) {
        Producto producto;
        final String sql = "select * from productos where nombre_pro like '%" + buscarProducto + "%'";
        Vector<Producto> vectorProductos = new Vector<Producto>();
        con = conexion.conectar();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                producto = new Producto();
                producto.setIdProd(rs.getInt(1));
                producto.setNombreProd(rs.getString(2));
                producto.setPrecio(rs.getDouble(3));
                producto.setCantidad(rs.getInt(4));
                vectorProductos.add(producto);
            }
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en: " + ex);
        }
        return vectorProductos;
    }

    public int[] BuscarProductoPorID(int idProducto) {
        int datos[] = new int[2];
        final String sql = "SELECT producIdProduc, cantidad FROM detalle WHERE producIdProduc=?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();
            while (rs.next()) {
                datos[0] = rs.getInt("producIdProduc");
                datos[1] = rs.getInt("cantidad");
            }
            con.close();
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
        return datos;
    }
    
    public int obtenerStockActual(int idProducto){
        int stockActual = 0;
        final String sql = "SELECT cantidad FROM productos WHERE idproductos=?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();
            while (rs.next()) {
                stockActual = rs.getInt("cantidad");
            }
            con.close();
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
        return stockActual;
    }

    public int actualizarStock(int cantidadActual, int idProducto) {
        int resultado = 0;
        final String sql = "UPDATE productos SET cantidad=? WHERE idproductos=?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cantidadActual);
            ps.setInt(2, idProducto);
            resultado = ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
        return resultado;
    }
}
