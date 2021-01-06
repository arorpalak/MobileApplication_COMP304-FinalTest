package com.palak.arora;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "StockInfo")
public class StockInfo {
    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "CompanyName")
    private String companyName;

    @ColumnInfo(name = "OpeningPrice")
    private double openingPrice;

    @ColumnInfo(name = "ClosingPrice")
    private double closingPrice;


    public StockInfo(String companyName, double openingPrice, double closingPrice) {
        this.closingPrice = closingPrice;
        this.companyName = companyName;
        this.openingPrice = openingPrice;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(double openingPrice) {
        this.openingPrice = openingPrice;
    }

    public double getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(double closingPrice) {
        this.closingPrice = closingPrice;
    }
}
