package local.services;

import java.util.Arrays;

public class GameLogicService {
    private int[][] gameState;
    private final int size;

    public GameLogicService(int size) {
        this.size = size;
        initializeGameState();
    }

    /**
     * Intitialize game state
     */
    private void initializeGameState() {
        gameState = new int[size][size];
        int count = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gameState[i][j] = count++;
            }
        }
        gameState[size - 1][size - 1] = 0; // Empty space: 0
    }

    /**
     * Get game state
     * @return game state
     */
    public int[][] getGameState() {
        return gameState;
    }

    /**
     * Make move
     * @param tileValue the value of tile to move
     */
    public void makeMove(int tileValue) {
        int[] tilePosition = findTilePosition(tileValue);
        int[] emptyTilePosition = findEmptyTilePosition();

        if (tilePosition == null || emptyTilePosition == null) {
            throw new IllegalArgumentException("Invalid move");
        }

        if (!isAdjacent(tilePosition, emptyTilePosition)) {
            throw new IllegalArgumentException("Invalid move");
        }

        swapTiles(tilePosition, emptyTilePosition);
    }

    /**
     * Shuffle game
     */
    public void shuffleGame() {
        for (int i = 0; i < 1000; i++) {
            int[] emptyTilePosition = findEmptyTilePosition();
            int randomDirection = (int) (Math.random() * 4); // 0: up, 1: down, 2: left, 3: right
            int[] newPosition = getNewPosition(emptyTilePosition, randomDirection);
            if (isValidPosition(newPosition)) {
                swapTiles(emptyTilePosition, newPosition);
            }
        }
    }

    /**
     * Check if game is complete
     * @return true if the game is complete, false otherwise.
     */
    public boolean isGameComplete() {
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

    /**
     * Get empty tile position
     * @return empty tile position
     */
    private int[] findEmptyTilePosition() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (gameState[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalStateException("Empty tile not found in the game state");
    }

    /**
     * Get new position
     * @param currentPosition
     * @param direction
     * @return new position
     */
    private int[] getNewPosition(int[] currentPosition, int direction) {
        int[] newPosition = Arrays.copyOf(currentPosition, currentPosition.length);
        switch (direction) {
            case 0: // Up
                newPosition[0]--;
                break;
            case 1: // Down
                newPosition[0]++;
                break;
            case 2: // Left
                newPosition[1]--;
                break;
            case 3: // Right
                newPosition[1]++;
                break;
        }
        return newPosition;
    }

    /**
     * Check if position is valid
     * @param position position
     * @return the new position after moving in the specified direction.
     */
    private boolean isValidPosition(int[] position) {
        int row = position[0];
        int col = position[1];
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    /**
     * Swap tiles
     * @param position1 the position of the first tile to swap [row, column].
     * @param position2 the position of the second tile to swap [row, column].
     */
    private void swapTiles(int[] position1, int[] position2) {
        int temp = gameState[position1[0]][position1[1]];
        gameState[position1[0]][position1[1]] = gameState[position2[0]][position2[1]];
        gameState[position2[0]][position2[1]] = temp;
    }

    /**
     * Find tile position
     * @param tileValue
     * @return
     */
    public int[] findTilePosition(int tileValue) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (gameState[i][j] == tileValue) {
                    return new int[]{i, j};
                }
            }
        }
        return null; // Tile not found
    }

    /**
     * Check if is adjecant
     * @param tilePosition the position of the tile to check [row, column].
     * @param emptyTilePosition the position of the empty tile [row, column].
     * @return true if the tile is adjacent to the empty tile, false otherwise.
     */
    public boolean isAdjacent(int[] tilePosition, int[] emptyTilePosition) {
        int rowDiff = Math.abs(tilePosition[0] - emptyTilePosition[0]);
        int colDiff = Math.abs(tilePosition[1] - emptyTilePosition[1]);
        return (rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1);
    }


}
