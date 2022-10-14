package com.example.hashset;


import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;

public class HashSetActivity {

    private  HashMap<String,StackPane> graphicObjectsList = new HashMap<>();
    private HashSetInstance hashSet  = new HashSetInstance();
    private static final int  WIDTH = 100;
    private static final int  HEIGHT = 100;
    private static int  currentX = 0;
    private static int  currentY = 0;




    // method that add new object in the hash and draw it
    public StackPane add(String data){
        if(hashSet.add(data)){
            Rectangle rectangle = new Rectangle();

            rectangle.setArcHeight(5);
            rectangle.setArcWidth(5);
            rectangle.setFill(Color.RED);
            rectangle.setX(currentX);
            rectangle.setY(currentY);
            rectangle.setWidth(WIDTH);
            rectangle.setHeight(HEIGHT);

            Label label = new Label();
            label.setText(data);

            currentX+= WIDTH;
            currentY = HEIGHT;

            StackPane stackPane = new StackPane(rectangle,label);
            graphicObjectsList.put(data,stackPane);

            return stackPane;
        }
        return null;
    }


    public StackPane remove(String data){
        StackPane stackPane = graphicObjectsList.get(data);
        return stackPane;
    }

}
