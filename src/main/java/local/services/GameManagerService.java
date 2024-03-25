package local.services;

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
     *
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
     *
     * @param gameId game ID
     * @return game state
     */
    public int[][] getGameState(String gameId) {
        GameLogicService gameLogicService = getGameLogicService(gameId);
        return gameLogicService.getGameState();
    }

    /**
     * Make move
     *
     * @param gameId    game ID
     * @param tileValue tile Value
     */
    public void makeMove(String gameId, int tileValue) {
        GameLogicService gameLogicService = getGameLogicService(gameId);
        gameLogicService.makeMove(tileValue);
    }

    /**
     * Shuffle game
     *
     * @param gameId game ID
     */
    public void shuffleGame(String gameId) {
        GameLogicService gameLogicService = getGameLogicService(gameId);
        gameLogicService.shuffleGame();
    }

    /**
     * Check if selected game is complete
     *
     * @param gameId game ID
     * @return {@code true} if the game is complete, {@code false} if the game is not complete.
     */
    public boolean isGameComplete(String gameId) {
        GameLogicService gameLogicService = getGameLogicService(gameId);
        return gameLogicService.isGameComplete();
    }

    /**
     * Generate unique game ID
     *
     * @return generated game ID in String format
     */
    public String generateGameId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Retrieve the GameLogicService associated with the given gameId, or throw an IllegalArgumentException if not found.
     *
     * @param gameId game ID
     * @return the GameLogicService associated with the given gameId
     * @throws IllegalArgumentException if the game with the given gameId is not found
     */
    private GameLogicService getGameLogicService(String gameId) {
        GameLogicService gameLogicService = games.get(gameId);
        if (gameLogicService == null) {
            throw new IllegalArgumentException("Game not found");
        }
        return gameLogicService;
    }
}
