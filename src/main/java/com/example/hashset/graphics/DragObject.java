package com.example.hashset.graphics;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class DragObject {
    private double mouseAnchorX,mouseAnchorY;

    public void makeDraggable(Node node){

        node.setOnMousePressed(event -> {
            mouseAnchorX = event.getX();
            mouseAnchorY = event.getY();
        });
        node.setOnMouseDragged(event -> {
            node.setLayoutX(event.getSceneX() - mouseAnchorX);
            node.setLayoutY(event.getSceneY() - mouseAnchorY);
        });
    }
}
