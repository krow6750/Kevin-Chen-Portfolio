package cs5004.tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TicTacToeModel implements TicTacToe {

	// Here we make our GameBoard
	private Player[][] GameBoard = null;
	private Player Winner = null;

	// Set the First Player
	private int CurrentPlayerTurn = 1;

	// We define the Size of the GameBoard
	int boardSize = 3;

	// Initialize the GameBoard
	public TicTacToeModel() {
		GameBoard = new Player[boardSize][boardSize];
		resetBoard();
	}

	// Reset the GameBoard to Null Values
	private void resetBoard() {
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				GameBoard[row][col] = null;
			}
		}
	}

	@Override
	public String toString() {
		// Using Java stream API to save code:
		return Arrays.stream(getBoard()).map(row -> " "
				+ Arrays.stream(row).map(p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
				.collect(Collectors.joining("\n-----------\n"));
	}

	@Override
	public void move(int row, int col) {

		// Check if the game is over after the current move
		if (isGameOver()) {
			throw new IllegalStateException("Game is Over!");
		}

		// Check if the given row and column are within the bounds of the board
		if (row < 0 || row >= boardSize || col < 0 || col >= boardSize) {
			throw new IllegalArgumentException("Invalid Arguments");
		}

		// Check if the cell is already occupied
		if (GameBoard[row][col] != null) {
			throw new IllegalArgumentException("Invalid Move");
		}

		// Set the current player's symbol on the board
		GameBoard[row][col] = getTurn();

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

		if (CurrentPlayerTurn == 0) {
			return Player.O;
		}

		return Player.X;
	}

	@Override
	public boolean isGameOver() {
		// Check horizontal and vertical lines
		for (int i = 0; i < 3; i++) {
			if (GameBoard[i][0] != null && GameBoard[i][0] == GameBoard[i][1] && GameBoard[i][1] == GameBoard[i][2]) {
				Winner = GameBoard[i][0];
				return true;
			}
			if (GameBoard[0][i] != null && GameBoard[0][i] == GameBoard[1][i] && GameBoard[1][i] == GameBoard[2][i]) {
				Winner = GameBoard[0][i];
				return true;
			}
		}
		// Check diagonal lines
		if (GameBoard[1][1] != null && ((GameBoard[0][0] == GameBoard[1][1] && GameBoard[1][1] == GameBoard[2][2])
				|| (GameBoard[0][2] == GameBoard[1][1] && GameBoard[1][1] == GameBoard[2][0]))) {
			Winner = GameBoard[1][1];
			return true;
		}
		// Check if there are any empty spaces left
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (GameBoard[i][j] == null) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public Player getWinner() {
		return Winner;
	}

	@Override
	public Player[][] getBoard() {
		Player[][] copy = new Player[boardSize][boardSize];
		for (int i = 0; i < GameBoard.length; i++) {
			for (int j = 0; j < GameBoard[i].length; j++) {
				copy[i][j] = GameBoard[i][j];
			}
		}
		return copy;
	}

	@Override
	public Player getMarkAt(int r, int c) {
		if (r < 0 || r >= boardSize || c < 0 || c >= boardSize) {
			throw new IllegalArgumentException("Invalid arguments");
		}

		return GameBoard[r][c];
	}

}
