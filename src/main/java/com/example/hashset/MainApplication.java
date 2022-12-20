package com.example.hashset;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Class that launch application
 **/
public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        String css = String.valueOf(this.getClass().getResource("main.css"));

        Image img= new Image(Objects.requireNonNull(getClass().getResource("/com/example/hashset/icon.png")).toString());
        stage.getIcons().add(img);


        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setResizable(false);
        stage.setWidth(1280);
        stage.setHeight(800);
        stage.setTitle("HashSet");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}