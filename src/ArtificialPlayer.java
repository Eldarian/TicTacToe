public class ArtificialPlayer implements Player {
    private Mark mark;
    ArtificialPlayer(Mark mark) {
        this.mark = mark;
    }
    public int[] turn() {
        //Bot logic here
        return new int[]{0, 0};
    }
    public Mark getMark() {
        return mark;
    }
}
