/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.jayanidu.gui;

import com.jayanidu.model.MySQL;
import com.jayanidu.model.TableAlignment;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Vector;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asus
 */
public class Product extends javax.swing.JDialog {

    /**
     * Creates new form Product
     */
    Home h;

    public Product(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        this.h = (Home) parent;

        initComponents();

        jTable1.getColumnModel().getColumn(0).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(1).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(2).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(3).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(4).setCellRenderer(TableAlignment.centerAlignment());

        jTextField1.setEditable(false);
        jTextField2.setEditable(false);
        jTextField3.setEditable(false);

        loadTable();
        setLocationRelativeTo(h);
    }

    GRN g;

    public Product(GRN parent, boolean modal) {
        super(parent, modal);

        this.g = parent;

        initComponents();

        jTable1.getColumnModel().getColumn(0).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(1).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(2).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(3).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(4).setCellRenderer(TableAlignment.centerAlignment());

        jTextField1.setEditable(false);
        jTextField2.setEditable(false);
        jTextField3.setEditable(false);

        loadTable();
        setLocationRelativeTo(g);
    }

    private void clearFields() {
        jTextArea1.setText("");
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        replacePath = null;
        jLabel8.setIcon(null);
    }

    private void loadTable() {
        try {

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ResultSet rs = MySQL.search("SELECT * FROM `product` p INNER JOIN `disease` d ON p.`disease_id` = d.`id` INNER JOIN `brand` b ON p.`brand_id` = b.`id` INNER JOIN `type` t ON p.`type_id` = t.`id` WHERE `status` = 1 ORDER BY d.`name` ASC");
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getInt("p.id"));
                v.add(rs.getString("d.name"));
                v.add(rs.getString("b.name"));
                v.add(rs.getString("t.name"));
                v.add(rs.getString("p.description"));
                dtm.addRow(v);
            }

            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exception() {
        try {
            URL url = this.getClass().getResource("/com/jayanidu/resources/notFound.jpg");
            Image img = ImageIO.read(url);
            img = img.getScaledInstance(jLabel8.getWidth(), jLabel8.getHeight(), Image.SCALE_SMOOTH);
            jLabel8.setIcon(new ImageIcon(img));

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
        jLabel6 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jMenuItem1.setText("Delete");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("PRODUCTS");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLabel6.setOpaque(true);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 530, 70));
        jLabel6.setBackground(new Color(242, 242, 242, 120));

        jButton9.setBackground(new java.awt.Color(224, 235, 237));
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
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 560, -1, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 178, 194), 3));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(52, 73, 94));
        jLabel7.setText("Description");

        jTextArea1.setColumns(1);
        jTextArea1.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(52, 73, 94));
        jTextArea1.setRows(3);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(52, 73, 94));
        jLabel2.setText("Disease");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(52, 73, 94));
        jLabel3.setText("Brand");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(52, 73, 94));
        jLabel5.setText("type");

        jButton5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(52, 73, 94));
        jButton5.setText("Add Product");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(52, 73, 94));
        jButton1.setText("Select ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(52, 73, 94));

        jTextField2.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(52, 73, 94));

        jTextField3.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(52, 73, 94));

        jButton6.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(52, 73, 94));
        jButton6.setText("Update Product");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTable1.setForeground(new java.awt.Color(52, 73, 94));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Disease", "Brand", "Type", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(20);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(20);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(250);
        }

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(52, 73, 94));
        jButton4.setText("Browse");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 130, -1));

        jButton2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(52, 73, 94));
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 120, -1));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 281, 270));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField2)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(206, 206, 206)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(15, 15, 15))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(667, 667, 667))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, -1, 450));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jayanidu/resources/27.jpg"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 178, 194), 5));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new ManageProductTypes(this, true).setVisible(true);
        jLabel8.setIcon(null);
        jTextArea1.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    String replacePath = null;

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {

            JFileChooser jf = new JFileChooser("C:\\Users\\asus\\Documents\\NetBeansProjects\\Senani");
            int showOpenDialog = jf.showOpenDialog(this);
            if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jf.getSelectedFile();
                String absolutePath = selectedFile.getAbsolutePath();
                replacePath = absolutePath.replace("\\", "/");
                File file = new File(replacePath);
                Image img = ImageIO.read(file);
                img = img.getScaledInstance(jLabel8.getWidth(), jLabel8.getHeight(), Image.SCALE_SMOOTH);
                jLabel8.setIcon(new ImageIcon(img));

            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Invalid file type, Accepts images only", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String disease = jTextField1.getText();
        String brand = jTextField2.getText();
        String type = jTextField3.getText();
        String description = jTextArea1.getText().trim();

        if (disease.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please select Disease, Brand and Type using '+' button!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (description.isBlank()) {
            JOptionPane.showMessageDialog(this, "Description field empty!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {

                ResultSet rs1 = MySQL.search("SELECT * FROM `disease` WHERE `name` = '" + disease + "'");
                ResultSet rs2 = MySQL.search("SELECT * FROM `brand` WHERE `name` = '" + brand + "'");
                ResultSet rs3 = MySQL.search("SELECT * FROM `type` WHERE `name` = '" + type + "'");
                rs1.next();
                rs2.next();
                rs3.next();

                ResultSet rs = MySQL.search("SELECT * FROM `product` WHERE `id` = '" + pid + "'");
                if (!rs.next()) {

                    if (replacePath == null) {
                        int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Do you really want to save product without image?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (showConfirmDialog == JOptionPane.YES_OPTION) {
                            MySQL.iud("INSERT INTO `product`(`description`, `disease_id`, `brand_id`, `type_id`) VALUES('" + description + "', " + rs1.getInt("id") + ", " + rs2.getInt("id") + ", " + rs3.getInt("id") + ")");
                            JOptionPane.showMessageDialog(this, "Product added successfull!", "Success", JOptionPane.INFORMATION_MESSAGE);

                            clearFields();
                            loadTable();
                        }
                    } else {
                        MySQL.iud("INSERT INTO `product`(`description`, `path`, `disease_id`, `brand_id`, `type_id`) VALUES('" + description + "', '" + replacePath + "', " + rs1.getInt("id") + ", " + rs2.getInt("id") + ", " + rs3.getInt("id") + ")");
                        JOptionPane.showMessageDialog(this, "Product added successfull!", "Success", JOptionPane.INFORMATION_MESSAGE);

                        clearFields();
                        loadTable();
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Product already exists!", "Warning", JOptionPane.ERROR_MESSAGE);
                    clearFields();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private int pid;

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            int row = jTable1.getSelectedRow();

            if (row != -1) {
                try {

                    pid = (int) jTable1.getValueAt(row, 0);
                    ResultSet rs = MySQL.search("SELECT `path` FROM `product` WHERE `id` = '" + pid + "'");
                    if (rs.next()) {
                        replacePath = rs.getString("path");
                        if (replacePath != null) {
                            File file = new File(replacePath);
                            Image img = ImageIO.read(file);
                            img = img.getScaledInstance(jLabel8.getWidth(), jLabel8.getHeight(), Image.SCALE_SMOOTH);
                            jLabel8.setIcon(new ImageIcon(img));
                        } else {
                            jLabel8.setIcon(null);
                        }
                    }

                    jTextField1.setText(jTable1.getValueAt(row, 1).toString());
                    jTextField2.setText(jTable1.getValueAt(row, 2).toString());
                    jTextField3.setText(jTable1.getValueAt(row, 3).toString());
                    jTextArea1.setText(jTable1.getValueAt(row, 4).toString());

                } catch (IIOException e) {
                    exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        if (evt.getClickCount() == 2 && g != null) {
            int row = jTable1.getSelectedRow();

            if (row != -1) {
                g.jTextField10.setText(jTextField1.getText());
                g.jTextField11.setText(jTextField2.getText());
                g.jTextField13.setText(jTextField3.getText());
                g.jTextArea2.setText(jTextArea1.getText().trim());
                g.pid = pid;
                this.dispose();
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String disease = jTextField1.getText();
        String brand = jTextField2.getText();
        String type = jTextField3.getText();
        String description = jTextArea1.getText().trim();

        if (disease.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please select Disease, Brand and Type using '+' button!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (description.isBlank()) {
            JOptionPane.showMessageDialog(this, "Description field empty!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {

                ResultSet rs = MySQL.search("SELECT * FROM `product` WHERE `id` = '" + pid + "'");
                if (rs.next()) {

                    ResultSet rs1 = MySQL.search("SELECT * FROM `disease` WHERE `name` = '" + disease + "'");
                    ResultSet rs2 = MySQL.search("SELECT * FROM `brand` WHERE `name` = '" + brand + "'");
                    ResultSet rs3 = MySQL.search("SELECT * FROM `type` WHERE `name` = '" + type + "'");
                    rs1.next();
                    rs2.next();
                    rs3.next();

                    if (replacePath == null) {
                        int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Do you really want to update product without image?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (showConfirmDialog == JOptionPane.YES_OPTION) {
                            MySQL.iud("UPDATE `product` SET `description` = '" + description + "', `disease_id` = " + rs1.getInt("id") + ", `brand_id` = " + rs2.getInt("id") + ", `type_id` = " + rs3.getInt("id") + " WHERE `id` = '" + pid + "'");
                            JOptionPane.showMessageDialog(this, "Product updated successfull!", "Success", JOptionPane.INFORMATION_MESSAGE);

                            clearFields();
                            loadTable();
                        }
                    } else {
                        MySQL.iud("UPDATE `product` SET `description` = '" + description + "', `path` = '" + replacePath + "', `disease_id` = " + rs1.getInt("id") + ", `brand_id` = " + rs2.getInt("id") + ", `type_id` = " + rs3.getInt("id") + " WHERE `id` = '" + pid + "'");
                        JOptionPane.showMessageDialog(this, "Product updated successfull!", "Success", JOptionPane.INFORMATION_MESSAGE);

                        clearFields();
                        loadTable();
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Product does not exists!", "Warning", JOptionPane.ERROR_MESSAGE);
                    clearFields();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jLabel8.setIcon(null);
    }//GEN-LAST:event_jButton2ActionPerformed

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

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseDragged

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if (pid != 0) {
            int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Do you really want to remove this product?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (showConfirmDialog == JOptionPane.YES_OPTION) {
                MySQL.iud("UPDATE `product` SET `status` = 0 WHERE `id` = '" + pid + "'");
                JOptionPane.showMessageDialog(this, "Product removed successfull!", "Success", JOptionPane.INFORMATION_MESSAGE);

                clearFields();
                loadTable();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product first!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Product dialog = new Product(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextField jTextField1;
    public javax.swing.JTextField jTextField2;
    public javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
