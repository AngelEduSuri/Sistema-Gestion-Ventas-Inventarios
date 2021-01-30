package vista;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import modelo.Conexion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Erick Fabricio Guayanay
 */
public class ReportesModulo extends javax.swing.JInternalFrame {

    Conexion conexion = new Conexion();
    Connection con = conexion.conectar();

    public ReportesModulo() {
        initComponents();
    }

    //Metodo para llamar al objeto JasperReport que contiene todos los datos de ventas
    private void reporteTotal() {
        JasperReport reporte = null;
        String rutaReporte = "src\\reportes\\VentasTotal.jasper"; //Agrego la ruta del archivo jasper
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(rutaReporte); //Cargo esa ruta, al objeto JasperReport
            JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, null, con); //Envio el objeto y la conexion
            JasperViewer verReporte = new JasperViewer(imprimirReporte, false); //Muestro todo el reporte con los datos
            verReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            verReporte.setVisible(true);
        } catch (JRException ex) {
            System.err.println("Error en: " + ex);
        }

        fechaInicio.setDate(null);
        fechaFinal.setDate(null);
    }

    //Metodo para llamar al objeto JasperReport por rangos de fechas
    private void reportefecha() {
        if (fechaInicio.getDate() != null && fechaFinal.getDate() == null) { //Compruebo que el componente fecha de inicio este vacion
            Date fecha = fechaInicio.getDate(); //Si no esta vacion obtengo la fecha del componente
            long l = fecha.getTime(); //Obtengo la fecha completa y lo guardo en una variable long
            java.sql.Date fechaConvertida = new java.sql.Date(l); //Convierto la fecha en un tipo de dato Dato sql para usarlo como parametro de busqueda
            JasperReport reporteFecha = null; //Creo un objeto de tipo JasperReport
            String rutaReporte = "src\\reportes\\VentasFecha.jasper"; //Señalo la ruta relativa donde esta el objeto reporte
            try {
                reporteFecha = (JasperReport) JRLoader.loadObjectFromFile(rutaReporte); //Cargo esa ruta al objeto JasperReport
                Map parametro = new HashMap(); //Hago un mapeo de los datos
                parametro.put("filtro", fechaConvertida); //Agrego como parametro de busqueda la fecha de inicio
                JasperPrint imprimirReporte = JasperFillManager.fillReport(reporteFecha, parametro, con); //Envio el parametro, el objeto JasperReport y la conexion
                if (imprimirReporte.getPages().isEmpty()) { //Compruebo si esta vacio el reporte
                    JOptionPane.showMessageDialog(rootPane, "No hay registros con ese fecha"); //Si esta vacio muestro un mensaje 
                } else {//Si no esta vacio muestro el reporte
                    JasperViewer verReporte = new JasperViewer(imprimirReporte, false); //
                    verReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    verReporte.setVisible(true);
                }
                fechaInicio.setDate(null); //Reseteo el componente fecha inicio una vez cierre el reporte
            } catch (JRException ex) {
                System.err.println("Error en: " + ex);
            }
        } else if (fechaInicio.getDate() != null || fechaFinal.getDate() != null) { //Compruebo que la fecha inicio y fecha final no esten vacias
            //Obtengo ambas fechas 
            Date fechaInicioFormato = fechaInicio.getDate();
            Date fechaFinalFormato = fechaFinal.getDate();
            //Paso ambas fechas a variable de tipo long
            long inicio = fechaInicioFormato.getTime();
            long fechafinal = fechaFinalFormato.getTime();
            //Las convierto en tipo date sql para usarlos como parametros
            java.sql.Date fechaConvertidaInicio = new java.sql.Date(inicio);
            java.sql.Date fechaConvertidaFinal = new java.sql.Date(fechafinal);
            JasperReport reporteFechaRango = null; //Creo el objeto JasperReport
            String rutaReporteRango = "src\\reportes\\VentasRangoFecha.jasper"; //Obtengo la ruta relativa del reporte
            try {
                reporteFechaRango = (JasperReport) JRLoader.loadObjectFromFile(rutaReporteRango); //Envio esa ruta al objeto JasperReport
                Map parametro = new HashMap(); //Mapeo de los datos
                //Creo los parametros para fecha inicio y final para generar el rango de reporte
                parametro.put("flitroInicio", fechaConvertidaInicio); 
                parametro.put("filtroFinal", fechaConvertidaFinal);
                JasperPrint imprimirReporte = JasperFillManager.fillReport(reporteFechaRango, parametro, con); //Envio dichos parametros con la conexion
                if (imprimirReporte.getPages().isEmpty()) { //Compruebo que no este vacion el reporte
                    JOptionPane.showMessageDialog(rootPane, "No hay registros en ese rango de fechas");
                } else { //Si no esta vacio, lo muestra
                    JasperViewer verReporte = new JasperViewer(imprimirReporte, false);
                    verReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    verReporte.setVisible(true);
                }
                //Reseto las fechas una vez que cierre el reporte
                fechaInicio.setDate(null); 
                fechaFinal.setDate(null);
            } catch (JRException ex) {
                System.err.println("Error en: " + ex);
            }
        } else { //Si no hay ninguna fecha agregada, el mensaje pide que ingrese una
            JOptionPane.showMessageDialog(panelFondo, "Ingrese una fecha para generar el reporte", "Falta ingresar fechas", JOptionPane.WARNING_MESSAGE);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnreporteTotal = new javax.swing.JButton();
        btnReporteFecha = new javax.swing.JButton();
        fechaInicio = new com.toedter.calendar.JDateChooser();
        fechaFinal = new com.toedter.calendar.JDateChooser();
        lblFechaFinal = new javax.swing.JLabel();
        lblFechaInicio = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Reporte de Ventas");
        setMinimumSize(new java.awt.Dimension(530, 450));
        setPreferredSize(new java.awt.Dimension(530, 450));
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

        panelFondo.setBackground(new java.awt.Color(51, 71, 91));

        lblTitulo.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Generar Reportes");

        btnreporteTotal.setBackground(new java.awt.Color(0, 204, 51));
        btnreporteTotal.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnreporteTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reporte-de-negocios.png"))); // NOI18N
        btnreporteTotal.setText("Generar Reporte Completo");
        btnreporteTotal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnreporteTotal.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnreporteTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreporteTotalActionPerformed(evt);
            }
        });

        btnReporteFecha.setBackground(new java.awt.Color(0, 204, 51));
        btnReporteFecha.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnReporteFecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calendario.png"))); // NOI18N
        btnReporteFecha.setText("Generar Reporte por Fecha");
        btnReporteFecha.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReporteFecha.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnReporteFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteFechaActionPerformed(evt);
            }
        });

        fechaInicio.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        fechaFinal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblFechaFinal.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lblFechaFinal.setForeground(new java.awt.Color(255, 255, 255));
        lblFechaFinal.setText("Fecha Final");

        lblFechaInicio.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lblFechaInicio.setForeground(new java.awt.Color(255, 255, 255));
        lblFechaInicio.setText("Fecha Inicio");

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnReporteFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFechaInicio)
                    .addComponent(fechaFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFechaFinal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnreporteTotal)
                .addGap(49, 49, 49))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblTitulo)
                .addGap(17, 17, 17)
                .addComponent(lblFechaInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(lblFechaFinal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReporteFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnreporteTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(142, 142, 142))
        );

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

    private void btnreporteTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreporteTotalActionPerformed
        reporteTotal();
    }//GEN-LAST:event_btnreporteTotalActionPerformed

    private void btnReporteFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteFechaActionPerformed
        reportefecha();
    }//GEN-LAST:event_btnReporteFechaActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        if (evt.getInternalFrame().isClosable()) {
            Menu.contador = 0;
        }
    }//GEN-LAST:event_formInternalFrameClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReporteFecha;
    private javax.swing.JButton btnreporteTotal;
    private com.toedter.calendar.JDateChooser fechaFinal;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private javax.swing.JLabel lblFechaFinal;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelFondo;
    // End of variables declaration//GEN-END:variables
}
