package com.ets__agodji;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static com.ets__agodji.Dao.AllDao.BuildTables;

/**
 * __    _  _  _____  _  _  _  _  __  __
 *   /__\  ( \( )(  _  )( \( )( \/ )(  \/  )
 *  /(__)\  )  (  )(_)(  )  (  \  /  )    (
 * (__)(__)(_)\_)(_____)(_)\_) (__) (_/\/\_)
 *
 */
public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent  root = FXMLLoader.load(getClass().getResource("Resources/templates/Login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {
        // Construire toutes les tables
        BuildTables();
        launch(args);


    }

}
