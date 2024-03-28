package cs5004.tictactoe;

import java.io.IOException;
import java.util.Scanner;

public class TicTacToeConsoleController implements TicTacToeController {

    private final Scanner scanner;
    private final Appendable gameLog;

    public TicTacToeConsoleController(Readable input, Appendable gameLog) {
        this.scanner = new Scanner(input);
        this.gameLog = gameLog;
    }

    @Override
    public void playGame(TicTacToe game) {
        try {
            while (!game.isGameOver()) {
                logGameState(game);
                logPrompt(game.getTurn());

                String input = scanner.nextLine().trim();
                if (input.equals("q")) {
                    logGameQuit(game);
                    return;
                }

                try {
                    String[] coordinates = input.split(" ");
                    int row = Integer.parseInt(coordinates[0]);
                    int col = Integer.parseInt(coordinates[1]);
                    game.move(row, col);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                    logInvalidInput();
                    logGameQuit(game);
                    return;
                }
            }
            logGameState(game);
            logGameResult(game);
        } catch (IOException e) {
            throw new IllegalStateException("Error writing to game log", e);
        } finally {
            scanner.close();
        }
    }

    private void logGameState(TicTacToe game) throws IOException {
        gameLog.append(game.toString()).append("\n");
    }

    private void logPrompt(TicTacToe.Player player) throws IOException {
        gameLog.append("Enter a move for ").append(player.toString()).append(": ");
    }

    private void logGameQuit(TicTacToe game) throws IOException {
        gameLog.append("Game quit! Ending game state:\n");
        logGameState(game);
    }

    private void logInvalidInput() throws IOException {
        gameLog.append("Invalid input entered:\n");
    }

    private void logGameResult(TicTacToe game) throws IOException {
        if (game.getWinner() == null) {
            gameLog.append("Game is over! Tie game.");
        } else {
            gameLog.append("Game is over! ").append(game.getWinner().toString()).append(" wins.");
        }
    }
}
