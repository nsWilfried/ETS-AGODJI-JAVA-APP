package com.ets__agodji.Controllers;

import com.ets__agodji.Models.Products;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import jidefx.scene.control.field.LabeledTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.ets__agodji.Controllers.MainController.openConfirmationAlert;
import static com.ets__agodji.Controllers.MainController.openStage;
import static com.ets__agodji.Dao.AllDao.ProductDao;

public class ProductsController implements Initializable {


    @FXML
    private Button addProductButton;

    @FXML
    private Button deleteProductButton;

    @FXML
    private TableView<Products> productsTabView;

    @FXML
    private Button updateProductButton;


    @FXML
    private TableColumn<Products, Integer> colAlertStock;

    @FXML
    private TableColumn<Products, Integer> colBuyPrice;

    @FXML
    private TableColumn<Products, String> colCreatedBy;

    @FXML
    private TableColumn<Products, Integer> colId;

    @FXML
    private TableColumn<Products, String> colName;

    @FXML
    private TableColumn<Products, String> colRef;

    @FXML
    private TableColumn<Products, Integer> colSellPrice;

    @FXML
    private TableColumn<Products, Integer> colStock;

    @FXML
    private TableColumn<Products, String> colCategory;

    @FXML
    private LabeledTextField searchField;

    public static Products product;

    /**
     * récupérer tous les produits
     * ... et les ajouter dans la table view
     * @return {TableView}
     * @throws SQLException
     */
     private TableView<?> getAllProducts() throws SQLException {

        ObservableList products = FXCollections.observableArrayList();

        for (Products product: ProductDao()){

            // créer les objets produits et les ajouter dans la products list
           createNewProducts(products, product);
        }

        productsTabView.setItems(products);
        return productsTabView;

    }

    /**
     * Permet de créer les objets produits
     * .. et de les ajouter dans une Observable list qui sera ajouté au table view
     * @param products il s'agit de la liste qui sera ajouté dans la table view
     * @param product permet de récupérer les informations du produit à ajouter dans la liste
     *
     */
    private void createNewProducts(ObservableList products,Products product){
        products.add( new Products(
                product.getId(),product.getReference(), product.getName(), product.getSell_price(),
                product.getBuy_price(), product.getStock(), product.getAlert_stock(),
                product.getCategory(),product.getCreate_by(), product.getCreate_by_username(), product.getCategory().getName()
        ));
    }

    @FXML
    private void addProduct(ActionEvent actionEvent) throws IOException{
        openStage("../Resources/templates/AddProduct.fxml", "Ajout produit");
    }


    @FXML
    private void getOneProduct(MouseEvent mouseEvent){
         product = productsTabView.getSelectionModel().getSelectedItem();

         if(product != null){
             updateProductButton.setDisable(false);
             deleteProductButton.setDisable(false);
         }

    }

    @FXML
    private void updateProduct(ActionEvent actionEvent) throws IOException {
         openStage("../Resources/templates/ProductUpdate.fxml", "Mis à jour produit");
    }

    @FXML
    private TableView<?> DeleteProduct(ActionEvent actionEvent) throws SQLException {

        Optional<ButtonType> alert = openConfirmationAlert("Voulez vous vraiment supprimer ce produit?").showAndWait();

        if (alert.isPresent() && alert.get() == ButtonType.OK){

            Products selected_product = ProductDao().queryForId(String.valueOf(product.getId()));
            ProductDao().delete(selected_product);

            getAllProducts();
        }
         return productsTabView;

    }

    @FXML
    private void refreshProducts(ActionEvent actionEvent) throws SQLException {
        getAllProducts();
    }

    @FXML
    private void searchProduct(KeyEvent keyEvent) throws SQLException {
         String searchText = searchField.getText();
         if (searchText.isEmpty()){
            getAllProducts();
        }else {
           searchLogic("name", searchText);
        }

    }

    /**
     * Contient la logique de la barre de recherche
     * @param columnName la colonne sur laquelle effectuer la recherche
     * @param searchText l'information à rechercher
     * @throws SQLException
     */
    private void searchLogic(String columnName, String searchText) throws SQLException {
        QueryBuilder<Products, String> queryBuilder = ProductDao().queryBuilder();
        Where<Products, String> where = queryBuilder.where();
        where.like(columnName, searchText.charAt(0)+"%");

        PreparedQuery<Products> preparedQuery = queryBuilder.prepare();
        List<Products> productsList = ProductDao().query(preparedQuery);

        if(productsList.size()!=0){

            ObservableList searchProducts = FXCollections.observableArrayList();
            for(Products product: productsList){
                // supprimer la table view
                productsTabView.getItems().clear();

                // créer les objets produits avec les informations du produit de la searchProducts liste
                createNewProducts(searchProducts, product);

            }
            // ajouter les produits à la table view
            productsTabView.setItems(searchProducts);

        }else {
            productsTabView.getItems().clear();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateProductButton.setDisable(true);
        deleteProductButton.setDisable(true);
        colAlertStock.setCellValueFactory(new PropertyValueFactory<>("alert_stock"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("product_category"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colBuyPrice.setCellValueFactory(new PropertyValueFactory<>("buy_price"));
        colSellPrice.setCellValueFactory(new PropertyValueFactory<>("sell_price"));
        colRef.setCellValueFactory(new PropertyValueFactory<>("reference"));
        colCreatedBy.setCellValueFactory(new PropertyValueFactory<>("create_by_username"));


        try {
            getAllProducts();
        } catch (SQLException e) {
            System.out.println("le programme n'arrive pas à charger les produits");
        }
    }


}
