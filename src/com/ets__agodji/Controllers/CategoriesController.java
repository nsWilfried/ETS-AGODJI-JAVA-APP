package com.ets__agodji.Controllers;

import com.ets__agodji.Models.Categories;
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
import static com.ets__agodji.Dao.AllDao.CategoryDao;

public class CategoriesController implements Initializable {

    @FXML
    private Button refreshCategoriesButton;

    @FXML
    private Button addCategoryButton;

    @FXML
    private TableView<Categories> categoriesTableView;

    @FXML
    private Button deleteCategoryButton;

    @FXML
    private Button updateCategoryButton;

    @FXML
    private TableColumn<Categories, String> colDescription;

    @FXML
    private TableColumn<Categories, String> colName;

    public  static  Categories category;

    @FXML
    private LabeledTextField searchField;



    private TableView<?> getAllCategories() throws SQLException {
        ObservableList categories = FXCollections.observableArrayList();

        for (Categories category:CategoryDao()){
            categories.add(new Categories(category.getName(), category.getDescription()));
        }
        categoriesTableView.setItems(categories);
        return categoriesTableView;
    }

    @FXML
    private void addCategory(ActionEvent event) throws IOException {
        openStage("../Resources/templates/AddCategory.fxml", "Gestion des catégories");
    }

    @FXML
    void getOneCategory(MouseEvent event) {
        category = categoriesTableView.getSelectionModel().getSelectedItem();

        if (category != null){
            updateCategoryButton.setDisable(false);
            deleteCategoryButton.setDisable(false);
        }

    }

    @FXML
    private void refreshCategories(ActionEvent actionEvent) throws SQLException {
        getAllCategories();
    }

    @FXML
    private TableView<?> deleteCategory(ActionEvent event) throws SQLException {

        Optional<ButtonType> alert = openConfirmationAlert("Voulez vous vraiment supprimer cette catégorie ?").showAndWait();

        if (alert.isPresent() && alert.get() == ButtonType.OK){
            Categories selected_category = CategoryDao().queryForId(category.getName());
            CategoryDao().delete(selected_category);

            getAllCategories();
        }

        return categoriesTableView;

    }

    private void categoriesSearchLogic(String columnName, String searchText) throws SQLException{
        QueryBuilder<Categories, String> queryBuilder = CategoryDao().queryBuilder();
        Where<Categories, String> where = queryBuilder.where();
        where.like(columnName, searchText.charAt(0)+"%");

        PreparedQuery<Categories> preparedQuery = queryBuilder.prepare();
        List<Categories> categoriesList = CategoryDao().query(preparedQuery);

        if(categoriesList.size()!=0){

            ObservableList searchCategories = FXCollections.observableArrayList();
            for(Categories category: categoriesList){
                // supprimer la table view
                categoriesTableView.getItems().clear();
                searchCategories.add(new Categories(category.getName(), category.getDescription()));

            }
            // ajouter les fournisseurs à la table view
            categoriesTableView.setItems(searchCategories);

        }else {
            categoriesTableView.getItems().clear();
        }
    }

    @FXML
    private void searchCategory(KeyEvent event) throws SQLException {
        String searchText = searchField.getText();
        if (searchText.isEmpty()){
            getAllCategories();
        }else {
           categoriesSearchLogic("name", searchText);
        }
    }



    @FXML
    private void updateCategory(ActionEvent event) throws SQLException, IOException {
        openStage("../Resources/templates/CategoryUpdate.fxml", "Mis à jour de la catégorie");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        try {
            getAllCategories();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des catégories");
        }
    }
}
