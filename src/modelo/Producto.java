package modelo;

/**
 *
 * @author Karla Minga Herrera
 */
public class Producto {
    //Variables para los objetos productos
    private int idProd;
    private String nombreProd;
    private double precio;
    private int cantidad;
    
    
    //Se crean los metodos get y set para asignar y obtener los datos
    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
