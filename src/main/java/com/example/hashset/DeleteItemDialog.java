package com.example.hashset;

import javafx.scene.control.ButtonType;
import javafx.util.Callback;


public class DeleteItemDialog extends CustomDialog {
    public DeleteItemDialog(String title, String subtitle) {
        super(title, subtitle);
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
        if(inputField.getText().isBlank()){
            return false;
        }
        return true;
    }


}
