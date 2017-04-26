package CS501Final;

import javax.swing.JTextField;
import CS501Final.Square;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MazePane extends GridPane {
	//create a button to find path
	public Button FindPath = new Button("Find Path");
	//create a button to clear path
	public Button ClearPath = new Button("Clear Path");
	//create a button to clear all marks
	public Button ClearMarks = new Button("Clear All Marks");
	
	//define a variable
	Square[][] sq;

	//set the square coordinate 
	int x;
	int y;
	
	//distribute the squares to pane
	public MazePane(int size, int width){
		//create a new empty square
		sq = new Square[size][size];
		
		//add square to every unit
		for(int i = 0; i < sq.length; i++)
			for(int j = 0; j < sq[i].length; j++){
				sq[i][j] = new Square(width);
				add(sq[i][j], j, i);
			}
		
		//set the starting square to be disabled
		sq[0][0].setDisable(true);
		//set the exiting square to be disabled
		sq[size-1][size-1].setDisable(true);
		//set the method clearPath to button ClearPath
		
		ClearPath.setOnMouseClicked(e -> clearWholePath());
		//set the method findPath to button FindPath
		FindPath.setOnMouseClicked(e -> findPath());
		//set the method clearAllMarks to button ClearMarks
		ClearMarks.setOnMouseClicked(e -> clearAllMarks());
		
		//set width border of pane is 10
		setPadding(new Insets(10));
	}
	
	//clear the path
	private void clearWholePath(){
		//from the starting point to clean
		x = 0;
		y = 0;
		//for every point
		for(int i = 0; i < sq.length; i++)
			for(int j = 0; j < sq[i].length; j++)
				//set the square to be not filled
				sq[i][j].clearPath();
	}
	
	//clear marks
	private void clearAllMarks(){
		//from the starting point to clear 
		x = 0;
		y = 0;
		//for every square
		 for(int i = 0; i < sq.length; i++)
			 for(int j = 0; j < sq[0].length; j++){
				 //if this square is marked
				 if(sq[i][j].Marked()){
					 //clear the mark
					 sq[i][j].clearMark();
				 }
			 }
	}
	
	//judge whether the route is drop-dead halt when they form a square
	private boolean DropDeadHalt(int x, int y){
		int count = 0;
		//if there are three or more square is filled around this square[x][y], this means that the path lost in endless loop
		//right
		if(x < sq[0].length-1 && sq[y][x+1].Filled())
			count++;
		//down
		if(y < sq.length-1 && sq[y+1][x].Filled())
			count++;
		//left
		if(x > 0 && sq[y][x-1].Filled())
			count++;
		//top
		if(y > 0 && sq[y-1][x].Filled())
			count++;
		//top left
		if(y > 0 && x > 0 && sq[y-1][x-1].Filled())
			count++;
		//top right
		if(x < sq[0].length-1 && y > 0 && sq[y-1][x+1].Filled())
			count++;
		//down right
		if(y < sq.length-1 && x < sq[0].length-1 && sq[y+1][x+1].Filled())
			count++;
		//down left
		if(x > 0 && y < sq.length -1 && sq[y+1][x-1].Filled())
			count++;

		//return true if there are 3 around filled squares
		return (count >= 3);
	}
	
	//judge whether there is a path
	private boolean findPath(){
		return findPath(0, 0);
	}
	
	//find path method
	private boolean findPath(int x, int y){
		//set this square to be filled
		sq[y][x].Filled =true;
		
		//the square is lost in drop-dead halt
		if(DropDeadHalt(x, y)){
			//unfilled this square
			sq[y][x].Filled = false;
			//this path is false
			return false;
		}
		
		//when arriving to the ending square
		if(x == sq[0].length-1 && y == sq.length-1){
			//fill the square and return true
			sq[y][x].fillSquare();
			return true;
		}
		//find the path toward right
		if(x < sq.length-1 && !sq[y][x+1].Marked() && !sq[y][x+1].Filled()){
			//if this square has next valid path
			if(findPath(x+1, y)){
				//fill square
				sq[y][x].fillSquare();
				//this path is correct and return true
				return true;
			}
		}
		//find the path toward down
		if(y < sq.length-1 && !sq[y+1][x].Marked() && !sq[y+1][x].Filled()){
			//if this square has next valid path
			if(findPath(x,y+1)){
				//fill square
				sq[y][x].fillSquare();
				//this path is correct and return true
				return true;
			}
		}
		//find the path toward left
		if(x > 0 && !sq[y][x-1].Marked() && !sq[y][x-1].Filled()){
			//if this square has next valid path
			if(findPath(x-1, y)){
				//fill square
				sq[y][x].fillSquare();
				//this path is correct and return true
				return true;
			}
		}
		//find the path toward top
		if(y > 0 && !sq[y-1][x].Marked() && !sq[y-1][x].Filled()){
			//if this square has next valid path
			if(findPath(x, y-1)){
				//fill square
				sq[y][x].fillSquare();
				//this path is correct and return true
				return true;
			}
		}
		//if all of above is not valid, unfilled this square
		sq[y][x].Filled = false;
		//no valid path for this square, return false
		return false;
	}

}