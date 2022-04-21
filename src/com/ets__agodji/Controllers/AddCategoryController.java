package com.ets__agodji.Controllers;

import com.ets__agodji.Models.Categories;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import jidefx.scene.control.field.LabeledTextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.ets__agodji.Dao.AllDao.CategoryDao;

public class AddCategoryController implements Initializable {
    @FXML
    private Button addCategoryButton;

    @FXML
    private LabeledTextField categoryDescField;

    @FXML
    private LabeledTextField categoryNameField;


    /**
     *
     * @param actionEvent
     * @throws SQLException
     * Raccourci pour : Ajouter une catégorie à la base de données
     */
    @FXML
    public void addCategory(ActionEvent actionEvent) throws SQLException {
        if(!categoryNameField.getText().isEmpty()){
            Categories new_category = new Categories(categoryNameField.getText(), categoryDescField.getText());
            CategoryDao().create(new_category);
            clearAllInput();
        }else {
            System.out.println("veuillez remplir le champ name");
        }
    }


    public void clearAllInput(){
        categoryNameField.clearText();
        categoryDescField.clearText();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
