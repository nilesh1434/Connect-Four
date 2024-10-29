// Raghav Sharma & Nilesh Jain (Partners)
// CS 342 Project 2
// Professor Haze
// 03/08/2021

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameButton extends Button {
	private int row;
	private int column;
	private int player;
	private boolean clicked;

	GameButton (int row, int column) {
		this.row = row;
		this.column = column;
		this.clicked = false;
		this.player = 0;
	}
	
	//sets color based on theme and if its a valid move
	public boolean setColor(boolean swap, GameButton b, int theme) {
		if(!clicked){
		if(b.getClicked() || this.atBottom()){
		if(swap){
			if(theme == 0){
				this.setStyle("-fx-background-color:red;");
			} else if(theme == 1) {
				this.setStyle("-fx-background-color:black;");
			} else if(theme == 2) {
				this.setStyle("-fx-background-color:firebrick;");
			}
			player = 1;
		} else {
			if(theme == 0){
				this.setStyle("-fx-background-color:yellow;");
			} else if(theme == 1) {
				this.setStyle("-fx-background-color:darkorange;");
			} else if(theme == 2) {
				this.setStyle("-fx-background-color:green;");
			}
			player = 2;
		}
		clicked = true;
		return true;
		}
		return false;
		} else {
			return false;
		}
	}

	//allows for the undo
	public void undoColor(){
		this.setStyle("-fx-background-color:lightgrey;");
		clicked = false;
	}

	//changes to the original theme
	public void setTheme0(){
		if(clicked) {
			if(player == 1) {
				this.setStyle("-fx-background-color:red;");
			} else if(player == 2) {
				this.setStyle("-fx-background-color:yellow;");
			}
		}
		
	}

	//changes to theme1
	public void setTheme1(){
		if(clicked) {
			if(player == 1) {
				this.setStyle("-fx-background-color:black;");
			} else if(player == 2) {
				this.setStyle("-fx-background-color:darkorange;");
			}
		}
	}

	//changes to theme2
	public void setTheme2(){
		if(clicked) {
			if(player == 1) {
				this.setStyle("-fx-background-color:firebrick;");
			} else if(player == 2) {
				this.setStyle("-fx-background-color:green;");
			}
		}
	}

	//getters
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public boolean getClicked() {
		return clicked;
	}

	public void setClicked(boolean val) {
		this.clicked = val;
	}

	public int getPlayer(){
		return player;
	}
	
	//checks to see if the button is at the bottom of the board
	public boolean atBottom() {
		if(column == 5) {
			return true;
		}
		return false;
	}

	public void setWin(int theme){
		if(theme == 0){
			if(player == 1) {
				this.setStyle("-fx-background-color:darkred;");
			} else if(player == 2) {
				this.setStyle("-fx-background-color:gold;");
			}
		} else if(theme == 1){
			if(player == 1) {
				this.setStyle("-fx-background-color:darkslategrey;");
			} else if(player == 2) {
				this.setStyle("-fx-background-color:orangered;");
			}
		} else if(theme == 2){
			if(player == 1) {
				this.setStyle("-fx-background-color:maroon;");
			} else if(player == 2) {
				this.setStyle("-fx-background-color:darkolivegreen;");
			}
		}
	}
};