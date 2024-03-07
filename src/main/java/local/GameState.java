package local;

public class GameState {
    private final int[][] board;

    public GameState(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard() {
        return board;
    }
}
