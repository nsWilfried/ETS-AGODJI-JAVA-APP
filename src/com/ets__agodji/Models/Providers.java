package com.ets__agodji.Models;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "fournisseurs")
public class Providers {

    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private String name;

    @DatabaseField(canBeNull = true)
    private String description;

    @DatabaseField(canBeNull = false)
    private String  adress;

    @DatabaseField(canBeNull = true)
    private String number;

    public Providers() {

    }

    public Providers(Integer id,String name, String description, String adress, String number){
        setId(id);
        setName(name);
        setDescription(description);
        setAdress(adress);
        setNumber(number);

    }

    public Providers(String text, String text1, String text2, String text3) {
        setName(name);
        setDescription(description);
        setAdress(adress);
        setNumber(number);
    }

    //getters
    public Integer getId(){return id;}
    public String getName() {return name;}
    public String getDescription(){return description;}
    public String getAdress(){return adress;}
    public String getNumber(){return number; }

    //setters
    public void setId(Integer id){this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setAdress(String adress){this.adress = adress;}
    public void setDescription(String description) {this.description = description;}
    public void setNumber(String number) {this.number = number;}
}
