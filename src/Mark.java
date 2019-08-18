public enum Mark {
    CROSS, NOUGHT, EMPTY;

    public String toString() {
        if (this == CROSS) return "X";
        else if (this == NOUGHT) return "O";
        else return " ";
    }
}
