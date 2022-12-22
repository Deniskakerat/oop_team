package com.example.hashset.dialogs;

import com.example.hashset.CustomDialog;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Class extends the CustomDialog and serve for user input of value that will be added to hashSet
 **/
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


}
