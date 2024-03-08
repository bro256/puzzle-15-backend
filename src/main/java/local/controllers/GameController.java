package local.controllers;

import local.GameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameManager gameManager;

    @PostMapping
    public ResponseEntity<String> createGame(){
        String gameId = gameManager.createGame();
        return new ResponseEntity<>(gameId, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<int[][]> getGameState(@PathVariable String gameId){
        int[][] gameState = gameManager.getGameState(gameId);
        return new ResponseEntity<>(gameState, HttpStatus.OK);
    }


}