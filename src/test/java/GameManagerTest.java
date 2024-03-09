import local.services.GameManagerService;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;

public class GameManagerTest {

    @Test
    public void testGenerateGameId() {
        GameManagerService gameManagerService = new GameManagerService();

        Set<String> gameIds = new HashSet<>();
        int numberOfIdsToGenerate = 10000;
        for (int i = 0; i < numberOfIdsToGenerate; i++) {
            String gameId = gameManagerService.generateGameId();
            assertNotNull(gameId);
            assertFalse("Duplicate game ID generated", gameIds.contains(gameId));
            gameIds.add(gameId);
        }
        assertEquals(numberOfIdsToGenerate, gameIds.size());
    }
}
