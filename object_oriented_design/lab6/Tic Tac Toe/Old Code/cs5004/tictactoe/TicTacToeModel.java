package cs5004.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeModel implements TicTacToe {
	
	// add your implementation here
	private int CurrentPlayerTurn = 0;
	
	// Board Size 
	int BOARD_SIZE = 3;
	
	// Create Our Board
	private Player[][] Board = null; 
    private Player Winner = null;
    
    // Inialize the Board
    public TicTacToeModel() {
        Board = new Player[BOARD_SIZE][BOARD_SIZE];
        resetBoard();
        CurrentPlayerTurn = 1;
    }
    
    // Reset the Board to Null Values
    private void resetBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Board[row][col] = null;
            }
        }
    }
	
	
  @Override
  public String toString() {
    List<String> rows = new ArrayList<String>();
    for(int i=0; i<getBoard().length; i++) {
      List<String> rowStrings = new ArrayList<String>();
      for(int j=0; j<getBoard().length; j++) {
        Player p = getMarkAt(i, j);
        if(p == null) {
          rowStrings.add(" ");
        } else {
          rowStrings.add(p.toString());
        }
      }
      rows.add(" " + String.join(" | ", rowStrings));
    }
    return String.join("\n-----------\n", rows);
  }


@Override
public void move(int row, int col) {
	
	// Check if the game is over after the current move
    if (isGameOver()) {
    	throw new IllegalStateException("Game is Over!");
    }

    // Check if the given row and column are within the bounds of the board
    if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
    	throw new IllegalArgumentException("Invalid Arguments");
    }

    // Check if the cell is already occupied
    if (Board[row][col] != null) {
    	throw new IllegalArgumentException("Invalid Move");
    }

    // Set the current player's symbol on the board
    Board[row][col] = getTurn();
       
    // Switch to the other player's turn
    switchTurn();
}


private void switchTurn() {
    if (CurrentPlayerTurn == 1) {
        CurrentPlayerTurn = 0;
    } else {
        CurrentPlayerTurn = 1;
    }
}



@Override
public Player getTurn() {
	
	if(CurrentPlayerTurn == 0) {
		return Player.O;
	}
	
	return Player.X;
}

@Override
public boolean isGameOver() {
    // Check horizontal and vertical lines
    for (int i = 0; i < 3; i++) {
        if (Board[i][0] != null && Board[i][0] == Board[i][1] && Board[i][1] == Board[i][2]) {
            Winner = Board[i][0];
            return true;
        }
        if (Board[0][i] != null && Board[0][i] == Board[1][i] && Board[1][i] == Board[2][i]) {
            Winner = Board[0][i];
            return true;
        }
    }
    // Check diagonal lines
    if (Board[1][1] != null && ((Board[0][0] == Board[1][1] && Board[1][1] == Board[2][2]) || (Board[0][2] == Board[1][1] && Board[1][1] == Board[2][0]))) {
        Winner = Board[1][1];
        return true;
    }
    // Check if there are any empty spaces left
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (Board[i][j] == null) {
                return false;
            }
        }
    }
    return true;
}


@Override
public Player getWinner() {
	if(isGameOver() == true) {
		if(Winner != null) {
			return Winner;
		}
	}
	return null;
}

@Override
public Player[][] getBoard() {
	
	Player [][] newBoard = new Player[3][3];
	
	// Inzialize the Board
	for(int i = 0; i < 3 ; i++) {
		for(int j = 0; j < 3; j++) {
			newBoard[i][j] = Board[i][j];
		}
	}
			
	return newBoard;
}

@Override
public Player getMarkAt(int r, int c) {
	if (r < 0 || r >= BOARD_SIZE || c < 0 || c >= BOARD_SIZE) {
		throw new IllegalArgumentException("Invalid arguments");
	}
	
	return Board[r][c];
}

}
