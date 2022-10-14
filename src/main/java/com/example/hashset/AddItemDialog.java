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
                    System.out.println("---------------------------------------------------------");
                    System.out.println(inputField.getText());
                    return inputField.getText();
                }
                return null;
            }
        };
        setResultConverter(stringResultConverter);
    }

    @Override
    public boolean isInputValid() {
        if(inputField.getText().isBlank()){
            return false;
        }
        return true;
    }


}
