package com.example.hashset;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
/**
 * Class that launch application
 **/
public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        String css = String.valueOf(this.getClass().getResource("main.css"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setResizable(false);
        stage.setWidth(1280);
        stage.setHeight(800);
        stage.setTitle("HashSet Gui");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}