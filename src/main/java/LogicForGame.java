// Raghav Sharma & Nilesh Jain (Partners)
// CS 342 Project 2
// Professor Hallenbeck
// 03/08/2021

//Was created to test logic, could be used to implement in game as well
public class LogicForGame {

    public static boolean checkWin(GameButton[][] arr, int theme){
		boolean isWin = false;
		for (int x = 0; x < 7; x++) {
			for(int y = 5; y >= 0; y--) {
				if(arr[x][y].getClicked()){
					if(x <= 3) {
						isWin = checkH(x, y, arr, theme);
						if(isWin) {
							return true;
						}
					}
					if(y <= 2) {
						isWin = checkV(x, y, arr, theme);
						if(isWin) {
							return true;
						}
					}
					if(y >= 3) {
						isWin = checkD(x, y, arr, theme);
						if(isWin) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}


    public static boolean checkTie(GameButton[][] arr){
		for(int x = 0; x < 7; x++) {
			for(int y = 0; y < 6; y++){
				if(!arr[x][y].getClicked()){
					return false;
				}
			}
		}
		return true;
	}


    //checks for horizontal win
	public static boolean checkH(int x, int y, GameButton[][] arr, int theme){
		int playerNum = arr[x][y].getPlayer();
		if(playerNum == arr[x + 1][y].getPlayer() && 
		playerNum == arr[x + 2][y].getPlayer() &&
		playerNum == arr[x + 3][y].getPlayer()) {
			arr[x][y].setWin(theme);
			arr[x + 1][y].setWin(theme);
			arr[x + 2][y].setWin(theme);
			arr[x + 3][y].setWin(theme);
			return true;
		}
		return false;
	}

	//checks for vertical win
	public static boolean checkV(int x, int y, GameButton[][] arr, int theme){
		int playerNum = arr[x][y].getPlayer();
		if(playerNum == arr[x][y + 1].getPlayer() && 
		playerNum == arr[x][y + 2].getPlayer() &&
		playerNum == arr[x][y + 3].getPlayer()) {
			arr[x][y].setWin(theme);
			arr[x][y + 1].setWin(theme);
			arr[x][y + 2].setWin(theme);
			arr[x][y + 3].setWin(theme);
			return true;
		}
		return false;
	}

	//checks for diagonal win
	public static boolean checkD(int x, int y, GameButton[][] arr, int theme){
		int playerNum = arr[x][y].getPlayer();
		if(x >= 3) {
			if(playerNum == arr[x - 1][y - 1].getPlayer() && 
				playerNum == arr[x - 2][y - 2].getPlayer() &&
				playerNum == arr[x - 3][y - 3].getPlayer()) {
				arr[x][y].setWin(theme);
				arr[x -1][y - 1].setWin(theme);
				arr[x - 2][y - 2].setWin(theme);
				arr[x - 3][y - 3].setWin(theme);
				return true;
			} 
		}
		if(x <= 3) {
			if(playerNum == arr[x + 1][y - 1].getPlayer() && 
				playerNum == arr[x + 2][y - 2].getPlayer() &&
				playerNum == arr[x + 3][y - 3].getPlayer()) {
				arr[x][y].setWin(theme);
				arr[x + 1][y - 1].setWin(theme);
				arr[x + 2][y - 2].setWin(theme);
				arr[x + 3][y - 3].setWin(theme);
				return true;
			}
		}
		return false;
	}

}