package com.ets__agodji.Controllers;

import com.ets__agodji.Models.Providers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import jidefx.scene.control.field.LabeledTextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.ets__agodji.Dao.AllDao.ProviderDao;

public class AddProviderController implements Initializable {

    @FXML
    private Button addProviderButton;

    @FXML
    private LabeledTextField adressField;

    @FXML
    private LabeledTextField descField;

    @FXML
    private LabeledTextField nameField;

    @FXML
    private LabeledTextField numberField;

    @FXML
    private void addProvider(ActionEvent event) throws SQLException {
        if(!nameField.getText().isEmpty()){
            Providers new_provider = new Providers(nameField.getText(), descField.getText(), adressField.getText(), numberField.getText());
            ProviderDao().create(new_provider);
            clearAllInput();
        }else {
            System.out.println("veuillez remplir le champ name");
        }
    }
    public void clearAllInput(){
        nameField.clearText();
        descField.clearText();
        adressField.clearText();
        numberField.clearText();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
