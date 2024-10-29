// Raghav Sharma & Nilesh Jain (Partners)
// CS 342 Project 2
// Professor Haze
// 03/08/2021

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Stack;
import java.util.concurrent.TimeUnit;
import javafx.animation.PauseTransition;

public class JavaFXTemplate extends Application {
	//Variables used in this class
	private EventHandler<ActionEvent> myHandler;
	private GameButton[][] arr;
	private GridPane G;
	private Stack<GameButton> undoList; //accounts for each move made in order
	private boolean swap; //swaps between player 1 and 2
	private Button themes;
	private Button OriginalTheme;
	private Button Halloween;
	private Button Christmas;
	private Button gameplay;
	private Button Undo;
	private Button startGame;
	private Button options;
	private Button HowToPlay;
	private Button NewGame;
	private Button Exit;
	private HBox menu; //sets up the menu bar
	private Scene scene;
	//These VBox and HBox allows us to combine buttons/other boxes together
	private VBox start;
	private VBox inGame;
	private VBox gameplayVbox;
	private VBox themesVbox;
	private VBox optionsVbox;
	private VBox tempVbox;
	private HBox tempHbox;
	private HBox themeHbox;
	private HBox optionsHbox;
	private GameButton undoButton;
	private BorderPane bp; //The main border
	private int theme; //tells which theme # it is
	private Image bg; //The image used for the background
	private ImageView bgView;
	private Button backButton;
	private TextField playerTurn;
	private TextField validTurn;
	private VBox turn;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// My Handler which checks each button to see if its a valid move as well as change its color
		myHandler = new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent e) {
			GameButton button = (GameButton) e.getSource();
			GameButton button2;
			
			if(!button.atBottom()) {
				button2 = arr[button.getRow()][button.getColumn()+1];
			} else {
				button2 = button;
			}
			if(button.setColor(swap, button2, theme)) {
			System.out.println(GridPane.getColumnIndex(button) + " " + GridPane.getRowIndex(button));
				if(swap == true) {
					validTurn.setText("Player 1 moved " + GridPane.getColumnIndex(button) + "," + GridPane.getRowIndex(button) + ".");
					swap = false;
					playerTurn.setText("Player 2's Turn");
				} else {
					validTurn.setText("Player 2 moved " + GridPane.getColumnIndex(button) + "," + GridPane.getRowIndex(button) + ".");
					swap = true;
					playerTurn.setText("Player 1's Turn");
				}
				undoList.push(button);
				
				PauseTransition pause = new PauseTransition(Duration.seconds(3));

				if(LogicForGame.checkWin(arr, theme)) {
					disableAll(arr);
					pause.play();
					pause.setOnFinished(t->{
					TextField t1 = new TextField();
					t1.setText("Player " + button.getPlayer() + " Wins");
					VBox WinScreen = new VBox(15, t1, startGame, Exit);
					bg = new Image("original1.jpg");
					bgView = new ImageView(bg);
					bp.getChildren().addAll(bgView);
					bp.setTop(WinScreen);
					bp.setBottom(null);
					primaryStage.setScene(scene);
				});
				} else if(LogicForGame.checkTie(arr)) {
					disableAll(arr);
					pause.play();
					pause.setOnFinished(t->{
					TextField t1 = new TextField();
					t1.setText("There Was A Tie");
					VBox WinScreen = new VBox(15, t1, startGame, Exit);
					bg = new Image("original1.jpg");
					bgView = new ImageView(bg);
					bp.getChildren().addAll(bgView);
					bp.setTop(WinScreen);
					bp.setBottom(null);
					primaryStage.setScene(scene);
					});
				}
			} else {
				if(swap == true) {
					validTurn.setText("Player one moved to " +
					GridPane.getColumnIndex(button) + "," + GridPane.getRowIndex(button) +
					". This is NOT a valid move. Player one pick again.");
					System.out.println("Player one moved to " +
					GridPane.getColumnIndex(button) + "," + GridPane.getRowIndex(button) +
					". This is NOT a valid move. Player one pick again.");
				} else {
					validTurn.setText("Player two moved to " +
					GridPane.getColumnIndex(button) + "," + GridPane.getRowIndex(button) +
					". This is NOT a valid move. Player two pick again.");
					System.out.println("Player two moved to " +
					GridPane.getColumnIndex(button) + "," + GridPane.getRowIndex(button) +
					". This is NOT a valid move. Player two pick again.");
				}
				}
			}
		};

		//declares each variable and starts the main menu
		bg = new Image("original1.jpg");
		bgView = new ImageView(bg);
		bp = new BorderPane();
		theme = 0;
		backButton = new Button("<- Go Back");
		startGame = new Button("Start Game");
		themes = new Button("Themes");
		gameplay = new Button("Gameplay");
		options = new Button("Options");
		Undo = new Button("Reverse Move");
		OriginalTheme = new Button("Original Theme");
		Halloween = new Button("Halloween Theme");
		Christmas = new Button("Christmas Theme");
		HowToPlay = new Button("How To Play");
		NewGame = new Button("New Game");
		Exit = new Button("Exit");
		playerTurn = new TextField();
		validTurn = new TextField();
		turn = new VBox(5, playerTurn, validTurn);
		declareButtons(primaryStage);
		menu = new HBox(gameplay, themes, options);
		menu.setAlignment(Pos.TOP_CENTER);
		start = new VBox(startGame);
		start.setAlignment(Pos.CENTER);
		undoList = new Stack();
		primaryStage.setTitle("Connect 4");
		arr = new GameButton[7][6];
		swap = true;
		G = new GridPane();
		G.setVgap(5); 
     	G.setHgap(5);
		G.setAlignment(Pos.CENTER);
		addGrid(G);
		bp.getChildren().addAll(bgView);
		bp.setCenter(start);
		scene = new Scene(bp, 700, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// shows the Game Board
	public void addGrid(GridPane grid) {
	GameButton b1;
		for(int x = 0; x < 7; x++) {
			for(int y = 0; y < 6; y++) {
				b1 = new GameButton(x,y);
				b1.setMinSize(40, 40);
				b1.setStyle("-fx-background-color:lightgrey;");
				b1.setOnAction(myHandler);
				arr[x][y] = b1;
				grid.add(arr[x][y], x, y);
			}
		}
	}

	public void disableAll(GameButton[][] arr){
		for(int x = 0; x < 7; x++) {
			for(int y = 0; y < 6; y++) {
				arr[x][y].setClicked(true);
			}
		}
	}

	//gives each button a function
	public void declareButtons(Stage primaryStage){

		// Start game option
		startGame.setOnAction(e->{
			
			undoList.clear();
			validTurn.setText("");
			swap = true;
			addGrid(G);
			inGame = new VBox(200, menu, G);
			if(theme == 0) {
				bg = new Image("original.jpg");
			} else if(theme == 1) {
				bg = new Image("halloween1.jpg");
			} else if(theme == 2) {
				bg = new Image("christmas1.jpg");
			}
			playerTurn.setText("Player 1's Turn");
			bgView = new ImageView(bg);
			bp.getChildren().addAll(bgView);
			bp.setTop(inGame);
			bp.setBottom(turn);
			primaryStage.setScene(scene);
		});

		// Reverse button
		Undo.setOnAction(e->{
			if(!undoList.empty()) {
			undoButton = undoList.pop();
			undoButton.undoColor();
			if(swap == true) {
				if(!undoList.empty()) {
					undoButton = undoList.peek();
					validTurn.setText("Player 1 moved " + GridPane.getColumnIndex(undoButton) + "," + GridPane.getRowIndex(undoButton) + ".");
				} else {
					validTurn.setText("");
				}
					swap = false;
					playerTurn.setText("Player 2's Turn");
				} else {
					if(!undoList.empty()) {
					undoButton = undoList.peek();
					validTurn.setText("Player 2 moved " + GridPane.getColumnIndex(undoButton) + "," + GridPane.getRowIndex(undoButton) + ".");
					} else {
						validTurn.setText("");
					}
					swap = true;
					playerTurn.setText("Player 1's Turn");
				}
			}
		});

		// Gameplay button
		gameplay.setOnAction(e->{
			tempVbox = new VBox(menu, Undo);
			tempVbox.setAlignment(Pos.TOP_CENTER);
			gameplayVbox = new VBox(175, tempVbox, G);		
			bp.setTop(gameplayVbox);
			primaryStage.setScene(scene);
		});
		
		// Themes button
		themes.setOnAction(e->{
			themeHbox = new HBox(OriginalTheme, Halloween, Christmas);
			themeHbox.setAlignment(Pos.TOP_CENTER);
			tempVbox = new VBox(menu, themeHbox);
			tempVbox.setAlignment(Pos.TOP_CENTER);
			themesVbox = new VBox(175, tempVbox, G);
			bp.setTop(themesVbox);
			primaryStage.setScene(scene);
		});
		
		// Options button
		options.setOnAction(e->{
			optionsHbox = new HBox(HowToPlay, NewGame, Exit);
			optionsHbox.setAlignment(Pos.TOP_CENTER);
			tempVbox = new VBox(menu, optionsHbox);
			tempVbox.setAlignment(Pos.TOP_CENTER);
			optionsVbox = new VBox(175, tempVbox, G);
			bp.setTop(optionsVbox);
			primaryStage.setScene(scene);
		});

		// if Halloween theme is pressed
		Halloween.setOnAction(e->{
			theme = 1;
			for(int x = 0; x < 7; x++) {
				for(int y = 0; y < 6; y++) {
					arr[x][y].setTheme1();
				}
			}
			bg = new Image("halloween1.jpg");
			bgView = new ImageView(bg);
			bp = new BorderPane();
			bp.getChildren().addAll(bgView);
			bp.setTop(themesVbox);
			bp.setBottom(turn);
			scene = new Scene(bp, 700, 700);
			primaryStage.setScene(scene);
		});

		// if Christmas theme is pressed
		Christmas.setOnAction(e->{
			theme = 2;
			for(int x = 0; x < 7; x++) {
				for(int y = 0; y < 6; y++) {
					arr[x][y].setTheme2();
				}
			}
			bg = new Image("christmas1.jpg");
			bgView = new ImageView(bg);
			bp = new BorderPane();
			bp.getChildren().addAll(bgView);
			bp.setTop(themesVbox);
			bp.setBottom(turn);
			scene = new Scene(bp, 700, 700);
			primaryStage.setScene(scene);
		});

		// If original theme is pressed
		OriginalTheme.setOnAction(e->{
			theme = 0;
			for(int x = 0; x < 7; x++) {
				for(int y = 0; y < 6; y++) {
					arr[x][y].setTheme0();
				}
			}
			bg = new Image("original.jpg");
			bgView = new ImageView(bg);
			bp = new BorderPane();
			bp.getChildren().addAll(bgView);
			bp.setTop(themesVbox);
			bp.setBottom(turn);
			scene = new Scene(bp, 700, 700);
			primaryStage.setScene(scene);
		});
		
		// If how to play is pressed
		HowToPlay.setOnAction(e->{
			bg = new Image("connect4rules.jpg");
			bgView = new ImageView(bg);
			bp.getChildren().addAll(bgView);
			bp.setBottom(backButton);
			primaryStage.setScene(scene);
		});

		// Goes back to the Game screen
		backButton.setOnAction(e->{
			if(theme == 0){
				bg = new Image("original1.jpg");
			} else if(theme == 1){
				bg = new Image("halloween1.jpg");
			} else if(theme == 2) {
				bg = new Image("christmas1.jpg");
			}
			bgView = new ImageView(bg);
			bp.getChildren().addAll(bgView);
			inGame = new VBox(200, menu, G);
			bp.setTop(inGame);
			bp.setBottom(turn);
			primaryStage.setScene(scene);
		});
		
		// Starts a new game
		NewGame.setOnAction(e->{
			while(!undoList.empty()){
				undoList.pop();
			}
			validTurn.setText("");
			playerTurn.setText("Player 1's Turn");
			addGrid(G);
			swap = true;
			bp.setLeft(null);
		});

		// Exits the application
		Exit.setOnAction(e->{
			System.exit(0);
		});
	}
};