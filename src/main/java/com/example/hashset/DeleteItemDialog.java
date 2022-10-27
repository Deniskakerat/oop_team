package com.example.hashset;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteItemDialog extends CustomDialog {
    public DeleteItemDialog(String title, String subtitle) {
        super(title, subtitle);
    }

    @Override
    public boolean isInputValid() {
        return false;
    }

    @Override
    protected void setResultConverter() {

    }

}
