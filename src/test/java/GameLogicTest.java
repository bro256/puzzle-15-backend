import local.services.GameLogicService;
import local.services.GameManagerService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameLogicTest {
    private GameLogicService gameLogicService;

    @Before
    public void setUp(){
        gameLogicService = new GameLogicService(4);
        gameLogicService.shuffleGame();
    }

    @Test
    public void testGameState(){
        int[][] gameState = gameLogicService.getGameState();
        assertNotNull(gameState);
        assertEquals(4, gameState.length);
        assertEquals(4, gameState[0].length);
        assertEquals(4, gameState[1].length);
        assertEquals(4, gameState[2].length);
        assertEquals(4, gameState[3].length);
    }

    @Test
    public void testNumberRange(){
        int[][] gameState = gameLogicService.getGameState();
        for(int[] row : gameState){
            for(int tile : row){
                assertTrue(tile >= 0 && tile <= 15);
            }
        }
    }

    @Test
    public void testGameComplete() {
        int[][] gameState = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 0}
        };

        assertTrue(isGameComplete(gameState));
    }
    private boolean isGameComplete(int[][] gameState) {
        int expectedValue = 1;
        int totalTiles = gameState.length * gameState[0].length;

        for (int[] row : gameState) {
            for (int value : row) {
                if (value != expectedValue) {
                    // Check last value
                    boolean isLastExpectedValue = (expectedValue == totalTiles && value == 0);
                    if (!isLastExpectedValue) {
                        return false;
                    }
                }
                expectedValue++;
            }
        }
        return true;
    }


}
