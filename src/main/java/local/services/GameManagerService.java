package local.services;

import local.services.GameLogicService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class GameManagerService {
    private final Map<String, GameLogicService> games;

    public GameManagerService() {
        this.games = new HashMap<>();
    }

    /**
     * Create new game
     * @return game ID
     */
    public String createGame() {
        String gameId = generateGameId();
        GameLogicService gameLogicService = new GameLogicService(4);
        gameLogicService.shuffleGame();
        games.put(gameId, gameLogicService);
        return gameId;
    }

    /**
     * Get game state
     * @param gameId
     * @return game state
     */
    public int[][] getGameState(String gameId) {
        GameLogicService gameLogicService = games.get(gameId);
        if (gameLogicService == null) {
            throw new IllegalArgumentException("Game not found");
        }
        return gameLogicService.getGameState();
    }

    /**
     * Make move
     * @param gameId
     * @param tileValue
     */
    public void makeMove(String gameId, int tileValue) {
        GameLogicService gameLogicService = games.get(gameId);
        if (gameLogicService == null) {
            throw new IllegalArgumentException("Game not found");
        }
        gameLogicService.makeMove(tileValue);
    }

    /**
     * Shuffle game
     * @param gameId
     */
    public void shuffleGame(String gameId) {
        GameLogicService gameLogicService = games.get(gameId);
        if (gameLogicService == null) {
            throw new IllegalArgumentException("Game not found");
        }
        gameLogicService.shuffleGame();
    }

    /**
     * Check if selected game is complete
     * @param gameId
     * @return {@code true} if the game is complete, {@code false} if the game is not complete.
     */
    public boolean isGameComplete(String gameId) {
        GameLogicService gameLogicService = games.get(gameId);
        if (gameLogicService == null) {
            throw new IllegalArgumentException("Game not found");
        }
        return gameLogicService.isGameComplete();
    }

    /**
     * Generate unique game ID
     * @return generated game ID in String format
     */
    public String generateGameId() {
        return UUID.randomUUID().toString();
    }
}
