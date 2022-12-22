package com.example.hashset.dialogs;

import com.example.hashset.CustomDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Objects;

/*
Сontain item

 */
public class ContainsItemDialog extends CustomDialog {

    public ContainsItemDialog(String title, String highlight) {
        super(title, highlight);

        // add icon
        Image containsIcon = new Image(Objects.requireNonNull(getClass().getResource("/com/example/hashset/contains.png")).toString());
        Stage stage = (Stage) this.getDialogPane().getScene().getWindow();
        stage.getIcons().add(containsIcon);
        // init ok button
        Button okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);

        // if the input validation failed then user can't press button OK
        okButton.addEventFilter(ActionEvent.ACTION, actionEvent -> {
            if (!isInputValid()) {
                actionEvent.consume();
            }
        });
    }

    @Override
    protected void setResultConverter() {
        Callback<ButtonType, String> stringResultConverter = param -> {
            if (param == ButtonType.OK) {
                return inputField.getText();
            }
            return null;
        };
        setResultConverter(stringResultConverter);
    }

    public boolean isInputValid() {
        if (inputField.getText().isBlank() || !isParsable(inputField.getText())) {
            return false;
        }
        return true;
    }

    // check if we could parse Integer from String
    private static boolean isParsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
}
