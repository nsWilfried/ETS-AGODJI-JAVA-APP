package com.ets__agodji.Controllers;

import com.ets__agodji.Models.Categories;
import com.ets__agodji.Models.Products;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import jidefx.scene.control.editor.ChoiceBoxEditor;
import jidefx.scene.control.field.IntegerField;
import jidefx.scene.control.field.LabeledTextField;
import jidefx.scene.control.field.NumberField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.ets__agodji.Controllers.ProductController.product;
import static com.ets__agodji.Dao.AllDao.CategoryDao;
import static com.ets__agodji.Dao.AllDao.ProductDao;

public class ProductUpdateController implements Initializable {


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

    /**
     * Permet de récupérer les champs d'information du produit
     * ... et de le mettre à jour dans la bd
     * @param actionEvent
     * @throws SQLException
     */
    @FXML
    private void updateProduct(ActionEvent actionEvent) throws SQLException {
        Products selected_product = ProductDao().queryForId(String.valueOf(product.getId()));
        selected_product.setReference(refField.getText());
        selected_product.setName(nameField.getText());
        selected_product.setSell_price(Float.valueOf(sellPriceField.getText()));
        selected_product.setBuy_price(Float.valueOf(buyPriceField.getText()));
        selected_product.setStock(Integer.valueOf(stockField.getText()));
        selected_product.setAlert_stock(Integer.valueOf(alertStockField.getText()));
        selected_product.setCategory(CategoryDao().queryForId(categoryField.getValue()));
        selected_product.setProduct_category(categoryField.getValue());

        ProductDao().update(selected_product);
    }

    /**
     * Permet de remplir les champs avec les informations du produit
     * @throws SQLException
     */
    public void setProductData(
    ) throws SQLException {


        refField.setText(product.getReference());
        nameField.setText(product.getName());
        sellPriceField.setText(String.valueOf(product.getSell_price()));
        buyPriceField.setText(String.valueOf(product.getBuy_price()));
        stockField.setText(String.valueOf(product.getStock()));
        alertStockField.setText(String.valueOf(product.getAlert_stock()));
        categoryField.setValue(product.getProduct_category());

        if(CategoryDao() != null){
            for (Categories category: CategoryDao()){
                categoryField.getItems().add(category.getName());
            }
        }



    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setProductData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
