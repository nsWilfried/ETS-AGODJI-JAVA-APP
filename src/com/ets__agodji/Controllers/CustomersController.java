package com.ets__agodji.Controllers;

import com.ets__agodji.Models.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.ets__agodji.Controllers.MainController.openStage;
import static com.ets__agodji.Dao.AllDao.CustomerDao;

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
    private AnchorPane anchorPane;
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

    @FXML
    private Button openProductsButton;

    @FXML
    private Button openCategoriesButton;

    @FXML
    private Button openProvidersButton;

    @FXML
    private Button openSalesButton;



    static Customers customer;

    @FXML
    private void addCustomer() throws SQLException {
        String name = customerNameField.getText();
        String adress = customerAdressField.getText();
        String number = customerNumberField.getText();
        addCustomerToDb(name, adress, number);
        addDataToTabView();

    }


    /**
     * Permet de récupérer les produits de la bd
     * ... et finalement de les ajouter à table view
     * @return {productsTabView}
     * @throws SQLException
     */
    @FXML
    private TableView<Customers> addDataToTabView() throws SQLException {
        ObservableList clients = FXCollections.observableArrayList();

        if(customersTabView != null){
            for (Customers customer: CustomerDao()){
                clients.add(new Customers(customer.getName(), customer.getAdress(), customer.getNumber()));
            }
        }


        customersTabView.setItems(clients);

        return  customersTabView;
    }

    /**
     * Permet d'ajouter uniquement le client dans la bd
     * @param name
     * @param adress
     * @param number
     * @throws SQLException
     */
    private static void addCustomerToDb(String name, String adress, String number) throws SQLException {
        Customers customer = new Customers();
        customer.setName(name);
        customer.setAdress(adress);
        customer.setNumber(number);
        CustomerDao().create(customer);
    }


    /**
     * Permet de récupérer les informatins de l'utlisateur selectionné
     * @param mouseEvent
     */
    @FXML
    private void  getSelectedItem(MouseEvent mouseEvent) {
        customer = customersTabView.getSelectionModel().getSelectedItem();

        if(customer !=null){
            customerNameField.setText(customer.getName());
            customerAdressField.setText(customer.getAdress());
            customerNumberField.setText(customer.getNumber());
        }
    }

    /**
     * Mettre à jour le produit selectionné
     * @param actionEvent
     * @throws SQLException
     */
    @FXML
    private void updateTabView(ActionEvent actionEvent) throws SQLException {

        // récupérer les nouvelles valeurs des champs
        String new_name = customerNameField.getText();
        String new_adress = customerAdressField.getText();
        String new_number = customerNumberField.getText();

        //récupérer l'identifiant de la donnée sélectionnée ( en occurence ici l'id du client sélectionnée )
        String customer_name= customer.getName();
        Customers customer_selected = CustomerDao().queryForId(customer_name);

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


    /**
     * Permet de supprimer le client sélectionné de la db
     * ... et ensuite de rafraîchir la table view
     * @param actionEvent
     * @throws SQLException
     */
    @FXML
    private  void deleteCustomerFromDb(ActionEvent actionEvent) throws SQLException {
        // récupérer les données du client
        String customer_name = customer.getName();
        Customers customer_selected = CustomerDao().queryForId(customer_name);

        // supprimer tous les champs et les informations de l'utilistateur
        CustomerDao().delete(customer_selected);
        clearTableView();

        // rafraîchir la table
        addDataToTabView();

    }


    /**
     * @param url représente l'url du fichier xml
     */
    private void openPane(String url) throws IOException {

        Parent root =  FXMLLoader.load(getClass().getResource(url));
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(root);
    }
   @FXML
    private void openProductsView(ActionEvent actionEvent) throws IOException {
       openPane("../Resources/templates/Products.fxml");
    }

    @FXML
    private void openSalesView(ActionEvent actionEvent) throws IOException {
        openPane("../Resources/templates/Sales.fxml");
    }

    @FXML
    private void openCategoriesView(ActionEvent actionEvent) throws IOException {
        openStage("../Resources/templates/Categories.fxml", "Gestion des catégries");
    }

    @FXML
    private void openProvidersView(ActionEvent actionEvent) throws IOException {
        openStage("../Resources/templates/Providers.fxml", "Gestion des fournisseurs");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colAdress.setCellValueFactory(new PropertyValueFactory<>("adress"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        try {
            addDataToTabView();
        } catch (SQLException e) {
           System.out.print("Erreur lors de l'ajout des donnéees dans la table view");
        }

    }

}
