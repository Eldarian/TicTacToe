import java.io.IOException;

public interface Player {
    Mark getMark();
    int[] turn() throws IOException;
}
