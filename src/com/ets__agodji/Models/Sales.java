package com.ets__agodji.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "ventes")
public class Sales {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false)
    private Date created_at;

    @DatabaseField(canBeNull = false, foreign = true, columnDefinition = "Integer references clients(name) on delete cascade")
    private Customers client_id;

    @DatabaseField(canBeNull=false)
    private Float total_price;

    @DatabaseField(canBeNull = false, foreign = true, columnDefinition = "VARCHAR references users(username) on delete cascade")
    private Users created_by_id;

    @DatabaseField(canBeNull = false)
    private Float amount_paid;

    // pour ajouter le nom du client dans la table view
    private String client_name;

    public Sales(){}

    public Sales(
            Date date, Customers client_id, Float total_price, Users created_by_id, Float amount_paid){
        setCreatedAt(date);
        setClientId(client_id);
        setTotalPrice(total_price);
        setCreated_by_id(created_by_id);
        setAmount_paid(amount_paid);
    }

    //setters
    public void setCreatedAt(Date date){ this.created_at=date;}
    public void setClientId(Customers client_id){ this.client_id = client_id;}
    public void setTotalPrice(Float total_price){ this.total_price = total_price; }
    public void setCreated_by_id(Users created_by_id){this.created_by_id = created_by_id;}
    private void setAmount_paid(Float amount_paid){this.amount_paid = amount_paid;}

    //getters


    public Date getCreated_at() {
        return created_at;
    }
    public Customers getClient_id() {
        return client_id;
    }
    public Users getCreated_by_id() {return created_by_id;}
    public Float getTotal_price() {
        return total_price;
    }
    public Float getAmount_paid(){return amount_paid;}
}
