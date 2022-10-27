package com.example.hashset;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Class that allow us to create different dialogs for different tasks
 * which will inherit basic design
 **/
public abstract class CustomDialog extends Dialog<String>{
    protected TextField inputField;
    protected Label activityLabel;
    /**
     * Constructor :
     * Arguments:
     * title - title for dialog
     * instruction - tell user what to do in dialog
     **/


    public CustomDialog(String title, String instruction){
        this.setTitle(title);

        setUI(instruction);
        setResultConverter();


    }
    // Method that creates GUI
    private void setUI(String instruction){
        AnchorPane anchorPane = new AnchorPane();

        getDialogPane().setPrefSize(400,400);
        anchorPane.setPrefSize(400,350);

        activityLabel = new Label();
        activityLabel.setText(instruction);
        activityLabel.setLayoutY(90);
        activityLabel.setPrefHeight(16);
        activityLabel.setPrefWidth(400);
        activityLabel.setFont(Font.font("Ariel",16));
        activityLabel.setAlignment(Pos.CENTER);


        inputField = new TextField();
        inputField.setPrefWidth(200);
        inputField.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(activityLabel,inputField);
        VBox.setMargin(activityLabel,new Insets(100,0,0,0));
        VBox.setMargin(inputField,new Insets(50,100,0,100));

        anchorPane.getChildren().add(vBox);
        getDialogPane().getChildren().add(anchorPane);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    }

    // check if input valid
    public abstract boolean isInputValid();
    // convert result so we could return it from dialog
    protected abstract void setResultConverter();

}

