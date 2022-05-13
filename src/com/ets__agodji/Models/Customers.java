package com.ets__agodji.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="clients")
public class Customers {
    @DatabaseField(id = true)
    private String name;

    @DatabaseField(canBeNull = false)
    private String adress;

    @DatabaseField(canBeNull = false)
    private String number;



    // constructors
    public Customers( String name, String adress, String number) {
        setName(name);
        setAdress(adress);
        setNumber(number);
    }

    public  Customers(){

    }


    // getters
    public String getName(){return name;}
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

}
