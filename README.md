# Java Spring based 15 Puzzle Game backend API

## About
The 15 Puzzle (also called Gem Puzzle, Boss Puzzle, Game of Fifteen, Mystic Square and more) is a sliding puzzle. It has 15 square tiles numbered 1 to 15 in a frame that is 4 tile positions high and 4 tile positions wide, with one unoccupied position. Tiles in the same row or column of the open position can be moved by sliding them horizontally or vertically, respectively. The goal of the puzzle is to place the tiles in numerical order (from left to right, top to bottom).

## API
This project is Java Spring backend API</br>
No database is used</br>
Frontend application will be made in future :)

## API Endpoints
### Create Game
Create new game instance.</br>
Method: 'POST'</br>
URL: '/api/game'</br>
Request Body: None</br>
Response: Game ID</br>

### Get Game State
Retrieve the current state of the game board.</br>
Method: 'GET'</br>
URL: '/api/game/{gameId}'</br>
Response: Current state of the game board</br>

