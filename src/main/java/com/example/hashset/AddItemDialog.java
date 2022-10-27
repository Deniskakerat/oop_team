package com.example.hashset;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.util.Callback;


public class AddItemDialog extends CustomDialog {
    public AddItemDialog(String title, String highlight) {
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

    // Method that return from the dialog value that user entered into textField
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
        if(inputField.getText().isBlank()){
            return false;
        }
        return true;
    }


}
