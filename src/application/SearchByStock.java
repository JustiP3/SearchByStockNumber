package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



public class SearchByStock  extends Application {

	Label text;
	Button click;
	HBox root;
	String stockNumber;

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			displayGreeting(primaryStage);
		}catch (Exception e) {
			e.printStackTrace();
		}
		}


	public void displayGreeting(Stage stage) {
		stage.setTitle("Search By Stock Number");
		text = new Label("This Program Searches user defined text files \n for a user defined \"Stock Number\"");
		click = new Button("Next");
		click.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				promptStockNum(stage);
			}
		});

		root = new HBox();
		root.getChildren().addAll(text, click);
		Scene greeting = new Scene(root, 500, 200);
		stage.setScene(greeting);
		stage.show();

		}
	public void promptStockNum(Stage stage) {
		text.setText("Enter Stock Number: ");
		TextField input = new TextField();
		click.setText("Submit");
		click.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (input != null) {
					stockNumber = input.getText();
					promptFileLoc(stage);
				}
				//else {
				//notification prompt : "enter text"
				//}

			}
		});
		root = new HBox();
		root.getChildren().addAll(text, input, click);
		Scene enterStockNumber = new Scene(root, 500,200);
		stage.setScene(enterStockNumber);
		stage.show();
	}
	public void promptFileLoc(Stage stage){
		click.setText("Select File Location");
		FileChooser buddy = new FileChooser();
		buddy.setTitle("Select File");
		File selectedfile = buddy.showOpenDialog(stage);


		if (selectedfile != null) {
			boolean match = Search(selectedfile);
			if (match == true) {
				text.setText("It's a Match: " + stockNumber);
				HBox poop = new HBox();
				poop.getChildren().add(text);
				Scene endScene = new Scene(poop, 500, 200);
				stage.setScene(endScene);
				stage.show();

			}else{
				text.setText("No Match: " + stockNumber);
				HBox poop = new HBox();
				poop.getChildren().add(text);
				Scene endScene = new Scene(poop, 500, 200);
				stage.setScene(endScene);
				stage.show();
			};
		}

	}
	public boolean Search(File loc) {
		boolean match = false;

		try(BufferedReader br = new BufferedReader(new FileReader(loc))){
				String currentLine;
				while ((currentLine = br.readLine()) != null){
					if (currentLine.equals(stockNumber)) {
						match = true;

						return true;
					}}
				if (match == false) {
					return false;
				}}catch(IOException e) {
					e.printStackTrace();
				}
		return false;

	}

	public static void main(String[] args){
		launch(args);
	}
}
