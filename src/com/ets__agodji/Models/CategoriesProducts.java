package com.ets__agodji.Models;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "categories_produits")
public class CategoriesProducts {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false,foreignColumnName ="id", foreign = true)
    private Categories category_id;

    @DatabaseField(canBeNull = false,foreign = true, foreignColumnName = "id")
    private Products product_id;


    public void CategoriesProducts(){}

    public void CategoriesProducts(Categories category_id, Products product_id){
        setCategory_id(category_id);
        setProduct_id(product_id);
    }


    // setters
    public void setCategory_id(Categories category_id){this.category_id=category_id;}
    public void setProduct_id(Products product_id){this.product_id=product_id; }
    public void setId(Integer id){this.id = id;}

    // getters
    public Categories getSale_id(){return category_id;}
    public Products getProduct_id(){return product_id;}
    public Integer getId() {return id;}
}
