/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.jayanidu.gui;

import com.jayanidu.model.MySQL;
import com.jayanidu.model.TableAlignment;
import java.awt.Color;
import java.awt.Image;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author asus
 */
public class Stock extends javax.swing.JFrame {

    /**
     * Creates new form Stock
     */
    Home h;

    public Stock(java.awt.Frame parent, boolean modal) {
        this.h = (Home) parent;

        initComponents();
        
        try {
            ImageIcon i = new ImageIcon(this.getClass().getResource("/com/jayanidu/resources/logo1.png"));
            Image image = i.getImage();
            setIconImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        loadDisease();
        loadBrand();
        loadStock(jComboBox1.getSelectedItem().toString(), jComboBox2.getSelectedItem().toString(), jTextField2.getText().trim(), jComboBox4.getSelectedItem().toString());

        setLocationRelativeTo(h);

        jTable1.getColumnModel().getColumn(0).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(1).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(2).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(3).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(4).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(8).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(9).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(5).setCellRenderer(TableAlignment.rightAlignment());
        jTable1.getColumnModel().getColumn(6).setCellRenderer(TableAlignment.rightAlignment());
        jTable1.getColumnModel().getColumn(7).setCellRenderer(TableAlignment.rightAlignment());
        jTable1.getColumnModel().getColumn(10).setCellRenderer(TableAlignment.rightAlignment());
    }

    private final String query = "SELECT s.`id`, s.`qty`, s.`sprice`, s.`mfd`, s.`exd`, gi.`bprice`, p.`description`, d.`name`, b.`name`, t.`name` FROM `stock` s INNER JOIN `grn_item` gi ON s.`id` = gi.`stock_id` INNER JOIN `product` p ON s.`product_id` = p.`id` INNER JOIN `disease` d ON p.`disease_id` = d.`id` INNER JOIN `brand` b ON p.`brand_id` = b.`id` INNER JOIN `type` t ON p.`type_id` = t.`id`";

    private void loadStock(String disease, String brand, String text, String sort) {

        try {

            String orderBy = "";

            switch (sort) {
                case "Disease ASC":
                    orderBy = " ORDER BY d.`name` ASC";
                    break;
                case "Disease DESC":
                    orderBy = " ORDER BY d.`name` DESC";
                    break;
                case "Brand ASC":
                    orderBy = " ORDER BY b.`name` ASC";
                    break;
                case "Brand DESC":
                    orderBy = " ORDER BY b.`name` DESC";
                    break;
                case "Description ASC":
                    orderBy = " ORDER BY p.`description` ASC";
                    break;
                case "Description DESC":
                    orderBy = " ORDER BY p.`description` DESC";
                    break;
                case "Type ASC":
                    orderBy = " ORDER BY t.`name` ASC";
                    break;
                case "Type DESC":
                    orderBy = " ORDER BY t.`name` DESC";
                    break;
                case "EXD ASC":
                    orderBy = " ORDER BY s.`exd` ASC";
                    break;
                case "EXD DESC":
                    orderBy = " ORDER BY s.`exd` DESC";
                    break;
                case "Selling Price ASC":
                    orderBy = " ORDER BY s.`sprice` ASC";
                    break;
                case "Selling Price DESC":
                    orderBy = " ORDER BY s.`sprice` DESC";
                    break;
                case "Buying Price ASC":
                    orderBy = " ORDER BY s.`sprice` ASC";
                    break;
                case "Buying Price DESC":
                    orderBy = " ORDER BY s.`sprice` DESC";
                    break;
                case "Quantity ASC":
                    orderBy = " ORDER BY s.`qty` ASC";
                    break;
                case "Quantity DESC":
                    orderBy = " ORDER BY s.`qty` DESC";
                    break;
                default:
                    break;
            }

            if (disease.equals("Select Disease") && brand.equals("Select Brand") && text.isBlank()) {
                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);
                ResultSet rs = MySQL.search(query + orderBy);
                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getInt("s.id"));
                    v.add(rs.getString("d.name"));
                    v.add(rs.getString("b.name"));
                    v.add(rs.getString("p.description"));
                    v.add(rs.getString("t.name"));
                    v.add(rs.getDouble("gi.bprice"));

                    double sprice = rs.getDouble("s.sprice");
                    int qty = rs.getInt("s.qty");

                    v.add(sprice);
                    v.add(qty);
                    v.add(rs.getString("s.mfd"));
                    v.add(rs.getString("s.exd"));

                    double total = sprice * qty;

                    v.add(total);

                    dtm.addRow(v);
                }

                jTable1.setModel(dtm);

            } else if (disease.equals("Select Disease") && brand.equals("Select Brand") && !text.isBlank()) {

                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);
                ResultSet rs = MySQL.search(query + " WHERE p.`description` LIKE '%" + text + "%'" + orderBy);
                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getInt("s.id"));
                    v.add(rs.getString("d.name"));
                    v.add(rs.getString("b.name"));
                    v.add(rs.getString("p.description"));
                    v.add(rs.getString("t.name"));
                    v.add(rs.getDouble("gi.bprice"));

                    double sprice = rs.getDouble("s.sprice");
                    int qty = rs.getInt("s.qty");

                    v.add(sprice);
                    v.add(qty);
                    v.add(rs.getString("s.mfd"));
                    v.add(rs.getString("s.exd"));

                    double total = sprice * qty;

                    v.add(total);

                    dtm.addRow(v);
                }

                jTable1.setModel(dtm);

            } else if (disease.equals("Select Disease") && !brand.equals("Select Brand") && text.isBlank()) {

                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);
                ResultSet rs = MySQL.search(query + " WHERE p.`description` LIKE '%" + text + "%' AND b.`name` = '" + brand + "'" + orderBy);
                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getInt("s.id"));
                    v.add(rs.getString("d.name"));
                    v.add(rs.getString("b.name"));
                    v.add(rs.getString("p.description"));
                    v.add(rs.getString("t.name"));
                    v.add(rs.getDouble("gi.bprice"));

                    double sprice = rs.getDouble("s.sprice");
                    int qty = rs.getInt("s.qty");

                    v.add(sprice);
                    v.add(qty);
                    v.add(rs.getString("s.mfd"));
                    v.add(rs.getString("s.exd"));

                    double total = sprice * qty;

                    v.add(total);

                    dtm.addRow(v);
                }

                jTable1.setModel(dtm);

            } else if (disease.equals("Select Disease") && !brand.equals("Select Brand") && !text.isBlank()) {

                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);
                ResultSet rs = MySQL.search(query + " WHERE p.`description` LIKE '%" + text + "%' AND b.`name` = '" + brand + "'" + orderBy);
                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getInt("s.id"));
                    v.add(rs.getString("d.name"));
                    v.add(rs.getString("b.name"));
                    v.add(rs.getString("p.description"));
                    v.add(rs.getString("t.name"));
                    v.add(rs.getDouble("gi.bprice"));

                    double sprice = rs.getDouble("s.sprice");
                    int qty = rs.getInt("s.qty");

                    v.add(sprice);
                    v.add(qty);
                    v.add(rs.getString("s.mfd"));
                    v.add(rs.getString("s.exd"));

                    double total = sprice * qty;

                    v.add(total);

                    dtm.addRow(v);
                }

                jTable1.setModel(dtm);

            } else if (!disease.equals("Select Disease") && brand.equals("Select Brand") && text.isBlank()) {

                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);
                ResultSet rs = MySQL.search(query + " WHERE d.`name` = '" + disease + "'" + orderBy);
                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getInt("s.id"));
                    v.add(rs.getString("d.name"));
                    v.add(rs.getString("b.name"));
                    v.add(rs.getString("p.description"));
                    v.add(rs.getString("t.name"));
                    v.add(rs.getDouble("gi.bprice"));

                    double sprice = rs.getDouble("s.sprice");
                    int qty = rs.getInt("s.qty");

                    v.add(sprice);
                    v.add(qty);
                    v.add(rs.getString("s.mfd"));
                    v.add(rs.getString("s.exd"));

                    double total = sprice * qty;

                    v.add(total);

                    dtm.addRow(v);
                }

                jTable1.setModel(dtm);

            } else if (!disease.equals("Select Disease") && !brand.equals("Select Brand") && text.isBlank()) {

                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);
                ResultSet rs = MySQL.search(query + " WHERE d.`name` = '" + disease + "' AND b.`name` = '" + brand + "'" + orderBy);
                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getInt("s.id"));
                    v.add(rs.getString("d.name"));
                    v.add(rs.getString("b.name"));
                    v.add(rs.getString("p.description"));
                    v.add(rs.getString("t.name"));
                    v.add(rs.getDouble("gi.bprice"));

                    double sprice = rs.getDouble("s.sprice");
                    int qty = rs.getInt("s.qty");

                    v.add(sprice);
                    v.add(qty);
                    v.add(rs.getString("s.mfd"));
                    v.add(rs.getString("s.exd"));

                    double total = sprice * qty;

                    v.add(total);

                    dtm.addRow(v);
                }

                jTable1.setModel(dtm);

            } else if (!disease.equals("Select Disease") && !brand.equals("Select Brand") && !text.isBlank()) {

                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);
                ResultSet rs = MySQL.search(query + " WHERE p.`description` LIKE '%" + text + "%' AND b.`name` = '" + brand + "' AND d.`name` = '" + disease + "'" + orderBy);
                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getInt("s.id"));
                    v.add(rs.getString("d.name"));
                    v.add(rs.getString("b.name"));
                    v.add(rs.getString("p.description"));
                    v.add(rs.getString("t.name"));
                    v.add(rs.getDouble("gi.bprice"));

                    double sprice = rs.getDouble("s.sprice");
                    int qty = rs.getInt("s.qty");

                    v.add(sprice);
                    v.add(qty);
                    v.add(rs.getString("s.mfd"));
                    v.add(rs.getString("s.exd"));

                    double total = sprice * qty;

                    v.add(total);

                    dtm.addRow(v);
                }

                jTable1.setModel(dtm);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDisease() {
        try {
            Vector v = new Vector();
            v.add("Select Disease");
            ResultSet rs = MySQL.search("SELECT * FROM `disease` ORDER BY `name` ASC");
            while (rs.next()) {
                v.add(rs.getString("name"));
            }

            DefaultComboBoxModel cbm = new DefaultComboBoxModel(v);
            jComboBox1.setModel(cbm);

        } catch (Exception e) {
        }
    }

    private void loadBrand() {
        try {
            Vector v = new Vector();
            v.add("Select Brand");
            ResultSet rs = MySQL.search("SELECT * FROM `brand` ORDER BY `name` ASC");
            while (rs.next()) {
                v.add(rs.getString("name"));
            }

            DefaultComboBoxModel cbm = new DefaultComboBoxModel(v);
            jComboBox2.setModel(cbm);

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

        jLabel8 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

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

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("STOCK");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLabel8.setOpaque(true);
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 530, 70));
        jLabel8.setBackground(new Color(242, 242, 242, 120));

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

        jLabel4.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(52, 73, 94));
        jLabel4.setText("Disease");

        jComboBox1.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(52, 73, 94));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(52, 73, 94));
        jLabel5.setText("Brand");

        jComboBox2.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(52, 73, 94));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(52, 73, 94));
        jLabel7.setText("Name");

        jTextField2.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(52, 73, 94));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(52, 73, 94));
        jLabel15.setText("Sort Product By");

        jComboBox4.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jComboBox4.setForeground(new java.awt.Color(52, 73, 94));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disease ASC", "Disease DESC", "Brand ASC", "Brand DESC", "Description ASC", "Description DESC", "Type ASC", "Type DESC", "EXD ASC", "EXD DESC", "Selling Price ASC", "Selling Price DESC", "Buying Price ASC", "Buying Price DESC", "Quantity ASC", "Quantity DESC", " " }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTable1.setForeground(new java.awt.Color(52, 73, 94));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock ID", "Disease", "Brand", "Description", "Type", "Buying Price", "Selling Price", "Quantity", "MFD", "EXD", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(52, 73, 94));
        jLabel1.setText("Selling Price");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(52, 73, 94));
        jLabel2.setText("0.00");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(52, 73, 94));
        jLabel3.setText("New Price");

        jTextField1.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(52, 73, 94));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(52, 73, 94));
        jButton1.setText("Update Stock");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(52, 73, 94));
        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(15, 15, 15))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 1010, -1));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jayanidu/resources/32.jpg"))); // NOI18N
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 178, 194), 5));
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() == 1) {
            int row = jTable1.getSelectedRow();

            if (row != -1) {
                String bprice = jTable1.getValueAt(row, 6).toString();
                jLabel2.setText(bprice);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        String disease = jComboBox1.getSelectedItem().toString();
        String brand = jComboBox2.getSelectedItem().toString();
        String txt = jTextField2.getText().trim();
        String sort = jComboBox4.getSelectedItem().toString();

        loadStock(disease, brand, txt, sort);
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String disease = jComboBox1.getSelectedItem().toString();
        String brand = jComboBox2.getSelectedItem().toString();
        String txt = jTextField2.getText().trim();
        String sort = jComboBox4.getSelectedItem().toString();

        loadStock(disease, brand, txt, sort);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
        String disease = jComboBox1.getSelectedItem().toString();
        String brand = jComboBox2.getSelectedItem().toString();
        String txt = jTextField2.getText().trim();
        String sort = jComboBox4.getSelectedItem().toString();

        loadStock(disease, brand, txt, sort);
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
        String disease = jComboBox1.getSelectedItem().toString();
        String brand = jComboBox2.getSelectedItem().toString();
        String txt = jTextField2.getText().trim();
        String sort = jComboBox4.getSelectedItem().toString();

        loadStock(disease, brand, txt, sort);
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:

        String price = jTextField1.getText();
        String txt = price + evt.getKeyChar();

        if (!Pattern.compile("(0|0[.]|0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.]|[1-9][0-9]*[.][0-9]*").matcher(txt).matches()) {
            evt.consume();
        }

    }//GEN-LAST:event_jTextField1KeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        int row = jTable1.getSelectedRow();

        String bprice = jTable1.getValueAt(row, 5).toString();
        String newPrice = jTextField1.getText();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a stock!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("(0|[1-9][0-9]*)|([0][.]([0]*[1-9][0-9]*))|([1-9][0-9]*[.]([0]*[1-9][0-9]*))").matcher(newPrice).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid selling price", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            String stockId = jTable1.getValueAt(row, 0).toString();

            if (Double.parseDouble(newPrice) > Double.parseDouble(bprice)) {
                MySQL.iud("Update `stock` SET `sprice` = '" + newPrice + "' WHERE `id` = '" + stockId + "'");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid New price", "Warning", JOptionPane.WARNING_MESSAGE);
            }

            jLabel2.setText("0.00");
            jTextField1.setText("");

            loadStock(jComboBox1.getSelectedItem().toString(), jComboBox2.getSelectedItem().toString(), jTextField2.getText().trim(), jComboBox4.getSelectedItem().toString());

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {

            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String time = new SimpleDateFormat("HH:mm:ss").format(new Date());

            InputStream stream = getClass().getResourceAsStream("/com/jayanidu/reports/SenaniStock.jasper");

            HashMap parameteres = new HashMap();

            parameteres.put("Parameter1", date);
            parameteres.put("Parameter2", time);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

            JasperPrint jp = JasperFillManager.fillReport(stream, parameteres, dataSource);
            JasperViewer.viewReport(jp, false);
            JasperPrintManager.printReport(jp, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        h.setEnabled(true);
        h.toFront();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseDragged

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
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Stock dialog = new Stock(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
