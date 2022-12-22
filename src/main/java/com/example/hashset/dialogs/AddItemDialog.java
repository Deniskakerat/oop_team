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


public class AddItemDialog extends CustomDialog {
    public AddItemDialog(String title, String highlight) {
        super(title, highlight);

        // add icon
        Image addIcon = new Image(Objects.requireNonNull(getClass().getResource("/com/example/hashset/add.png")).toString());
        Stage stage = (Stage) this.getDialogPane().getScene().getWindow();
        stage.getIcons().add(addIcon);

        // init ok button
        Button okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);

        // if the input validation failed then user can't press button OK
        okButton.addEventFilter(ActionEvent.ACTION, actionEvent -> {
            if (!isInputValid()) {
                actionEvent.consume();
            }
        });
    }

    /**
     * convert result of input
     **/
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

    /**
     * check if there's entered value and if it's digit
     **/
    @Override
    public boolean isInputValid() {
        if (inputField.getText().isBlank() || !isParsable(inputField.getText())) {
            return false;
        }
        return true;
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

}
