// Raghav Sharma & Nilesh Jain (Partners)
// CS 342 Project 2
// Professor Haze
// 03/08/2021

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javafx.application.Application;
import javafx.stage.Stage;

import org.junit.jupiter.api.BeforeEach;


public class MyTest {
	GameButton[][] arr = new GameButton[7][6];
public static class NonApp extends Application {
	  @Override
	  public void start(Stage primaryStage) throws Exception {
	    // do nothing here since we just need to start the JavaFX toolkit
	  }
	}

	@BeforeEach
	public void initJFX() throws Exception {
	  Thread t = new Thread("JavaFX Application Thread") {
	     public void run() {
	        Application.launch(NonApp.class);
	     }
	  };
	  t.setDaemon(true);
	  t.start();
	}

	@Test
	void DrawTest() {
		for(int x = 0; x < 7; x++) {
			for(int y = 0; y < 6; y++) {
				arr[x][y] = new GameButton(x,y);
			}
		}

	assertEquals(false, LogicForGame.checkWin(arr,0), "There Should Be No Win");
	for(int x = 0; x < 7; x++) {
			for(int y = 0; y < 6; y++) {
				arr[x][y].setClicked(true);
			}
		}
		arr[0][0].setColor(true, arr[0][1], 0);
		arr[1][0].setColor(false, arr[1][1], 0);
		arr[2][0].setColor(true, arr[2][1], 0);
		arr[3][0].setColor(false, arr[3][1], 0);
		arr[4][0].setColor(true, arr[4][1], 0);
		arr[5][0].setColor(false, arr[5][1], 0);
		arr[6][0].setColor(true, arr[6][1], 0);

		arr[0][1].setColor(true, arr[0][2], 0);
		arr[1][1].setColor(false, arr[1][2], 0);
		arr[2][1].setColor(true, arr[2][2], 0);
		arr[3][1].setColor(false, arr[3][2], 0);
		arr[4][1].setColor(true, arr[4][2], 0);
		arr[5][1].setColor(false, arr[5][2], 0);
		arr[6][1].setColor(true, arr[6][2], 0);
		
		arr[0][2].setColor(false, arr[0][3], 0);
		arr[1][2].setColor(true, arr[1][3], 0);
		arr[2][2].setColor(false, arr[2][3], 0);
		arr[3][2].setColor(true, arr[3][3], 0);
		arr[4][2].setColor(false, arr[4][3], 0);
		arr[5][2].setColor(true, arr[5][3], 0);
		arr[6][2].setColor(false, arr[6][3], 0);
		
		arr[0][3].setColor(false, arr[0][4], 0);
		arr[1][3].setColor(true, arr[1][4], 0);
		arr[2][3].setColor(false, arr[2][4], 0);
		arr[3][3].setColor(true, arr[3][4], 0);
		arr[4][3].setColor(false, arr[4][4], 0);
		arr[5][3].setColor(true, arr[5][4], 0);
		arr[6][3].setColor(false, arr[6][4], 0);
		
		arr[0][4].setColor(true, arr[0][5], 0);
		arr[1][4].setColor(false, arr[1][5], 0);
		arr[2][4].setColor(true, arr[2][5], 0);
		arr[3][4].setColor(false, arr[3][5], 0);
		arr[4][4].setColor(true, arr[4][5], 0);
		arr[5][4].setColor(false, arr[5][5], 0);
		arr[6][4].setColor(true, arr[6][5], 0);

		arr[0][5].setColor(true, arr[0][5], 0);
		arr[1][5].setColor(false, arr[1][5], 0);
		arr[2][5].setColor(true, arr[2][5], 0);
		arr[3][5].setColor(false, arr[3][5], 0);
		arr[4][5].setColor(true, arr[4][5], 0);
		arr[5][5].setColor(false, arr[5][5], 0);
		arr[6][5].setColor(true, arr[6][5], 0);
		assertEquals(true, LogicForGame.checkTie(arr), "There Should Be a tie");
	}



	@Test
	void horizontalTest() {
		for(int x = 0; x < 7; x++) {
			for(int y = 0; y < 6; y++) {
				arr[x][y] = new GameButton(x,y);
			}
		}

		arr[2][3].setClicked(true);
		arr[3][3].setClicked(true);
		arr[4][3].setClicked(true);
		arr[5][3].setClicked(true);

		arr[2][3].setColor(false, arr[0][5], 0);
		arr[3][3].setColor(false, arr[0][5], 0);
		arr[4][3].setColor(false, arr[0][5], 0);
		arr[5][3].setColor(false, arr[0][5], 0);

		assertEquals(true, LogicForGame.checkH(2,3,arr,0), "didNotPassHorizontalTest");
		assertEquals(true, LogicForGame.checkWin(arr,0), "didNotPassWinTest");
			
	}
	
	@Test
	void verticalTest() {
		for(int x = 0; x < 7; x++) {
			for(int y = 0; y < 6; y++) {
				arr[x][y] = new GameButton(x,y);
			}
		}

		arr[2][0].setClicked(true);
		arr[2][1].setClicked(true);
		arr[2][2].setClicked(true);
		arr[2][3].setClicked(true);

		arr[2][0].setColor(false, arr[0][5], 0);
		arr[2][1].setColor(false, arr[0][5], 0);
		arr[2][2].setColor(false, arr[0][5], 0);
		arr[2][3].setColor(false, arr[0][5], 0);
		
		assertEquals(true, LogicForGame.checkV(2,0,arr,0), "didNotPassVerticalTest");
		assertEquals(true, LogicForGame.checkWin(arr,0), "didNotPassWinTest");
	}
	
	@Test
	void diagonalTest() {
		for(int x = 0; x < 7; x++) {
			for(int y = 0; y < 6; y++) {
				arr[x][y] = new GameButton(x,y);
			}
		}
		
		arr[3][3].setClicked(true);
		arr[2][2].setClicked(true);
		arr[1][1].setClicked(true);
		arr[0][0].setClicked(true);

		arr[3][3].setColor(false, arr[0][5], 0);
		arr[2][2].setColor(false, arr[0][5], 0);
		arr[1][1].setColor(false, arr[0][5], 0);
		arr[0][0].setColor(false, arr[0][5], 0);
		
		assertEquals(true, LogicForGame.checkD(2,3,arr,0), "didNotPassDiagonalTest");
		assertEquals(true, LogicForGame.checkWin(arr,0), "didNotPassWinTest");

		for(int x = 0; x < 7; x++) {
			for(int y = 0; y < 6; y++) {
				arr[x][y] = new GameButton(x,y);
			}
		}

		arr[3][3].setClicked(true);
		arr[4][2].setClicked(true);
		arr[5][1].setClicked(true);
		arr[6][0].setClicked(true);

		arr[3][3].setColor(false, arr[0][5], 0);
		arr[4][2].setColor(false, arr[0][5], 0);
		arr[5][1].setColor(false, arr[0][5], 0);
		arr[6][0].setColor(false, arr[0][5], 0);

		assertEquals(true, LogicForGame.checkD(3,3,arr,0), "didNotPassDiagonalTest");
		assertEquals(true, LogicForGame.checkWin(arr,0), "didNotPassWinTest");
	}

}


