
package com.aesuriagasalazar.modelo;

import java.util.List;

/**
 *
 * @author Angel Eduardo Suriaga
 */
public interface Crud {
        
    //Metodos para implementar en las clases 
        
    public List listar(); //Mostrar los productos, vendedores, proveedores en cada tabla

    public int add(Object[] o); //Añadir productos, vendedores, proveedores en la Base de datos

    public int actualizar(Object[] o); //Actualizar productos, vendedores, proveedores en la Base de datos

    public void eliminar(int id); //Eliminar productos, vendedores, proveedores en la Base de datos
}
