import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class ArtificialPlayer implements Player {
    private Mark mark;

    ArtificialPlayer(Mark mark) {
        this.mark = mark;
    }

    public int[] turn() {
        Grid testGrid = new Grid(TicTacToe.game.gameGrid.getGrid());
        ArrayList<ArrayList<Integer>> possibles = new ArrayList<ArrayList<Integer>>();

        //Collecting possible turns
        for (int i = 0; i < testGrid.getGrid().length; i++) {
            for (int j = 0; j < testGrid.getGrid().length; j++) {
                if (testGrid.getGrid()[i][j].own == Mark.EMPTY) possibles.add(new ArrayList<>(Arrays.asList(i, j)));
            }
        }

        //Trying to win
        for (ArrayList<Integer> pair : possibles) {
            int[] cell = new int[]{pair.get(0), pair.get(1)};
            testGrid.setCell(mark, cell);
            if (testGrid.checkWin(cell[0], cell[1])) return cell;
            testGrid.setCell(Mark.EMPTY, cell);
        }

        //Defence
        for (ArrayList<Integer> pair : possibles) {
            int[] cell = new int[]{pair.get(0), pair.get(1)};
            testGrid.setCell(mark == Mark.CROSS ? Mark.NOUGHT : Mark.CROSS, cell);
            if (testGrid.checkWin(cell[0], cell[1])) return cell;
            testGrid.setCell(Mark.EMPTY, cell);
        }

        //Center
        if (possibles.contains(Arrays.asList(1, 1))) return new int[]{1, 1};

        //Corners
        for (ArrayList<Integer> pair : possibles) {
            if(!pair.contains(1)) return new int[]{pair.get(0), pair.get(1)};
        }

        //Random turn
        ArrayList<Integer> random=possibles.get(ThreadLocalRandom.current().nextInt(0, possibles.size()));
        return new int[]{random.get(0), random.get(1)};
    }

    public Mark getMark() {
        return mark;
    }
}
