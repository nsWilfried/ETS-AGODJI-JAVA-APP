package com.ets__agodji.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "categories")
public class Categories {
    @DatabaseField(id = true)
    private String name;

    @DatabaseField(canBeNull = true)
    private String description;

    public Categories(){}

    public Categories(String name, String description){
        setName(name);
        setDescription(description);
    }

    // setters
    public void setName(String name){this.name = name;}
    public void setDescription(String description){this.description = description;}

    //getters
    public String getName(){return name; }
    public String getDescription(){return description;}

}
