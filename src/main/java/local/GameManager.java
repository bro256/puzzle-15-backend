package local;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameManager {
    private final Map<String, GameLogic> games;

    public GameManager() {
        this.games = new HashMap<>();
    }

    public String createGame() {
        String gameId = generateGameId();
        GameLogic gameLogic = new GameLogic(4);
        gameLogic.shuffleGame();
        games.put(gameId, gameLogic);
        return gameId;
    }

    public int[][] getGameState(String gameId) {
        GameLogic gameLogic = games.get(gameId);
        if (gameLogic == null) {
            throw new IllegalArgumentException("Game not found");
        }
        return gameLogic.getGameState();
    }

    public void makeMove(String gameId, int tileValue) {
        GameLogic gameLogic = games.get(gameId);
        if (gameLogic == null) {
            throw new IllegalArgumentException("Game not found");
        }
        gameLogic.makeMove(tileValue);
    }

    public void shuffleGame(String gameId) {
        GameLogic gameLogic = games.get(gameId);
        if (gameLogic == null) {
            throw new IllegalArgumentException("Game not found");
        }
        gameLogic.shuffleGame();
    }

    public boolean isGameComplete(String gameId) {
        GameLogic gameLogic = games.get(gameId);
        if (gameLogic == null) {
            throw new IllegalArgumentException("Game not found");
        }
        return gameLogic.isGameComplete();
    }

    private String generateGameId() {
        return UUID.randomUUID().toString();
    }
}
