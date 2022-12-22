package com.example.hashset;

import com.example.hashset.dialogs.AddItemDialog;
import com.example.hashset.dialogs.ContainsItemDialog;
import com.example.hashset.dialogs.RemoveItemDialog;
import com.example.hashset.dialogs.PrintDialog;
import com.example.hashset.exceptions.ItemAlreadyExists;
import com.example.hashset.exceptions.ItemNotExists;
import com.example.hashset.graphics.AnimatedZoom;
import com.example.hashset.graphics.DragObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
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

        AnimatedZoom zoomOperator = new AnimatedZoom();
        DragObject dragObject = new DragObject();

        // set animated zoom for hashSetList
        hashSetList.setOnScroll(event -> {
            double zoomFactor = 1.5;
            if (event.getDeltaY() <= 0) {
                // zoom out
                zoomFactor = 1 / zoomFactor;
            }
            zoomOperator.zoom(hashSetList, zoomFactor, event.getSceneX(), event.getSceneY());
        });

        //make hashSetList draggable
        dragObject.makeDraggable(hashSetList);
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

            // add new item as rectangle on the screen
            //hashSetList.getItems().add(stackPane);
            hashSetSizeValue.setText(String.valueOf(hashSetActivity.getHashSet().getHashSet().size()));
            hashSetActivity.getHashSet().printHashSet();
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
            System.out.println("Size = " + hashSetActivity.getHashSet().getHashSet().size());
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

    //TODO Method to clear the HashSet and delete all graphic representation on the screen
    public void clearButtonClick() {


    }

    /**
     * Method to print all the values from hashset in the file
     **/
    public void printButtonClick() {
        // directory in which to save
        PrintDialog printDialog = new PrintDialog();
        File dir = printDialog.chooseDirectory();
        // taking the chosen directory and add the file 'hashSet.txt'
        File file = new File(dir + "\\" + "hashSet.txt");

        writeToFile(file);

    }

    /**
     * helper method to write data in the file
     **/
    private void writeToFile(File file) {
        // create fileWriter
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException("Can't initialize the fileWriter");
        }
        // write all hashSet value into the file
        try {
            for (Integer value : hashSetActivity.getHashSet().getHashSet()) {
                fileWriter.write(value.toString());
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * update hashSetList in accordance with hashSet
     **/
    private void updateHashSet() {
        // clear the hashSetList
        hashSetList.getItems().clear();
        // then add values
        for (Integer value : hashSetActivity.getHashSet().getHashSet()) {
            hashSetList.getItems().add(hashSetActivity.createStackPane(value));
        }
    }

}