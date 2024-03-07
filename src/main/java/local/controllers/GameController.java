package local.controllers;

import local.GameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
