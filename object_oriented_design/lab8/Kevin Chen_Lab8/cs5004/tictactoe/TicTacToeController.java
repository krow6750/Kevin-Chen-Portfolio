/**
 * Name: Kevin Chen
 * Assignment: Lab 8
 * Date: 04/17/2023
 * Notes: Interface representing TicTacToe controller, handling user moves via the model.
 */

package cs5004.tictactoe;

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