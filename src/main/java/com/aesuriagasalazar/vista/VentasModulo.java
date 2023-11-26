package com.aesuriagasalazar.vista;

import com.aesuriagasalazar.modelo.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Angel Eduardo Suriaga
 */
public class VentasModulo extends javax.swing.JInternalFrame {

    DefaultComboBoxModel modeloCombo;
    Producto producto = new Producto();
    ProductosDatos productoDatos = new ProductosDatos();
    VentasDatos venDatos = new VentasDatos();
    Ventas ventas = new Ventas();
    DetalleVentas detalleVentas = new DetalleVentas();
    DefaultTableModel modeloTabla;
    DecimalFormat decimal = new DecimalFormat("0.00");

    int idProd;
    int cantidad;
    int stock;
    double precio;
    double total;
    double totalPagar;
    String fechaSql;

    public VentasModulo() {
        initComponents();
        diseñoTabla();
        diseñoCantidad();
        calendario();
        generarNumeroSerie();
    }

    private void diseñoCantidad() {
        SpinnerNumberModel modeloSpinner = new SpinnerNumberModel();
        modeloSpinner.setValue(1);
        modeloSpinner.setMinimum(1);
        modeloSpinner.setMaximum(20);
        cantidadCompra.setModel(modeloSpinner);
    }

    private void diseñoTabla() { //Metodo que le asigna un modelo a la tabla de los productos
        modeloTabla = new DefaultTableModel(); //Creo el modelo de la tabla
        tablaVentas.setModel(modeloTabla); //Asigno a la tabla el modelo

        //Añado las columnas al modelo de la tabla
        modeloTabla.addColumn("Orden #");
        modeloTabla.addColumn("C.O.D");
        modeloTabla.addColumn("Producto");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Precio Uni.");
        modeloTabla.addColumn("Total");

        //Asigno la anchura de las columnas a la tabla
        int anchoColummna[] = {5, 5, 220, 15, 20, 15};
        boolean cambiarAncho = false;

        for (int i = 0; i < anchoColummna.length; i++) {
            tablaVentas.getColumnModel().getColumn(i).setPreferredWidth(anchoColummna[i]);
            //Hago que las columnas no se puedan redimenzionar asignandoles la variable boolean false
            tablaVentas.getColumnModel().getColumn(i).setResizable(cambiarAncho);
        }
    }

    private void calendario() {
        Date fechaActual = new Date();
        SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat gregorian = new SimpleDateFormat("dd-MM-yyyy");
        String fechaSqlFormato = formatoSQL.format(fechaActual);
        String fechaGregorian = gregorian.format(fechaActual);
        txtFecha.setText(String.valueOf(fechaGregorian));
        fechaSql = fechaSqlFormato;
    }

    private void generarNumeroSerie() {
        String serie = venDatos.numSerieVentas();
        if (serie == null) {
            txtSerie.setText("0000001");
        } else {
            int incremento = Integer.parseInt(serie);
            incremento = incremento + 1;
            txtSerie.setText("00000" + incremento);
        }
        txtVendedor.setText(Menu.vendedor.getNombre());
    }

    private void buscarProductoNombre() {
        if (txtBuscarProducto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(panelComponentes, "Debe ingresar un nombre del producto a buscar", "Falta nombre", JOptionPane.WARNING_MESSAGE);
        } else {
            String buscarProducto = txtBuscarProducto.getText();
            Vector<Producto> vectorProducto = new Vector<>();
            vectorProducto = productoDatos.buscarProductoNombre(buscarProducto);
            if (vectorProducto.isEmpty()) {
                JOptionPane.showMessageDialog(panelComponentes, "No existe ese articulo", "No se encontro", JOptionPane.ERROR_MESSAGE);
            } else {
                modeloCombo = new DefaultComboBoxModel(vectorProducto);
                comboProductos.setModel(modeloCombo);
            }

        }
    }

    private void seleccionarProductoComboBox() {
        Producto productoActual = (Producto) comboProductos.getSelectedItem();
        txtIdPro.setText(String.valueOf(productoActual.getIdProd()));
        txtNombreProducto.setText(productoActual.getNombreProd());
        txtStock.setText(String.valueOf(productoActual.getCantidad()));
        txtPrecio.setText(String.valueOf(productoActual.getPrecio()));
    }

    public void agregarProductoTabla() {
        if (txtNombreProducto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(panelComponentes, "Primero busque un producto", "Busque producto", JOptionPane.WARNING_MESSAGE);
        } else {
            int item = 0;
            item = item + 1;
            idProd = Integer.parseInt(txtIdPro.getText());
            String nombrePro = txtNombreProducto.getText();
            precio = Double.parseDouble(txtPrecio.getText());
            cantidad = Integer.parseInt(cantidadCompra.getValue().toString());
            stock = Integer.parseInt(txtStock.getText());
            total = cantidad * precio;
            ArrayList listaProductos = new ArrayList();
            if (stock > 0) {
                listaProductos.add(item);
                listaProductos.add(idProd);
                listaProductos.add(nombrePro);
                listaProductos.add(cantidad);
                listaProductos.add(String.format("%.2f", precio));
                listaProductos.add(String.format("%.2f", total));
                Object[] objDatos = new Object[6];
                objDatos[0] = listaProductos.get(0);
                objDatos[1] = listaProductos.get(1);
                objDatos[2] = listaProductos.get(2);
                objDatos[3] = listaProductos.get(3);
                objDatos[4] = listaProductos.get(4);
                objDatos[5] = listaProductos.get(5);
                modeloTabla.addRow(objDatos);
                totalPagar = totalPagar + total;
                txtTotalPagar.setText(decimal.format(totalPagar));
                limpiarTextoProducto();

            } else {
                JOptionPane.showMessageDialog(panelComponentes, "Articulos en el almacen no disponible", "Falta Stock", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void guardarVenta() {
        if (txtTotalPagar.getText().isEmpty()) {
            JOptionPane.showMessageDialog(panelComponentes, "Agrege productos para hacer la venta", "Agregar productos", JOptionPane.WARNING_MESSAGE);
        } else {
            int idv = Menu.vendedor.getIdVendedor();
            String nombreCliente = txtCliente.getText();
            String serie = txtSerie.getText();
            Double monto = totalPagar;
            ventas.setIdVendedor(idv);
            ventas.setCliente(nombreCliente);
            ventas.setSerie(serie);
            ventas.setFecha(fechaSql);
            ventas.setMonto(monto);
            if (venDatos.guardarVentas(ventas) > 0) {
                JOptionPane.showMessageDialog(panelComponentes, "Venta Realizada", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                guardarDetalleVentas();
                actualizarStock();
                generarNumeroSerie();
                limpiarVenta();
            } else {
                JOptionPane.showMessageDialog(panelComponentes, "No se guardo la venta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void guardarDetalleVentas() {
        double precioConvertido = 0;
        double precioTotalConvertido = 0;
        String idVenta = venDatos.idVentas();
        int idventa = Integer.parseInt(idVenta);
        for (int i = 0; i < tablaVentas.getRowCount(); i++) {
            int idprod = Integer.parseInt(tablaVentas.getValueAt(i, 1).toString());
            String nomPro = tablaVentas.getValueAt(i, 2).toString();
            int cant = Integer.parseInt(tablaVentas.getValueAt(i, 3).toString());
            String precioProducto = tablaVentas.getValueAt(i, 4).toString();
            String precioTotal = tablaVentas.getValueAt(i, 5).toString();
            if (precioProducto.contains(",")) {
                precioConvertido = Double.parseDouble(precioProducto.replace(",", ".").trim());
            }
            if (precioTotal.contains(",")) {
                precioTotalConvertido = Double.parseDouble(precioTotal.replace(",", ".").trim());
            }
            detalleVentas.setIdVentas(idventa);
            detalleVentas.setIdProducto(idprod);
            detalleVentas.setNombrePro(nomPro);
            detalleVentas.setCantidad(cant);
            detalleVentas.setPrecio(precioConvertido);
            detalleVentas.setTotal(precioTotalConvertido);
            detalleVentas.setFecha(fechaSql);
            venDatos.guardarDetalleVentas(detalleVentas);
        }
    }

    public void actualizarStock() {
        int idproducto;
        int datosRecibidos[] = new int[2];
        int idActualizar;
        int cant;
        int cantidadGuardada;
        int stockActual;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            idproducto = Integer.parseInt(tablaVentas.getValueAt(i, 1).toString());
            datosRecibidos = productoDatos.BuscarProductoPorID(idproducto);
            idActualizar = datosRecibidos[0];
            cant = datosRecibidos[1];
            cantidadGuardada = productoDatos.obtenerStockActual(idActualizar);           
            stockActual = cantidadGuardada - cant;
            productoDatos.actualizarStock(stockActual, idActualizar);
        }
    }

    public void limpiarVenta() {
        txtCliente.setText(null);
        txtBuscarProducto.setText(null);
        txtNombreProducto.setText(null);
        txtPrecio.setText(null);
        txtStock.setText(null);
        cantidadCompra.setValue(1);
        txtTotalPagar.setText(null);
        txtIdPro.setText(null);
        modeloTabla.setRowCount(0);
    }

    private void limpiarTextoProducto() {
        txtBuscarProducto.setText(null);
        txtNombreProducto.setText(null);
        txtPrecio.setText(null);
        txtStock.setText(null);
        cantidadCompra.setValue(1);
        txtBuscarProducto.requestFocus();
        txtIdPro.setText(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelFondo = new javax.swing.JPanel();
        panelTitulos = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblTipoNegocio = new javax.swing.JLabel();
        lblCelular = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblSerie = new javax.swing.JLabel();
        txtSerie = new javax.swing.JTextField();
        panelComponentes = new javax.swing.JPanel();
        lblCliente = new javax.swing.JLabel();
        lblCodigoProducto = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        txtBuscarProducto = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        cantidadCompra = new javax.swing.JSpinner();
        btnBuscarProducto = new javax.swing.JButton();
        btnAgregarProducto = new javax.swing.JButton();
        txtFecha = new javax.swing.JTextField();
        lblProducto = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        lblVendedor = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        txtVendedor = new javax.swing.JTextField();
        lblBuscar = new javax.swing.JLabel();
        txtIdPro = new javax.swing.JTextField();
        lblNombreProducto = new javax.swing.JLabel();
        comboProductos = new javax.swing.JComboBox<>();
        panelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        panelVenta = new javax.swing.JPanel();
        btnLimipiarTodo = new javax.swing.JButton();
        btnGenerarVenta = new javax.swing.JButton();
        lblTotalPagar = new javax.swing.JLabel();
        txtTotalPagar = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Modulo Ventas");
        setMaximumSize(new java.awt.Dimension(732, 730));
        setMinimumSize(new java.awt.Dimension(732, 730));
        setPreferredSize(new java.awt.Dimension(732, 730));
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

        panelTitulos.setBackground(new java.awt.Color(51, 71, 91));
        panelTitulos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblTitulo.setText("MI PAPELERÍA");

        lblTipoNegocio.setFont(new java.awt.Font("Arial Narrow", 1, 16)); // NOI18N
        lblTipoNegocio.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoNegocio.setText("Ventas de Artículos Escolares y Oficina");

        lblCelular.setFont(new java.awt.Font("Arial Narrow", 1, 16)); // NOI18N
        lblCelular.setForeground(new java.awt.Color(255, 255, 255));
        lblCelular.setText("Tel.Celular: 0994593150");

        lblDireccion.setFont(new java.awt.Font("Arial Narrow", 1, 16)); // NOI18N
        lblDireccion.setForeground(new java.awt.Color(255, 255, 255));
        lblDireccion.setText("Direccion: Arenillas - Calle Juan Montalvo y Calle Esmeraldas");

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/seller_logo.png"))); // NOI18N

        lblSerie.setFont(new java.awt.Font("Arial Narrow", 1, 16)); // NOI18N
        lblSerie.setForeground(new java.awt.Color(255, 255, 255));
        lblSerie.setText("N° de Serie");

        txtSerie.setEditable(false);
        txtSerie.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 13)); // NOI18N

        javax.swing.GroupLayout panelTitulosLayout = new javax.swing.GroupLayout(panelTitulos);
        panelTitulos.setLayout(panelTitulosLayout);
        panelTitulosLayout.setHorizontalGroup(
            panelTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitulosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblCelular)
                        .addComponent(lblDireccion)
                        .addComponent(lblTipoNegocio)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(panelTitulosLayout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(lblSerie)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTitulosLayout.setVerticalGroup(
            panelTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitulosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTipoNegocio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCelular)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDireccion)
                .addGap(18, 18, 18)
                .addGroup(panelTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSerie)
                    .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelComponentes.setBackground(new java.awt.Color(51, 71, 91));
        panelComponentes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelComponentes.setLayout(new java.awt.GridBagLayout());

        lblCliente.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblCliente.setText("Cliente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        panelComponentes.add(lblCliente, gridBagConstraints);

        lblCodigoProducto.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblCodigoProducto.setForeground(new java.awt.Color(255, 255, 255));
        lblCodigoProducto.setText("Codigo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        panelComponentes.add(lblCodigoProducto, gridBagConstraints);

        lblPrecio.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblPrecio.setForeground(new java.awt.Color(255, 255, 255));
        lblPrecio.setText("Precio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        panelComponentes.add(lblPrecio, gridBagConstraints);

        lblCantidad.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblCantidad.setForeground(new java.awt.Color(255, 255, 255));
        lblCantidad.setText("Cantidad:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        panelComponentes.add(lblCantidad, gridBagConstraints);

        txtCliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelComponentes.add(txtCliente, gridBagConstraints);

        txtBuscarProducto.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtBuscarProducto.setToolTipText("Ingrese el nombre del articulo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelComponentes.add(txtBuscarProducto, gridBagConstraints);

        txtPrecio.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        panelComponentes.add(txtPrecio, gridBagConstraints);

        cantidadCompra.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelComponentes.add(cantidadCompra, gridBagConstraints);

        btnBuscarProducto.setBackground(new java.awt.Color(0, 204, 51));
        btnBuscarProducto.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/lens_icon.png"))); // NOI18N
        btnBuscarProducto.setText("Buscar");
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        panelComponentes.add(btnBuscarProducto, gridBagConstraints);

        btnAgregarProducto.setBackground(new java.awt.Color(0, 204, 51));
        btnAgregarProducto.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnAgregarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/add_cart_icon.png"))); // NOI18N
        btnAgregarProducto.setText("Agregar");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        panelComponentes.add(btnAgregarProducto, gridBagConstraints);

        txtFecha.setEditable(false);
        txtFecha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelComponentes.add(txtFecha, gridBagConstraints);

        lblProducto.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblProducto.setForeground(new java.awt.Color(255, 255, 255));
        lblProducto.setText("Nombre Prod:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        panelComponentes.add(lblProducto, gridBagConstraints);

        lblStock.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblStock.setForeground(new java.awt.Color(255, 255, 255));
        lblStock.setText("Stock:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        panelComponentes.add(lblStock, gridBagConstraints);

        lblVendedor.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblVendedor.setForeground(new java.awt.Color(255, 255, 255));
        lblVendedor.setText("Vendedor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        panelComponentes.add(lblVendedor, gridBagConstraints);

        txtNombreProducto.setEditable(false);
        txtNombreProducto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 280;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelComponentes.add(txtNombreProducto, gridBagConstraints);

        txtStock.setEditable(false);
        txtStock.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 280;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelComponentes.add(txtStock, gridBagConstraints);

        txtVendedor.setEditable(false);
        txtVendedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 280;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelComponentes.add(txtVendedor, gridBagConstraints);

        lblBuscar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblBuscar.setForeground(new java.awt.Color(255, 255, 255));
        lblBuscar.setText("Buscar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        panelComponentes.add(lblBuscar, gridBagConstraints);

        txtIdPro.setEditable(false);
        txtIdPro.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtIdPro.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        panelComponentes.add(txtIdPro, gridBagConstraints);

        lblNombreProducto.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblNombreProducto.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreProducto.setText("Producto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        panelComponentes.add(lblNombreProducto, gridBagConstraints);

        comboProductos.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        comboProductos.setMaximumSize(new java.awt.Dimension(250, 32767));
        comboProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProductosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelComponentes.add(comboProductos, gridBagConstraints);

        panelTabla.setBackground(new java.awt.Color(51, 71, 91));
        panelTabla.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablaVentas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Orden #", "C.O.D", "Producto", "Cantidad", "Precio Uni.", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaVentas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaVentas);
        if (tablaVentas.getColumnModel().getColumnCount() > 0) {
            tablaVentas.getColumnModel().getColumn(0).setResizable(false);
            tablaVentas.getColumnModel().getColumn(0).setPreferredWidth(5);
            tablaVentas.getColumnModel().getColumn(1).setResizable(false);
            tablaVentas.getColumnModel().getColumn(1).setPreferredWidth(5);
            tablaVentas.getColumnModel().getColumn(2).setResizable(false);
            tablaVentas.getColumnModel().getColumn(2).setPreferredWidth(230);
            tablaVentas.getColumnModel().getColumn(3).setResizable(false);
            tablaVentas.getColumnModel().getColumn(3).setPreferredWidth(15);
            tablaVentas.getColumnModel().getColumn(4).setResizable(false);
            tablaVentas.getColumnModel().getColumn(4).setPreferredWidth(20);
            tablaVentas.getColumnModel().getColumn(5).setResizable(false);
            tablaVentas.getColumnModel().getColumn(5).setPreferredWidth(15);
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
            .addGroup(panelTablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelVenta.setBackground(new java.awt.Color(51, 71, 91));
        panelVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelVenta.setLayout(new java.awt.GridBagLayout());

        btnLimipiarTodo.setBackground(new java.awt.Color(0, 204, 51));
        btnLimipiarTodo.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnLimipiarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/clear_icon.png"))); // NOI18N
        btnLimipiarTodo.setText("Limpiar");
        btnLimipiarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimipiarTodoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        panelVenta.add(btnLimipiarTodo, gridBagConstraints);

        btnGenerarVenta.setBackground(new java.awt.Color(0, 204, 51));
        btnGenerarVenta.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnGenerarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/bag_icon.png"))); // NOI18N
        btnGenerarVenta.setText("Generar Venta");
        btnGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVentaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        panelVenta.add(btnGenerarVenta, gridBagConstraints);

        lblTotalPagar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblTotalPagar.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalPagar.setText("Total a pagar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        panelVenta.add(lblTotalPagar, gridBagConstraints);

        txtTotalPagar.setEditable(false);
        txtTotalPagar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        txtTotalPagar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        panelVenta.add(txtTotalPagar, gridBagConstraints);

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTabla, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTitulos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelComponentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTitulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
        buscarProductoNombre();
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        agregarProductoTabla();
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnLimipiarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimipiarTodoActionPerformed
        limpiarVenta();
    }//GEN-LAST:event_btnLimipiarTodoActionPerformed

    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVentaActionPerformed
        guardarVenta();
    }//GEN-LAST:event_btnGenerarVentaActionPerformed

    private void comboProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProductosActionPerformed
        seleccionarProductoComboBox();
    }//GEN-LAST:event_comboProductosActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        if (evt.getInternalFrame().isClosable()) {
            Menu.contador = 0;
        }
    }//GEN-LAST:event_formInternalFrameClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnGenerarVenta;
    private javax.swing.JButton btnLimipiarTodo;
    private javax.swing.JSpinner cantidadCompra;
    private javax.swing.JComboBox<String> comboProductos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCelular;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblCodigoProducto;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNombreProducto;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblSerie;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblTipoNegocio;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotalPagar;
    private javax.swing.JLabel lblVendedor;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JPanel panelTitulos;
    private javax.swing.JPanel panelVenta;
    private javax.swing.JTable tablaVentas;
    private javax.swing.JTextField txtBuscarProducto;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIdPro;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtSerie;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtTotalPagar;
    private javax.swing.JTextField txtVendedor;
    // End of variables declaration//GEN-END:variables
}
