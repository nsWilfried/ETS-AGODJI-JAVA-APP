package com.ets__agodji.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ventes_produits")
public class SalesProducts {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false,foreign = true, foreignColumnName = "id")
    private Sales sale_id;

    @DatabaseField(canBeNull = false,foreignColumnName = "id", foreign = true)
    private Products product_id;

    @DatabaseField(canBeNull = false)
    private Integer quantity;



    public void SalesProducts(){}

    public void SalesProducts(Sales sale_id,Products product_id, Integer quantity){
        setProduct_id(product_id);
        setSale_id(sale_id);
        setQuantity(quantity);
    }


    // setters
    public void setSale_id(Sales sale_id){this.sale_id=sale_id;}
    public void setProduct_id(Products product_id){this.product_id=product_id; }
    public void setQuantity(Integer quantity){this.quantity=quantity;}
    public void setId(Integer id){this.id = id;}

    // getters
    public Sales getSale_id(){return sale_id;}
    public Products getProduct_id(){return product_id;}
    public Integer getQuantity(){return quantity;}
    public Integer getId(){return id;}
}
