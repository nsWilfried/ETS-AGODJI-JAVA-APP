package com.ets__agodji.Models;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="users")
public class Users {

    @DatabaseField(id = true)
    private String username;

    @DatabaseField(canBeNull = false)
    private String password;


    public String getPassword() {
        return password;
    }
    public String getUsername(){
        return username;
    }
    public String setUsername(String username){
        return this.username = username;
    }
    public String setPassword(String password){
        return this.password = password;
    }
}
