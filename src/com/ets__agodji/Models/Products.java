package com.ets__agodji.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "produits")
public class Products {
    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false, unique = true)
    private String name;

    @DatabaseField(canBeNull = false)
    private String reference;

    @DatabaseField(canBeNull = false)
    private Integer stock;

    @DatabaseField(canBeNull = false, foreign = true, columnDefinition = "Integer references categories(id) on delete cascade")
    private Categories category;

    @DatabaseField(canBeNull = false)
    private Float sell_price;

    @DatabaseField(canBeNull = false)
    private Float buy_price;

    @DatabaseField(canBeNull = false)
    private Integer alert_stock;

    @DatabaseField(canBeNull = false, foreign = true, columnDefinition = "VARCHAR references users(username) on delete cascade")
    private Users create_by;

    private String create_by_username;
    private String product_category;

    // constructors
    public Products(){}

    public Products(Integer id,String reference, String name,
                    Float sell_price, Float buy_price,
                    Integer stock, Integer alert_stock, Categories category, Users create_by, String product_category ){
        setAll(id,reference, name, sell_price,  buy_price, stock,  alert_stock, category,  create_by, product_category);
    }

    public String getReference() {return reference;}
    public Integer getId() {return id;}
    public String getName() {return name;}
    public Categories getCategory() {return category;}
    public Integer getAlert_stock() {return alert_stock;}
    public Float getBuy_price() {return buy_price;}
    public Float getSell_price() {return sell_price;}
    public Integer getStock() {return stock;}
    public Users getCreate_by() {return create_by;}
    public String getCreate_by_username(){return create_by_username;}
    public String getProduct_category(){return product_category;}

    // setters

    public void setAlert_stock(Integer alert_stock) {this.alert_stock = alert_stock;}
    public void setBuy_price(Float buy_price) {this.buy_price = buy_price;}
    public void setCategory(Categories category) {this.category = category;}
    public void setCreate_by(Users create_by) {this.create_by = create_by;}
    public void setName(String name) {this.name = name;}
    public void setReference(String reference) {this.reference = reference;}
    public void setSell_price(Float sell_price) {this.sell_price = sell_price;}
    public void setStock(Integer stock) {this.stock = stock;}
    public void setId(Integer id){this.id = id ;}
    public void setCreate_by_username(String create_by_username){this.create_by_username=create_by_username;}
    public void setProduct_category(String product_category){this.product_category = product_category;}

    // set all
    public void setAll(Integer id,String reference, String name,
                       Float sell_price, Float buy_price,
                       Integer stock, Integer alert_stock, Categories category, Users create_by,  String product_category){
        setId(id);
        setReference(reference);
        setName(name);
        setSell_price(sell_price);
        setCreate_by(create_by);
        setBuy_price(buy_price);
        setStock(stock);
        setAlert_stock(alert_stock);
        setCategory(category);
        setCreate_by_username(getCreate_by().getUsername());
        setProduct_category(product_category);
    }

}

