/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views_ui;

import conexion_mysql.GenerarCodigo;
import conexion_mysql.conexionMySQL;
import conexion_mysql.servicios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marco
 */
public class pagos extends javax.swing.JInternalFrame {

    public static String v;
    Calendar Fecha_actual = new GregorianCalendar();
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Creates new form pagos
     */
    public pagos() throws Exception {
        initComponents();
        int x = frm_mAdmin.escritorio.getWidth() - this.getWidth();
        int y = frm_mAdmin.escritorio.getHeight() - this.getHeight();
        setLocation(x / 2, y / 2);
        setVisible(true);
        txtreferencia.setEnabled(false);
        TxtFecha.setEnabled(false);
        TxtFecha.setCalendar(Fecha_actual);
        v = "v";
        txtusuario.setVisible(false);
        labelm.setVisible(false);
        txtm.setVisible(false);
        combom();
        cargarReferencias();
        cargarcodigo();
        cargarServicios();
    }

    private void combom() {
        txtm.addItem("1");
        txtm.addItem("2");
        txtm.addItem("3");
        txtm.addItem("4");
        txtm.addItem("5");
        txtm.addItem("6");
    }

    private void cargarReferencias() throws Exception {
        Connection con = conexionMySQL.getConnection();
        txtservicio.removeAllItems();
        try {
            ps = con.prepareStatement("SELECT * FROM servicios;");
            rs = ps.executeQuery();
            while (rs.next()) {
                txtservicio.addItem(rs.getString("Nombre"));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al extraer");
        }
    }

    void cargarcodigo() throws Exception {
        Connection con = conexionMySQL.getConnection();

        int j;
        String num = "";
        String c = "";
        String SQL = "select max(referencia) from pagos";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }

            if (c == null) {
                txtreferencia.setText("RP000001");
            } else {
                char r1 = c.charAt(2);
                char r2 = c.charAt(3);
                char r3 = c.charAt(4);
                char r4 = c.charAt(5);
                char r5 = c.charAt(6);
                char r6 = c.charAt(7);
                String r = "";
                r = "" + r1 + r2 + r3 + r4 + r5 + r6;

                j = Integer.parseInt(r);
                GenerarCodigo gen = new GenerarCodigo();
                gen.generar(j);
                txtreferencia.setText("RP" + gen.serie());

            }

        } catch (Exception e) {
        }
    }

    private void costos() {
        servicios serv = new servicios();
        try {
            Connection con = conexionMySQL.getConnection();
            ps = con.prepareStatement("SELECT * FROM jcsdb.servicios WHERE Nombre = ?");
            ps.setString(1, (String) txtservicio.getSelectedItem());
            rs = ps.executeQuery();
            if (rs.next()) {
                serv.cvreferencia = rs.getString("referencia");
                serv.cvNombre = rs.getString("Nombre");
                serv.cvCosto = rs.getString("costo");
            } else {
                JOptionPane.showMessageDialog(null, "No Existe servicio");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void cargarServicios() throws Exception {
        Connection con = conexionMySQL.getConnection();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Servicio");
        modelo.addColumn("Nombre");
        modelo.addColumn("Costo");
        String[] datos = new String[3];
        try {
            ps = con.prepareStatement("SELECT referencia_s, nombre, costo FROM s_pagos where referencia_p = ?;");
            ps.setString(1, txtreferencia.getText());
            rs = ps.executeQuery();
            while (rs.next()) {
                jTable1.setModel(modelo);
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                modelo.addRow(datos);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void inserServicio() throws Exception {
         costos();
         Connection con = conexionMySQL.getConnection();
            try {
                servicios serv = new servicios();
                ps = con.prepareStatement("INSERT INTO `s_pagos` (`referencia_p`, `referencia_s`, `nombre`, `costo`) VALUES (? , ?, ?, ?);");
                ps.setString(1, txtreferencia.getText());
                ps.setString(2, serv.cvreferencia);
                ps.setString(3, serv.cvNombre);
                ps.setString(4, serv.cvCosto);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Servicio agregado", "Guardar", JOptionPane.INFORMATION_MESSAGE);

                cargarServicios();
            } catch (SQLException sqle) {
                JOptionPane.showMessageDialog(null, "No se agrego servicio");
            }
    }

    private void deleteServicio(){
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TxtTotal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        TxtCantidadArt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        BTNGUARDAR = new javax.swing.JButton();
        TxtClaveUsuario = new javax.swing.JTextField();
        BTNCANCELA = new javax.swing.JButton();
        TxtFecha = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtservicio = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        txtreferencia = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        TxtCantidad = new javax.swing.JTextField();
        BtnEliminarP = new javax.swing.JButton();
        BtnAgregarP = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelm = new javax.swing.JLabel();
        txtm = new javax.swing.JComboBox();
        jRadioButton1 = new javax.swing.JRadioButton();
        txtusuario = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Pagos");
        setPreferredSize(new java.awt.Dimension(760, 410));
        setVisible(true);
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

        jTable1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 153), null, null));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Total $");

        jLabel3.setBackground(new java.awt.Color(69, 73, 74));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Referencia pago");

        TxtTotal.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        TxtTotal.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 204, 204), null, null));
        TxtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtTotalActionPerformed(evt);
            }
        });
        TxtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtTotalKeyTyped(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(69, 73, 74));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Clave del alumno");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("N. servicios");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Fecha ");

        TxtCantidadArt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        TxtCantidadArt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 204, 204), null, null));
        TxtCantidadArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtCantidadArtActionPerformed(evt);
            }
        });
        TxtCantidadArt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtCantidadArtKeyTyped(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(69, 73, 74));
        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel2.setText("Datos del pago");

        BTNGUARDAR.setText("Confirmar");
        BTNGUARDAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNGUARDARActionPerformed(evt);
            }
        });

        TxtClaveUsuario.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 204, 204), null, null));
        TxtClaveUsuario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TxtClaveUsuario.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        TxtClaveUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtClaveUsuarioActionPerformed(evt);
            }
        });
        TxtClaveUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtClaveUsuarioKeyTyped(evt);
            }
        });

        BTNCANCELA.setText("Cancelar");
        BTNCANCELA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCANCELAActionPerformed(evt);
            }
        });

        TxtFecha.setBackground(new java.awt.Color(255, 255, 255));
        TxtFecha.setForeground(new java.awt.Color(255, 255, 255));
        TxtFecha.setDateFormatString("yyyy/MM/dd");
        TxtFecha.setInheritsPopupMenu(true);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Referencia servicio");

        txtservicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtservicioItemStateChanged(evt);
            }
        });
        txtservicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtservicioActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel6.setText("Datos del servicio ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(txtservicio, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtservicio, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        txtreferencia.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 204, 204), null, null));
        txtreferencia.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtreferencia.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtreferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtreferenciaActionPerformed(evt);
            }
        });
        txtreferencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtreferenciaKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Descuento %");

        TxtCantidad.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 204, 204), null, null));
        TxtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtCantidadActionPerformed(evt);
            }
        });
        TxtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtCantidadKeyTyped(evt);
            }
        });

        BtnEliminarP.setBackground(new java.awt.Color(255, 255, 255));
        BtnEliminarP.setForeground(new java.awt.Color(255, 255, 255));
        BtnEliminarP.setText("eliminar");
        BtnEliminarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarPActionPerformed(evt);
            }
        });

        BtnAgregarP.setBackground(new java.awt.Color(255, 255, 255));
        BtnAgregarP.setForeground(new java.awt.Color(255, 255, 255));
        BtnAgregarP.setText("agregar");
        BtnAgregarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarPActionPerformed(evt);
            }
        });

        labelm.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelm.setText("Meses");

        txtm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmActionPerformed(evt);
            }
        });

        jRadioButton1.setText("Mensualidad");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtm, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelm, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtm, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtCantidadArt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(BTNGUARDAR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTNCANCELA)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(153, 153, 153)
                                .addComponent(jLabel4)
                                .addGap(64, 64, 64)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TxtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)))
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtreferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(TxtClaveUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TxtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(BtnAgregarP, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnEliminarP, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel10)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2))
                    .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel14)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(TxtClaveUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtreferencia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(TxtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnAgregarP, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnEliminarP, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(TxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(TxtCantidadArt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNCANCELA)
                    .addComponent(BTNGUARDAR))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        v = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void TxtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTotalActionPerformed

    private void TxtTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTotalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTotalKeyTyped

    private void TxtCantidadArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCantidadArtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCantidadArtActionPerformed

    private void TxtCantidadArtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCantidadArtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCantidadArtKeyTyped

    private void BTNGUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNGUARDARActionPerformed


    }//GEN-LAST:event_BTNGUARDARActionPerformed

    private void TxtClaveUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtClaveUsuarioActionPerformed


    }//GEN-LAST:event_TxtClaveUsuarioActionPerformed

    private void TxtClaveUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtClaveUsuarioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtClaveUsuarioKeyTyped

    private void BTNCANCELAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCANCELAActionPerformed

        try {
            Connection con = conexionMySQL.getConnection();
            ps = con.prepareStatement("DELETE FROM pagos WHERE referencia=?");
            ps.setInt(1, Integer.parseInt(txtreferencia.getText()));
            int res = ps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Pago Cancelada");
            } else {
                JOptionPane.showMessageDialog(null, "Error Al cancelar");

            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }//GEN-LAST:event_BTNCANCELAActionPerformed

    private void TxtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCantidadActionPerformed

    private void TxtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCantidadKeyTyped
        char tecla = evt.getKeyChar();
        if (((tecla < '0') || (tecla > '9'))) {
            evt.consume();
        }
        if (TxtCantidad.getText().length() >= 2) {
            evt.consume();

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCantidadKeyTyped

    private void BtnAgregarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarPActionPerformed
        try {
            Connection con = conexionMySQL.getConnection();
            ps = con.prepareStatement("INSERT INTO `jcsdb`.`pagos` (`referencia`, `clave_usuario`, `clave_alum`, `fecha`) VALUES (?, ?, ?, ?);");
            ps.setString(1, txtreferencia.getText());
            ps.setString(2, txtusuario.getText());
            ps.setString(3, TxtClaveUsuario.getText());
            ps.setString(4, ((JTextField) TxtFecha.getDateEditor().getUiComponent()).getText());
            ps.executeUpdate();
                inserServicio();
        } catch (Exception e) {
            try {
                inserServicio();
            } catch (Exception ex) {
                Logger.getLogger(pagos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_BtnAgregarPActionPerformed

    private void BtnEliminarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarPActionPerformed
/*https://www.youtube.com/watch?v=0Lxl1m1APGk&ab_channel=Ingenier%C3%ADadeSistemas*/
    }//GEN-LAST:event_BtnEliminarPActionPerformed

    private void txtreferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtreferenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtreferenciaActionPerformed

    private void txtreferenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtreferenciaKeyTyped
        char tecla = evt.getKeyChar();
        if (((tecla < '0') || (tecla > '9'))) {
            evt.consume();
        }
        if (txtreferencia.getText().length() >= 5) {
            evt.consume();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtreferenciaKeyTyped

    private void txtservicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtservicioActionPerformed

    }//GEN-LAST:event_txtservicioActionPerformed

    private void txtmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if (jRadioButton1.isSelected() == true) {
            labelm.setVisible(true);
            txtm.setVisible(true);

        } else {
            labelm.setVisible(false);
            txtm.setVisible(false);

        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void txtservicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtservicioItemStateChanged

    }//GEN-LAST:event_txtservicioItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNCANCELA;
    private javax.swing.JButton BTNGUARDAR;
    private javax.swing.JButton BtnAgregarP;
    private javax.swing.JButton BtnEliminarP;
    private javax.swing.JTextField TxtCantidad;
    private javax.swing.JTextField TxtCantidadArt;
    private javax.swing.JTextField TxtClaveUsuario;
    private com.toedter.calendar.JDateChooser TxtFecha;
    private javax.swing.JTextField TxtTotal;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelm;
    private javax.swing.JComboBox txtm;
    private javax.swing.JTextField txtreferencia;
    private javax.swing.JComboBox txtservicio;
    public static javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
