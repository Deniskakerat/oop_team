package com.example.hashset.dialogs;

import com.example.hashset.CustomDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.util.Callback;


public class DeleteItemDialog extends CustomDialog {
    public DeleteItemDialog(String title, String highlight) {
        super(title, highlight);

        Button okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);

        // if the input validation failed then user can't press button OK
        okButton.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!isInputValid()){
                    actionEvent.consume();
                }
            }
        });
    }

    @Override
    protected void setResultConverter() {
        Callback<ButtonType, String> stringResultConverter = new Callback<ButtonType, String>() {
            @Override
            public String call(ButtonType param) {
                if (param == ButtonType.OK) {
                    return inputField.getText();
                }
                return null;
            }
        };
        setResultConverter(stringResultConverter);
    }
    //TODO add check : same value can't be added to hashset if it's in it
    @Override
    public boolean isInputValid() {
        if(inputField.getText().isBlank() || !isParsable(inputField.getText())){
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
