package com.example.hashset;


import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import java.util.HashMap;

/**
 *Class that manage the graphic objects
 **/
public class HashSetActivity {

    private  HashMap<Integer,StackPane> graphicObjectsList = new HashMap<>();
    private HashSetInstance hashSet  = new HashSetInstance();
    private static final int  WIDTH = 100;
    private static final int  HEIGHT = 100;

    // method that add new object in the hash and draw it
    public StackPane add(Integer data){
        if(hashSet.add(data)){
            createStackPane(data);
        }
        return null;
    }

    // Method to delete value from HashSet and remove rectangle with that value
    public StackPane remove(Integer data){
        if(hashSet.remove(data)){
            StackPane stackPane = graphicObjectsList.get(data);
            return stackPane;
        }
        return null;
    }

    public  HashSetInstance getHashSet() {
        return hashSet;
    }

    public HashMap<Integer, StackPane> getGraphicObjectsList() {
        return graphicObjectsList;
    }

    public void setGraphicObjectsList(HashMap<Integer, StackPane> graphicObjectsList) {
        this.graphicObjectsList = graphicObjectsList;
    }
    public StackPane createStackPane(Integer value){
        // Draw rectangle with value in center
        Rectangle rectangle = new Rectangle();

        rectangle.setArcHeight(5);
        rectangle.setArcWidth(5);
        rectangle.setFill(Paint.valueOf("#0392cf"));
        rectangle.setWidth(WIDTH);
        rectangle.setHeight(HEIGHT);

        Label label = new Label();
        label.setTextFill(Color.BLACK);
        label.setText(value.toString());

        StackPane stackPane = new StackPane(rectangle,label);
        graphicObjectsList.put(value,stackPane);

        return stackPane;
    }
}
