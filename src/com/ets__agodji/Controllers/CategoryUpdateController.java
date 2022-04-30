package com.ets__agodji.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import jidefx.scene.control.field.LabeledTextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.ets__agodji.Controllers.CategoriesController.category;
import static com.ets__agodji.Dao.AllDao.CategoryDao;

public class CategoryUpdateController implements Initializable {
    @FXML
    private LabeledTextField categoryDescField;

    @FXML
    private LabeledTextField categoryNameField;

    @FXML
    private Button updateCategoryButton;


    @FXML
    private void updateCategory(ActionEvent event) throws SQLException {
        String name = categoryNameField.getText();
        String description = categoryDescField.getText();
        category.setName(name);
        category.setDescription(description);

        CategoryDao().update(category);
        clearAllInput();
    }

    private void clearAllInput(){
        categoryNameField.clearText();
        categoryDescField.clearText();
    }

    private void setCategoryFieldData(){
        categoryNameField.setText(category.getName());
        categoryDescField.setText(category.getDescription());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCategoryFieldData();
    }
}
