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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.ets__agodji.Dao.AllDao.UserDao;

public class MainController implements Initializable {


    @FXML
    private  TextField usernameField;

    @FXML
    private  PasswordField passwordField;

    @FXML
    private  Button submitButton;

    @FXML
    public Text errorLabel;

    @FXML
    public void onSubmit(ActionEvent event) throws SQLException, IOException {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        //permet de construire les requêtes sur la table user
        QueryBuilder<Users,String> queryBuilder = UserDao().queryBuilder();

        // permet de poser des conditions sur les requêtes
        Where<Users, String> where = queryBuilder.where();

        where.and(where.eq("username", username), where.eq("password", password));

        // traduction de la requête en sql
        PreparedQuery<Users> preparedQuery = queryBuilder.prepare();
        List<Users> usersList = UserDao().query(preparedQuery);

        if (username.isEmpty() || password.isEmpty() ) {
            errorLabel.setText("Entrez tout les champs");
        }
        else if (usersList.size() != 0){
            System.out.println("utilisateur connecté");
            clearInput();

            submitButton.getScene().getWindow().hide();
            buildHomeStage();
        }
        else {
            errorLabel.setText("Nom d'utilisateur ou mot de passe erroné");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("I have a great message for you!");

            alert.showAndWait();
        }

    }

    private void clearInput(){
        usernameField.clear();
        passwordField.clear();
    }

    private void buildHomeStage() throws IOException {
        Stage homeStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../Resources/templates/home.fxml"));
        Scene scene = new Scene(root);
        homeStage.setScene(scene);
        homeStage.setFullScreen(true);
        homeStage.show();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


}
