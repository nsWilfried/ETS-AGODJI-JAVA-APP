package com.ets__agodji.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ventes_produits")
public class SalesProducts {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false,foreign = true, columnDefinition = "Integer references ventes(id) on delete cascade" )
    private Sales sale_id;

    @DatabaseField(canBeNull = false)
    private Integer quantity;

    @DatabaseField(canBeNull= false)
    private String productName;

    @DatabaseField(canBeNull = false)
    private Float sellPrice;

    @DatabaseField(canBeNull = false)
    private Float buyPrice;

    @DatabaseField(canBeNull = false)
    private Integer stock;

    public  SalesProducts(){}

    public SalesProducts(Sales sale_id,String product_name,Float sellPrice, Float buyPrice, Integer quantity, Integer stock){
        setAll(sale_id, product_name, sellPrice, buyPrice, quantity, stock);
    }


    // setters

    public void setBuyPrice(Float buyPrice) {this.buyPrice = buyPrice;}
    public void setSellPrice(Float sellPrice) {this.sellPrice = sellPrice;}
    public void setProductName(String productName) {this.productName = productName;}
    public void setStock(Integer stock) {this.stock = stock;}
    public void setSale_id(Sales sale_id){this.sale_id=sale_id;}
    public void setQuantity(Integer quantity){this.quantity=quantity;}
    public void setId(Integer id){this.id = id;}

    // getters
    public Sales getSale_id(){return sale_id;}
    public Integer getQuantity(){return quantity;}
    public Integer getId(){return id;}
    public Float getBuyPrice() {return buyPrice;}
    public Float getSellPrice() {return sellPrice;}
    public Integer getStock() {return stock;}
    public String getProductName() {return productName;}

    private void setAll(Sales sale_id,String product_name,Float sellPrice, Float buyPrice, Integer quantity, Integer stock){
        setSale_id(sale_id);
        setQuantity(quantity);
        setBuyPrice(buyPrice);
        setSellPrice(sellPrice);
        setStock(stock);
        setProductName(product_name);
    }
}
