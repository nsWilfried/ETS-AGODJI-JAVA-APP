package com.ets__agodji.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="clients")
public class Customers {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false)
    private String adress;

    @DatabaseField(canBeNull = false)
    private String number;



    // constructors
    public Customers(Integer id,String name, String adress, String number) {
        setName(name);
        setAdress(adress);
        setNumber(number);
        setId(id);
    }

    public  Customers(){

    }


    // getters
    public String getName(){return name;}
    public Integer getId(){return id;}
    public String getAdress(){
        return adress;
    }
    public String getNumber(){
        return number;
    }

    //setters
    public void setName(String name){
        this.name = name;
    }
    public void setAdress(String adress){
        this.adress = adress;
    }
    public void setNumber(String number){this.number = number;}
    public void setId(Integer id){this.id = id;}

}
