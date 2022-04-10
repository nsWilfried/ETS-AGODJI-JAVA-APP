package com.ets__agodji.Controllers;

import com.ets__agodji.Models.Customers;
import com.ets__agodji.Models.Products;
import com.ets__agodji.Models.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.ets__agodji.Dao.AllDao.*;

public class CustomersController implements Initializable {

    @FXML
    public Button randomTabButton;
    @FXML
    private Button homeTabButton;

    @FXML
    private TextField customerAdressField;

    @FXML
    private Button customerDeleteButton;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField customerNumberField;

    @FXML
    private Button customerSubmitBtn;

    @FXML
    private
    TableView<Customers> customersTabView;

    @FXML
    public  TableColumn<Customers, String> colAdress;

    @FXML
    private TableColumn<Customers, Integer> colId;

    @FXML
    private TableColumn<Customers, String> colName;

    @FXML
    private TableColumn<Customers, String> colNumber;

    @FXML
    private  TabPane TabView;

    @FXML
    private Tab DemoTab;


    static Customers customer;

    @FXML
    // ajouter un client
    private void addCustomer() throws SQLException {
        String name = customerNameField.getText();
        String adress = customerAdressField.getText();
        String number = customerNumberField.getText();
        addCustomerToDb(name, adress, number);
        addDataToTabView();


    }

    @FXML
    // ajouter un client dans la tableView
    private TableView<Customers> addDataToTabView() throws SQLException {
        ObservableList clients = FXCollections.observableArrayList();

        if(customersTabView != null){
            for (Customers customer: CustomerDao()){
                clients.add(new Customers(customer.getId(),customer.getName(), customer.getAdress(), customer.getNumber()));
            }
            System.out.println("les clients sont:"+clients);
        }


        customersTabView.setItems(clients);

        return  customersTabView;
    }

    // ajouter le client dans la bd
    private static void addCustomerToDb(String name, String adress, String number) throws SQLException {
        Customers customer = new Customers();
        customer.setName(name);
        customer.setAdress(adress);
        customer.setNumber(number);
        CustomerDao().create(customer);
    }


    @FXML
    private  void random(ActionEvent actionEventadmin) throws IOException {
        if(homeTabButton.isFocused()  == true){
            Tab homeTab = new Tab();
            TabView.getTabs().add(homeTab);
            homeTab.setContent(FXMLLoader.load(getClass().getResource("../Resources/templates/home.fxml")));
        }
        else {
            System.out.println("le bouton home tab n'est pas pressé");
        }
    }


    @FXML
    // retourner les informations du client selectionné
    private void  getSelectedItem(MouseEvent mouseEvent) {
        customer = customersTabView.getSelectionModel().getSelectedItem();

        if(customer !=null){
            customerNameField.setText(customer.getName());
            customerAdressField.setText(customer.getAdress());
            customerNumberField.setText(customer.getNumber());
        }
    }

    @FXML
    // mettre à jour les données client dans la table view
    private void updateTabView(ActionEvent actionEvent) throws SQLException {

        // récupérer les nouvelles valeurs des champs
        String new_name = customerNameField.getText();
        String new_adress = customerAdressField.getText();
        String new_number = customerNumberField.getText();

        //récupérer l'identifiant de la donnée sélectionnée ( en occurence ici l'id du client sélectionnée )
        Integer id = customer.getId();
        Customers customer_selected = CustomerDao().queryForId(String.valueOf(id));

        customer_selected.setName(new_name);
        customer_selected.setAdress(new_adress);
        customer_selected.setNumber(new_number);

        CustomerDao().update(customer_selected);
        addDataToTabView();


    }

    // effacer les champs de la table view
    private  void clearTableView(){
        customerNameField.clear();
        customerNumberField.clear();
        customerAdressField.clear();
    }

    @FXML
    // supprimer le client de la bd
    private  void deleteCustomerFromDb(ActionEvent actionEvent) throws SQLException {
        // récupérer les donnnées du client
        Integer customerId = customer.getId();
        Customers customer_selected = CustomerDao().queryForId(String.valueOf(customerId));

        // supprimer tous les champs et les informations de l'utilistateur
        CustomerDao().delete(customer_selected);
        clearTableView();

        // rafraîchir la table
        addDataToTabView();

    }

    public void test(){
        Products new_product = new Products();
        Users admin = null;
        try {
            admin = UserDao().queryForId("1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new_product.setName("Ciment d'or");
        new_product.setCategory("Ciment");
        new_product.setStock(200);
        new_product.setAlert_stock(150);
        new_product.setReference("CO4");
        new_product.setBuy_price(3000F);
        new_product.setSell_price(3500F);
        new_product.setCreate_by(admin);



        try {
            ProductDao().create(new_product);
            Products random = ProductDao().queryForId(String.valueOf(new_product.getId()));
            System.out.println(random);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colAdress.setCellValueFactory(new PropertyValueFactory<>("adress"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        try {
            addDataToTabView();
        } catch (SQLException e) {
           System.out.print("Erreur lors de l'ajout des donnéees dans la table view");
        }

    }

}
