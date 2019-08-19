import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicTacToe {
    private static final int gridSize = 3;
    static TicTacToe game;
    Player player1;
    Player player2;
    Grid gameGrid;
    int[] lastTurn;
    GameStates gameState=GameStates.WAITNEWGAME;

    TicTacToe() {
        initGame();
    }

    //gamestart methods

    private static Player addPlayer(boolean isReal, Mark mark) {
        return isReal ? new RealPlayer(mark) : new ArtificialPlayer(mark);
    }

    private void initGame() {
        gameGrid = new Grid(gridSize);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Single (press s) or Multiplayer (press m)?");
        Random r = new Random();
        player1 = addPlayer(true, r.nextBoolean() ? Mark.CROSS:Mark.NOUGHT);
        while (true) {
            try {
                String input = reader.readLine();
                if (input.equals("s")) {
                    player2 = addPlayer(false, player1.getMark()==Mark.CROSS ? Mark.NOUGHT : Mark.CROSS);
                    gameState = GameStates.PLAYING;
                    break;
                } else if (input.equals("m")) {
                    player2 = addPlayer(false, player1.getMark()==Mark.CROSS ? Mark.NOUGHT : Mark.CROSS);
                    gameState = GameStates.PLAYING;
                    break;
                } else throw new IOException();
            } catch (IOException e) {
                System.out.println("Invalid input");
            }
        }
        gameState = GameStates.PLAYING;
    }

    private void playingLoop() { //need to find a good way to catch wrong input
        Player currentPlayer = player1.getMark()==Mark.CROSS ? player1 : player2;
        while (gameState == GameStates.PLAYING) {
            try {
                if (currentPlayer instanceof RealPlayer) {
                    System.out.println("Make your turn, "+ currentPlayer.getMark() + ". Pattern: n n, n={1-3}");
                }
                //Before setCell() next conditions should be checked: is input has format "%n %n", is this cell empty
                int[] currentTurn = currentPlayer.turn();
                gameGrid.setCell(currentPlayer.getMark(), currentTurn);
                gameGrid.printGrid();
                if (gameGrid.checkWin(currentTurn[0], currentTurn[1])) {
                    gameState = currentPlayer.getMark()==Mark.CROSS ? GameStates.CROSSESWON : GameStates.NOUGHTSWON;
                    break;
                }
                if (gameGrid.checkFullGrid()) gameState=GameStates.DRAW;
                currentPlayer = currentPlayer == player1 ? currentPlayer = player2 : player1;
                lastTurn = currentTurn;
            } catch (IOException e) {
                System.out.println("IOException!");
            }
        }
    }

    void statesSwitcher() {
        while (true) {
            switch (gameState) {
                case WAITNEWGAME:
                    initGame();
                    break;
                case PLAYING:
                    playingLoop();
                    break;
                case DRAW:
                    System.out.println("It's a draw!");
                    gameState = GameStates.WAITNEWGAME;
                    break;
                case CROSSESWON:
                    System.out.println("Crosses won!");
                    gameState = GameStates.WAITNEWGAME;
                    break;
                case NOUGHTSWON:
                    System.out.println("Noughts won!");
                    gameState = GameStates.WAITNEWGAME;
                    break;
            }
        }
    }

    public static void main(String ... args) {
       game = new TicTacToe();
       game.statesSwitcher();
    }
}
