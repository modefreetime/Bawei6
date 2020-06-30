package com.example.finalce.entity;

public class FinalceEntity {

    private int id;
    private String productname;
    private String productdesc;
    private int producttype;
    private double yearrate;
    private double totalamount;
    private double saleamount;
    private String labels;
    private int lockdays;
    private double minbugamount;
    private int isnew;
    private double startlevel;

    public FinalceEntity() {
    }

    public FinalceEntity(String productname) {
        this.productname = productname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }

    public int getProducttype() {
        return producttype;
    }

    public void setProducttype(int producttype) {
        this.producttype = producttype;
    }

    public double getYearrate() {
        return yearrate;
    }

    public void setYearrate(double yearrate) {
        this.yearrate = yearrate;
    }

    public double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(double totalamount) {
        this.totalamount = totalamount;
    }

    public double getSaleamount() {
        return saleamount;
    }

    public void setSaleamount(double saleamount) {
        this.saleamount = saleamount;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public int getLockdays() {
        return lockdays;
    }

    public void setLockdays(int lockdays) {
        this.lockdays = lockdays;
    }

    public double getMinbugamount() {
        return minbugamount;
    }

    public void setMinbugamount(double minbugamount) {
        this.minbugamount = minbugamount;
    }

    public int getIsnew() {
        return isnew;
    }

    public void setIsnew(int isnew) {
        this.isnew = isnew;
    }

    public double getStartlevel() {
        return startlevel;
    }

    public void setStartlevel(double startlevel) {
        this.startlevel = startlevel;
    }
}
