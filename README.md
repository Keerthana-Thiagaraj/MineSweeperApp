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
- **Design Patterns Used**:
  - **MVC (Model-View-Controller)**: The overall architecture separates concerns:
    - **Model**: `Board`, `Cell` classes represent the game state and data.
    - **View**: `Cli` class handles user interaction and display.
    - **Controller**: `GameService` class manages game logic and coordinates between model and view.
  - **Service Layer Pattern**: `GameService` encapsulates business logic, win/loss detection, and game rules, providing a clean API for the CLI.
  - **Factory Pattern**: The `Board` class acts as a factory for creating the grid and placing mines, abstracting the initialization logic.
  - **Dependency Injection**: Constructors in `GameService`, `Board`, and `Cli` accept dependencies, making the code flexible and testable.
  - **Strategy Pattern (implicit)**: The mine placement logic in `Board` can be extended to support different strategies for mine distribution.

## Design Explanation
- **MinesweeperMain.java**: Entry point. Uses the Factory pattern to instantiate the CLI and GameService. Follows Dependency Injection by passing dependencies to constructors.
- **Cli.java**: Implements the View in MVC. Handles all user input/output, prompts, and game loop. Uses Dependency Injection to receive the GameService instance. Follows the Observer pattern by updating the display after each move.
- **GameService.java**: Implements the Controller in MVC and the Service Layer pattern. Manages game state, win/loss logic, and user moves. Coordinates between the CLI and Board. Uses Dependency Injection for testability.
- **Board.java**: Implements the Model in MVC and the Factory pattern. Responsible for grid creation, mine placement, and uncovering logic. Can be extended for different mine placement strategies (Strategy pattern).
- **Cell.java**: Implements the Model in MVC. Represents each cell's state (mine, uncovered, flagged, etc.).
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

## How to Run the Application

### Prerequisites
- Ensure you have Java 17 or higher installed.
- Ensure you have Maven installed (for local build/test).
- For Docker, ensure Docker Desktop (macOS/Windows) or Docker Engine (Linux) is installed and running.

### Running Locally (Without Docker)
1. **Build the project:**
   ```sh
   mvn clean package
   ```
2. **Run the application:**
   ```sh
   java -cp target/MinesweeperGame-1.0-SNAPSHOT.jar com.minesweepergame.MinesweeperMain
   ```

### Running with Docker
1. **Build the Docker image:**
   ```sh
   docker build -t minesweeper-game .
   ```
2. **Run the application in a container:**
   ```sh
   docker run -it minesweeper-game
   ```
   The CLI will start and you can interact with the game in your terminal.

### Running Tests and Viewing Code Coverage
1. **Run all tests and generate code coverage report:**
   ```sh
   mvn clean test verify
   ```
2. **View the coverage report:**
   Open `target/site/jacoco/index.html` in your browser for a detailed report.

### Common Issues
- **Docker Daemon Not Running:**
  If you see `ERROR: Cannot connect to the Docker daemon...`, start Docker Desktop (macOS/Windows) or the Docker service (Linux).
- **Legacy Builder Warning:**
  If you see `DEPRECATED: The legacy builder is deprecated...`, you can safely ignore it or enable BuildKit:
  ```sh
  export DOCKER_BUILDKIT=1
  docker build -t minesweeper-game .
  ```
- **Starting Docker on macOS:**
  Download Docker Desktop from [docker.com](https://www.docker.com/products/docker-desktop/), install, and start from Applications or Launchpad. Wait for the whale icon in the menu bar before running Docker commands.

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
