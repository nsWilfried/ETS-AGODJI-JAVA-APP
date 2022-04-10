package com.ets__agodji;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static com.ets__agodji.Dao.AllDao.BuildTables;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent  root = FXMLLoader.load(getClass().getResource("Resources/templates/login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {
        // Construire toutes les tables
        BuildTables();
        launch(args);


    }

}
