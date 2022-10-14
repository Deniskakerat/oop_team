package com.example.hashset;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;

public abstract class CustomDialog extends Dialog<String> {
    @FXML
    public Label inputTitle;
    @FXML
    protected TextField inputField;
    @FXML
    protected ButtonType OK;
    @FXML
    protected ButtonType CANCEL;
    public CustomDialog(String title, String subtitle){
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("dialog.fxml"));

            loader.setController(this);
            DialogPane dialogPane = loader.load();
            setResizable(false);
            setTitle(title);
            setDialogPane(dialogPane);

            setUI(subtitle);
            setResultConverter();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setUI(String labelName){
        /*AnchorPane pane = new AnchorPane();
        pane.setPrefSize(350,400);

        Label activityLabel = new Label(labelName);
        activityLabel.setLayoutX(10);
        activityLabel.setLayoutY(90);
        activityLabel.setMaxHeight(16);
        activityLabel.setMaxWidth(400);
        activityLabel.setFont(Font.font("Ariel",16));
        activityLabel.setAlignment(Pos.CENTER);


        textField = new TextField();
        textField.setLayoutX(115);
        textField.setLayoutY(163);
        textField.setPrefSize(170,30);


        pane.getChildren().addAll(activityLabel,textField);
        getDialogPane().getChildren().add(pane);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        setResultConverter();*/


    }

    public abstract boolean isInputValid();
    protected abstract void setResultConverter();

}

