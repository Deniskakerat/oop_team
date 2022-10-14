package com.example.hashset;

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


public class Controller implements Initializable {
    @FXML
    private Button addButton, deleteButton, containsButton, clearButton, sizeButton;
    @FXML
    private HBox hashBox;
    @FXML
    private ListView<StackPane> hashSetList;// list that show hashSet elements on the screen
    private HashSetActivity hashSetActivity;

    public ListView<StackPane> getHashSetList() {
        return hashSetList;
    }


    // this method is called automatically when setup Controller
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        hashSetActivity = new HashSetActivity();
        //hashSetList.setCellFactory(cell -> new ListViewController.Cell());


    }

    //TODO Method to add new item to HashSet. 'Should call the dialog window' and draw new item on the screen
    public void addButtonClick(ActionEvent actionEvent) throws IOException {
        /*CustomDialog customDialog = new AddItemDialog("Add Item", "Enter new item into text box");
        Optional<String> result = customDialog.showAndWait();

        StackPane stackPane;

        System.out.println("Res = " + customDialog.getTextField().getText());
        hsActivity = new HSActivity();
        stackPane = hsActivity.add(customDialog.getTextField().getText());
        hashSetList.getItems().add(stackPane);*/

        /*Dialog<String> addItemDialog = new AddItemDialog("Add Item","Enter new element");
        Optional<String> result = addItemDialog.showAndWait();

        */
        Dialog<String> addItemDialog = new AddItemDialog("Add Item","Enter new element");
        Optional<String> result = addItemDialog.showAndWait();
        if(result.isPresent()){
            String inputData = result.get();
            StackPane stackPane;
            hashSetActivity = new HashSetActivity();
            stackPane = hashSetActivity.add(inputData);
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

    //TODO Method to get size of HashSet 'Should call getter'
    public void sizeButtonClick(ActionEvent actionEvent) {

    }

    static class Cell extends ListCell<Rectangle> {
        public Cell(Label label) {
            this.label = label;
        }

        private Label label = new Label(); // it represents the data user entered in dialog box
        private StackPane stackPane = new StackPane(); // allow us to stack elements one on the other
        private Rectangle rectangle = new Rectangle(); // object of Rectangle (Instance that represents every cell in HashSet)

        public Cell() {
            super();
            // set stackPane as the main Node since it let us center text on Rectangle
            stackPane.setPrefSize(100, 100);
            stackPane.getChildren().addAll(rectangle, label);
            stackPane.setBackground(Background.fill(Color.WHITE));

        }

        @Override  // realize how the every cell must look
        protected void updateItem(Rectangle rectangle, boolean b) {
            super.updateItem(rectangle, b);
            setText(null);
            setGraphic(null);
            if (rectangle != null && !b) {
                setGraphic(stackPane);
            }
        }
    }

}