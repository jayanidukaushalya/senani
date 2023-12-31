/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.jayanidu.gui;

import com.jayanidu.model.MySQL;
import com.jayanidu.model.TableAlignment;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asus
 */
public class AddUserType extends javax.swing.JDialog {

    /**
     * Creates new form AddUserType
     */
    public AddUserType(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    User u;

    public AddUserType(User parent, boolean modal) {
        super(parent, modal);
        this.u = parent;
        initComponents();

        jTable1.getColumnModel().getColumn(0).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(1).setCellRenderer(TableAlignment.centerAlignment());

        loadUserType();

        setLocationRelativeTo(u);
    }

    private void loadUserType() {

        try {

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            ResultSet rs = MySQL.search("SELECT * FROM `utype` WHERE `status` = 1");
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getInt("id"));
                v.add(rs.getString("name"));
                dtm.addRow(v);
            }

            jTable1.setModel(dtm);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void clearCheckBoxes() {
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);
        jCheckBox4.setSelected(false);
        jCheckBox5.setSelected(false);
        jCheckBox7.setSelected(false);
        jCheckBox8.setSelected(false);
        jCheckBox9.setSelected(false);
    }

    private void loadVisibleFrames(String utype) {
        clearCheckBoxes();

        try {
            ResultSet rs = MySQL.search("SELECT * FROM `utype` WHERE `name` = '" + utype + "'");
            if (rs.next()) {

                int aInt0 = rs.getInt("user");
                int aInt1 = rs.getInt("agent");
                int aInt2 = rs.getInt("return");
                int aInt3 = rs.getInt("grn");
                int aInt4 = rs.getInt("report");
                int aInt5 = rs.getInt("product");
                int aInt6 = rs.getInt("stock");
                int aInt7 = rs.getInt("sales");

                if (aInt0 == 1) {
                    jCheckBox1.setSelected(true);
                }
                if (aInt1 == 1) {
                    jCheckBox2.setSelected(true);
                }
                if (aInt2 == 1) {
                    jCheckBox3.setSelected(true);
                }
                if (aInt3 == 1) {
                    jCheckBox8.setSelected(true);
                }
                if (aInt4 == 1) {
                    jCheckBox4.setSelected(true);
                }
                if (aInt5 == 1) {
                    jCheckBox5.setSelected(true);
                }
                if (aInt6 == 1) {
                    jCheckBox7.setSelected(true);
                }
                if (aInt7 == 1) {
                    jCheckBox9.setSelected(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jMenuItem1.setText("Remove User Type");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(52, 73, 94));
        jLabel1.setText("User Type");

        jTextField1.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(52, 73, 94));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(52, 73, 94));
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(52, 73, 94));
        jLabel2.setText("Configurations");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(52, 73, 94));
        jLabel3.setText("Visible Frames");

        jCheckBox1.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(52, 73, 94));
        jCheckBox1.setText("User");
        jCheckBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jCheckBox2.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jCheckBox2.setForeground(new java.awt.Color(52, 73, 94));
        jCheckBox2.setText("Agent");
        jCheckBox2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jCheckBox3.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jCheckBox3.setForeground(new java.awt.Color(52, 73, 94));
        jCheckBox3.setText("Return");
        jCheckBox3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jCheckBox4.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jCheckBox4.setForeground(new java.awt.Color(52, 73, 94));
        jCheckBox4.setText("Report");
        jCheckBox4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jCheckBox5.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jCheckBox5.setForeground(new java.awt.Color(52, 73, 94));
        jCheckBox5.setText("Product");
        jCheckBox5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jCheckBox7.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jCheckBox7.setForeground(new java.awt.Color(52, 73, 94));
        jCheckBox7.setText("Stock");
        jCheckBox7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jCheckBox8.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jCheckBox8.setForeground(new java.awt.Color(52, 73, 94));
        jCheckBox8.setText("GRN");
        jCheckBox8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jCheckBox9.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jCheckBox9.setForeground(new java.awt.Color(52, 73, 94));
        jCheckBox9.setText("Sales");
        jCheckBox9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "User Types"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox9))
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox8))))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox7)
                    .addComponent(jCheckBox9))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String utype = jTextField1.getText().trim();

        int user = 0;
        int agent = 0;
        int returrn = 0;
        int grn = 0;
        int report = 0;
        int product = 0;
        int stock = 0;
        int sales = 0;

        if (jCheckBox1.isSelected()) {
            user = 1;
        }
        if (jCheckBox2.isSelected()) {
            agent = 1;
        }
        if (jCheckBox3.isSelected()) {
            returrn = 1;
        }
        if (jCheckBox8.isSelected()) {
            grn = 1;
        }
        if (jCheckBox4.isSelected()) {
            report = 1;
        }
        if (jCheckBox5.isSelected()) {
            product = 1;
        }
        if (jCheckBox7.isSelected()) {
            stock = 1;
        }
        if (jCheckBox9.isSelected()) {
            sales = 1;
        }

        try {

            if (utype.isBlank()) {
                JOptionPane.showMessageDialog(this, "User Type field empty!", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (utype.length() > 20) {
                JOptionPane.showMessageDialog(this, "User Type field out of range!", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ResultSet rs = MySQL.search("SELECT * FROM `utype` WHERE `name` = '" + utype + "' AND status = 1");
                if (rs.next()) {

                    int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Entered UserType already exists!, Do you want to update?", "Warning", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                    if (showConfirmDialog == JOptionPane.YES_OPTION) {
                        MySQL.iud("UPDATE `utype` SET `user` = " + user + ",`agent` = " + agent + ",`return` = " + returrn + ",`grn` = " + grn + ",`report` = " + report + ",`product` = " + product + ",`stock` = " + stock + ",`sales` = " + sales + " WHERE `name` = '" + utype + "'");
                        JOptionPane.showMessageDialog(this, "User type updated successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        u.loadUserTypes();
                        loadUserType();
                        clearCheckBoxes();
                        jTextField1.setText("");
                    }
                } else {
                    MySQL.iud("INSERT INTO `utype`(`name`,`user`,`agent`,`return`,`grn`,`report`,`product`,`stock`,`sales`) VALUES('" + utype + "'," + user + "," + agent + "," + returrn + "," + grn + "," + report + "," + product + "," + stock + "," + sales + ")");
                    JOptionPane.showMessageDialog(this, "User type added successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    u.loadUserTypes();
                    loadUserType();
                    clearCheckBoxes();
                    jTextField1.setText("");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        String utype = jTextField1.getText();

        loadVisibleFrames(utype);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();

        if (row != -1) {
            int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Do you really want to remove this selected User Type?", "Remove", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (showConfirmDialog == JOptionPane.YES_OPTION) {

                int id = Integer.parseInt(jTable1.getValueAt(row, 0).toString());
                MySQL.iud("UPDATE `utype` SET `status` = 0 WHERE `id` = " + id + "");
                loadUserType();
                clearCheckBoxes();
                jTextField1.setText("");
                u.loadUserTypes();
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();

        if (evt.getClickCount() == 1) {
            jTextField1.setText(jTable1.getValueAt(row, 1).toString());
            loadVisibleFrames(jTextField1.getText());
        }
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(AddUserType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddUserType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddUserType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddUserType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddUserType dialog = new AddUserType(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
