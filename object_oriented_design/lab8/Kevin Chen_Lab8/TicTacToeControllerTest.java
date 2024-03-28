import org.junit.Test;

import cs5004.tictactoe.FailingAppendable;
import cs5004.tictactoe.TicTacToe;
import cs5004.tictactoe.TicTacToeModel;

import static org.junit.Assert.assertEquals;

import cs5004.tictactoe.TicTacToeConsoleController;
import cs5004.tictactoe.TicTacToeController;
import java.io.StringReader;
import java.util.Arrays;

/**
 * Test cases for the tic tac toe controller, using mocks for readable and
 * appendable.
 */
public class TicTacToeControllerTest {


	@Test
	public void testSingleValidMove() {
		TicTacToe m = new TicTacToeModel();
		StringBuilder gameLog = new StringBuilder();
		TicTacToeController c = new TicTacToeConsoleController(new StringReader("1 1 q"), gameLog);
		c.playGame(m);
		assertEquals("   |   |  \n" + "-----------\n" + "   |   |  \n" + "-----------\n" + "   |   |  \n"
				+ "Enter a move for X:\n" + "   |   |  \n" + "-----------\n" + "   | X |  \n" + "-----------\n"
				+ "   |   |  \n" + "Enter a move for O:\n" + "Game quit! Ending game state:\n" + "   |   |  \n"
				+ "-----------\n" + "   | X |  \n" + "-----------\n" + "   |   |  \n", gameLog.toString());
	}

	@Test
	public void testBogusInputAsRow() {
		TicTacToe m = new TicTacToeModel();
		StringReader input = new StringReader("!#$ 2 q");
		StringBuilder gameLog = new StringBuilder();
		TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
		c.playGame(m);
		// split the output into an array of lines
		String[] lines = gameLog.toString().split("\n");
		// check that it's the correct number of lines
		assertEquals(13, lines.length);
		// check that the last 6 lines are correct
		String lastMsg = String.join("\n", Arrays.copyOfRange(lines, lines.length - 6, lines.length));
		assertEquals("Game quit! Ending game state:\n" + "   |   |  \n" + "-----------\n" + "   |   |  \n"
				+ "-----------\n" + "   |   |  ", lastMsg);
		// note no trailing \n here, because of the earlier split
	}

	@Test
	public void testTieGame() {
		TicTacToe m = new TicTacToeModel();
		// note the entire sequence of user inputs for the entire game is in this one
		// string:
		StringReader input = new StringReader("1 1 0 0 2 2 0 1 0 2 1 2 1 0 2 0 2 1");
		StringBuilder gameLog = new StringBuilder();
		TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
		System.out.print(gameLog);
		c.playGame(m);
		String[] lines = gameLog.toString().split("\n");
		assertEquals(60, lines.length);
		assertEquals("Game is over! Tie game.", lines[lines.length - 1]);
	}

	@Test(expected = IllegalStateException.class)
	public void testFailingAppendable() {
		TicTacToe m = new TicTacToeModel();
		StringReader input = new StringReader("1 1 0 0 2 2 0 1 0 2 1 2 1 0 2 0 2 1");
		Appendable gameLog = new FailingAppendable();
		TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
		c.playGame(m);
	}

}
