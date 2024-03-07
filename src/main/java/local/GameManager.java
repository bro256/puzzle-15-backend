package local;

import java.util.HashMap;
import java.util.Map;

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
        return new int[4][4];
    }

    private void moveTile(int[][] gameState, int tileValue) {
//
    }

    private boolean isPuzzleComplete(int[][] gameState) {
        //
        return false;
    }

}
