package local.controllers;

import local.services.GameManagerService;
import local.dto.MoveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameManagerService gameManagerService;

    @PostMapping
    public ResponseEntity<String> createGame() {
        String gameId = gameManagerService.createGame();
        return new ResponseEntity<>(gameId, HttpStatus.CREATED);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<int[][]> getGameState(@PathVariable String gameId) {
        int[][] gameState = gameManagerService.getGameState(gameId);
        if (gameState == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gameState, HttpStatus.OK);
    }

    @PutMapping("/{gameId}/move")
    public ResponseEntity<int[][]> makeMove(@PathVariable String gameId, @RequestBody MoveRequest moveRequest) {
        try {
            gameManagerService.makeMove(gameId, moveRequest.getTileValue());
            int[][] updatedGameState = gameManagerService.getGameState(gameId);
            return new ResponseEntity<>(updatedGameState, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{gameId}/complete")
    public ResponseEntity<Boolean> checkGameCompletion(@PathVariable String gameId) {
        boolean isComplete = gameManagerService.isGameComplete(gameId);
        return new ResponseEntity<>(isComplete, HttpStatus.OK);
    }


}
