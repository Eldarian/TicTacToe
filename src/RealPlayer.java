import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RealPlayer implements Player {
    private Mark mark;

    RealPlayer(Mark mark) {
        this.mark = mark;
    }

    public Mark getMark() {
        return mark;
    }



    @Override
    public int[] turn() {
        Scanner inputScanner = new Scanner(System.in);
        inputScanner.useDelimiter("\\s");
        System.out.println("Insert coordinates:");
        int[] result = new int[]{-1, -1};
            do {
                for (int i = 0; i<2; i++) {
                    if (inputScanner.hasNextInt()) {
                        result[i] = inputScanner.nextInt() - 1;
                        System.out.println(result[i]);
                        if (result[i] < 0 || result[i] >= TicTacToe.gridSize) {
                            System.out.println("Invalid input, try again (1-3).");
                            break;
                        }
                    } else {
                        System.out.println("Invalid input, try again (1-3).");
                        inputScanner.next();
                        break;
                    }
                }
            } while (result[0]<0 || result[0]>=TicTacToe.gridSize || result[1]<0 || result[1]>=TicTacToe.gridSize);

        return result;
    }
}
