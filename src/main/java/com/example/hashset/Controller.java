package com.example.hashset;

import com.example.hashset.dialogs.AddItemDialog;
import com.example.hashset.dialogs.DeleteItemDialog;
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
//TODO We need to update hashSetList after every add or remove operation
public class Controller implements Initializable {
    @FXML
    private ListView<StackPane> hashSetList;// list that show hashSet elements on the screen
    @FXML
    private Label hashSetSizeValue; // show the size of HashSet
    private HashSetActivity hashSetActivity;

    // this method is called automatically when setup Controller
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

    /**Method to add new item to HashSet. Call the dialog window and then pass the value to HashSetActivity.add()**/
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
                valueExistInHashSet.getDialogPane().setHeaderText("Value exists!");
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

    /** Method to delete item from HashSet  **/
    public void deleteButtonClick() throws ItemNotExists {
        CustomDialog deleteItemDialog = new DeleteItemDialog("Remove Item", "Enter element to remove");

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
            hashSetActivity.getHashSet().printHashSet();
        }
    }

    //TODO Method to check if HashSet contains item that user enter in the dialog window
    public void containsButtonClick() {

    }

    //TODO Method to clear the HashSet and delete all graphic representation on the screen
    public void clearButtonClick() {


    }

    /** Method to print all the values from hashset in the file **/
    public void printButtonClick() {
        // directory in which to save
        PrintDialog printDialog = new PrintDialog();
        File dir = printDialog.chooseDirectory();
        File file = new File(dir + "\\" + "sir.txt");

        writeToFile(file);

    }

    // helper method to write data into file
    private void writeToFile(File file) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException("Can't initialize the fileWriter");
        }
        try {
            for (Integer value : hashSetActivity.getHashSet().getHashSet()) {
                fileWriter.write(value.toString());
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException("Can't close the fileWriter");
            }
        }
    }

    // update hashSetList in accordance with hashSet
    private void updateHashSet() {
        hashSetList.getItems().clear();
        for (Integer value : hashSetActivity.getHashSet().getHashSet()) {
            hashSetList.getItems().add(hashSetActivity.createStackPane(value));
        }
    }

}