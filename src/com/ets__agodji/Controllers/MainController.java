package com.ets__agodji.Controllers;
import com.ets__agodji.Models.Users;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.ets__agodji.Dao.AllDao.UserDao;
import static com.ets__agodji.Main.scene;

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
    public void onSubmit(ActionEvent event) throws SQLException {
        String username = usernameField.getText();
        String password = passwordField.getText();

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
        }
        else {
            System.out.println("Nom d'utilisateur ou mot de passe erroné");
        }

    }

    private void clearInput(){
        usernameField.clear();
        passwordField.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("le système est bien initialisé");

    }


}
