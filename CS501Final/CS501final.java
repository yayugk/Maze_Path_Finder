package CS501Final;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.*;

import com.sun.corba.se.spi.orbutil.fsm.Input;

public class CS501final extends Application{	
	Scanner input = new Scanner(System.in);
	public void start(Stage primaryStage) throws Exception{
		System.out.println("This project can let user to input the size of maze and create own maze by marking the block by clicking square."
				+ "/nThe path is starting from the left-up square and ending to right-down square");
		int size = 8;
		//prompt user to define the size of maze
		System.out.println("Please input the size of maze:");
		size = input.nextInt();
		//create a pane
		MazePane pane = new MazePane(size, 50);
		//create a borderPane
		BorderPane borderPane = new BorderPane(pane);
		//add button to hBox
		HBox hBox = new HBox(30, pane.FindPath, pane.ClearPath, pane.ClearMarks);
		//set position to the center
        hBox.setAlignment(Pos.BASELINE_CENTER);
        //set the gap
        hBox.setPadding(new Insets(10));
        //set buttons to the bottom of borderPane
        borderPane.setBottom(hBox);
        //add borderPane to scene
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        //set the window's title
        primaryStage.setTitle("Maze");
        //show result
        primaryStage.show();
    }
	
	public static void main(String[] args){
		Application.launch(args);
	}
}