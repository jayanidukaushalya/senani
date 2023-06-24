/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jayanidu.model;

import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author asus
 */
public class TableAlignment {
    
    public static DefaultTableCellRenderer rightAlignment() {
        DefaultTableCellRenderer right = new DefaultTableCellRenderer();
        right.setHorizontalAlignment(4);
        
        return right;
    }
    
    public static DefaultTableCellRenderer centerAlignment() {
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(0);
        
        return center;
    }
    
}
