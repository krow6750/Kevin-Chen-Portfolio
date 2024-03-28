/**
 * Name: Kevin Chen
 * Assignment: Lab 8
 * Date: 04/17/2023
 * Notes: Class to run interactive TicTacToe game on the console
 */

package cs5004.tictactoe;

import java.io.InputStreamReader;

public class Main {
  /**
   * Entry point for running a Tic Tac Toe game interactively on the console.
   * Initializes a new TicTacToeConsoleController with System.in and System.out,
   * and plays the game using a new TicTacToeModel.
   */
  public static void main(String[] args) {
    TicTacToeConsoleController consoleController = new TicTacToeConsoleController(
        new InputStreamReader(System.in), System.out);
    TicTacToeModel ticTacToeModel = new TicTacToeModel();
    consoleController.playGame(ticTacToeModel);
  }
}
