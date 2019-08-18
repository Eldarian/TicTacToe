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
    public int[] turn() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] turnString = reader.readLine().split("\\s");
        return new int[]{Integer.parseInt(turnString[0]) - 1, Integer.parseInt(turnString[1]) - 1};

//        Scanner inputScanner = new Scanner(System.in);
//        System.out.println("Insert coordinates:");
//        int[] result = new int[2];
//        for (int i = 0; i<2; i++) {
//            while (true) {
//                if (inputScanner.hasNextInt()) {
//                    result[i] = inputScanner.nextInt();
//                    if (result[i]>0 && result[i]<=TicTacToe.gridSize) break;
//                    else System.out.println("Invalid input, try again (1-3).");
//                }
//            }
//        }
//        return result;
    }
}
