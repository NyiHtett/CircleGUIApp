/**
Name: Nyi Htet
Course: CS125-01
Lab#: Lab Four
Lab due date: 11/17/2022
Submission Date: 11/8/2022
Description: CircleApp driver class
update: second commit
*/

import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CircleApp extends Application{   //driver class
	private TextField radiusTextField;  //text field for user input radius
	private Label radiusResult;         //label for radius
	private Label perimeterResult;      //label for perimeter
	private Label areaResult;           //label for area

	public static void main(String[] args) {   //main method
		launch(args);  
	} //end of main method

	@Override
	public void start(Stage primaryStage) throws Exception { //start method
		Label promptLabel = new Label("Radius");  //asking radius
		radiusTextField = new TextField();        //text field for radius
		radiusResult = new Label();               //label for radius result
		perimeterResult = new Label();            //label for perimeter result
		areaResult = new Label();                 //label for area result
		
		Button submit = new Button("Submit");	  //button for calculation
		submit.setOnAction(new SubmitButtonHandler());  //register the event handler
		
		Button clear = new Button("Clear");   //button for clear
		clear.setOnAction(new ClearButtonHandler());  //register the event handler
		
		Button exit = new Button("Exit");   //button for exit
		exit.setOnAction(new ExitButtonHandler());   //register the event handler
			
		HBox hbox = new HBox(10,promptLabel,radiusTextField); //first horizontal control layout
		hbox.setAlignment(Pos.CENTER);   //aligning to the center
		hbox.setPadding(new Insets(10)); //setting padding
		HBox buttonBox = new HBox(10, submit, clear, exit);   //second horizontal control layout
		buttonBox.setAlignment(Pos.CENTER);   //aligning to the center
		buttonBox.setPadding(new Insets(10)); //setting padding
		VBox vbox = new VBox(10, hbox, buttonBox, radiusResult, perimeterResult, areaResult);  //vertical control layout
		vbox.setAlignment(Pos.CENTER_LEFT);   //aligning to the left side of center
		vbox.setPadding(new Insets(10));      //setting padding
		Scene scene = new Scene(vbox,300,200);  //creating scene, assigning vbox layout and size of scene
		
		primaryStage.setScene(scene);        //setting the scene
		primaryStage.setTitle("Circle App"); //setting the title
		primaryStage.show();    //open the window
	} //end of method
	
	class SubmitButtonHandler implements EventHandler<ActionEvent>{  //Event handler class for submit button
		@Override
		public void handle(ActionEvent event) {  //handle method
			try {                             //try block
				double radius = Double.parseDouble(radiusTextField.getText());  //assign radius
				if(radius < 0) {              //if radius is negative 
					throw new NegativeDoubleException(radius);   //throw custom-designed exception
				}     //end of if-statement
				double perimeter = 2*Math.PI*radius;    //calculate perimeter
				double area = Math.PI*radius*radius;    //calculate area
				radiusResult.setText(String.format("radius\t\t: %,.2f", radius));    //show radius
				perimeterResult.setText(String.format("perimeter\t\t: %,.2f", perimeter));  //show perimeter
				areaResult.setText(String.format("area\t\t\t: %,.2f", area));    //show area
			}     //end of try block
			catch(NullPointerException e) {    //catch block in case user didn't assign any input
				JOptionPane.showMessageDialog(null, "must enter a positive real number!");  //show message using JOptionPane
			}    //end of catch block
			catch(NumberFormatException e) {   //catch block in case user put letters
				JOptionPane.showMessageDialog(null, "A positive real number, not letter");  //show message using JOptionPane
				radiusTextField.setText(null);  //set radius text field to null
				radiusResult.setText(null);     //set radius result label to null
				perimeterResult.setText(null);    //set perimeter result label to null
				areaResult.setText(null);    //set area result label to null
			}   //end of catch block
			catch(NegativeDoubleException e) {   //catch block in case user assign negative data
				JOptionPane.showMessageDialog(null, e.getMessage()); //show message using JOptionPane
				radiusTextField.setText(null);  //set radius text field to null
				radiusResult.setText(null);    //set radius result label to null
				perimeterResult.setText(null);   //set perimeter result label to null
				areaResult.setText(null);   //set area result label to null
			}  //end of catch block
		}  //end of handle method
	}   //end of event handler class
	
	class ClearButtonHandler implements EventHandler<ActionEvent>{  //Event handler class for clear button
		@Override
		public void handle(ActionEvent event) {   //handle method
			radiusTextField.setText(null);      //set radius text field to null
			radiusResult.setText(null);   //set radius result label to null
			perimeterResult.setText(null);  //set perimeter result label to null
			areaResult.setText(null);   //set area result label to null
		}	//end of handle method
	}  //end of event handler class
	
	class ExitButtonHandler implements EventHandler<ActionEvent>{  //Event handler class for exit button
		@Override
		public void handle(ActionEvent event) {   //handle method
		    Platform.exit();  //close the window
		}    //end of handle method
	}   //end of event handler class
	
} //end of class

