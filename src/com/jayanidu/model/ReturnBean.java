/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jayanidu.model;

/**
 *
 * @author asus
 */
public class ReturnBean {
    
    public ReturnBean(double bprice, int rqty, double total, String itemBrand, String description, String exDate) {
        this.bprice = bprice;
        this.rqty = rqty;
        this.total = total;
        this.itemBrandd = itemBrand;
        this.descriptionn = description;
        this.exDate = exDate;
    }
    
    private double bprice;
    private int rqty;
    private double total;
    private String itemBrandd;
    private String descriptionn;
    private String exDate;

    public double getBprice() {
        return bprice;
    }

    public void setBprice(double bprice) {
        this.bprice = bprice;
    }

    public int getRqty() {
        return rqty;
    }

    public void setRqty(int rqty) {
        this.rqty = rqty;
    }

    public double getTotal() {
        return total;
    }

    public String getItemBrandd() {
        return itemBrandd;
    }

    public void setItemBrandd(String itemBrandd) {
        this.itemBrandd = itemBrandd;
    }

    public String getDescriptionn() {
        return descriptionn;
    }

    public void setDescriptionn(String descriptionn) {
        this.descriptionn = descriptionn;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getExDate() {
        return exDate;
    }

    public void setExDate(String exDate) {
        this.exDate = exDate;
    }       

}