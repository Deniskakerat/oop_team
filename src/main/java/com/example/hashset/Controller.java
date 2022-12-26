package com.example.hashset;

import com.example.hashset.dialogs.AddItemDialog;
import com.example.hashset.dialogs.ContainsItemDialog;
import com.example.hashset.dialogs.DirChooserDialog;
import com.example.hashset.dialogs.RemoveItemDialog;
import com.example.hashset.exceptions.ItemAlreadyExists;
import com.example.hashset.exceptions.ItemNotExists;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Class that control all the events that goes on in our main scene
 **/
public class Controller implements Initializable {
    @FXML
    private ListView<StackPane> hashSetList;// list that show hashSet elements on the screen
    @FXML
    private Label hashSetSizeValue; // show the size of HashSet
    private HashSetActivity hashSetActivity;

    /**
     * this method is called automatically when setup Controller
     **/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hashSetActivity = new HashSetActivity();
    }

    /**
     * Method to add new item to HashSet
     **/
    public void addButtonClick() throws ItemAlreadyExists {

        CustomDialog addItemDialog = new AddItemDialog("Add Item", "Enter element to add");

        // returning value from dialog
        Optional<String> result = addItemDialog.showAndWait();

        // if value is present
        if (result.isPresent()) {
            // get Integer value that we need to add to our hashSet
            Integer inputData = Integer.parseInt(result.get());

            // Check if value we get from dialog already in the HashSet
            if (hashSetActivity.getHashSet().contains(inputData)) {
                Alert valueExistInHashSet = new Alert(Alert.AlertType.ERROR);
                valueExistInHashSet.getDialogPane().setHeaderText("Value already exists!");
                valueExistInHashSet.getDialogPane().setContentText("Such value already exists in the HashSet!");
                valueExistInHashSet.showAndWait();
                throw new ItemAlreadyExists("Such value already exists in the HashSet!");
            }

            // graphic element that will represent our hashSet item
            // in stackPane we're having rectangle and label
            hashSetActivity.add(inputData);

            //update the list
            updateHashSet();

        }
    }

    /**
     * Method to remove item from HashSet
     **/
    public void removeButtonClick() throws ItemNotExists {
        CustomDialog deleteItemDialog = new RemoveItemDialog("Remove Item", "Enter element to remove");

        // returning value from dialog
        Optional<String> result = deleteItemDialog.showAndWait();

        // if value is present
        if (result.isPresent()) {

            // get Integer value that we need to add to our hashSet
            Integer deleteData = Integer.parseInt(result.get());

            // Check if value we get from dialog is not exist in the HashSet
            if (!hashSetActivity.getHashSet().contains(deleteData)) {
                Alert valueExistInHashSet = new Alert(Alert.AlertType.ERROR);
                valueExistInHashSet.getDialogPane().setHeaderText("Value not exists!");
                valueExistInHashSet.getDialogPane().setContentText("Such value not  exists in the HashSet!");
                valueExistInHashSet.showAndWait();
                throw new ItemNotExists("Such value not exists in the HashSet!");
            }

            // graphic element that will represent our hashSet item
            // in stackPane we're having rectangle and label
            StackPane stackPane = hashSetActivity.remove(deleteData);
            // add new item as rectangle on the screen
            hashSetList.getItems().remove(stackPane);

            // change size label
            hashSetSizeValue.setText(String.valueOf(hashSetActivity.getHashSet().getHashSet().size()));
        }
    }

    /**
     * Method to check if HashSet contains item that user entered
     **/
    public void containsButtonClick() {
        CustomDialog containsItemDialog = new ContainsItemDialog("Check if hashset contains item",
                "Enter element");

        // returning value from dialog
        Optional<String> result = containsItemDialog.showAndWait();

        // if value is present
        if (result.isPresent()) {

            // get Integer value that we need to add to our hashSet
            Integer containsData = Integer.parseInt(result.get());

            // Check if value we get from dialog is not exist in the HashSet
            if (!hashSetActivity.getHashSet().contains(containsData)) {
                Alert valueNotExistInHashSet = new Alert(Alert.AlertType.INFORMATION);
                valueNotExistInHashSet.getDialogPane().setHeaderText("Value not exists!");
                valueNotExistInHashSet.getDialogPane().setContentText("Value " + containsData + " not  exists in the HashSet!");
                valueNotExistInHashSet.showAndWait();
            } else {
                Alert valueExistInHashSet = new Alert(Alert.AlertType.INFORMATION);
                valueExistInHashSet.getDialogPane().setHeaderText("Value exists!");
                valueExistInHashSet.getDialogPane().setContentText("Value " + containsData + " exists in the HashSet!");
                valueExistInHashSet.showAndWait();
            }
        }
    }

    /** TODO Method to clear the HashSet and delete all graphic representation on the screen */
    public void clearButtonClick() {
        // Create dialog to confirm that user want to clear the HashSet
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"", ButtonType.YES, ButtonType.NO);

        // set clear icon
        Image clearIcon = new Image(Objects.requireNonNull(getClass().getResource("/com/example/hashset/clear.png")).toString());
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(clearIcon);

        // set alert text
        alert.setTitle("Clear HashSet");
        alert.setHeaderText("You want to clear the HashSet?");
        alert.showAndWait();

        // If user pressed YES button
        if (alert.getResult() == ButtonType.YES) {
            hashSetActivity.clear();
            // update the hashSetList
            updateHashSet();
        }
    }


    /**
     * Method to print all the values from hashset in the file
     **/
    public void toFileButtonClick() {
        // directory in which to save
        DirChooserDialog dirChooserDialog = new DirChooserDialog();
        File dir = dirChooserDialog.chooseDirectory();
        // taking the chosen directory and add to it the file 'hashSet.txt'
        File file = new File(dir + "\\" + "hashSet.txt");

        try {
            writeToFile(file);
        } catch (IOException e) {
            // If we have an error during writing process or when initialize or close file writer
            Alert writingInFileError = new Alert(Alert.AlertType.ERROR);
            writingInFileError.getDialogPane().setHeaderText("Error during file writing!");
            writingInFileError.getDialogPane().setContentText("Can't write hashSet to the file!");
            writingInFileError.showAndWait();
        }

    }

    /**
     * helper method to write data in the file
     **/
    private void writeToFile(File file) throws IOException {
        // create fileWriter
        try (FileWriter fileWriter = new FileWriter(file)) {
            // write all the data from hashSet into file
            for (Integer value : hashSetActivity.getHashSet().getHashSet()) {
                fileWriter.write(value.toString());
                fileWriter.write("\n");
            }
        }
    }

    /**
     * update hashSetList in accordance with hashSet and set the size label
     **/
    private void updateHashSet() {
        // clear the hashSetList
        hashSetList.getItems().clear();
        // then add values
        for (Integer value : hashSetActivity.getHashSet().getHashSet()) {
            hashSetList.getItems().add(hashSetActivity.createStackPane(value));
        }
        // update the size label value
        hashSetSizeValue.setText(String.valueOf(hashSetList.getItems().size()));
    }

}