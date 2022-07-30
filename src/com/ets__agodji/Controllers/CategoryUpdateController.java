package com.ets__agodji.Controllers;

import com.ets__agodji.Models.Categories;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private void updateCategory(ActionEvent event) throws SQLException {

            Categories selected_category = CategoryDao().queryForId(String.valueOf(category.getId()));
            selected_category.setName(categoryNameField.getText());
            selected_category.setDescription(categoryDescField.getText());
            CategoryDao().update(selected_category);

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
