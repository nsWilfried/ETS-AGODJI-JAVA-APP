package com.ets__agodji.Controllers;

import com.ets__agodji.Models.Categories;
import com.ets__agodji.Models.Products;
import com.ets__agodji.Models.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import jidefx.scene.control.editor.ChoiceBoxEditor;
import jidefx.scene.control.field.IntegerField;
import jidefx.scene.control.field.LabeledTextField;
import jidefx.scene.control.field.NumberField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.ets__agodji.Controllers.MainController.openStage;
import static com.ets__agodji.Dao.AllDao.*;

public class AddProductController implements Initializable {
    @FXML
    private IntegerField alertStockField;

    @FXML
    private NumberField buyPriceField;

    @FXML
    private ChoiceBoxEditor<String> categoryField;

    @FXML
    private LabeledTextField nameField;

    @FXML
    private LabeledTextField refField;

    @FXML
    private NumberField sellPriceField;

    @FXML
    private IntegerField stockField;

    @FXML
    private ChoiceBoxEditor<String> createByField;

    @FXML
    private Button addProductButton;


    @FXML
    private Button openCategoryButton;


    /**
     * Permet d'ajouter un produit dans la base de données
     * ... Pour celà, on récupère les informations sur la catégorie et sur l'utilisateur qui ajoute le produit
     * @param actionEvent
     * @throws SQLException
     */
    @FXML
    private void addProduct(ActionEvent actionEvent) throws SQLException {

        String create_by_username = createByField.getValue();
        String product_category = categoryField.getValue();

        // récupérer la catégorie du produit dans la base de données
        Categories category = CategoryDao().queryForId(product_category);

        // récupérer l'utilisateur qui a ajouté le produit dans la base de données
        Users user = UserDao().queryForId(create_by_username);

        // créer l'objet produit et l'ajouter dans la bd
        Products new_product = new Products();
        new_product.setReference(refField.getText());
        new_product.setCategory(category);
        new_product.setName(nameField.getText());
        new_product.setCreate_by(user);
        new_product.setAlert_stock(Integer.valueOf(alertStockField.getText()));
        new_product.setStock( Integer.valueOf(stockField.getText()));
        new_product.setSell_price(Float.valueOf(sellPriceField.getText()));
        new_product.setBuy_price(Float.valueOf(buyPriceField.getText()));

        ProductDao().create(new_product);
        clearAllInput();
    }

    @FXML
    // afficher la fenêtre pour l'ajout de la catégorie
    public void openCategoryDialog(ActionEvent actionEvent) throws IOException {
        openStage("../Resources/templates/AddCategory.fxml", "Ajout catégorie");
    }


    /**
     * Permet de récupérer les (catégories,utilisateurs) dans la bd et ensuite
     * ... ajouter leurs champs name dans leurs choice box respectifs
     */
    public void addCategoriesToChoiceBox(){
        // add categories
        try {
            if(CategoryDao() != null){
                for  (Categories category: CategoryDao()){
                    categoryField.getItems().add(category.getName());
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout des catégories");
        }
    }
    public void addCreateByToChoiceBox(){
        // add categories
        try {
            if(UserDao() != null){
                for  (Users user: UserDao()){
                    createByField.getItems().add(user.getUsername());
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout des catégories");
        }
    }


    @FXML
    public void refreshCategories(ActionEvent actionEvent) {
        categoryField.getItems().clear();
        addCategoriesToChoiceBox();
    }

    public void clearAllInput(){
        refField.clearText();
        nameField.clearText();
        stockField.clear();
        alertStockField.clear();
        sellPriceField.clear();
        buyPriceField.clear();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addCategoriesToChoiceBox();
        addCreateByToChoiceBox();
    }


}
