package com.ets__agodji.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ventes_produits")
public class SalesProducts {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false,foreign = true, columnDefinition = "Integer references ventes(id) on delete cascade" )
    private Sales sale_id;

    @DatabaseField(canBeNull = false, foreign = true, columnDefinition = "Integer references produits(id) on delete cascade")
    private Products product_id;

    @DatabaseField(canBeNull = false)
    private Integer quantity;

    private String product_name;
    private Float sell_price;
    private Float buy_price;
    private Integer stock;

    public  SalesProducts(){}

    public SalesProducts(String name, Integer quantity, Float sell_price, Float buy_price, Integer stock) {
        setProduct_name(name);
        setQuantity(quantity);
        setSell_price(sell_price);
        setBuy_price(buy_price);
        setStock(stock);
    }


    // setters
    public void setBuy_price(Float buy_price) {this.buy_price = buy_price;}
    public void setSell_price(Float sell_price) {this.sell_price = sell_price;}
    public void setProduct_name(String product_name) {this.product_name = product_name;}
    public void setStock(Integer stock) {this.stock = stock;}
   public  void setProduct_id(Products product_id){this.product_id = product_id;}
    public void setSale_id(Sales sale_id){this.sale_id=sale_id;}
    public void setId(Integer id){this.id = id;}
    public void setQuantity(Integer quantity){this.quantity=quantity;}

    // getters
    public Sales getSale_id(){return sale_id;}
    public Products getProduct_id(){return product_id;}
    public Integer getQuantity(){return quantity;}

    public Integer getId(){return id;}
    public Float getBuy_price() {return buy_price;}
    public Float getSell_price() {return sell_price;}
    public Integer getStock() {return stock;}
    public String getProduct_name() {return product_name;}

}
