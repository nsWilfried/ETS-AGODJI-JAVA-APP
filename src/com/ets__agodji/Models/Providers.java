package com.ets__agodji.Models;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "fournisseurs")
public class Providers {
    @DatabaseField(id = true)
    private String name;

    @DatabaseField(canBeNull = true)
    private String description;

    @DatabaseField(canBeNull = false)
    private String  adress;

    @DatabaseField(canBeNull = true)
    private String number;

    public Providers() {

    }

    public Providers(String name, String description, String adress, String number){
        setName(name);
        setDescription(description);
        setAdress(adress);
        setNumber(number);

    }

    //getters
    public String getName() {return name;}
    public String getDescription(){return description;}
    public String getAdress(){return adress;}
    public String getNumber(){return number; }

    //setters
    public void setName(String name) {this.name = name;}
    public void setAdress(String adress){this.adress = adress;}
    public void setDescription(String description) {this.description = description;}
    public void setNumber(String number) {this.number = number;}
}
