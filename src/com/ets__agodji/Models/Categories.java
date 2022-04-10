package com.ets__agodji.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "categories")
public class Categories {


    @DatabaseField(generatedId = true)
    private  Integer id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = true)
    private String description;

    public Categories(){}

    public void Categories(String name, String description){
        setName(name);
        setDescription(description);
    }

    // setters
    public void setName(String name){this.name = name;}
    public void setDescription(String description){this.description = description;}

    public void setId(Integer id) {this.id = id;}

    //getters
    public String getName(){return name; }
    public String getDescription(){return description;}

    public Integer getId() {
        return id;
    }
}
