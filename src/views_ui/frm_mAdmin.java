package views_ui;

import conexion_mysql.loginSession;
import static conexion_mysql.loginSession.adminID;
import java.util.logging.Level;
import java.util.logging.Logger;
import static views_ui.frm_mAdmin.v;

/**
 *
 * @author nesto
 */
public class frm_mAdmin extends javax.swing.JFrame {

    public static String v;

    /**
     * Creates new form frm_menuAdmin
     */
    public frm_mAdmin() {
        initComponents();
        this.setTitle("Sistema Contable Preparatoria JCS");
        setLocationRelativeTo(null);
        id.setText(loginSession.adminID);
        adminLbl.setText(loginSession.username);
        fullnameLbl.setText(loginSession.fullname);
        usertypeLbl.setText(loginSession.admintype);
        this.setExtendedState(MAXIMIZED_BOTH);
        v = "v";
        id.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        adminLbl = new javax.swing.JLabel();
        fullnameLbl = new javax.swing.JLabel();
        usertypeLbl = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        id = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        RegAlumnos = new javax.swing.JButton();
        LisAlumnos = new javax.swing.JButton();
        RPagos = new javax.swing.JButton();
        RegAlumnos2 = new javax.swing.JButton();
        RegAlumnos3 = new javax.swing.JButton();
        escritorio = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                formComponentRemoved(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tipo:");

        adminLbl.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        adminLbl.setForeground(new java.awt.Color(255, 255, 255));

        fullnameLbl.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        fullnameLbl.setForeground(new java.awt.Color(255, 255, 255));

        usertypeLbl.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        usertypeLbl.setForeground(new java.awt.Color(255, 255, 255));

        jButton3.setBackground(new java.awt.Color(0, 100, 150));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Cerrar Sesión");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        id.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        id.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fullnameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usertypeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usertypeLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(fullnameLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(adminLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton4.setText("Registro usuarios");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        RegAlumnos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RegAlumnos.setText("Registro alumnos");
        RegAlumnos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RegAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegAlumnosActionPerformed(evt);
            }
        });

        LisAlumnos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        LisAlumnos.setText("Mensualidades");
        LisAlumnos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LisAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LisAlumnosActionPerformed(evt);
            }
        });

        RPagos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RPagos.setText("Pagos");
        RPagos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RPagosActionPerformed(evt);
            }
        });

        RegAlumnos2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RegAlumnos2.setText("Reportes de cobro");
        RegAlumnos2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RegAlumnos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegAlumnos2ActionPerformed(evt);
            }
        });

        RegAlumnos3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RegAlumnos3.setText("servicios");
        RegAlumnos3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RegAlumnos3.setPreferredSize(new java.awt.Dimension(132, 30));
        RegAlumnos3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegAlumnos3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(RegAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RegAlumnos3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LisAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RegAlumnos2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(RegAlumnos3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(RPagos)
                        .addComponent(LisAlumnos)
                        .addComponent(RegAlumnos2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(RegAlumnos)))
                .addContainerGap())
        );

        escritorio.setBackground(new java.awt.Color(0, 102, 153));
        escritorio.setForeground(new java.awt.Color(0, 102, 153));
        escritorio.setToolTipText("");
        escritorio.setAutoscrolls(true);
        escritorio.setPreferredSize(new java.awt.Dimension(1000, 365));
        escritorio.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                escritorioComponentAdded(evt);
            }
        });

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 1014, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        frm_login abrir = new frm_login();
        abrir.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String v = usuarios_adm.v;
        if (v == null) {
            usuarios_adm pag = new usuarios_adm();
            escritorio.add(pag);
            pag.toFront();
            pag.setVisible(true);
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void escritorioComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_escritorioComponentAdded
        // TODO add your handling code here:
        v = null;
    }//GEN-LAST:event_escritorioComponentAdded

    private void RPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RPagosActionPerformed
        // TODO add your handling code here:

        String v = pagos.v;
        if (v == null) {
            pagos pag = null;
            try {
                pag = new pagos();
            } catch (Exception ex) {
                Logger.getLogger(frm_mAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
            escritorio.add(pag);
            pag.toFront();
            pag.setVisible(true);
        }
        pagos.txtusuario.setText(id.getText());

    }//GEN-LAST:event_RPagosActionPerformed

    private void RegAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegAlumnosActionPerformed
        String v = alumnos.v;
        if (v == null) {
            alumnos alum = new alumnos();
            escritorio.add(alum);
            alum.toFront();
            alum.setVisible(true);
        }

    }//GEN-LAST:event_RegAlumnosActionPerformed

    private void LisAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LisAlumnosActionPerformed
        // TODO add your handling code here:

        String v = Mensualidades.v;
        if (v == null) {
            Mensualidades meses = new Mensualidades();
            escritorio.add(meses);
            meses.toFront();
            meses.setVisible(true);
        }

    }//GEN-LAST:event_LisAlumnosActionPerformed

    private void RegAlumnos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegAlumnos2ActionPerformed
        // TODO add your handling code here:
        /*ListaAlumnos lista = new ListaAlumnos();
        escritorio.add(lista);
        lista.toFront();
        lista.setVisible(true);
         */
    }//GEN-LAST:event_RegAlumnos2ActionPerformed

    private void formComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentRemoved
        // TODO add your handling code here:

    }//GEN-LAST:event_formComponentRemoved

    private void RegAlumnos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegAlumnos3ActionPerformed
        String v = Servicios.v;
        if (v == null) {
            Servicios serv = new Servicios();
            escritorio.add(serv);
            serv.toFront();
            serv.setVisible(true);
        }
    }//GEN-LAST:event_RegAlumnos3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_mAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_mAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_mAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_mAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_mAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LisAlumnos;
    private javax.swing.JButton RPagos;
    private javax.swing.JButton RegAlumnos;
    private javax.swing.JButton RegAlumnos2;
    private javax.swing.JButton RegAlumnos3;
    private javax.swing.JLabel adminLbl;
    public static javax.swing.JDesktopPane escritorio;
    private javax.swing.JLabel fullnameLbl;
    public static javax.swing.JLabel id;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel usertypeLbl;
    // End of variables declaration//GEN-END:variables
}
