package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Address");

        initRootLayout();

        showPersonOnView();
    }

    public void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(Main.class.getResource("view/rootLayout.fxml"));
            InputStream stream = getClass().getResourceAsStream("view/rootLayout.fxml");
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonOnView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(Main.class.getResource("view/mainScene.fxml"));
            InputStream stream = getClass().getResourceAsStream("view/mainScene.fxml");
            AnchorPane personOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
