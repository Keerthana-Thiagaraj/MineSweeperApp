# MinesweeperGame CLI Application

## Overview
This is a command-line Minesweeper game implemented in Java, following SOLID principles and clean code practices. The application allows users to play Minesweeper on a customizable grid, with mines randomly placed. The game is interactive and displays the board after each move, following the exact input/output format specified in the problem statement.

## Features
- Customizable grid size and mine count (with a maximum of 35% mines)
- Random mine placement
- Automatic uncovering of adjacent empty squares
- Win/loss detection
- Command-line interface with clear prompts and grid display
- Service and model layers for clean architecture
- Maximum unit tests and code coverage
- Maven support for easy build and dependency management
- Docker support for easy execution

## Design Principles
- **SOLID Principles**: Each class has a single responsibility, dependencies are injected for testability, and the code is open for extension but closed for modification.
- **Clean Architecture**: The project is organized into service, model, and CLI layers for separation of concerns and maintainability.
- **Design Patterns**: MVC pattern is used to separate user interface, business logic, and data. Service layer encapsulates game rules and logic.
- **Testability**: All core logic is covered by unit tests, and CLI interaction is tested for input validation.

## Design Explanation
- **MinesweeperMain.java**: Entry point, initializes Cli and GameService.
- **Cli.java**: Handles user input/output, prompts, and game loop. Follows the exact input/output format from the problem statement.
- **GameService.java**: Manages game state, win/loss logic, and user moves.
- **Board.java**: Represents the grid, mine placement, and uncovering logic.
- **Cell.java**: Represents each cell (mine, uncovered, flagged, etc.).
- **Tests**: Board, Cell, and GameService logic are unit tested for correctness. CLITest simulates user interaction and validates CLI output.

## Project Structure
```
src/
  main/java/com/minesweepergame/
    MinesweeperMain.java
    cli/
      Cli.java
    model/
      Board.java
      Cell.java
    service/
      GameService.java
  test/java/com/minesweepergame/
    cli/
      CLITest.java
    model/
      BoardTest.java
      CellTest.java
    service/
      GameServiceTest.java
pom.xml
Dockerfile
README.md
```

## Environment
- Java 17+
- JUnit 5 (for tests)
- OS: Windows, macOS, Linux

## Running Tests & Viewing Code Coverage
To run all tests and generate a code coverage report, use:

```
mvn clean test verify
```

After running, open the coverage report at:

```
target/site/jacoco/index.html
```

This HTML file shows detailed code coverage for your project.

## Building and Running with Docker

1. **Build the Docker image:**
   ```sh
   docker build -t minesweeper-game .
   ```

2. **Run the application:**
   ```sh
   docker run -it minesweeper-game
   ```

This will start the Minesweeper CLI in a container. You can interact with the game in your terminal.

## Docker Build Warning
If you see the message:

```
DEPRECATED: The legacy builder is deprecated and will be removed in a future release.
```

This is a warning from Docker and does not affect your application. You can safely ignore it. For a modern build experience, you can enable BuildKit:

```
export DOCKER_BUILDKIT=1
```
Then build your image as usual:
```
docker build -t minesweeper-game .
```

## Gameplay Example
### Success Example
```
Welcome to Minesweeper!

Enter the size of the grid (e.g. 4 for a 4x4 grid): 
4
Enter the number of mines to place on the grid (maximum is 35% of the total squares): 
3

Here is your minefield:
  1 2 3 4
A _ _ _ _
B _ _ _ _
C _ _ _ _
D _ _ _ _

Select a square to reveal (e.g. A1): D4
This square contains 0 adjacent mines. 

Here is your updated minefield:
  1 2 3 4
A _ _ 2 0
B _ _ 2 0
C _ 2 1 0
D _ 1 0 0

Select a square to reveal (e.g. A1): B1
This square contains 3 adjacent mines. 

Here is your updated minefield:
  1 2 3 4
A _ _ 2 0
B 3 _ 2 0
C _ 2 1 0
D _ 1 0 0

Select a square to reveal (e.g. A1): A1
This square contains 2 adjacent mines. 

Here is your updated minefield:
  1 2 3 4
A 2 _ 2 0
B 3 _ 2 0
C _ 2 1 0
D _ 1 0 0

Select a square to reveal (e.g. A1): D1
This square contains 1 adjacent mines. 

Here is your updated minefield:
  1 2 3 4
A 2 _ 2 0
B 3 _ 2 0
C _ 2 1 0
D 1 1 0 0

Congratulations, you have won the game!
Press any key to play again...
```
### Failure Example
```
Welcome to Minesweeper!

Enter the size of the grid (e.g. 4 for a 4x4 grid): 
3
Enter the number of mines to place on the grid (maximum is 35% of the total squares): 
3

Here is your minefield:
  1 2 3
A _ _ _
B _ _ _
C _ _ _

Select a square to reveal (e.g. A1): C3
Oh no, you detonated a mine! Game over.
Press any key to play again...
```
