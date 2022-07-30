package com.ets__agodji.Controllers;

import com.ets__agodji.Models.Users;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.ets__agodji.Dao.AllDao.UserDao;

public class MainController implements Initializable {


    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    public Text errorLabel;


    public static Alert openConfirmationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Demande de confirmation");
        alert.setContentText(message);
        return alert;
    }

    /**
     * Permet d'ouvrir des fenêtres
     *
     * @param url   url du fichier fxml de la fenêtre
     * @param title titre de la fenêtre
     * @throws IOException
     */
    public static void openStage(String url, String title) throws IOException {
        Parent root = FXMLLoader.load(MainController.class.getResource(url));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Permet de vérifier si l'utilisateur se trouve dans la base de données
     * @param username
     * @param password
     * @return l'utilisateur
     * @throws SQLException
     */
    private List<Users> verifyUserPassordAndUsername(String username, String password) throws SQLException {
        //permet de construire les requêtes sur la table user
        QueryBuilder<Users, String> queryBuilder = UserDao().queryBuilder();

        // permet de poser des conditions sur les requêtes
        Where<Users, String> where = queryBuilder.where();

        where.and(where.eq("username", username), where.eq("password", password));

        // traduction de la requête en sql
        PreparedQuery<Users> preparedQuery = queryBuilder.prepare();
        List<Users> usersList = UserDao().query(preparedQuery);

        return usersList;
    }

    /**
     * Permet d'afficher les messages d'erreur lors de l'authentification
     * @param username
     * @param password
     * @param usersList
     * @throws IOException
     */
    private void showAuthErrorMessage(String username, String password, List<Users> usersList) throws IOException {
        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Entrez tout les champs");
        } else if (usersList.size() != 0) {
            clearAllInput();

            submitButton.getScene().getWindow().hide();
            buildHomeStage();
        }else if(usersList.size()==0){
            errorLabel.setText("Nom d'utilisateur ou mot de passe incorrect");
        }
    }
    /**
     * Contient la logique de l'authentification à l'app
     *
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    private void onSubmit(ActionEvent event) throws SQLException, IOException {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        List<Users> usersList = verifyUserPassordAndUsername(username, password);
        showAuthErrorMessage(username, password, usersList);

    }

    private void clearAllInput() {
        usernameField.clear();
        passwordField.clear();
    }

    private void buildHomeStage() throws IOException {
        openStage("../Resources/templates/Home.fxml", "Home");
    }

    public static void createValidatorForField(Control field){
        ValidationSupport validationSupport = new ValidationSupport();
        validationSupport.setErrorDecorationEnabled(true);
        validationSupport.registerValidator(field, Validator.createEmptyValidator("Ce champ est requis"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createValidatorForField(usernameField);
        createValidatorForField(passwordField);
    }


}
