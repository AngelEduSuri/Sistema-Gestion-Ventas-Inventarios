package vista;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
 * @author AngelEdu
 */
public class ReportesModulo extends javax.swing.JInternalFrame {

    Conexion conexion = new Conexion();
    Connection con = conexion.conectar();

    public ReportesModulo() {
        initComponents();
        btnReporteFecha.setEnabled(false);

        cambioCalendario();
    }

    private void reporteTotal() {
        JasperReport reporte = null;
        String rutaReporte = "src\\reportes\\VentasTotal.jasper";
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(rutaReporte);
            JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, null, con);
            JasperViewer verReporte = new JasperViewer(imprimirReporte, false);
            verReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            verReporte.setVisible(true);
        } catch (JRException ex) {
            System.err.println("Error en: " + ex);
        }
        fechaSeleccion.setDate(null);
        fechaInicio.setDate(null);
        fechaFinal.setDate(null);
    }

    private void reportefecha() {
        if (fechaSeleccion.getDate() == null) {
            JOptionPane.showMessageDialog(panelFondo, "Ingrese una fecha para buscar", "Falta fecha", JOptionPane.WARNING_MESSAGE);
        } else {
            Date fecha = fechaSeleccion.getDate();
            long l = fecha.getTime();
            java.sql.Date fechaConvertida = new java.sql.Date(l);
            JasperReport reporteFecha = null;
            String rutaReporte = "src\\reportes\\VentasFecha.jasper";
            try {
                reporteFecha = (JasperReport) JRLoader.loadObjectFromFile(rutaReporte);
                Map parametro = new HashMap();
                parametro.put("filtro", fechaConvertida);
                JasperPrint imprimirReporte = JasperFillManager.fillReport(reporteFecha, parametro, con);
                JasperViewer verReporte = new JasperViewer(imprimirReporte, false);
                verReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                verReporte.setVisible(true);
            } catch (JRException ex) {
                System.err.println("Error en: " + ex);
            }
        }
        fechaInicio.setDate(null);
        fechaFinal.setDate(null);
    }

    private void rangoFecha() {
        if (fechaInicio.getDate() == null || fechaFinal.getDate() == null) {
            JOptionPane.showMessageDialog(panelFondo, "Ingrese una de inicio y fin para buscar", "Falta rango de fechas", JOptionPane.WARNING_MESSAGE);
        } else {
            Date fechaInicioFormato = fechaInicio.getDate();
            Date fechaFinalFormato = fechaFinal.getDate();
            long inicio = fechaInicioFormato.getTime();
            long fechafinal = fechaFinalFormato.getTime();
            java.sql.Date fechaConvertidaInicio = new java.sql.Date(inicio);
            java.sql.Date fechaConvertidaFinal = new java.sql.Date(fechafinal);

            JasperReport reporteFechaRango = null;
            String rutaReporteRango = "src\\reportes\\VentasRangoFecha.jasper";
            try {
                reporteFechaRango = (JasperReport) JRLoader.loadObjectFromFile(rutaReporteRango);
                Map parametro = new HashMap();
                parametro.put("flitroInicio", fechaConvertidaInicio);
                parametro.put("filtroFinal", fechaConvertidaFinal);
                JasperPrint imprimirReporte = JasperFillManager.fillReport(reporteFechaRango, parametro, con);
                JasperViewer verReporte = new JasperViewer(imprimirReporte, false);
                verReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                verReporte.setVisible(true);
            } catch (JRException ex) {
                System.err.println("Error en: " + ex);
            }
        }
        fechaSeleccion.setDate(null);
    }

    private void cambioCalendario() {
        fechaSeleccion.getDateEditor().addPropertyChangeListener(
                new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (fechaSeleccion.getDate() != null) {
                    btnReporteFecha.setEnabled(true);
                } else {
                    btnReporteFecha.setEnabled(false);
                }

            }
        });
        this.add(fechaSeleccion);

        fechaInicio.getDateEditor().addPropertyChangeListener(
                new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (fechaInicio.getDate() != null && fechaFinal.getDate() != null) {
                    btnReporteRango.setEnabled(true);
                } else {
                    btnReporteRango.setEnabled(false);
                }

            }
        });
        this.add(fechaInicio);

        fechaFinal.getDateEditor().addPropertyChangeListener(
                new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (fechaFinal.getDate() != null && fechaInicio.getDate() != null) {
                    btnReporteRango.setEnabled(true);
                } else {
                    btnReporteRango.setEnabled(false);
                }

            }
        });
        this.add(fechaInicio);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnreporteTotal = new javax.swing.JButton();
        btnReporteFecha = new javax.swing.JButton();
        fechaSeleccion = new com.toedter.calendar.JDateChooser();
        lblFiltro = new javax.swing.JLabel();
        btnReporteRango = new javax.swing.JButton();
        fechaInicio = new com.toedter.calendar.JDateChooser();
        fechaFinal = new com.toedter.calendar.JDateChooser();
        lblFechaFinal = new javax.swing.JLabel();
        lblFechaInicio = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Reporte de Ventas");
        setMinimumSize(new java.awt.Dimension(530, 450));
        setPreferredSize(new java.awt.Dimension(530, 450));

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

        lblFiltro.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lblFiltro.setForeground(new java.awt.Color(255, 255, 255));
        lblFiltro.setText("Filtrar por fecha");

        btnReporteRango.setBackground(new java.awt.Color(0, 204, 51));
        btnReporteRango.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnReporteRango.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calendar.png"))); // NOI18N
        btnReporteRango.setText("Generar Reporte por Rango");
        btnReporteRango.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReporteRango.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnReporteRango.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteRangoActionPerformed(evt);
            }
        });

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReporteRango)
                .addGap(165, 165, 165))
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addComponent(lblFiltro)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createSequentialGroup()
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(fechaSeleccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnReporteFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblFechaInicio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFechaFinal)
                            .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnreporteTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fechaFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(45, 45, 45))))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblTitulo)
                .addGap(27, 27, 27)
                .addComponent(lblFiltro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechaSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReporteFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnreporteTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaFinal)
                    .addComponent(lblFechaInicio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fechaInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnReporteRango, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
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

    private void btnReporteRangoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteRangoActionPerformed
        rangoFecha();
    }//GEN-LAST:event_btnReporteRangoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReporteFecha;
    private javax.swing.JButton btnReporteRango;
    private javax.swing.JButton btnreporteTotal;
    private com.toedter.calendar.JDateChooser fechaFinal;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private com.toedter.calendar.JDateChooser fechaSeleccion;
    private javax.swing.JLabel lblFechaFinal;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelFondo;
    // End of variables declaration//GEN-END:variables
}
