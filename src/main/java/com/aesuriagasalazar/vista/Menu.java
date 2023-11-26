package com.aesuriagasalazar.vista;

import com.aesuriagasalazar.modelo.Vendedor;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Angel Eduardo Suriaga
 */
public class Menu extends javax.swing.JFrame {

    ProductoModulo ventanaProducto;
    ProveedorModulo ventanaProveedor;
    VendedorModulo ventanaVendedor;
    VentasModulo ventanaVentas;
    ReportesModulo ventanaReportes;
    public static int contador = 0;
    public static Vendedor vendedor;

    public Menu() {
        initComponents();
        ventana(); //Llamamos al metodo en el constructor para aplicar las caracteristicas
        iconoBotones();
    }

    //Segundo constructor que recibe los datos del vendedor
    public Menu(Vendedor vendedor) {
        initComponents();
        ventana(); //Llamamos al metodo en el constructor para aplicar las caracteristicas
        iconoBotones();
        Menu.vendedor = vendedor;
        lblMensajeUsuario.setText("Bienvenido: " + vendedor.getNombre());
        if (!vendedor.getUsuario().equals("administrador")) {
            btnVendedor.setEnabled(false);
            btnReportes.setEnabled(false);
        }
    }

    private void ventana() { //Metodo para inicializar la ventana maximizada y con titulo
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("SISTEMA DE GESTION DE VENTAS E INVENTARIOS - MI PAPELERIA");
    }

    //Metodo para asignar los iconos a los botones
    private void iconoBotones() {
        ImageIcon iconoVenta = new ImageIcon(getClass().getResource("/drawables/shopping_bag_icon.png"));
        int anchoVenta = btnNuevaVenta.getWidth();
        int altoVenta = btnNuevaVenta.getHeight();
        ImageIcon iconUno = new ImageIcon(iconoVenta.getImage().getScaledInstance(anchoVenta, altoVenta, Image.SCALE_SMOOTH));
        btnNuevaVenta.setIcon(iconUno);

        ImageIcon iconoProducto = new ImageIcon(getClass().getResource("/drawables/inventory_icon.png"));
        int anchoProducto = btnProductos.getWidth();
        int altoProducto = btnProductos.getHeight();
        ImageIcon iconDos = new ImageIcon(iconoProducto.getImage().getScaledInstance(anchoProducto, altoProducto, Image.SCALE_SMOOTH));
        btnProductos.setIcon(iconDos);

        ImageIcon iconoVendedor = new ImageIcon(getClass().getResource("/drawables/seller_icon.png"));
        int anchoVendedor = btnVendedor.getWidth();
        int altoVendedor = btnProveedor.getHeight();
        ImageIcon iconTres = new ImageIcon(iconoVendedor.getImage().getScaledInstance(anchoVendedor, altoVendedor, Image.SCALE_SMOOTH));
        btnVendedor.setIcon(iconTres);

        ImageIcon iconoReporte = new ImageIcon(getClass().getResource("/drawables/report_icon.png"));
        int anchoReporte = btnReportes.getWidth();
        int altoReporte = btnReportes.getHeight();
        ImageIcon iconCuatro = new ImageIcon(iconoReporte.getImage().getScaledInstance(anchoReporte, altoReporte, Image.SCALE_SMOOTH));
        btnReportes.setIcon(iconCuatro);

        ImageIcon iconoProveedor = new ImageIcon(getClass().getResource("/drawables/provider.png"));
        int anchoProveedor = btnProveedor.getWidth();
        int altoProveedor = btnProveedor.getHeight();
        ImageIcon iconCinco = new ImageIcon(iconoProveedor.getImage().getScaledInstance(anchoProveedor, altoProveedor, Image.SCALE_SMOOTH));
        btnProveedor.setIcon(iconCinco);

        ImageIcon iconoSalir = new ImageIcon(getClass().getResource("/drawables/close_icon.png"));
        int anchoSalir = btnSalir.getWidth();
        int altoSalir = btnSalir.getHeight();
        ImageIcon iconSeis = new ImageIcon(iconoSalir.getImage().getScaledInstance(anchoSalir, altoSalir, Image.SCALE_SMOOTH));
        btnSalir.setIcon(iconSeis);
    }

    private void centrar(JInternalFrame ventana) {
        ventanaModulos.add(ventana);
        Dimension desktopSize = ventanaModulos.getSize();
        Dimension FrameSize = ventana.getSize();
        ventana.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        ventana.show();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelFondo = new javax.swing.JPanel();
        panelBotones = new javax.swing.JPanel();
        btnNuevaVenta = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnVendedor = new javax.swing.JButton();
        btnProveedor = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblMensajeUsuario = new javax.swing.JLabel();
        panelVentana = new javax.swing.JPanel();
        ImageIcon fondo = new ImageIcon(getClass().getResource("/drawables/stationery_store_background.jpg"));
        Image img = fondo.getImage();
        ventanaModulos = new javax.swing.JDesktopPane(){

            public void paintComponent(Graphics g){
                g.drawImage(img, 0, 0, getWidth(), 
                    getHeight(),this);
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        panelFondo.setLayout(new java.awt.GridBagLayout());

        panelBotones.setBackground(new java.awt.Color(0, 0, 51));
        panelBotones.setLayout(new java.awt.GridBagLayout());

        btnNuevaVenta.setBackground(new java.awt.Color(0, 102, 102));
        btnNuevaVenta.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        btnNuevaVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevaVenta.setText("Nueva Venta");
        btnNuevaVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevaVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevaVenta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaVentaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 5);
        panelBotones.add(btnNuevaVenta, gridBagConstraints);

        btnProductos.setBackground(new java.awt.Color(0, 102, 102));
        btnProductos.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        btnProductos.setForeground(new java.awt.Color(255, 255, 255));
        btnProductos.setText("Productos");
        btnProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProductos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 10);
        panelBotones.add(btnProductos, gridBagConstraints);

        btnVendedor.setBackground(new java.awt.Color(0, 102, 102));
        btnVendedor.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        btnVendedor.setForeground(new java.awt.Color(255, 255, 255));
        btnVendedor.setText("Vendedores");
        btnVendedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVendedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVendedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendedorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        panelBotones.add(btnVendedor, gridBagConstraints);

        btnProveedor.setBackground(new java.awt.Color(0, 102, 102));
        btnProveedor.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        btnProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnProveedor.setText("Proveedores");
        btnProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProveedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProveedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        panelBotones.add(btnProveedor, gridBagConstraints);

        btnReportes.setBackground(new java.awt.Color(0, 102, 102));
        btnReportes.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        btnReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes.setText("Reportes");
        btnReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReportes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 5);
        panelBotones.add(btnReportes, gridBagConstraints);

        btnSalir.setBackground(new java.awt.Color(0, 102, 102));
        btnSalir.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Salir");
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 10);
        panelBotones.add(btnSalir, gridBagConstraints);

        lblMensajeUsuario.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        lblMensajeUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblMensajeUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMensajeUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblMensajeUsuario.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        panelBotones.add(lblMensajeUsuario, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.3;
        panelFondo.add(panelBotones, gridBagConstraints);

        panelVentana.setBackground(new java.awt.Color(51, 51, 0));

        javax.swing.GroupLayout ventanaModulosLayout = new javax.swing.GroupLayout(ventanaModulos);
        ventanaModulos.setLayout(ventanaModulosLayout);
        ventanaModulosLayout.setHorizontalGroup(
            ventanaModulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );
        ventanaModulosLayout.setVerticalGroup(
            ventanaModulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelVentanaLayout = new javax.swing.GroupLayout(panelVentana);
        panelVentana.setLayout(panelVentanaLayout);
        panelVentanaLayout.setHorizontalGroup(
            panelVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ventanaModulos)
        );
        panelVentanaLayout.setVerticalGroup(
            panelVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ventanaModulos)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        panelFondo.add(panelVentana, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        if (contador == 0 && ventanaProducto == null) {
            ventanaProducto = new ProductoModulo();
            centrar(ventanaProducto);
            contador++;
            ventanaProducto = null;
        } else {
            JOptionPane.showMessageDialog(panelVentana, "Un modulo ya se encuentra abierto", "Abierto", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorActionPerformed
        if (contador == 0 && ventanaProveedor == null) {
            ventanaProveedor = new ProveedorModulo();
            centrar(ventanaProveedor);
            contador++;
            ventanaProveedor = null;
        } else {
            JOptionPane.showMessageDialog(panelVentana, "Un modulo ya se encuentra abierto", "Abierto", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnProveedorActionPerformed

    private void btnVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendedorActionPerformed
        if (contador == 0 && ventanaVendedor == null) {
            ventanaVendedor = new VendedorModulo();
            centrar(ventanaVendedor);
            contador++;
            ventanaVendedor = null;
        } else {
            JOptionPane.showMessageDialog(panelVentana, "Un modulo ya se encuentra abierto", "Abierto", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnVendedorActionPerformed

    private void btnNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaVentaActionPerformed
        if (contador == 0 && ventanaVentas == null) {
            ventanaVentas = new VentasModulo();
            centrar(ventanaVentas);
            contador++;
            ventanaVentas = null;
        } else {
            JOptionPane.showMessageDialog(panelVentana, "Un modulo ya se encuentra abierto", "Abierto", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnNuevaVentaActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        if (contador == 0 && ventanaReportes == null) {
            ventanaReportes = new ReportesModulo();
            centrar(ventanaReportes);
            contador++;
            ventanaReportes = null;
        } else {
            JOptionPane.showMessageDialog(panelVentana, "Un modulo ya se encuentra abierto", "Abierto", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnReportesActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        try {

            UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevaVenta;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedor;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVendedor;
    private javax.swing.JLabel lblMensajeUsuario;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPanel panelVentana;
    private javax.swing.JDesktopPane ventanaModulos;
    // End of variables declaration//GEN-END:variables
}
