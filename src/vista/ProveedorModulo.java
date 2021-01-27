package vista;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Proveedor;
import modelo.ProveedorDatos;

/**
 *
 * @author Oswaldo Aguilar
 */
public class ProveedorModulo extends javax.swing.JInternalFrame {

    int idProveedor;
    ProveedorDatos datosProveedor = new ProveedorDatos();
    Proveedor proveedor = new Proveedor();
    DefaultTableModel modeloTabla; //Creo un objeto para hacer un modelo a la tabla proveedor

    public ProveedorModulo() {
        initComponents();
        diseñoTabla();
        listarProveedores();

    }

    private void diseñoTabla() { //Metodo que le asigna un modelo a la tabla de los productos
        modeloTabla = new DefaultTableModel(); //Creo el modelo de la tabla
        tablaProveedor.setModel(modeloTabla); //Asigno a la tabla el modelo

        //Añado las columnas al modelo de la tabla
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Cédula");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Productos");

        //Asigno la anchura de las columnas a la tabla
        int anchoColummna[] = {5, 30, 120, 30, 120};
        boolean cambiarAncho = false;

        for (int i = 0; i < anchoColummna.length; i++) {
            tablaProveedor.getColumnModel().getColumn(i).setPreferredWidth(anchoColummna[i]);
            //Hago que las columnas no se puedan redimenzionar asignandoles la variable boolean false
            tablaProveedor.getColumnModel().getColumn(i).setResizable(cambiarAncho);
        }
    }

    private void listarProveedores() {
        List<Proveedor> lista = datosProveedor.listar();
        Object[] objProv = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            objProv[0] = lista.get(i).getIdProveedor();
            objProv[1] = lista.get(i).getCedula();
            objProv[2] = lista.get(i).getNombre();
            objProv[3] = lista.get(i).getTelefono();
            objProv[4] = lista.get(i).getProductos();
            modeloTabla.addRow(objProv);
        }
        tablaProveedor.setModel(modeloTabla);
    }

    private void agregarProveedor() {
        if (txtCedula.getText().isEmpty() || txtNombres.getText().isEmpty()) {
            JOptionPane.showMessageDialog(panelTabla, "Debe agregar al menos Cedula y Nombre del proveedor", "Faltan Datos", JOptionPane.WARNING_MESSAGE);
        } else {
            String cedula = txtCedula.getText();
            String nombre = txtNombres.getText();
            String telefono = txtTelefono.getText();
            String productos = txtProductos.getText();

            Object[] objDatos = new Object[4];
            objDatos[0] = cedula;
            objDatos[1] = nombre;
            objDatos[2] = telefono;
            objDatos[3] = productos;
            if (datosProveedor.add(objDatos) > 0) {
                JOptionPane.showMessageDialog(panelTabla, "Agregado exitosamente", "Proveedor Agregado", JOptionPane.INFORMATION_MESSAGE);
                limpiarCajasTexto();
                listarProveedores();
            } else {
                JOptionPane.showMessageDialog(panelTabla, "Error al agregar", "Proveedor no agregado", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void actualizarProveedor() {
        int fila = tablaProveedor.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(panelTabla, "Debe seleccionar un proveedor", "Seleccione un proveedor", JOptionPane.WARNING_MESSAGE);
        } else {
            String cedula = txtCedula.getText();
            String nombre = txtNombres.getText();
            String telefono = txtTelefono.getText();
            String productos = txtProductos.getText();
            Object[] objDatos = new Object[5];
            objDatos[0] = cedula;
            objDatos[1] = nombre;
            objDatos[2] = telefono;
            objDatos[3] = productos;
            objDatos[4] = idProveedor;
            if (datosProveedor.actualizar(objDatos) > 0) {
                JOptionPane.showMessageDialog(panelTabla, "Actualizado exitosamente", "Proveedor Actualizado", JOptionPane.INFORMATION_MESSAGE);
                limpiarCajasTexto();
                listarProveedores();
            } else {
                JOptionPane.showMessageDialog(panelTabla, "Error al actualizar", "Proveedor no actualizado", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void eliminarProveedor() {
        int fila = tablaProveedor.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(panelTabla, "Debe seleccionar un proveedor", "Seleccione un proveedor", JOptionPane.WARNING_MESSAGE);
        } else {
            datosProveedor.eliminar(idProveedor);
            JOptionPane.showMessageDialog(panelTabla, "Eliminado correctamente", "Proveedor eliminado", JOptionPane.INFORMATION_MESSAGE);
            limpiarCajasTexto();
            listarProveedores();
        }
    }
    
    private void agregarDatosDeBaseDatosCajasTexto() {
        int fila = tablaProveedor.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(panelTabla, "Debe seleccionar algun proveedor", "Seleccione un proveedor", JOptionPane.WARNING_MESSAGE);
        } else {
            idProveedor = Integer.parseInt(tablaProveedor.getValueAt(fila, 0).toString());
            String cedula = tablaProveedor.getValueAt(fila, 1).toString();
            String nombre = tablaProveedor.getValueAt(fila, 2).toString();
            String telefono = tablaProveedor.getValueAt(fila, 3).toString();
            String productos = tablaProveedor.getValueAt(fila, 4).toString();
            txtCedula.setText(cedula);
            txtNombres.setText(nombre);
            txtTelefono.setText(telefono);
            txtProductos.setText(productos);
        }
    }     

    private void limpiarCajasTexto() {
        txtCedula.setText(null);
        txtNombres.setText(null);
        txtTelefono.setText(null);
        txtProductos.setText(null);
        txtCedula.requestFocus();
        modeloTabla.setRowCount(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelComponentes = new javax.swing.JPanel();
        panelCajas = new javax.swing.JPanel();
        lblCedula = new javax.swing.JLabel();
        lblNombres = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblProductos = new javax.swing.JLabel();
        txtProductos = new javax.swing.JTextField();
        panelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProveedor = new javax.swing.JTable();

        setClosable(true);
        setTitle("Ventana Proveedor");

        panelComponentes.setBackground(new java.awt.Color(51, 71, 91));
        panelComponentes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        panelCajas.setBackground(new java.awt.Color(51, 71, 91));
        panelCajas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelCajas.setLayout(new java.awt.GridBagLayout());

        lblCedula.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        lblCedula.setForeground(new java.awt.Color(255, 255, 255));
        lblCedula.setText("Cédula:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 15);
        panelCajas.add(lblCedula, gridBagConstraints);

        lblNombres.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        lblNombres.setForeground(new java.awt.Color(255, 255, 255));
        lblNombres.setText("Nombres:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 15);
        panelCajas.add(lblNombres, gridBagConstraints);

        lblTelefono.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono.setText("Teléfono:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 15);
        panelCajas.add(lblTelefono, gridBagConstraints);

        txtCedula.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 5, 10);
        panelCajas.add(txtCedula, gridBagConstraints);

        txtNombres.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 5, 10);
        panelCajas.add(txtNombres, gridBagConstraints);

        txtTelefono.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 5, 10);
        panelCajas.add(txtTelefono, gridBagConstraints);

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
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
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
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
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
        gridBagConstraints.gridy = 4;
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
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 20, 10);
        panelCajas.add(btnEliminar, gridBagConstraints);

        lblProductos.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        lblProductos.setForeground(new java.awt.Color(255, 255, 255));
        lblProductos.setText("Productos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 15);
        panelCajas.add(lblProductos, gridBagConstraints);

        txtProductos.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 5, 10);
        panelCajas.add(txtProductos, gridBagConstraints);

        panelTabla.setBackground(new java.awt.Color(51, 71, 91));
        panelTabla.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablaProveedor.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tablaProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Cédula", "Nombres", "Teléfono", "Productos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProveedor.getTableHeader().setReorderingAllowed(false);
        tablaProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProveedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProveedor);
        if (tablaProveedor.getColumnModel().getColumnCount() > 0) {
            tablaProveedor.getColumnModel().getColumn(0).setResizable(false);
            tablaProveedor.getColumnModel().getColumn(0).setPreferredWidth(5);
            tablaProveedor.getColumnModel().getColumn(1).setResizable(false);
            tablaProveedor.getColumnModel().getColumn(1).setPreferredWidth(30);
            tablaProveedor.getColumnModel().getColumn(2).setResizable(false);
            tablaProveedor.getColumnModel().getColumn(2).setPreferredWidth(120);
            tablaProveedor.getColumnModel().getColumn(3).setResizable(false);
            tablaProveedor.getColumnModel().getColumn(3).setPreferredWidth(30);
            tablaProveedor.getColumnModel().getColumn(4).setResizable(false);
            tablaProveedor.getColumnModel().getColumn(4).setPreferredWidth(120);
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
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
                .addComponent(panelCajas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        agregarProveedor();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizarProveedor();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tablaProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProveedorMouseClicked
        agregarDatosDeBaseDatosCajasTexto();
    }//GEN-LAST:event_tablaProveedorMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarProveedor();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCajasTexto();
        listarProveedores();
    }//GEN-LAST:event_btnLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblProductos;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JPanel panelCajas;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JTable tablaProveedor;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtProductos;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
