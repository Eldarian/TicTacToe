import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicTacToe {
    private static final int gridSize = 3;
    static TicTacToe game;
    Player player1;
    Player player2;
    Grid gameGrid;
    int[] lastTurn;

    TicTacToe() {
        initGame();
        gameGrid = new Grid(gridSize);
        //gameGrid.initGrid();

    }

    //gamestart methods

    private static Player addPlayer(boolean isReal, Mark mark) {
        return isReal ? new RealPlayer(mark) : new ArtificialPlayer(mark);
    }

    private void initGame() {
        BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Single (press s) or Multiplayer (press m)?");
        player1 = addPlayer(true, Mark.CROSS);
        while (true) {
            try {
                String input = reader.readLine();
                if (input.equals("s")) {
                    player2 = addPlayer(false, Mark.NOUGHT);
                    break;
                } else if (input.equals("m")) {
                    player2 = addPlayer(true, Mark.NOUGHT);
                    break;
                } else throw new IOException();
            } catch (IOException e) {
                System.out.println("Invalid input");
            }
        }
    }

    private void gameloop() { //need to find a good way to catch wrong input
        Player currentPlayer = player1;
        while (!gameGrid.checkFullGrid()) {
            try {
                //Before setCell() next conditions should be checked: is input has format "%n %n", is this cell empty
                int[] currentTurn = currentPlayer.turn();
                gameGrid.setCell(currentPlayer.getMark(), currentTurn);
                gameGrid.printGrid();
                if (gameGrid.checkWin(currentTurn[0], currentTurn[1])) {
                    System.out.println(currentPlayer.getMark() + " won!");
                    break;
                }
                currentPlayer = currentPlayer == player1 ? currentPlayer = player2 : player1;
                lastTurn = currentTurn;
            } catch (IOException e) {
                System.out.println("IOException!");
            }
        }
    }

    public static void main(String ... args) {
       game = new TicTacToe();
       game.gameloop();
    }
}
