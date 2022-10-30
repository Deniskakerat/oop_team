package com.example.hashset;

import com.example.hashset.exceptions.ItemAlreadyExists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    private HashSetActivity hashSetActivity;


    // this method is called automatically when setup Controller
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hashSetActivity = new HashSetActivity();

    }

    // Method to add new item to HashSet. Call the dialog window and then pass the value to HashSetActivity.add()
    public void addButtonClick(ActionEvent actionEvent) throws ItemAlreadyExists {

        CustomDialog addItemDialog = new AddItemDialog("Add Item","Enter element to add");

        // returning value from dialog
        Optional<String> result = addItemDialog.showAndWait();

        // if value is present
        if(result.isPresent()){
            // get string value that we need to add to our hashSet
            String inputData = result.get();

            if(hashSetActivity.getHashSet().contains(inputData)){
                Alert valueExistInHashSet = new Alert(Alert.AlertType.ERROR);
                valueExistInHashSet.getDialogPane().setHeaderText("Value exists!");
                valueExistInHashSet.getDialogPane().setContentText("Such value already exists in the HashSet!");
                valueExistInHashSet.showAndWait();
                throw  new ItemAlreadyExists("Such value already exists in the HashSet!");
            }

            // graphic element that will represent our hashSet item
            // in stackPane we're having rectangle and label
            StackPane stackPane;
            hashSetActivity = new HashSetActivity();
            stackPane = hashSetActivity.add(inputData);

            // add new item as rectangle on the screen
            hashSetList.getItems().add(stackPane);
        }
    }

    //TODO Method to delete item from HashSet 'Should call the dialog in which user enter the item to delete'
    public void deleteButtonClick(ActionEvent actionEvent) {

        /*String objectToRemove = "ww";
        StackPane removeStackPane = hashSetActivity.remove(objectToRemove);
        hashSetList.getItems().remove(removeStackPane);*/
        /*Dialog<String> addItemDialog = new AddItemDialog("Delete Item","Enter item to delete");
        Optional<String> result = addItemDialog.showAndWait();*/
    }

    //TODO Method to check if HashSet contains item that user enter in the dialog window
    public void containsButtonClick(ActionEvent actionEvent) {
    }

    //TODO Method to clear the HashSet and delete all graphic representation on the screen
    public void clearButtonClick(ActionEvent actionEvent) {


    }

    //TODO Method to print all the values from hashset on the screen
    public void printButtonClick(ActionEvent actionEvent){

    }

}