package CS501Final;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.Node;

public class Square extends Pane {
	
    public boolean Filled;

    Square(int size) {
    	//set the maze pane length and width
        setPrefSize(size, size);
        //set the maze pane border color and background color
        setStyle("-fx-border-color: black;" +"-fx-background-color: transparent;");
        //every click on mouse can mark a square
        this.setOnMouseClicked(e -> MarkX());
    }
    //draw the mark symbol
    private void MarkX() {
    	//create a observablelist to follow every change
        ObservableList<Node> list = this.getChildren();
        
        //if list's size is not zero, clear all
        if (list.size() > 0) {
            list.clear();
        } else {
        	//else add crossing mark to the square
            list.addAll(
            		//line from left up to right down
                    new Line(0, 0, getWidth(), getHeight()),
                    //line from right up to left down
                    new Line(getWidth(), 0, 0, getHeight())
            );
        }
    }

    //fill the square and turn Filled to true
    public void fillSquare() {
        setStyle("-fx-background-color: blue; -fx-border-color: black;");
        Filled = true;
    }

    //whether the square is marked or not
    public boolean Marked() {
        return (getChildren().size() > 0);
    }

    //Filled method return whether Filled is true or not
    public boolean Filled() {
        return Filled;
    }

    //set the square Filled to false;
    public void clearPath() {
        setStyle("-fx-background-color: transparent; -fx-border-color: black");
        Filled = false;
    }

    //clear the Marked square to empty
    public void clearMark() {
        MarkX();
    }
}