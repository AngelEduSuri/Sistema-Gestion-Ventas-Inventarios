package vista;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

/**
 *
 * @author AngelEdu
 */
public class Menu extends javax.swing.JFrame {

    public Menu() {
        initComponents();
        ventana(); //Llamamos al metodo en el constructor para aplicar las caracteristicas
        iconoBotones();
    }

    private void ventana() { //Metodo para inicializar la ventana maximizada y con titulo
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("SISTEMA DE GESTION DE VENTAS E INVENTARIOS");
       this.setMinimumSize(new Dimension(1000, 600));             
    }
    
    //Metodo para asignar los iconos a los botones
    private void iconoBotones(){
        ImageIcon iconoVenta = new ImageIcon(getClass().getResource("/img/bolsa-de-la-compra.png"));
        int anchoVenta = btnNuevaVenta.getWidth();
        int altoVenta = btnNuevaVenta.getHeight();
        ImageIcon iconUno = new ImageIcon(iconoVenta.getImage().getScaledInstance(anchoVenta, altoVenta, Image.SCALE_SMOOTH));
        btnNuevaVenta.setIcon(iconUno);
        
        ImageIcon iconoProducto = new ImageIcon(getClass().getResource("/img/inventario.png"));
        int anchoProducto = btnProductos.getWidth();
        int altoProducto = btnProductos.getHeight();
        ImageIcon iconDos = new ImageIcon(iconoProducto.getImage().getScaledInstance(anchoProducto, altoProducto, Image.SCALE_SMOOTH));
        btnProductos.setIcon(iconDos);
        
        ImageIcon iconoVendedor = new ImageIcon(getClass().getResource("/img/vendedor.png"));
        int anchoVendedor = btnVendedor.getWidth();
        int altoVendedor = btnProveedor.getHeight();
        ImageIcon iconTres = new ImageIcon(iconoVendedor.getImage().getScaledInstance(anchoVendedor, altoVendedor, Image.SCALE_SMOOTH));
        btnVendedor.setIcon(iconTres);
        
        ImageIcon iconoReporte = new ImageIcon(getClass().getResource("/img/reporte.png"));
        int anchoReporte = btnReportes.getWidth();
        int altoReporte = btnReportes.getHeight();
        ImageIcon iconCuatro = new ImageIcon(iconoReporte.getImage().getScaledInstance(anchoReporte, altoReporte, Image.SCALE_SMOOTH));
        btnReportes.setIcon(iconCuatro);
        
        ImageIcon iconoProveedor = new ImageIcon(getClass().getResource("/img/proveedor.png"));
        int anchoProveedor= btnProveedor.getWidth();
        int altoProveedor = btnProveedor.getHeight();
        ImageIcon iconCinco = new ImageIcon(iconoProveedor.getImage().getScaledInstance(anchoProveedor, altoProveedor, Image.SCALE_SMOOTH));
        btnProveedor.setIcon(iconCinco);
        
        ImageIcon iconoSalir = new ImageIcon(getClass().getResource("/img/log-out.png"));
        int anchoSalir= btnSalir.getWidth();
        int altoSalir = btnSalir.getHeight();
        ImageIcon iconSeis = new ImageIcon(iconoSalir.getImage().getScaledInstance(anchoSalir, altoSalir, Image.SCALE_SMOOTH));
        btnSalir.setIcon(iconSeis);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelFondo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnNuevaVenta = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnVendedor = new javax.swing.JButton();
        btnProveedor = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelFondo.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        btnNuevaVenta.setBackground(new java.awt.Color(0, 102, 102));
        btnNuevaVenta.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        btnNuevaVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevaVenta.setText("Nueva Venta");
        btnNuevaVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevaVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevaVenta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(btnNuevaVenta, gridBagConstraints);

        btnProductos.setBackground(new java.awt.Color(0, 102, 102));
        btnProductos.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        btnProductos.setForeground(new java.awt.Color(255, 255, 255));
        btnProductos.setText("Productos");
        btnProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProductos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel1.add(btnProductos, gridBagConstraints);

        btnVendedor.setBackground(new java.awt.Color(0, 102, 102));
        btnVendedor.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        btnVendedor.setForeground(new java.awt.Color(255, 255, 255));
        btnVendedor.setText("Vendedores");
        btnVendedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVendedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVendedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(btnVendedor, gridBagConstraints);

        btnProveedor.setBackground(new java.awt.Color(0, 102, 102));
        btnProveedor.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        btnProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnProveedor.setText("Proveedores");
        btnProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProveedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProveedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel1.add(btnProveedor, gridBagConstraints);

        btnReportes.setBackground(new java.awt.Color(0, 102, 102));
        btnReportes.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        btnReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes.setText("Reportes");
        btnReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReportes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel1.add(btnReportes, gridBagConstraints);

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
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(btnSalir, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.3;
        panelFondo.add(jPanel1, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(51, 51, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        panelFondo.add(jPanel2, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
       System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

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
        //</editor-fold>

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelFondo;
    // End of variables declaration//GEN-END:variables
}
