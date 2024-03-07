package local;

import java.util.*;

public class GameManager {
    private final Map<String, int[][]> games;

    public GameManager() {
        this.games = new HashMap<>();
    }

    public String createGame() {
        String gameId = generateGameId();
        int[][] initialState = initializeGameState();
        games.put(gameId, initialState);
        return gameId;
    }

    public int[][] getGameState(String gameId) {
        return games.get(gameId);
    }

    public void makeMove(String gameId, int tileValue) {
        int[][] gameState = games.get(gameId);
//
        moveTile(gameState, tileValue);
    }

    public boolean isGameComplete(String gameId) {
        int[][] gameState = games.get(gameId);
//
        return isPuzzleComplete(gameState);
    }

    private String generateGameId() {
        //
        return "1";
    }

    private int[][] initializeGameState() {
        int[][] gameState = new int[4][4];
        List<Integer> tiles = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        Collections.shuffle(tiles);
        System.out.println(tiles);

        int index = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                gameState[row][col] = tiles.get(index++);
            }
        }

        return gameState;
    }

    private void moveTile(int[][] gameState, int tileValue) {
//
    }

    private boolean isPuzzleComplete(int[][] gameState) {
        //
        return false;
    }

}
