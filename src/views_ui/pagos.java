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
    PreparedStatement ps, ps1, ps2;
    ResultSet rs, rs1;

    javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel() {
        @Override
        public boolean isCellEditable(int i, int i1) {
            return false; //To change body of generated methods, choose Tools | Templates.
        }
    };

    /**
     * Creates new form pagos
     */
//    public void borrar_tabla() {
//        DefaultTableModel modelo = new DefaultTableModel();
//        jTable1.setModel(modelo);
//        modelo.setRowCount(0);
//    }
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
        jcmeses.setVisible(false);
        jbanticipo.setVisible(false);
        Txtabono.setVisible(false);
        TxtDescuento.setEnabled(false);
        TxtDescuento.setVisible(false);
        jLabel10.setVisible(false);
        combom();
        cargarReferencias();
        cargarcodigo();
        cargarServicios();

    }

    private void combom() {
        jcmeses.addItem("1");
        jcmeses.addItem("2");
        jcmeses.addItem("3");
        jcmeses.addItem("4");
        jcmeses.addItem("5");
        jcmeses.addItem("6");
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
        //DefaultTableModel modelo = new DefaultTableModel();
        modelo.setRowCount(0);
        modelo.setColumnCount(0);
        modelo.addColumn("Servicio");
        modelo.addColumn("Nombre");
        modelo.addColumn("Costo");
        String[] datos = new String[3];

        try {
            ps = con.prepareStatement("SELECT referencia_s, nombre, costo FROM s_pagos where referencia_p = ?;");
            ps.setString(1, txtreferencia.getText());
            rs = ps.executeQuery();

            while (rs.next()) {
                tblServicios.setModel(modelo);
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                modelo.addRow(datos);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        try {
            ps = con.prepareStatement("SELECT sum(costo) as Total FROM jcsdb.s_pagos WHERE referencia_p = ?;");
            ps.setString(1, txtreferencia.getText());
            rs = ps.executeQuery();
            while (rs.next()) {
                TxtTotal1.setText(rs.getString("Total"));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void cargarServicios_mes() throws Exception {
        Connection con = conexionMySQL.getConnection();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Servicio");
        modelo.addColumn("Costo");
        modelo.addColumn("Meses");
        modelo.addColumn("Anticipo");
        modelo.addColumn("Saldo");
        modelo.addColumn("Mensualidad");
        String[] datos = new String[6];
        try {
            ps = con.prepareStatement("SELECT referencia_s,costo, mensualidad,anticipo, "
                    + "(costo - anticipo) as saldo, FORMAT(((costo - anticipo)/mensualidad),2) as menpago FROM jcsdb.s_pagos where referencia_p = ?;");
            ps.setString(1, txtreferencia.getText());
            rs = ps.executeQuery();
            while (rs.next()) {
                tblServicios.setModel(modelo);
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                modelo.addRow(datos);
            }
            try {
                ps = con.prepareStatement("SELECT (costo - anticipo) as saldo FROM jcsdb.s_pagos WHERE referencia_p = ?;");
                ps.setString(1, txtreferencia.getText());
                rs = ps.executeQuery();
                while (rs.next()) {
                    TxtTotal1.setText(rs.getString("saldo"));
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void cargarServicios_m() throws Exception {
        Connection con = conexionMySQL.getConnection();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Servicio");
        modelo.addColumn("Costo");
        modelo.addColumn("Meses");
        modelo.addColumn("Mensualidad");
        String[] datos = new String[4];
        try {
            ps = con.prepareStatement("SELECT referencia_s,costo, mensualidad, "
                    + "FORMAT((costo/mensualidad),2) as menpago FROM jcsdb.s_pagos where referencia_p = ?;");
            ps.setString(1, txtreferencia.getText());
            rs = ps.executeQuery();
            while (rs.next()) {
                tblServicios.setModel(modelo);
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                modelo.addRow(datos);
            }
            try {
                ps = con.prepareStatement("SELECT costo FROM jcsdb.s_pagos WHERE referencia_p = ?;");
                ps.setString(1, txtreferencia.getText());
                rs = ps.executeQuery();
                while (rs.next()) {
                    TxtTotal1.setText(rs.getString("costo"));
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void saldo() throws Exception {
        Connection con = conexionMySQL.getConnection();
        try {
            ps = con.prepareStatement("UPDATE alumno SET Saldo = 0 WHERE clave = ?;");
            ps.setString(1, TxtClaveUsuario.getText());
            ps.executeUpdate();
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

            if (jRbSaldo.isSelected() == true) {
                if (TxtDescuento.getText().equals("0")) {
                    int saldo = Integer.parseInt(txtSaldoUser.getText());
                    if (saldo < Integer.parseInt(serv.cvCosto)) {
                        ps.setInt(4, (Integer.parseInt(serv.cvCosto) - saldo));
                        ps1 = con.prepareStatement("UPDATE alumno SET Saldo = 0 WHERE clave = ?;");
                        ps1.setString(1, TxtClaveUsuario.getText());
                        ps1.executeUpdate();
                        txtSaldoUser.setText("0");
                    } else {
                        int opc = saldo - (Integer.parseInt(serv.cvCosto));
                        ps.setInt(4, Integer.parseInt(serv.cvCosto));
                        ps1 = con.prepareStatement("UPDATE alumno SET Saldo = " + opc + " WHERE clave = ?;");
                        ps1.setString(1, TxtClaveUsuario.getText());
                        ps1.executeUpdate();
                        txtSaldoUser.setText("" + opc);
                    }
                } else {
                    int desc = Integer.parseInt(TxtDescuento.getText());
                    int saldo = Integer.parseInt(txtSaldoUser.getText());
                    if (saldo < Integer.parseInt(serv.cvCosto)) {
                        ps.setInt(4, (Integer.parseInt(serv.cvCosto) - (Integer.parseInt(serv.cvCosto) * desc) / 100) - saldo);
                        ps2 = con.prepareStatement("UPDATE alumno SET Saldo = 0 WHERE clave = ?;");
                        ps2.setString(1, TxtClaveUsuario.getText());
                        ps2.executeUpdate();
                        txtSaldoUser.setText("0");
                    } else {
                        int opc = saldo - (Integer.parseInt(serv.cvCosto) - (Integer.parseInt(serv.cvCosto) * desc) / 100);
                        ps.setInt(4, Integer.parseInt(serv.cvCosto) - (Integer.parseInt(serv.cvCosto) * desc) / 100);
                        ps2 = con.prepareStatement("UPDATE alumno SET Saldo = " + opc + " WHERE clave = ?;");
                        ps2.setString(1, TxtClaveUsuario.getText());
                        ps2.executeUpdate();
                        txtSaldoUser.setText("" + opc);
                    }
                }
            } else {
                if (TxtDescuento.getText().equals("0")) {
                    ps.setString(4, serv.cvCosto);
                } else {
                    int desc = Integer.parseInt(TxtDescuento.getText());
                    ps.setInt(4, Integer.parseInt(serv.cvCosto) - (Integer.parseInt(serv.cvCosto) * desc) / 100);
                }
            }
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Servicio agregado", "Guardar", JOptionPane.INFORMATION_MESSAGE);
            cargarServicios();
            TxtNotas.setText("");
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "No se agrego servicio");
        }
    }

    private void inserServicio_mes() throws Exception {
        costos();
        Connection con = conexionMySQL.getConnection();
        try {
            servicios serv = new servicios();
            ps = con.prepareStatement("INSERT INTO jcsdb.s_pagos (`referencia_p`, `referencia_s`,`Nota`,`nombre`,`costo`,`mensualidad`, `anticipo`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, 0);");
            ps.setString(1, txtreferencia.getText());
            ps.setString(2, serv.cvreferencia);
            ps.setString(3, TxtNotas.getText());
            ps.setString(4, serv.cvNombre);
            if (TxtDescuento.getText().equals("0")) {
                ps.setString(5, serv.cvCosto);
            } else {
                int desc = Integer.parseInt(TxtDescuento.getText());
                ps.setInt(5, Integer.parseInt(serv.cvCosto) - (Integer.parseInt(serv.cvCosto) * desc) / 100);
            }
            int combos = jcmeses.getSelectedIndex();
            switch (combos) {
                case 0: {
                    ps.setString(6, "1");
                    break;
                }
                case 1: {
                    ps.setString(6, "2");
                    break;
                }
                case 2: {
                    ps.setString(6, "3");
                    break;
                }
                case 3: {
                    ps.setString(6, "4");
                    break;
                }
                case 4: {
                    ps.setString(6, "5");
                    break;
                }
                case 5: {
                    ps.setString(6, "6");
                    break;
                }
            }
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Servicio agregado", "Guardar", JOptionPane.INFORMATION_MESSAGE);
            cargarServicios_m();
            TxtNotas.setText("");
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "No se agrego servicio");
        }
    }

    private void inserServicio_mes_anti() throws Exception {
        costos();
        Connection con = conexionMySQL.getConnection();
        try {
            servicios serv = new servicios();
            ps = con.prepareStatement("INSERT INTO jcsdb.s_pagos (`referencia_p`,`referencia_s`,`fecha_a`,`Nota`,`nombre`,`costo`,`mensualidad`,`anticipo`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
            ps.setString(1, txtreferencia.getText());
            ps.setString(2, serv.cvreferencia);
            ps.setString(3, ((JTextField) TxtFecha.getDateEditor().getUiComponent()).getText());
            ps.setString(4, TxtNotas.getText());
            ps.setString(5, serv.cvNombre);
            if (TxtDescuento.getText().equals("0")) {
                ps.setString(6, serv.cvCosto);
            } else {
                int desc = Integer.parseInt(TxtDescuento.getText());
                ps.setInt(6, Integer.parseInt(serv.cvCosto) - (Integer.parseInt(serv.cvCosto) * desc) / 100);
            }
            int combos = jcmeses.getSelectedIndex();
            switch (combos) {
                case 0: {
                    ps.setString(7, "1");
                    break;
                }
                case 1: {
                    ps.setString(7, "2");
                    break;
                }
                case 2: {
                    ps.setString(7, "3");
                    break;
                }
                case 3: {
                    ps.setString(7, "4");
                    break;
                }
                case 4: {
                    ps.setString(7, "5");
                    break;
                }
                case 5: {
                    ps.setString(7, "6");
                    break;
                }
            }
            if (jRbSaldo.isSelected() == true) {
                int saldo = Integer.parseInt(txtSaldoUser.getText());
                int anticipo = Integer.parseInt(Txtabono.getText());
                if (anticipo > saldo) {
                    JOptionPane.showMessageDialog(null, "No cuenta con saldo suficiente");
                    txtSaldoUser.setText("");
                } else {
                    int opc = saldo - anticipo;
                    int costo = Integer.parseInt(serv.cvCosto);
                    if (anticipo >= costo) {
                        JOptionPane.showMessageDialog(null, "Por favor pague de contado");
                        txtSaldoUser.setText("");
                    } else {
                        ps.setInt(8, anticipo);
                        ps1 = con.prepareStatement("UPDATE alumno SET Saldo = " + opc + " WHERE clave = ?;");
                        ps1.setString(1, TxtClaveUsuario.getText());
                        ps1.executeUpdate();
                        txtSaldoUser.setText("" + opc);
                    }
                }
            } else {
                ps.setString(8, Txtabono.getText());
            }
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Servicio agregado", "Guardar", JOptionPane.INFORMATION_MESSAGE);
            cargarServicios_mes();
            TxtNotas.setText("");
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "No se agrego servicio");
        }
    }

    private void agregar() {
        if (jbmensualidad.isSelected() == true && jbanticipo.isSelected() == true) {
            try {
                servicio_desc();
            } catch (Exception ex) {
                Logger.getLogger(pagos.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Connection con = conexionMySQL.getConnection();
                ps = con.prepareStatement("INSERT INTO `jcsdb`.`pagos` (`referencia`, `clave_usuario`, `clave_alum`, `fecha`, `estatus`) VALUES (?, ?, ?, ?,'pendiente');");
                ps.setString(1, txtreferencia.getText());
                ps.setString(2, txtusuario.getText());
                ps.setString(3, TxtClaveUsuario.getText());
                ps.setString(4, ((JTextField) TxtFecha.getDateEditor().getUiComponent()).getText());
                ps.executeUpdate();
                inserServicio_mes_anti();
            } catch (Exception e) {
                try {
                    inserServicio_mes_anti();
                } catch (Exception ex) {
                    Logger.getLogger(pagos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (jbmensualidad.isSelected() == true) {
            try {
                servicio_desc();
            } catch (Exception ex) {
                Logger.getLogger(pagos.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Connection con = conexionMySQL.getConnection();
                ps = con.prepareStatement("INSERT INTO `jcsdb`.`pagos` (`referencia`, `clave_usuario`, `clave_alum`, `fecha`, `estatus`) VALUES (?, ?, ?, ?,'pendiente');");
                ps.setString(1, txtreferencia.getText());
                ps.setString(2, txtusuario.getText());
                ps.setString(3, TxtClaveUsuario.getText());
                ps.setString(4, ((JTextField) TxtFecha.getDateEditor().getUiComponent()).getText());
                ps.executeUpdate();
                inserServicio_mes();
            } catch (Exception e) {
                try {
                    inserServicio_mes();
                } catch (Exception ex) {
                    Logger.getLogger(pagos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            // se paga a contado
            try {
                servicio_desc();
            } catch (Exception ex) {
                Logger.getLogger(pagos.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Connection con = conexionMySQL.getConnection();
                ps = con.prepareStatement("INSERT INTO `jcsdb`.`pagos` (`referencia`, `clave_usuario`, `clave_alum`, `fecha`, `estatus`) VALUES (?, ?, ?, ?,'pagado');");
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
        }
    }

    private void servicio_desc() throws Exception {
        servicios serv = new servicios();
        try {
            Connection con = conexionMySQL.getConnection();
            ps = con.prepareStatement("SELECT * FROM jcsdb.servicios WHERE Nombre = ?");
            ps.setString(1, (String) txtservicio.getSelectedItem());
            rs = ps.executeQuery();
            if (rs.next()) {
                serv.cvNombre = rs.getString("Nombre");
            } else {
                JOptionPane.showMessageDialog(null, "No Existe servicio");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        if (serv.cvNombre.equals("Colegiaturas, Servicios educativos nivel medio superior") || serv.cvNombre.equals("Reinscripcion") || serv.cvNombre.equals("Inscripcion")) {
            TxtDescuento.setVisible(true);
            jLabel10.setVisible(true);
        } else {
            TxtDescuento.setVisible(false);
            TxtDescuento.setText("0");
            jLabel10.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton2 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblServicios = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
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
        TxtDescuento = new javax.swing.JTextField();
        BtnEliminarP = new javax.swing.JButton();
        BtnAgregarP = new javax.swing.JButton();
        txtusuario = new javax.swing.JTextField();
        TxtTotal1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        labelm = new javax.swing.JLabel();
        jcmeses = new javax.swing.JComboBox();
        jbmensualidad = new javax.swing.JRadioButton();
        jbanticipo = new javax.swing.JRadioButton();
        Txtabono = new javax.swing.JTextField();
        TxtNotas = new javax.swing.JTextField();
        labelm1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtSaldoUser = new javax.swing.JTextField();
        jRbSaldo = new javax.swing.JRadioButton();

        jRadioButton2.setText("Mensualidad");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        setClosable(true);
        setTitle("Pagos");
        setPreferredSize(new java.awt.Dimension(760, 490));
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

        tblServicios.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 153), null, null));
        tblServicios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblServicios);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Total $");

        jLabel3.setBackground(new java.awt.Color(69, 73, 74));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Referencia pago");

        jLabel4.setBackground(new java.awt.Color(69, 73, 74));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Clave del alumno");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Fecha ");

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
        TxtClaveUsuario.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                TxtClaveUsuarioCaretUpdate(evt);
            }
        });
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        TxtDescuento.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 204, 204), null, null));
        TxtDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtDescuentoActionPerformed(evt);
            }
        });
        TxtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtDescuentoKeyTyped(evt);
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

        TxtTotal1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        TxtTotal1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 204, 204), null, null));
        TxtTotal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtTotal1ActionPerformed(evt);
            }
        });
        TxtTotal1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtTotal1KeyTyped(evt);
            }
        });

        labelm.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelm.setText("Meses");

        jcmeses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmesesActionPerformed(evt);
            }
        });

        jbmensualidad.setText("Mensualidad");
        jbmensualidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbmensualidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbmensualidad, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelm, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jcmeses, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jbmensualidad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcmeses, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jbanticipo.setText("Anticipo");
        jbanticipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbanticipoActionPerformed(evt);
            }
        });

        Txtabono.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Txtabono.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 204, 204), null, null));
        Txtabono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtabonoActionPerformed(evt);
            }
        });
        Txtabono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtabonoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jbanticipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Txtabono, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbanticipo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Txtabono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        labelm1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelm1.setText("Observaciones");

        jLabel1.setText("Saldo:");

        txtSaldoUser.setEnabled(false);
        txtSaldoUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaldoUserActionPerformed(evt);
            }
        });

        jRbSaldo.setText("Aplicar saldo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(TxtTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTNGUARDAR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTNCANCELA)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TxtNotas, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(177, 177, 177)
                                        .addComponent(jLabel4)
                                        .addGap(82, 82, 82)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TxtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtreferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(TxtClaveUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelm1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BtnAgregarP, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BtnEliminarP, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TxtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel10))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSaldoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jRbSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSaldoUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jRbSaldo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel14)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(TxtClaveUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtreferencia, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtDescuento, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnAgregarP, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnEliminarP, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelm1))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtNotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(BTNCANCELA)
                    .addComponent(BTNGUARDAR)
                    .addComponent(TxtTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        v = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void TxtabonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtabonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtabonoActionPerformed

    private void TxtabonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtabonoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtabonoKeyTyped

    private void BTNGUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNGUARDARActionPerformed

        JOptionPane.showMessageDialog(null, "Se ha realizado la operacion exitosamente");
        v = null;
        dispose();

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
            ps.setString(1, txtreferencia.getText());
            int res = ps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Pago Cancelado");
                v = null;
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error al cancelar");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }//GEN-LAST:event_BTNCANCELAActionPerformed

    private void TxtDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDescuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDescuentoActionPerformed

    private void TxtDescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtDescuentoKeyTyped
        char tecla = evt.getKeyChar();
        if (((tecla < '0') || (tecla > '9'))) {
            evt.consume();
        }
        if (TxtDescuento.getText().length() >= 2) {
            evt.consume();

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDescuentoKeyTyped

    private void BtnAgregarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarPActionPerformed
        agregar();
    }//GEN-LAST:event_BtnAgregarPActionPerformed

    private void BtnEliminarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarPActionPerformed
        try {
            Connection con = conexionMySQL.getConnection();
            ps = con.prepareStatement("DELETE FROM `s_pagos` WHERE (referencia_p = ? and referencia_s = ?);");
            ps.setString(1, txtreferencia.getText());
            ps.setString(2, (String) tblServicios.getValueAt(tblServicios.getSelectedRow(), 0));
            int res = ps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Servicio eliminado ");
                cargarServicios();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese servicios por cobrar");
                //borrar_tabla();
                cargarServicios();
            }
        } catch (Exception e) {
            System.err.println(e);
        }

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

    private void jcmesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmesesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmesesActionPerformed

    private void jbmensualidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbmensualidadActionPerformed
        if (jbmensualidad.isSelected() == true) {
            labelm.setVisible(true);
            jcmeses.setVisible(true);
            jbanticipo.setVisible(true);

        } else {
            labelm.setVisible(false);
            jcmeses.setVisible(false);
            jbanticipo.setVisible(false);

        }
    }//GEN-LAST:event_jbmensualidadActionPerformed

    private void txtservicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtservicioItemStateChanged

    }//GEN-LAST:event_txtservicioItemStateChanged

    private void TxtTotal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtTotal1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTotal1ActionPerformed

    private void TxtTotal1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTotal1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTotal1KeyTyped

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jbanticipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbanticipoActionPerformed
        if (jbanticipo.isSelected() == true) {
            Txtabono.setVisible(true);
            labelm.setVisible(false);
            jcmeses.setVisible(false);
            jbmensualidad.setVisible(false);
        } else {
            Txtabono.setVisible(false);
            labelm.setVisible(true);
            jcmeses.setVisible(true);
            jbmensualidad.setVisible(true);
        }
    }//GEN-LAST:event_jbanticipoActionPerformed

    private void TxtClaveUsuarioCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_TxtClaveUsuarioCaretUpdate
        String valor = TxtClaveUsuario.getText();
        if (valor.isEmpty()) {
            TxtDescuento.setText(null);
        } else {
            try {
                Connection con = conexionMySQL.getConnection();
                ps = con.prepareStatement("select descuento, saldo from jcsdb.alumno where clave = " + valor + ";");
                rs = ps.executeQuery();
                while (rs.next()) {
                    TxtDescuento.setText(rs.getString("descuento"));
                    txtSaldoUser.setText(rs.getString("Saldo"));
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }//GEN-LAST:event_TxtClaveUsuarioCaretUpdate

    private void txtSaldoUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaldoUserActionPerformed
        // TODO add your handling code here:
        txtSaldoUser.setEnabled(false);
    }//GEN-LAST:event_txtSaldoUserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNCANCELA;
    private javax.swing.JButton BTNGUARDAR;
    private javax.swing.JButton BtnAgregarP;
    private javax.swing.JButton BtnEliminarP;
    private javax.swing.JTextField TxtClaveUsuario;
    private javax.swing.JTextField TxtDescuento;
    private com.toedter.calendar.JDateChooser TxtFecha;
    private javax.swing.JTextField TxtNotas;
    private javax.swing.JTextField TxtTotal1;
    private javax.swing.JTextField Txtabono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRbSaldo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jbanticipo;
    private javax.swing.JRadioButton jbmensualidad;
    private javax.swing.JComboBox jcmeses;
    private javax.swing.JLabel labelm;
    private javax.swing.JLabel labelm1;
    private javax.swing.JTable tblServicios;
    private javax.swing.JTextField txtSaldoUser;
    private javax.swing.JTextField txtreferencia;
    private javax.swing.JComboBox txtservicio;
    public static javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
