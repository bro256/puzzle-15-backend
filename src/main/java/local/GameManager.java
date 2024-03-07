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


    public void makeMove(String gameId, int tileValue) {
        int[][] gameState = games.get(gameId);
        if (gameState == null) {
            throw new IllegalArgumentException("Game not found");
        }

        int[] tilePosition = findTilePosition(gameState, tileValue);
        if (tilePosition == null) {
            throw new IllegalArgumentException("Tile not found in the game state");
        }

        int[] emptyTilePosition = findEmptyTilePosition(gameState);
        if (emptyTilePosition == null) {
            throw new IllegalStateException("Empty tile not found in the game state");
        }

        if (!isAdjacent(tilePosition, emptyTilePosition)) {
            throw new IllegalArgumentException("Invalid move");
        }

        swapTiles(gameState, tilePosition, emptyTilePosition);
    }


    private int[] findTilePosition(int[][] gameState, int tileValue) {
        for (int row = 0; row < gameState.length; row++) {
            for (int col = 0; col < gameState[row].length; col++) {
                if (gameState[row][col] == tileValue) {
                    return new int[]{row, col};
                }
            }
        }
        return null;
    }


    private int[] findEmptyTilePosition(int[][] gameState) {
        for (int row = 0; row < gameState.length; row++) {
            for (int col = 0; col < gameState[row].length; col++) {
                if (gameState[row][col] == 0) {
                    return new int[]{row, col};
                }
            }
        }
        return null;
    }


    private boolean isAdjacent(int[] tilePosition, int[] emptyTilePosition) {
        return Math.abs(tilePosition[0] - emptyTilePosition[0]) + Math.abs(tilePosition[1] - emptyTilePosition[1]) == 1;
    }


    private void swapTiles(int[][] gameState, int[] tilePosition, int[] emptyTilePosition) {
        int temp = gameState[tilePosition[0]][tilePosition[1]];
        gameState[tilePosition[0]][tilePosition[1]] = gameState[emptyTilePosition[0]][emptyTilePosition[1]];
        gameState[emptyTilePosition[0]][emptyTilePosition[1]] = temp;
    }


    private String generateGameId() {
        return UUID.randomUUID().toString();
    }


    public boolean isGameComplete(String gameId) {
        int[][] gameState = games.get(gameId);
        if (gameState == null) {
            throw new IllegalArgumentException("Game not found");
        }
        return isPuzzleComplete(gameState);
    }


    private boolean isPuzzleComplete(int[][] gameState) {
        int previousTile = -1;
        for (int[] row : gameState) {
            for (int tile : row) {
                if (tile != previousTile + 1) {
                    return false;
                }
                previousTile = tile;
            }
        }
        return true;
    }

}
