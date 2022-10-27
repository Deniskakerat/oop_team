package com.example.hashset;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
/**
 * Class that allow us to create different dialogs for different tasks
 * which will inherit basic design
 **/
public abstract class CustomDialog extends Dialog<String> {
    @FXML
    public Label inputTitle;
    @FXML
    protected TextField inputField;
    @FXML
    protected ButtonType OK;
    @FXML
    protected ButtonType CANCEL;
    /**
     * Constructor :
     * Arguments:
     * title - title for dialog
     * instruction - tell user what to do in dialog
     **/

    public CustomDialog(String title, String instruction){
        try
        {
            // load 'dialog.fxml'
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("dialog.fxml"));
            loader.setController(this);
            DialogPane dialogPane = loader.load();

            setResizable(false);
            setTitle(title);
            // TODO make it possible to change label that constains instuction for input
            this.inputTitle.setText(instruction);
            setDialogPane(dialogPane);

            setResultConverter();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // check if input valid
    public abstract boolean isInputValid();
    // convert result so we could return it from dialog
    protected abstract void setResultConverter();

}

