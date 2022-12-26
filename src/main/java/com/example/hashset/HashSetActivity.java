package com.example.hashset;


import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;

/**
 * Class that manage the graphic objects
 **/
public class HashSetActivity {
    private final HashMap<Integer, StackPane> graphicObjectsList = new HashMap<>();
    private final HashSetInstance hashSet = new HashSetInstance();
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    /**
     * method that add new object in the hashSet and call the method that draw it
     **/
    public void add(Integer data) {
        if (hashSet.add(data)) {
            createStackPane(data);
        }
    }

    /**
     * Method to remove value from HashSet and remove rectangle with that value
     **/
    public StackPane remove(Integer data) {
        // check if we could remove value then return graphic object that represents its value
        if (hashSet.remove(data)) {
            return graphicObjectsList.get(data);
        }
        return null;
    }
    /**
     * Clear the hashSet and it's graphic representation
     **/
    public void clear (){
        // clear the hashSet
        hashSet.getHashSet().clear();
    }

    /**
     * Creating graphic element that represent value in the HashSet
     **/
    public StackPane createStackPane(Integer value) {
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

        StackPane stackPane = new StackPane(rectangle, label);
        graphicObjectsList.put(value, stackPane);

        return stackPane;
    }

    public HashSetInstance getHashSet() {
        return hashSet;
    }
}
