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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author asus
 */
public class SalesGRNReport extends javax.swing.JFrame {

    /**
     * Creates new form Report
     */
    
    Home h;
    
    public SalesGRNReport(java.awt.Frame parent, boolean modal) {
        this.h = (Home) parent;
        
        initComponents();
        
        try {
            ImageIcon i = new ImageIcon(this.getClass().getResource("/com/jayanidu/resources/logo1.png"));
            Image image = i.getImage();
            setIconImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        setLocationRelativeTo(h);
        
        jTable1.getColumnModel().getColumn(0).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(1).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(2).setCellRenderer(TableAlignment.rightAlignment());
        jTable1.getColumnModel().getColumn(3).setCellRenderer(TableAlignment.centerAlignment());
        jTable1.getColumnModel().getColumn(4).setCellRenderer(TableAlignment.rightAlignment());
        jTable1.getColumnModel().getColumn(5).setCellRenderer(TableAlignment.rightAlignment());
        jTable1.getColumnModel().getColumn(6).setCellRenderer(TableAlignment.centerAlignment());
    }
    
    private void salesReport(Date d, String sortType) {
        try {

            String mainReport = "SELECT * FROM `sales` s INNER JOIN `payment_sales` ps ON s.`id` = ps.`sales_id` INNER JOIN `pmethod` pm ON ps.`pmethod_id` = pm.`id` INNER JOIN `user` u ON ps.`user_id` = u.`id`";
            String orderPart = " ORDER BY s.`date_time` ASC";

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            if (d != null) {

                if (sortType.equals("Sort to Date")) {
                    date = new SimpleDateFormat("yyyy-MM-dd").format(d);
                } else {
                    date = new SimpleDateFormat("yyyy-MM").format(d);
                }

                String conditionPart = " WHERE s.`date_time` LIKE '%" + date + "%'";

                ResultSet rs = MySQL.search(mainReport + conditionPart + orderPart);
                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getString("s.date_time"));
                    v.add(rs.getString("s.id"));
                    v.add(rs.getString("s.nettotal"));
                    v.add(rs.getString("pm.name"));
                    v.add(rs.getString("ps.payment"));
                    v.add(rs.getString("ps.balance"));
                    v.add(rs.getString("u.name"));
                    dtm.addRow(v);
                }
            } else {

                ResultSet rs = MySQL.search(mainReport + orderPart);
                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getString("s.date_time"));
                    v.add(rs.getString("s.id"));
                    v.add(rs.getString("s.nettotal"));
                    v.add(rs.getString("pm.name"));
                    v.add(rs.getString("ps.payment"));
                    v.add(rs.getString("ps.balance"));
                    v.add(rs.getString("u.name"));
                    dtm.addRow(v);
                }
            }

            jTable1.setModel(dtm);
            calculateTotal();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String date = null;

    private void grnReport(Date d, String sortType) {
        try {

            String mainPart = "SELECT * FROM `grn` g INNER JOIN `payment_grn` pg ON g.`id` = pg.`grn_id` INNER JOIN `pmethod` pm ON pg.`pmethod_id` = pm.`id` INNER JOIN `user` u ON pg.`user_id` = u.`id`";
            String orderPart = " ORDER BY g.`date_time` ASC";

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            if (d != null) {

                if (sortType.equals("Sort to Date")) {
                    date = new SimpleDateFormat("yyyy-MM-dd").format(d);
                } else {
                    date = new SimpleDateFormat("yyyy-MM").format(d);
                }

                String conditionPart = " WHERE g.`date_time` LIKE '%" + date + "%'";

                ResultSet rs = MySQL.search(mainPart + conditionPart + orderPart);
                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getString("g.date_time"));
                    v.add(rs.getString("g.id"));
                    v.add(rs.getString("g.nettotal"));
                    v.add(rs.getString("pm.name"));
                    v.add(rs.getString("pg.payment"));
                    v.add(rs.getString("pg.balance"));
                    v.add(rs.getString("u.name"));
                    dtm.addRow(v);
                }
            } else {
                ResultSet rs = MySQL.search(mainPart + orderPart);
                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getString("g.date_time"));
                    v.add(rs.getString("g.id"));
                    v.add(rs.getString("g.nettotal"));
                    v.add(rs.getString("pm.name"));
                    v.add(rs.getString("pg.payment"));
                    v.add(rs.getString("pg.balance"));
                    v.add(rs.getString("u.name"));
                    dtm.addRow(v);
                }
            }

            jTable1.setModel(dtm);
            calculateTotal();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculateTotal() {

        double total = 0;

        for (int i = 0; i < jTable1.getRowCount(); i++) {

            total = total + Double.parseDouble(jTable1.getValueAt(i, 2).toString());
            jLabel5.setText(new DecimalFormat("0.00").format(total));

        }

    }

    private void calculateProfit() {

        String reportType = jComboBox1.getSelectedItem().toString();

        double totalBprice = 0;
        double totalSprice = 0;

        try {

            if (reportType.equals("Sales Report")) {

                for (int i = 0; i < jTable1.getRowCount(); i++) {

                    long id = Long.parseLong(jTable1.getValueAt(i, 1).toString());

                    ResultSet rs = MySQL.search("SELECT * FROM `sales` s INNER JOIN `sales_item` si ON s.`id` = si.`sales_id` INNER JOIN `stock` ss ON ss.`id` = si.`stock_id` INNER JOIN `grn_item` gi ON ss.`id` = gi.`stock_id` WHERE s.`id` = " + id + "");
                    while (rs.next()) {

                        int sqty = rs.getInt("si.qty");
                        totalBprice = totalBprice + rs.getDouble("gi.bprice") * sqty;
                        totalSprice = totalSprice + rs.getDouble("ss.sprice") * sqty;

                    }
                }
            }

            double profit = totalSprice - totalBprice;
            jLabel7.setText(new DecimalFormat("0.00").format(profit));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void clear() {
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);

        jLabel5.setText("0.00");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 178, 194), 3));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(52, 73, 94));
        jLabel1.setText("Report Type");

        jComboBox1.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(52, 73, 94));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Type", "Sales Report", "GRN Report", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jDateChooser1.setForeground(new java.awt.Color(52, 73, 94));
        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(52, 73, 94));
        jLabel3.setText("Date");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(52, 73, 94));
        jLabel4.setText("Total Value");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(52, 73, 94));
        jLabel5.setText("0.00");

        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(52, 73, 94));
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTable1.setForeground(new java.awt.Color(52, 73, 94));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "ID", "Net Total", "Payment Method", "Payment", "Balance", "User"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel2.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(52, 73, 94));
        jLabel2.setText("Sort Type");

        jComboBox2.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(52, 73, 94));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sort to Date", "Sort to Month" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(52, 73, 94));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(52, 73, 94));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(16, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)))
                .addGap(27, 27, 27))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 770, 370));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("REPORTS");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLabel9.setOpaque(true);
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 530, 70));
        jLabel9.setBackground(new Color(242, 242, 242, 120));

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

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jayanidu/resources/34.jpg"))); // NOI18N
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 178, 194), 5));
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        Date d = jDateChooser1.getDate();
        String reportType = jComboBox1.getSelectedItem().toString();
        String sortType = jComboBox2.getSelectedItem().toString();

        switch (reportType) {
            case "Select Type":
                clear();
                jLabel6.setText("");
                jLabel7.setText("");
                break;
            case "Sales Report":
                clear();
                salesReport(d, sortType);
                jLabel6.setText("Total Profit");
                jLabel7.setText("0.00");
                calculateProfit();
                break;
            case "GRN Report":
                clear();
                grnReport(d, sortType);
                jLabel6.setText("");
                jLabel7.setText("");
                break;

            default:
                break;
        }

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        // TODO add your handling code here:
        Date d = jDateChooser1.getDate();
        String reportType = jComboBox1.getSelectedItem().toString();
        String sortType = jComboBox2.getSelectedItem().toString();

        switch (reportType) {
            case "Select Type":
                clear();
                break;
            case "Sales Report":
                clear();
                salesReport(d, sortType);
                calculateProfit();
                break;
            case "GRN Report":
                clear();
                grnReport(d, sortType);
                break;

            default:
                break;
        }
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        Date d = jDateChooser1.getDate();
        String reportType = jComboBox1.getSelectedItem().toString();
        String sortType = jComboBox2.getSelectedItem().toString();

        switch (reportType) {
            case "Select Type":
                clear();
                break;
            case "Sales Report":
                clear();
                salesReport(d, sortType);
                calculateProfit();
                break;
            case "GRN Report":
                clear();
                grnReport(d, sortType);
                break;

            default:
                break;
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {

            if (jTable1.getRowCount() > 0) {

                String reportType = jComboBox1.getSelectedItem().toString();
                String dateGenerated = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                String time = new SimpleDateFormat("HH:mm:ss").format(new Date());

                InputStream stream = getClass().getResourceAsStream("/com/jayanidu/reports/Report1.jasper");

                if (reportType.equals("Sales Report")) {

                    HashMap parameteres = new HashMap();

                    parameteres.put("Parameter1", dateGenerated);
                    parameteres.put("Parameter2", time);
                    parameteres.put("Parameter3", date);
                    parameteres.put("Parameter4", jLabel5.getText());
                    parameteres.put("Parameter5", "SALES REPORT");
                    parameteres.put("Parameter6", "Total Profit");
                    parameteres.put("Parameter7", " : " + jLabel7.getText());

                    JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

                    JasperPrint jp = JasperFillManager.fillReport(stream, parameteres, dataSource);
                    JasperViewer.viewReport(jp, false);
                    JasperPrintManager.printReport(jp, true);
                    


                } else if (reportType.equals("GRN Report")) {

                    HashMap parameteres = new HashMap();

                    parameteres.put("Parameter1", dateGenerated);
                    parameteres.put("Parameter2", time);
                    parameteres.put("Parameter3", date);
                    parameteres.put("Parameter4", jLabel5.getText());
                    parameteres.put("Parameter5", "GRN REPORT");
                    parameteres.put("Parameter6", "");
                    parameteres.put("Parameter7", "");

                    JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

                    JasperPrint jp = JasperFillManager.fillReport(stream, parameteres, dataSource);
                    JasperViewer.viewReport(jp, false);
                    JasperPrintManager.printReport(jp, true);
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        if (evt.getClickCount() == 1 && row > -1) {

            long id = Long.parseLong(jTable1.getValueAt(row, 1).toString());

            String reportType = jComboBox1.getSelectedItem().toString();

            if (reportType.equals("Sales Report")) {

                new ReportItem(this, true, id, 0).setVisible(true);

            } else if (reportType.equals("GRN Report")) {

                new ReportItem(this, true, id, 1).setVisible(true);

            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(SalesGRNReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesGRNReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesGRNReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesGRNReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SalesGRNReport dialog = new SalesGRNReport(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
