class Grid {
    private final int gridSize;
    private Cell[][] grid;

    Grid(int gridSize) {
        this.gridSize = gridSize;
        this.grid = new Cell[this.gridSize][this.gridSize];
        this.initGrid();
    }

    Grid(Cell[][] grid) {
        this.gridSize = grid.length;
        this.grid = new Cell[gridSize][gridSize];
        this.initGrid(grid);
    }

    private void initGrid() {
        for (int i = 0; i<gridSize; i++) {
            for (int j = 0; j<gridSize; j++) {
                this.grid[i][j] = new Cell(Mark.EMPTY);
            }
        }
    }

    private void initGrid(Cell[][] grid) {
        for (int i = 0; i<gridSize; i++) {
            for (int j = 0; j<gridSize; j++) {
                this.grid[i][j] = new Cell(grid[i][j].own);
            }
        }
    }

    Cell[][] getGrid() {
        return grid;
    }

    void setCell(Mark mark, int[] choice){
        grid[choice[0]][choice[1]].own = mark;
    }

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

    boolean checkFullGrid() {
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


}
