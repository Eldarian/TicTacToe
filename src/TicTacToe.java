import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicTacToe {
    static TicTacToe game;
    Player player1;
    Player player2;
    private static final int gridSize = 3;
    Cell[][] grid = new Cell[gridSize][gridSize];

    TicTacToe() {
        initGame();
        initGrid();

    }

    //grid methods
    void initGrid() {
        for (int i = 0; i<gridSize; i++) {
            for (int j = 0; j<gridSize; j++) {
                this.grid[i][j] = new Cell(Mark.EMPTY);
            }
        }
    }

    void setCell(Mark mark, int[] choice){
        grid[choice[0]][choice[1]].own = mark;
    }

    Cell[][] getGrid() {
        return grid;
    }

    //How to print grid with unknown size?
    void printGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print(grid[i][j].own);
                if (j!=gridSize-1) System.out.print(" | ");
            }
            System.out.println();
            if(i!=gridSize-1) System.out.println("-".repeat(gridSize*3));
        }
    }


    //gamestart methods
    String input(/*Pattern pattern*/) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();
        //Pattern matching
        reader.close();
        return text;
    }

    private static Player addPlayer(boolean isReal, Mark mark) {
        return isReal ? new RealPlayer(mark) : new ArtificialPlayer(mark);
    }

    private void initGame() {
        /*System.out.println("Single (press s) or Multiplayer (press m)?");
        Player player1 = addPlayer(true, true);
        Player player2;
        while (true) {
            try {
                if (game.input().equals("s")) {
                    player2 = addPlayer(false, false);
                    break;
                } else if (game.input().equals("m")) {
                    player2 = addPlayer(true, false);
                    break;
                } else throw new IOException();
            } catch (IOException e) {
                System.out.println("Invalid input");
            }
        }*/
        player1 = addPlayer(true, Mark.CROSS);
        player2 = addPlayer(true, Mark.NOUGHT);
    }

    private String getTurn() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }


    //Win methods
    private boolean checkDiagonalWin(int i, int j) {
        if(i==j) {
            for (int a = 0; a < gridSize; a++) {
                if (grid[i][i].own != grid[a][a].own) return false;
            }
            return true;
        }
        if(i+j==gridSize-1) {
            for (int row = 0; row < gridSize; row++) {
                if (grid[row][gridSize-1-row].own != grid[i][j].own) return false;
            }
            return true;
        }
        return false;
    }

    private boolean checkVerticalWin(int i, int j) {
        for (int row = 0; row<gridSize; row++) {
            if (grid[row][j].own != grid[i][j].own) return false;
        }
        return true;
    }

    private boolean checkHorizontalWin(int i, int j) {
        for (int col = 0; col<gridSize; col++) {
            if (grid[i][col].own != grid[i][j].own) return false;
        }
        return true;
    }

    private boolean checkFullGrid() {
        for (int i = 0; i<gridSize; i++) {
            for (int j = 0; j<gridSize; j++) {
                if (grid[i][j].own == Mark.EMPTY) return false;
            }
        }
        return true;
    }

    boolean checkWin(int i, int j) {
        return checkDiagonalWin(i, j) || checkHorizontalWin(i, j) || checkVerticalWin(i, j);
    }

    private void gameloop() { //need to find a good way to catch wrong input
        Player currentPlayer = player1;
        while (!checkFullGrid()) {
            try {
                //Before setCell() next conditions should be checked: is input has format "%n %n", is this cell empty
                int[] currentTurn = currentPlayer.turn();
                setCell(currentPlayer.getMark(), currentTurn);
                printGrid();
                if (checkWin(currentTurn[0], currentTurn[1])) {
                    System.out.println(currentPlayer.getMark() + " won!");
                    break;
                }
                currentPlayer = currentPlayer == player1 ? currentPlayer = player2 : player1;
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
