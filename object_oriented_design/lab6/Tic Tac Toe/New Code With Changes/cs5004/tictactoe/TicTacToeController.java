package cs5004.tictactoe;

/**
 * 
 * This interface represents a controller for Tic Tac Toe game. It is
 * responsible for handling user moves by executing them using the model and
 * conveying move outcomes to the user in some form.
 */
public interface TicTacToeController {
	/**
	 * 
	 * Execute a single game of Tic Tac Toe given a TicTacToe model. When the game
	 * is over, the playGame method ends.
	 * 
	 * @param model a non-null TicTacToe model
	 */
	void playGame(TicTacToe model);
}