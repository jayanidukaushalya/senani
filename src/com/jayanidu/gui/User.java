/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.jayanidu.gui;

import com.jayanidu.model.MySQL;
import com.jayanidu.model.TableAlignment;
import java.awt.Color;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asus
 */
public class User extends javax.swing.JDialog {

    /**
     * Creates new form User
     */
    Home h;

    public User(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        this.h = (Home) parent;

        initComponents();
        
        jTable1.getColumnModel().getColumn(0).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(1).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(2).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(3).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(4).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(5).setCellRenderer(TableAlignment.centerAlignment());
        
        loadUsers();
        loadTable();

        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jTextField5.setEditable(false);

        setLocationRelativeTo(h);
    }

    private void loadTable() {
        try {

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ResultSet rs = MySQL.search("SELECT * FROM `user` u INNER JOIN `utype` ut ON u.`utype_id` = ut.`id` INNER JOIN `city` c ON u.`city_id` = c.`id` WHERE u.`status` = 1 ORDER BY ut.`name` ASC");
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("u.name"));
                v.add(rs.getString("u.nic"));
                v.add(rs.getString("u.phone"));
                v.add(rs.getString("u.username"));
                v.add(rs.getString("ut.name"));
                v.add(rs.getString("c.name"));

                dtm.addRow(v);
            }

            jTable1.setModel(dtm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadUsers() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `user` INNER JOIN `utype` ON `user`.`utype_id` = `utype`.`id` WHERE `user`.`status` = 1");
            Vector v = new Vector();
            v.add("Select User");
            while (rs.next()) {
                v.add(rs.getString("user.username") + "-" + rs.getString("utype.name"));
            }
            DefaultComboBoxModel cbm = new DefaultComboBoxModel(v);
            jComboBox3.setModel(cbm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadCities() {
        String uname = jComboBox3.getSelectedItem().toString().split("-")[0];
        try {
            Vector v = new Vector();
            ResultSet rs = MySQL.search("SELECT * FROM `user` INNER JOIN `utype` ON `user`.`utype_id` = `utype`.`id` INNER JOIN `city` ON `user`.`city_id` = `city`.`id` WHERE `user`.`username` = '" + uname + "' AND `user`.`status` = 1");

            if (rs.next()) {
                String city1 = rs.getString("city.name");
                v.add(city1);
                ResultSet rs1 = MySQL.search("SELECT * FROM `city` WHERE `name` <> '" + city1 + "' AND `status` = 1");
                while (rs1.next()) {
                    v.add(rs1.getString("name"));
                }
            } else {
                ResultSet rs2 = MySQL.search("SELECT * FROM `city` WHERE `status` = 1");
                v.add("Select City");
                while (rs2.next()) {
                    v.add(rs2.getString("name"));
                }
            }

            DefaultComboBoxModel cbm = new DefaultComboBoxModel(v);
            jComboBox2.setModel(cbm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadUserTypes() {
        String uname = jComboBox3.getSelectedItem().toString().split("-")[0];
        try {
            Vector v = new Vector();
            ResultSet rs = MySQL.search("SELECT * FROM `user` INNER JOIN `utype` ON `user`.`utype_id` = `utype`.`id` WHERE `user`.`username` = '" + uname + "' AND `user`.`status` = 1");

            if (rs.next()) {
                String utype1 = rs.getString("utype.name");
                v.add(utype1);
                ResultSet rs1 = MySQL.search("SELECT * FROM `utype` WHERE `name` <> '" + utype1 + "' AND `status` = 1");
                while (rs1.next()) {
                    v.add(rs1.getString("name"));
                }
            } else {
                ResultSet rs2 = MySQL.search("SELECT * FROM `utype` WHERE `status` = 1");
                v.add("Select User Type");
                while (rs2.next()) {
                    v.add(rs2.getString("name"));
                }
            }

            DefaultComboBoxModel cbm = new DefaultComboBoxModel(v);
            jComboBox1.setModel(cbm);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void clearFields() {
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jComboBox1.removeAllItems();
        jComboBox2.removeAllItems();

        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
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
        jLabel17 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField8 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jMenuItem1.setText("Remove User");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("USER CONFIGURATIONS");
        jLabel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLabel17.setOpaque(true);
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 530, 70));
        jLabel17.setBackground(new Color(242, 242, 242, 120));

        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Back");
        jButton9.setContentAreaFilled(false);
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton9MouseExited(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 560, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 178, 194), 3));

        jTable1.setFont(new java.awt.Font("Century Gothic", 2, 12)); // NOI18N
        jTable1.setForeground(new java.awt.Color(52, 73, 94));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "NIC", "Phone", "Username", "Type", "City"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(100);
        }

        jButton3.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(52, 73, 94));
        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(52, 73, 94));
        jLabel3.setText("User");

        jComboBox3.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(52, 73, 94));
        jComboBox3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox3FocusGained(evt);
            }
        });
        jComboBox3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox3MouseClicked(evt);
            }
        });
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(52, 73, 94));
        jLabel8.setText("Name");

        jTextField7.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(52, 73, 94));
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(52, 73, 94));
        jLabel5.setText("Username");

        jTextField5.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(52, 73, 94));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(52, 73, 94));
        jLabel11.setText("Mobile");

        jTextField9.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(52, 73, 94));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.LEADING, 0, 179, Short.MAX_VALUE)
                    .addComponent(jTextField9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel7.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(52, 73, 94));
        jLabel7.setText("User Type");

        jLabel10.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(52, 73, 94));
        jLabel10.setText("NIC");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(52, 73, 94));
        jLabel6.setText("Password");

        jLabel13.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(52, 73, 94));
        jLabel13.setText("City");

        jComboBox1.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(52, 73, 94));
        jComboBox1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox1FocusGained(evt);
            }
        });
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField8.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(52, 73, 94));

        jTextField6.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(52, 73, 94));

        jComboBox2.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(52, 73, 94));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("+");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, 0, 197, Short.MAX_VALUE)
                            .addComponent(jTextField8)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(17, 17, 17)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(jButton3)
                .addGap(31, 31, 31))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 860, 420));

        jLabel1.setBackground(new java.awt.Color(204, 255, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jayanidu/resources/39.jpg"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 178, 194), 5));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1FocusGained

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String getuser = jComboBox3.getSelectedItem().toString();
        String getname = jTextField7.getText().trim();
        String getuname = jTextField5.getText().trim();
        String getpword = jTextField6.getText().trim();
        String getnic = jTextField8.getText().trim();
        String getmobile = jTextField9.getText().trim();

        if (getuser.equals("Select User")) {
            JOptionPane.showMessageDialog(this, "Please select a user!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jComboBox1.getSelectedItem().toString().equals("Select User Type")) {
            JOptionPane.showMessageDialog(this, "Please select a user type!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (getname.length() > 45) {
            JOptionPane.showMessageDialog(this, "Name field out of range!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (getname.isBlank()) {
            JOptionPane.showMessageDialog(this, "Name field empty!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!username.equals(getuname)) {
            JOptionPane.showMessageDialog(this, "Username cannot be update!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (getpword.length() > 20) {
            JOptionPane.showMessageDialog(this, "Password field out of range!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (getnic.length() > 12) {
            JOptionPane.showMessageDialog(this, "NIC field out of range!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (getnic.isBlank()) {
            JOptionPane.showMessageDialog(this, "NIC field empty!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (getmobile.length() > 10) {
            JOptionPane.showMessageDialog(this, "Mobile field out of range!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (getmobile.isBlank()) {
            JOptionPane.showMessageDialog(this, "Mobile field empty!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jComboBox2.getSelectedItem().toString().equals("Select City")) {
            JOptionPane.showMessageDialog(this, "Please select a city!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            String getUtype = jComboBox1.getSelectedItem().toString();
            String getcity = jComboBox2.getSelectedItem().toString();

            try {
                if (!utype.equals(getUtype) || !name.equals(getname) || !pword.equals(getpword) || !nic.equals(getnic) || !mobile.equals(getmobile) || !city.equals(getcity)) {
                    ResultSet rs = MySQL.search("SELECT `id` FROM `utype` WHERE `name` = '" + getUtype + "'");
                    ResultSet rs2 = MySQL.search("SELECT `id` FROM `city` WHERE `name` = '" + getcity + "'");
                    if (rs.next() && rs2.next()) {
                        MySQL.iud("UPDATE `user` SET `name` = '" + getname + "', `utype_id` = '" + rs.getString("id") + "', `password` = '" + getpword + "', `nic` = '" + getnic + "', `phone` = '" + getmobile + "', `city_id` = '" + rs2.getString("id") + "' WHERE `username` = '" + username + "'");
                        JOptionPane.showMessageDialog(this, "Update successfull!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        loadTable();
                        clearFields();
                        jComboBox3.setSelectedIndex(0);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Nothing to update!", "Error", JOptionPane.WARNING_MESSAGE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3FocusGained

    private void jComboBox3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3MouseClicked

    String utype;
    String name;
    String username;
    String pword;
    String nic;
    String mobile;
    String city;

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
        String user = jComboBox3.getSelectedItem().toString();

        if (!user.equals("Select User")) {

            jButton1.setEnabled(true);
            jButton2.setEnabled(true);

            loadUserTypes();
            loadCities();

            String uname = jComboBox3.getSelectedItem().toString().split("-")[0];
            jTextField5.setText(uname);
            try {
                ResultSet rs = MySQL.search("SELECT * FROM `user` WHERE `username` = '" + uname + "'");

                if (rs.next()) {
                    jTextField7.setText(rs.getString("name"));
                    jTextField8.setText(rs.getString("nic"));
                    jTextField9.setText(rs.getString("phone"));

                    utype = jComboBox1.getSelectedItem().toString();
                    name = jTextField7.getText().trim();
                    username = jTextField5.getText().trim();
                    pword = rs.getString("password");
                    nic = jTextField8.getText().trim();
                    mobile = jTextField9.getText().trim();
                    city = jComboBox2.getSelectedItem().toString();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            clearFields();
            pword = null;
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jButton9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseEntered
        // TODO add your handling code here:
        jButton9.setForeground(new Color(230, 230, 230));
    }//GEN-LAST:event_jButton9MouseEntered

    private void jButton9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseExited
        // TODO add your handling code here:
        jButton9.setForeground(Color.WHITE);
    }//GEN-LAST:event_jButton9MouseExited

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();

        if (row != -1) {
            int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Do you really want to remove this user?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (showConfirmDialog == JOptionPane.YES_OPTION) {
                MySQL.iud("UPDATE `user` SET `status` = 0 WHERE `username` = '" + jTable1.getValueAt(row, 3) + "'");
                loadTable();
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new AddUserType(this, true).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new AddCity(this, true).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                User dialog = new User(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    public javax.swing.JTextField jTextField5;
    public javax.swing.JTextField jTextField6;
    public javax.swing.JTextField jTextField7;
    public javax.swing.JTextField jTextField8;
    public javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
