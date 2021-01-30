package vista;

import com.jtattoo.plaf.texture.TextureLookAndFeel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import modelo.Vendedor;
import modelo.VendedorDatos;

/**
 *
 * @author John Javier Mora Vivar
 */
public class Login extends javax.swing.JFrame {

    Vendedor vendedor = new Vendedor();
    VendedorDatos vendedorDatos = new VendedorDatos();
    Menu menuPrincipal;

    public Login() {
        initComponents();        
    }

    private void iniciar() {
        String usuario;
        usuario = txtUsuario.getText();
        String contraseña = new String(txtContraseña.getPassword());
        if (usuario.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Ingrese sus credenciales", "Rellene los campos", JOptionPane.WARNING_MESSAGE);
        } else {
            vendedor.setUsuario(usuario);
            vendedor.setCedula(contraseña);
            if (vendedorDatos.iniciarSesion(vendedor)) {
                lblSesion.setText("Datos correctos, iniciando sesion....");
                this.dispose();
                menuPrincipal = new Menu(vendedor);
                menuPrincipal.setVisible(true);
            } else {
                lblSesion.setText("Datos incorrectos, verifique su usuario o contraseña");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        fondoUsuario = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        lblUsuario = new javax.swing.JLabel();
        lblContraseña = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtContraseña = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        lblSesion = new javax.swing.JLabel();
        cargandoSesion = new javax.swing.JProgressBar();
        fondoLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fondoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/login.png"))); // NOI18N
        panelFondo.add(fondoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 180, 180));

        btnSalir.setBackground(new java.awt.Color(84, 65, 136));
        btnSalir.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoutPrincipio.png"))); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        panelFondo.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 110, 35));

        lblUsuario.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("Vendedor:");
        panelFondo.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 265, -1, -1));

        lblContraseña.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblContraseña.setForeground(new java.awt.Color(255, 255, 255));
        lblContraseña.setText("Contraseña:");
        panelFondo.add(lblContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 325, -1, -1));

        txtUsuario.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        panelFondo.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 190, 30));

        txtContraseña.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        panelFondo.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 190, 30));

        btnIngresar.setBackground(new java.awt.Color(84, 65, 136));
        btnIngresar.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iniciarSesion.png"))); // NOI18N
        btnIngresar.setText("INGRESAR");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        panelFondo.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, 180, 35));

        lblSesion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblSesion.setForeground(new java.awt.Color(255, 255, 255));
        panelFondo.add(lblSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 310, 20));
        panelFondo.add(cargandoSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 474, 330, 20));

        fondoLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoLogin.jpg"))); // NOI18N
        panelFondo.add(fondoLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        iniciar();
    }//GEN-LAST:event_btnIngresarActionPerformed

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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new Login().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JProgressBar cargandoSesion;
    private javax.swing.JLabel fondoLogin;
    private javax.swing.JLabel fondoUsuario;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblSesion;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
