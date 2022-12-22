package com.example.hashset;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

/**
 * Class that allow us to create different dialogs for different tasks
 * which will inherit basic design
 **/
public abstract class CustomDialog extends Dialog<String> {
    protected TextField inputField;

    /**
     * Constructor :
     * Arguments:
     * title - title for dialog
     * instruction - tell user what to do in dialog
     **/
    public CustomDialog(String title, String instruction) {
        this.setTitle(title);
        setUI(instruction);
        setResultConverter();
    }

    /**
     * Method that creates GUI for dialog
     **/
    private void setUI(String instruction) {
        AnchorPane anchorPane = new AnchorPane();
        // adding css styles
        String css = String.valueOf(this.getClass().getResource("/com/example/hashset/dialog.css"));
        getDialogPane().getStylesheets().add(css);

        getDialogPane().setPrefSize(400, 400);
        anchorPane.setPrefSize(400, 350);

        Label activityLabel = new Label();
        activityLabel.setText(instruction);
        activityLabel.setLayoutY(90);
        activityLabel.setPrefHeight(16);
        activityLabel.setPrefWidth(400);
        activityLabel.setFont(Font.font("Ariel", 16));
        activityLabel.setAlignment(Pos.CENTER);

        inputField = new TextField();
        inputField.setPrefWidth(200);
        inputField.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(activityLabel, inputField);
        VBox.setMargin(activityLabel, new Insets(100, 0, 0, 0));
        VBox.setMargin(inputField, new Insets(50, 100, 0, 100));

        anchorPane.getChildren().add(vBox);
        getDialogPane().getChildren().add(anchorPane);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    }

    /**
     * Check if there's entered value and if it's integer
     **/
    public boolean isInputValid() {
        // if entered value either blank or not parsable to integer
        return !inputField.getText().isBlank() && isParsable(inputField.getText());
    }

    /**
     * check if we could parse Integer from String
     **/
    private static boolean isParsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    /**
     * Convert result of input
     **/
    protected void setResultConverter() {
        // if clicked on button OK and valid value entered
        Callback<ButtonType, String> stringResultConverter = param -> {
            if (param == ButtonType.OK) {
                return inputField.getText();
            }
            return null;
        };
        setResultConverter(stringResultConverter);
    }

}

