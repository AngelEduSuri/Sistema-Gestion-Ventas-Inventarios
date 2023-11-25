package com.aesuriagasalazar.vista;

import com.aesuriagasalazar.modelo.Vendedor;
import com.aesuriagasalazar.modelo.VendedorDatos;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;

/**
 *
 * @author John Javier Mora Vivar
 */
public class Login extends javax.swing.JFrame {

    Timer tiempo;
    Vendedor vendedor = new Vendedor();
    VendedorDatos vendedorDatos = new VendedorDatos();
    Menu menuPrincipal;
    String usuario;
    String contraseña;
    int contador = 0, local = 0;
    Font fuente;

    public Login() {
        initComponents();
        barraCargandoSesion.setVisible(false);
    }

    private void iniciar() {
        String mensaje = "<html>Datos incorrectos, verifique su usuario o contraseña<html>";
        usuario = txtUsuario.getText();
        contraseña = new String(txtContraseña.getPassword());
        if (usuario.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Ingrese sus credenciales", "Rellene los campos", JOptionPane.WARNING_MESSAGE);
        } else {
            vendedor.setUsuario(usuario);
            vendedor.setCedula(contraseña);
            if (vendedorDatos.iniciarSesion(vendedor)) {
                lblSesionCorrecta.setText("Datos correctos");
                barraCarga();
                menuPrincipal = new Menu(vendedor);
            } else {
                lblSesionCorrecta.setText(mensaje);
                reseteoMensajeError();
            }
        }
    }

    private void barraCarga() {

        tiempo = new Timer(650, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (contador) {
                    case 1:
                        lblSesion.setText("Ingresando al sistema");
                        barraCargandoSesion.setVisible(true);
                        barraCargandoSesion.setValue(1);
                        break;
                    case 2:
                        lblSesion.setText("Ingresando al sistema . ");
                        barraCargandoSesion.setValue(25);
                        break;
                    case 3:
                        lblSesion.setText("Ingresando al sistema . . ");
                        barraCargandoSesion.setValue(50);
                        break;
                    case 4:
                        lblSesion.setText("Ingresando al sistema . . . ");
                        barraCargandoSesion.setValue(75);
                        break;
                    case 5:
                        lblSesion.setText("Ingresando al sistema . . . . ");
                        barraCargandoSesion.setValue(100);

                        break;
                    case 6:
                        menuPrincipal.setVisible(true);
                        tiempo.stop();
                        dispose();
                        break;
                }
                contador++;
            }
        });
        tiempo.start();
    }

    private void reseteoMensajeError() {

        tiempo = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (local == 3) {
                    lblSesionCorrecta.setText(null);
                    tiempo.stop();
                    local = 0;
                }
                local++;
            }
        });
        tiempo.start();
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
        barraCargandoSesion = new javax.swing.JProgressBar();
        lblSesionCorrecta = new javax.swing.JLabel();
        fondoLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fondoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login_background.png"))); // NOI18N
        panelFondo.add(fondoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 180, 180));

        btnSalir.setBackground(new java.awt.Color(84, 65, 136));
        btnSalir.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout_icon.png"))); // NOI18N
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
        panelFondo.add(lblContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 315, -1, -1));

        txtUsuario.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        panelFondo.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 190, 30));

        txtContraseña.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        panelFondo.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 190, 30));

        btnIngresar.setBackground(new java.awt.Color(84, 65, 136));
        btnIngresar.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login_icon.png"))); // NOI18N
        btnIngresar.setText("INGRESAR");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        panelFondo.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, 180, 35));

        lblSesion.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        lblSesion.setForeground(new java.awt.Color(255, 255, 255));
        panelFondo.add(lblSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 330, 20));
        panelFondo.add(barraCargandoSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 474, 330, 20));

        lblSesionCorrecta.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        lblSesionCorrecta.setForeground(new java.awt.Color(255, 255, 255));
        panelFondo.add(lblSesionCorrecta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 330, 40));

        fondoLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.jpg"))); // NOI18N
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraCargandoSesion;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel fondoLogin;
    private javax.swing.JLabel fondoUsuario;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblSesion;
    private javax.swing.JLabel lblSesionCorrecta;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
