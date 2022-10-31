package com.example.hashset;

import com.example.hashset.exceptions.ItemAlreadyExists;
import com.example.hashset.exceptions.ItemNotExists;
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
    @FXML
    private Label hashSetSizeValue; // show the size of HashSet
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

            // Check if value we get from dialog already in the HashSet
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
            stackPane = hashSetActivity.add(inputData);

            // add new item as rectangle on the screen
            hashSetList.getItems().add(stackPane);
            hashSetSizeValue.setText(String.valueOf(hashSetActivity.getHashSet().getHashSet().size()));
        }
    }

    //TODO Method to delete item from HashSet 'Should call the dialog in which user enter the item to delete'
    public void deleteButtonClick(ActionEvent actionEvent) throws ItemNotExists {
        CustomDialog deleteItemDialog = new DeleteItemDialog("Remove Item","Enter element to remove");

        // returning value from dialog
        Optional<String> result = deleteItemDialog.showAndWait();

        // if value is present
        if(result.isPresent()){

            // get string value that we need to add to our hashSet
            String deleteData = result.get();

            // Check if value we get from dialog is not exist in the HashSet
            if(!hashSetActivity.getHashSet().contains(deleteData)){
                Alert valueExistInHashSet = new Alert(Alert.AlertType.ERROR);
                valueExistInHashSet.getDialogPane().setHeaderText("Value not exists!");
                valueExistInHashSet.getDialogPane().setContentText("Such value not  exists in the HashSet!");
                valueExistInHashSet.showAndWait();
                throw  new ItemNotExists("Such value not exists in the HashSet!");
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