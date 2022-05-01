package com.ets__agodji.Controllers;

import com.ets__agodji.Models.Providers;
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
import static com.ets__agodji.Dao.AllDao.ProviderDao;

public class ProvidersController implements Initializable {


    @FXML
    private TableColumn<Providers, String> colAdress;

    @FXML
    private TableColumn<Providers, String> colDesc;

    @FXML
    private TableColumn<Providers, String> colName;

    @FXML
    private TableColumn<Providers, String> colNumber;

    @FXML
    private Button addProviderButton;

    @FXML
    private Button deleteProviderButton;

    @FXML
    private TableView<Providers> providersTabView;

    @FXML
    private Button refreshProviderButton;

    @FXML
    private LabeledTextField searchField;

    @FXML
    private Button udpateProviderButton;

    public static Providers provider;
    private TableView<?> getAllProviders() throws SQLException {

        ObservableList providers = FXCollections.observableArrayList();

        for (Providers provider: ProviderDao()){
            providers.add(new Providers(provider.getName(), provider.getDescription(), provider.getAdress(), provider.getNumber()));
        }

        providersTabView.setItems(providers);
        return providersTabView;

    }
    @FXML
    private void addProvider(ActionEvent event) throws IOException {
        openStage("../Resources/templates/AddProvider.fxml", "Ajout du fournisseur");
    }

    @FXML
    private TableView<Providers> deleteProvider(ActionEvent event) throws SQLException {

        Optional<ButtonType> alert = openConfirmationAlert("Voulez vous vraiment supprimer ce fournisseur?").showAndWait();

        if (alert.isPresent() && alert.get() == ButtonType.OK){

            Providers selected_provider = ProviderDao().queryForId(String.valueOf(provider.getName()));
            ProviderDao().delete(selected_provider);

            getAllProviders();
        }
        return providersTabView;
    }

    @FXML
    private void getOneProvider(MouseEvent event) {
        provider = providersTabView.getSelectionModel().getSelectedItem();

        if(provider != null){
            udpateProviderButton.setDisable(false);
            deleteProviderButton.setDisable(false);
        }
    }

    @FXML
    void refreshProviders(ActionEvent event) throws SQLException {
        getAllProviders();
    }

    @FXML
    private void searchProvider(KeyEvent keyEvent) throws SQLException {
        String searchText = searchField.getText();
        if (searchText.isEmpty()){
            getAllProviders();
        }else {
            providersSearchLogic("name", searchText);
        }

    }

    private void providersSearchLogic(String columnName, String searchText) throws SQLException {
        QueryBuilder<Providers, String> queryBuilder = ProviderDao().queryBuilder();
        Where<Providers, String> where = queryBuilder.where();
        where.like(columnName, searchText.charAt(0)+"%");

        PreparedQuery<Providers> preparedQuery = queryBuilder.prepare();
        List<Providers> productsList = ProviderDao().query(preparedQuery);

        if(productsList.size()!=0){

            ObservableList searchProducts = FXCollections.observableArrayList();
            for(Providers provider: productsList){
                // supprimer la table view
                providersTabView.getItems().clear();
                searchProducts.add(new Providers(provider.getName(), provider.getDescription(), provider.getAdress(), provider.getNumber()));

            }
            // ajouter les fournisseurs à la table view
            providersTabView.setItems(searchProducts);

        }else {
            providersTabView.getItems().clear();
        }
    }
    @FXML
    private void updateProvider(ActionEvent event) throws IOException {
        openStage("../Resources/templates/ProviderUpdate.fxml", "Mis à jour fournisseur");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        udpateProviderButton.setDisable(true);
        deleteProviderButton.setDisable(true);
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colAdress.setCellValueFactory(new PropertyValueFactory<>("adress"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));



        try {
            getAllProviders();
        } catch (SQLException e) {
            System.out.println("le programme n'arrive pas à charger les fournisseurs");
        }
    }
}
