package com.example.hashset;

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
