/**
 * Name: Kevin Chen
 * Assignment: Lab 8
 * Date: 04/17/2023
 * Notes: Class to display TicTacToe game in console, prompting the user to make a move. Takes a Readable
 *        object for move inputs and Appendable object for output. Implements the playGame method to manage
 *        game logic.
 */

package cs5004.tictactoe;

import java.io.IOException;
import java.util.Scanner;

public class TicTacToeConsoleController implements TicTacToeController {

	private final Appendable output;
	private final Readable input;

	public TicTacToeConsoleController(Readable input, Appendable output) {
		this.input = input;
		this.output = output;
	}

	@Override
	public void playGame(TicTacToe game) {
		try (Scanner scanner = new Scanner(input)) {
			try {
				// The Game Starts Here
				while (!game.isGameOver()) {
					output.append(game.toString()).append("\n").append("Enter a move for ")
							.append(game.getTurn().toString()).append(":\n");
					char inputChar = scanner.next().charAt(0);
					if (inputChar == 'q') {
						output.append("Game quit! Ending game state:\n").append(game.toString()).append("\n");
						return;
					}
					int row, col;
					try {
						row = Character.getNumericValue(inputChar);
						col = Character.getNumericValue(scanner.next().charAt(0));
						game.move(row, col);
					} catch (IllegalArgumentException e) {
						output.append("Invalid Input Entered:\n").append("Game quit! Ending game state:\n")
								.append(game.toString());
						return;
					}
				}

				// Game is Over Now, We Just Display the Outcome
				output.append(game.toString()).append("\n");
				output.append("Game is over! ");
				output.append(game.getWinner() == null ? "Tie game." : game.getWinner().toString() + " wins.");
			} catch (IOException e) {
				// An Error Occurred While Writing to the Console
				throw new IllegalStateException("Unable to append to game log.");
			}
		}

	}
}
