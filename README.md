# Java Spring-based 15 Puzzle Game Backend API

## About
The 15 Puzzle (also called Gem Puzzle, Boss Puzzle, Game of Fifteen, Mystic Square, and more) is a sliding puzzle. It has 15 square tiles numbered 1 to 15 in a frame that is 4 tile positions high and 4 tile positions wide, with one unoccupied position. Tiles in the same row or column of the open position can be moved by sliding them horizontally or vertically, respectively. The goal of the puzzle is to place the tiles in numerical order (from left to right, top to bottom).

Full description: [Wikipedia](https://en.wikipedia.org/wiki/15_Puzzle)

## API
This project is a Java Spring backend API.

No database is used.

Frontend application will be made later :)

## API Endpoints
### Swagger OpenAPI definition
Local link: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

![Swagger UI](https://github.com/bro256/puzzle-15-backend/blob/main/images/swagger-game-controller.png)

### Create Game
Create a new game instance.

- Method: `POST`
- URL: `/api/game`
- Request Body: None
- Response: Game ID

### Get Game State
Retrieve the current state of the game board.

- Method: `GET`
- URL: `/api/game/{gameId}`
- Response: Current state of the game board

### Make Move
Make a move in the game by specifying the tile value to move.

- Method: `PUT`
- URL: `/api/game/{gameId}/move`
- Request Body: `tileValue` (integer): The value of the tile to move
- Response: Updated game state after the move

### Check Game Completion
Check if the game is completed (all tiles are in the correct order).

- Method: `GET`
- URL: `/api/game/{gameId}/complete`
- Response: Boolean indicating if the game is complete
