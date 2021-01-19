package vista;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;
import modelo.ProductosDatos;

/**
 *
 * @author MATHEO-PC
 */
public class ProductoModulo extends javax.swing.JInternalFrame {

    int idproducto;
    ProductosDatos datosProducto = new ProductosDatos(); //Creo un objeto de la clase datosProductos para tener acceso a los metodos del CRUD
    Producto producto = new Producto(); //Creo un objeto de la clase Producto p
    DefaultTableModel modeloTabla; //Creo un objeto para hacer un modelo a la tabla producto

    public ProductoModulo() {
        initComponents();
        diseñoTabla();
        listarProductos();
    }

    private void diseñoTabla() { //Metodo que le asigna un modelo a la tabla de los productos
        modeloTabla = new DefaultTableModel(); //Creo el modelo de la tabla
        tablaProductos.setModel(modeloTabla); //Asigno a la tabla el modelo

        //Añado las columnas al modelo de la tabla
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Cantidad");

        //Asigno la anchura de las columnas a la tabla
        int anchoColummna[] = {10, 300, 20, 20};
        boolean cambiarAncho = false;

        for (int i = 0; i < anchoColummna.length; i++) {
            tablaProductos.getColumnModel().getColumn(i).setPreferredWidth(anchoColummna[i]);
            //Hago que las columnas no se puedan redimenzionar asignandoles la variable boolean false
            tablaProductos.getColumnModel().getColumn(i).setResizable(cambiarAncho);
        }
    }

    private void listarProductos() { //Metodo para listar los productos en la tabla
        List<Producto> listaProducto = datosProducto.listar();    //Creo un arrayList donde llamo el objeto listar de la clase ProductosDatos
        Object[] datosBaseDatos = new Object[4]; //Creo un arreglo de objeto donde se almacenara los datos obtenido del arrayList 
        for (int i = 0; i < listaProducto.size(); i++) { //El for recorre el tamaño de la lista
            //En cada indice del arreglo, asigno los datos de los productos como id, nombre, precio, cantidad
            datosBaseDatos[0] = listaProducto.get(i).getIdProd();
            datosBaseDatos[1] = listaProducto.get(i).getNombreProd();
            datosBaseDatos[2] = listaProducto.get(i).getPrecio();
            datosBaseDatos[3] = listaProducto.get(i).getCantidad();

            modeloTabla.addRow(datosBaseDatos); //Asigno a cada fila del modelo de la tabla los datos guardado en el arreglo 
        }
        tablaProductos.setModel(modeloTabla); //Asigno dicho modelo de fila a la tabla de los productos
    }

    public void agregarBaseDatos() { //Metodo para agregar productos a la base de datos
        if (txtNombre.getText().isEmpty() || txtPrecio.getText().isEmpty() || txtCantidad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(panelTabla, "Faltan datos por agregar", "Datos Vacios", JOptionPane.WARNING_MESSAGE);
        } else {
            String nombrePro = txtNombre.getText();
            String precio = txtPrecio.getText();
            String cantidad = txtCantidad.getText();

            Object[] objDatos = new Object[3];
            objDatos[0] = nombrePro;
            objDatos[1] = precio;
            objDatos[2] = cantidad;
            datosProducto.add(objDatos);
            JOptionPane.showMessageDialog(panelTabla, "Agregado correctamente", "Articulo agregado", JOptionPane.INFORMATION_MESSAGE);
            limpiarCajasTexto();
            listarProductos();
        }
    }

    private void actualizarDatos() {
        int fila = tablaProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(panelTabla, "Debe seleccionar un articulo", "Seleccione un articulo", JOptionPane.WARNING_MESSAGE);
        } else {
            String nombrePro = txtNombre.getText();
            String precio = txtPrecio.getText();
            String cantidad = txtCantidad.getText();
            Object[] objDatos = new Object[4];
            objDatos[0] = nombrePro;
            objDatos[1] = precio;
            objDatos[2] = cantidad;
            objDatos[3] = idproducto;
            datosProducto.actualizar(objDatos);
            JOptionPane.showMessageDialog(panelTabla, "Actualizado correctamente", "Articulo actualizado", JOptionPane.INFORMATION_MESSAGE);
            limpiarCajasTexto();
            listarProductos();
        }
    }

    public void eliminarProducto() {
        int fila = tablaProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(panelTabla, "Debe seleccionar un articulo", "Seleccione un articulo", JOptionPane.WARNING_MESSAGE);
        } else {
            datosProducto.eliminar(idproducto);
            JOptionPane.showMessageDialog(panelTabla, "Eliminado correctamente", "Articulo eliminado", JOptionPane.INFORMATION_MESSAGE);
            limpiarCajasTexto();
            listarProductos();
        }
    }

    private void agregarDatosDeBaseDatosCajasTexto() {
        int fila = tablaProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(panelTabla, "Debe seleccionar algun articulo", "Seleccione un articulo", JOptionPane.WARNING_MESSAGE);
        } else {
            idproducto = Integer.parseInt(tablaProductos.getValueAt(fila, 0).toString());
            String nombrePro = tablaProductos.getValueAt(fila, 1).toString();
            String precio = tablaProductos.getValueAt(fila, 2).toString();
            String cantidad = tablaProductos.getValueAt(fila, 3).toString();
            txtNombre.setText(nombrePro);
            txtPrecio.setText(precio);
            txtCantidad.setText(cantidad);
        }
    }

    public void limpiarCajasTexto() {
        txtNombre.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtNombre.requestFocus();
        modeloTabla.setRowCount(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelComponentes = new javax.swing.JPanel();
        panelCajas = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        panelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();

        setClosable(true);
        setTitle("Ventana Producto");

        panelComponentes.setBackground(new java.awt.Color(51, 71, 91));
        panelComponentes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        panelCajas.setBackground(new java.awt.Color(51, 71, 91));
        panelCajas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelCajas.setLayout(new java.awt.GridBagLayout());

        lblNombre.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("Nombre Articulo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 0);
        panelCajas.add(lblNombre, gridBagConstraints);

        lblPrecio.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        lblPrecio.setForeground(new java.awt.Color(255, 255, 255));
        lblPrecio.setText("Precio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 0);
        panelCajas.add(lblPrecio, gridBagConstraints);

        lblCantidad.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        lblCantidad.setForeground(new java.awt.Color(255, 255, 255));
        lblCantidad.setText("Cantidad:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 0);
        panelCajas.add(lblCantidad, gridBagConstraints);

        txtNombre.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 5, 10);
        panelCajas.add(txtNombre, gridBagConstraints);

        txtPrecio.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        panelCajas.add(txtPrecio, gridBagConstraints);

        txtCantidad.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        panelCajas.add(txtCantidad, gridBagConstraints);

        btnLimpiar.setBackground(new java.awt.Color(0, 204, 51));
        btnLimpiar.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codigo-limpio.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 10);
        panelCajas.add(btnLimpiar, gridBagConstraints);

        btnAgregar.setBackground(new java.awt.Color(0, 204, 51));
        btnAgregar.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/añadir.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 20, 0);
        panelCajas.add(btnAgregar, gridBagConstraints);

        btnActualizar.setBackground(new java.awt.Color(0, 204, 51));
        btnActualizar.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 10);
        panelCajas.add(btnActualizar, gridBagConstraints);

        btnEliminar.setBackground(new java.awt.Color(0, 204, 51));
        btnEliminar.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 20, 10);
        panelCajas.add(btnEliminar, gridBagConstraints);

        panelTabla.setBackground(new java.awt.Color(51, 71, 91));
        panelTabla.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablaProductos.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Precio", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductos.getTableHeader().setReorderingAllowed(false);
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProductos);
        if (tablaProductos.getColumnModel().getColumnCount() > 0) {
            tablaProductos.getColumnModel().getColumn(0).setResizable(false);
            tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(10);
            tablaProductos.getColumnModel().getColumn(1).setResizable(false);
            tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(300);
            tablaProductos.getColumnModel().getColumn(2).setResizable(false);
            tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(20);
            tablaProductos.getColumnModel().getColumn(3).setResizable(false);
            tablaProductos.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

        javax.swing.GroupLayout panelTablaLayout = new javax.swing.GroupLayout(panelTabla);
        panelTabla.setLayout(panelTablaLayout);
        panelTablaLayout.setHorizontalGroup(
            panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        panelTablaLayout.setVerticalGroup(
            panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelComponentesLayout = new javax.swing.GroupLayout(panelComponentes);
        panelComponentes.setLayout(panelComponentesLayout);
        panelComponentesLayout.setHorizontalGroup(
            panelComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelComponentesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCajas, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelComponentesLayout.setVerticalGroup(
            panelComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComponentesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCajas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelComponentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelComponentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregarBaseDatos();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked
        agregarDatosDeBaseDatosCajasTexto();
    }//GEN-LAST:event_tablaProductosMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizarDatos();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarProducto();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
       limpiarCajasTexto();
       listarProductos();
    }//GEN-LAST:event_btnLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JPanel panelCajas;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
