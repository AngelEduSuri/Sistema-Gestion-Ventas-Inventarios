package com.aesuriagasalazar.vista;


import com.aesuriagasalazar.modelo.Vendedor;
import com.aesuriagasalazar.modelo.VendedorDatos;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;


/**
 *
 * @author HERNAN ESTUARDO ZARATE VELASCO
 */
public class VendedorModulo extends javax.swing.JInternalFrame {

    int idVendedor;
    VendedorDatos datosVendedor = new VendedorDatos();
    Vendedor vendedor = new Vendedor();
    DefaultTableModel modeloTabla;

    public VendedorModulo() {
        initComponents();
        diseñoTabla();
        listarVendedor();
    }

    private void diseñoTabla() { //Metodo que le asigna un modelo a la tabla de los vendedor
        modeloTabla = new DefaultTableModel(); //Creo el modelo de la tabla
        tablaVendedor.setModel(modeloTabla); //Asigno a la tabla el modelo

        //Añado las columnas al modelo de la tabla
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Usuario");
        modeloTabla.addColumn("Cédula");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Teléfono");

        //Asigno la anchura de las columnas a la tabla
        int anchoColummna[] = {5, 30, 30, 200, 30};
        boolean cambiarAncho = false;

        for (int i = 0; i < anchoColummna.length; i++) {
            tablaVendedor.getColumnModel().getColumn(i).setPreferredWidth(anchoColummna[i]);
            //Hago que las columnas no se puedan redimenzionar asignandoles la variable boolean false
            tablaVendedor.getColumnModel().getColumn(i).setResizable(cambiarAncho);
        }
    }

    private void listarVendedor() {
        List<Vendedor> listaVendedor = datosVendedor.listar();
        Object[] ob = new Object[5];
        for (int i = 0; i < listaVendedor.size(); i++) {
            ob[0] = listaVendedor.get(i).getIdVendedor();
            ob[1] = listaVendedor.get(i).getUsuario();
            ob[2] = listaVendedor.get(i).getCedula();
            ob[3] = listaVendedor.get(i).getNombre();
            ob[4] = listaVendedor.get(i).getTelefono();
            modeloTabla.addRow(ob);
        }
        tablaVendedor.setModel(modeloTabla);
    }

    private void agregarVendedor() {
        if (txtUsuario.getText().isEmpty() || txtCedula.getText().isEmpty() || txtNombres.getText().isEmpty() || txtTelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(panelTabla, "No puede agregar datos vacios", "Faltan Datos", JOptionPane.WARNING_MESSAGE);
        } else {
            String usuarioVen = txtUsuario.getText();
            String cedulaVen = txtCedula.getText();
            String nombreVen = txtNombres.getText();
            String telefonoVen = txtTelefono.getText();

            Object[] objDatos = new Object[4];
            objDatos[0] = usuarioVen;
            objDatos[1] = cedulaVen;
            objDatos[2] = nombreVen;
            objDatos[3] = telefonoVen;
            if (datosVendedor.add(objDatos) > 0) {
                JOptionPane.showMessageDialog(panelTabla, "Agregado exitosamente", "Vendedor agregado", JOptionPane.INFORMATION_MESSAGE);
                limpiarCajasTexto();
                listarVendedor();
            } else {
                JOptionPane.showMessageDialog(panelTabla, "Error al agregar", "Vendedor no agregado", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void actualizarVendedor() {
        int fila = tablaVendedor.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(panelTabla, "Debe seleccionar un vendedor", "Seleccione un vendedor", JOptionPane.WARNING_MESSAGE);
        } else {
            String usuairoVen = txtUsuario.getText();
            String cedulaVen = txtCedula.getText();
            String nombreVen = txtNombres.getText();
            String telefonoVen = txtTelefono.getText();
            Object[] objDatos = new Object[5];
            objDatos[0] = usuairoVen;
            objDatos[1] = cedulaVen;
            objDatos[2] = nombreVen;
            objDatos[3] = telefonoVen;
            objDatos[4] = idVendedor;
            if (datosVendedor.actualizar(objDatos) > 0) {
                JOptionPane.showMessageDialog(panelTabla, "Actualizado exitosamente", "Vendedor actualizado", JOptionPane.INFORMATION_MESSAGE);
                limpiarCajasTexto();
                listarVendedor();
            } else {
                JOptionPane.showMessageDialog(panelTabla, "Error al actualizar", "Vendedor no actualizado", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarVendedor() {
        int fila = tablaVendedor.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(panelTabla, "Debe seleccionar un vendedor", "Seleccione un vendedor", JOptionPane.WARNING_MESSAGE);
        } else {
            datosVendedor.eliminar(idVendedor);
            JOptionPane.showMessageDialog(panelTabla, "Eliminado correctamente", "Vendedor eliminado", JOptionPane.INFORMATION_MESSAGE);
            limpiarCajasTexto();
            listarVendedor();
        }
    }
    
    private void agregarDatosDeBaseDatosCajasTexto() {
        int fila = tablaVendedor.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(panelTabla, "Debe seleccionar un vendedor", "Seleccione un vendedor", JOptionPane.WARNING_MESSAGE);
        } else {
            idVendedor = Integer.parseInt(tablaVendedor.getValueAt(fila, 0).toString());
            String usuairoVen = tablaVendedor.getValueAt(fila, 1).toString();
            String cedulaVen = tablaVendedor.getValueAt(fila, 2).toString();
            String nombreVen = tablaVendedor.getValueAt(fila, 3).toString();
            String telefonoVen = tablaVendedor.getValueAt(fila, 4).toString();
            txtUsuario.setText(usuairoVen);
            txtCedula.setText(cedulaVen);
            txtNombres.setText(nombreVen);
            txtTelefono.setText(telefonoVen);
        }
    }

    private void limpiarCajasTexto() {
        txtUsuario.setText(null);
        txtCedula.setText(null);
        txtNombres.setText(null);
        txtTelefono.setText(null);
        txtUsuario.requestFocus();
        modeloTabla.setRowCount(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelComponentes = new javax.swing.JPanel();
        panelCajas = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblCedula = new javax.swing.JLabel();
        lblNombres = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        panelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVendedor = new javax.swing.JTable();

        setClosable(true);
        setTitle("Ventana Vendedor");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        panelComponentes.setBackground(new java.awt.Color(51, 71, 91));
        panelComponentes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        panelCajas.setBackground(new java.awt.Color(51, 71, 91));
        panelCajas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelCajas.setLayout(new java.awt.GridBagLayout());

        lblUsuario.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("Usuario:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 15);
        panelCajas.add(lblUsuario, gridBagConstraints);

        lblCedula.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        lblCedula.setForeground(new java.awt.Color(255, 255, 255));
        lblCedula.setText("Cédula:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 15);
        panelCajas.add(lblCedula, gridBagConstraints);

        lblNombres.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        lblNombres.setForeground(new java.awt.Color(255, 255, 255));
        lblNombres.setText("Nombres:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 15);
        panelCajas.add(lblNombres, gridBagConstraints);

        txtUsuario.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 25, 5, 10);
        panelCajas.add(txtUsuario, gridBagConstraints);

        txtCedula.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 5, 10);
        panelCajas.add(txtCedula, gridBagConstraints);

        txtNombres.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 25, 5, 10);
        panelCajas.add(txtNombres, gridBagConstraints);

        btnLimpiar.setBackground(new java.awt.Color(0, 204, 51));
        btnLimpiar.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/clear_icon.png"))); // NOI18N
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
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/add_icon.png"))); // NOI18N
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
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/update_icon.png"))); // NOI18N
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
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/delete_icon.png"))); // NOI18N
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

        lblTelefono.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono.setText("Teléfono:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 15);
        panelCajas.add(lblTelefono, gridBagConstraints);

        txtTelefono.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(10, 25, 5, 10);
        panelCajas.add(txtTelefono, gridBagConstraints);

        panelTabla.setBackground(new java.awt.Color(51, 71, 91));
        panelTabla.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablaVendedor.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tablaVendedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Usuario", "Cédula", "Nombre", "Teléfono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaVendedor.getTableHeader().setReorderingAllowed(false);
        tablaVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVendedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaVendedor);
        if (tablaVendedor.getColumnModel().getColumnCount() > 0) {
            tablaVendedor.getColumnModel().getColumn(0).setResizable(false);
            tablaVendedor.getColumnModel().getColumn(0).setPreferredWidth(5);
            tablaVendedor.getColumnModel().getColumn(1).setResizable(false);
            tablaVendedor.getColumnModel().getColumn(1).setPreferredWidth(30);
            tablaVendedor.getColumnModel().getColumn(2).setResizable(false);
            tablaVendedor.getColumnModel().getColumn(2).setPreferredWidth(30);
            tablaVendedor.getColumnModel().getColumn(3).setResizable(false);
            tablaVendedor.getColumnModel().getColumn(3).setPreferredWidth(200);
            tablaVendedor.getColumnModel().getColumn(4).setResizable(false);
            tablaVendedor.getColumnModel().getColumn(4).setPreferredWidth(30);
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
        agregarVendedor();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tablaVendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVendedorMouseClicked
        agregarDatosDeBaseDatosCajasTexto();
    }//GEN-LAST:event_tablaVendedorMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizarVendedor();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarVendedor();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCajasTexto();
        listarVendedor();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        if (evt.getInternalFrame().isClosable()){
            Menu.contador = 0;                   
        }
    }//GEN-LAST:event_formInternalFrameClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panelCajas;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JTable tablaVendedor;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
