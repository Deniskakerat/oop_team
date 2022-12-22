package com.example.hashset.dialogs;

import com.example.hashset.CustomDialog;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Class extends the CustomDialog and serve for user input of value that will be remover from hashSet
 **/
public class RemoveItemDialog extends CustomDialog {
    public RemoveItemDialog(String title, String highlight) {
        super(title, highlight);

        // add icon
        Image removeIcon = new Image(Objects.requireNonNull(getClass().getResource("/com/example/hashset/remove.png")).toString());
        Stage stage = (Stage) this.getDialogPane().getScene().getWindow();
        stage.getIcons().add(removeIcon);

        // init ok button
        Button okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);

        // if the input validation failed then user can't press button OK
        okButton.addEventFilter(ActionEvent.ACTION, actionEvent -> {
            if (!isInputValid()) {
                actionEvent.consume();
            }
        });
    }


}
