package com.ets__agodji.Models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDate;
import java.util.Date;

@DatabaseTable(tableName = "ventes")
public class Sales {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false, format = "yyyy-MM-dd", dataType= DataType.DATE_STRING)
    private Date created_at;

    @DatabaseField(canBeNull = false, foreign = true, columnDefinition = "VARCHAR references clients(id) on delete cascade")
    private Customers client_id;

    @DatabaseField(canBeNull=false)
    private Float total_price;

    @DatabaseField(canBeNull = false, foreign = true, columnDefinition = "VARCHAR references users(username) on delete cascade")
    private Users created_by_id;

    @DatabaseField(canBeNull = false)
    private Float amount_paid;

    // pour ajouter le nom du client dans la table view
    private String client_name;

    private LocalDate localDate;


    public Sales(){}


    /**
     * Il s'agit d'un constructeur pour ajouter l'élément dans la base de données avec
     *      le format localDate converit en date
     * @param created_at Date de vente enregistré sous format Date
     * @param client_id id du client
     * @param total_price prix total
     * @param created_by_id nom de l'utilisateur
     * @param amount_paid montant du paiement
     */
    public Sales(Date created_at, Customers client_id,
                 Float total_price, Users created_by_id, Float amount_paid) {
        setCreatedAt(created_at);
        setClientId(client_id);
        setTotalPrice(total_price);
        setCreated_by_id(created_by_id);
        setAmount_paid(amount_paid);
    }


    /**
     * Il s'agit d'un constructeur pour récupérer l'élément de la base
     *   et le créer avec la date mis sous format LocalDate
     * @param id id de la vente
     * @param local_date date de la vente
     * @param client_id id du client
     * @param total_price prix total
     * @param created_by_id nom de l'utilisateur
     * @param amount_paid prix payé
     */
    public Sales(Integer id,
                 LocalDate local_date, Customers client_id, Float total_price,
                 Users created_by_id, Float amount_paid) {
        setLocalDate(local_date);
        setAll(id, client_id, total_price, created_by_id, amount_paid);
    }

    public void setLocalDate(LocalDate local_date){
       this.localDate=local_date;
    }
    //setters
    public void setId(Integer id){this.id=id;}
    public void setCreatedAt(Date date){ this.created_at=date;}
    public void setClientId(Customers client_id){ this.client_id = client_id;}
    public void setTotalPrice(Float total_price){ this.total_price = total_price; }
    public void setCreated_by_id(Users created_by_id){this.created_by_id = created_by_id;}
    private void setAmount_paid(Float amount_paid){this.amount_paid = amount_paid;}
    private void setClient_name(String client_name){this.client_name=client_name;}


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
    public String getClient_name(){return client_name;}

    public Integer getId(){return id;}
    public LocalDate getLocalDate(){return localDate;}

    public void setAll(
            Integer id,
            Customers client_id, Float total_price,
            Users created_by_id, Float amount_paid
    ){
        setId(id);
        setClientId(client_id);
        setTotalPrice(total_price);
        setCreated_by_id(created_by_id);
        setAmount_paid(amount_paid);
        setClient_name(getClient_id().getName());
    }

}
