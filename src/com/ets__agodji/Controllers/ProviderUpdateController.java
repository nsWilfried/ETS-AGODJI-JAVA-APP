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

import static com.ets__agodji.Controllers.ProvidersController.provider;
import static com.ets__agodji.Dao.AllDao.ProviderDao;

public class ProviderUpdateController implements Initializable {
    @FXML
    private LabeledTextField adressField;

    @FXML
    private LabeledTextField descField;

    @FXML
    private LabeledTextField nameField;

    @FXML
    private LabeledTextField numberField;

    @FXML
    private Button updateProviderButton;

    @FXML
    private void updateProvider(ActionEvent event) throws SQLException {
        Providers selected_provider = ProviderDao().queryForId(String.valueOf(provider.getId()));
        selected_provider.setName(nameField.getText());
        selected_provider.setDescription(descField.getText());
        selected_provider.setAdress(adressField.getText());
        selected_provider.setNumber(numberField.getText());


        ProviderDao().update(selected_provider);
    }

    public void setProviderFieldData(
    ) throws SQLException {

  
        nameField.setText(provider.getName());
        descField.setText(provider.getDescription());
        adressField.setText(provider.getAdress());
        numberField.setText(provider.getNumber());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setProviderFieldData();
        } catch (SQLException e) {
            System.out.println("problème de chargement");
        }
    }
}
