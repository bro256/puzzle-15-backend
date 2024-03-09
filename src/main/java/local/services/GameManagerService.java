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

    public String createGame() {
        String gameId = generateGameId();
        GameLogicService gameLogicService = new GameLogicService(4);
        gameLogicService.shuffleGame();
        games.put(gameId, gameLogicService);
        return gameId;
    }

    public int[][] getGameState(String gameId) {
        GameLogicService gameLogicService = games.get(gameId);
        if (gameLogicService == null) {
            throw new IllegalArgumentException("Game not found");
        }
        return gameLogicService.getGameState();
    }

    public void makeMove(String gameId, int tileValue) {
        GameLogicService gameLogicService = games.get(gameId);
        if (gameLogicService == null) {
            throw new IllegalArgumentException("Game not found");
        }
        gameLogicService.makeMove(tileValue);
    }

    public void shuffleGame(String gameId) {
        GameLogicService gameLogicService = games.get(gameId);
        if (gameLogicService == null) {
            throw new IllegalArgumentException("Game not found");
        }
        gameLogicService.shuffleGame();
    }

    public boolean isGameComplete(String gameId) {
        GameLogicService gameLogicService = games.get(gameId);
        if (gameLogicService == null) {
            throw new IllegalArgumentException("Game not found");
        }
        return gameLogicService.isGameComplete();
    }

    public String generateGameId() {
        return UUID.randomUUID().toString();
    }
}
