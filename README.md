# Extension

1. Permanent high scores list

- create class 'Score' and class 'ScoreList'
- class 'Score' includes a score variable and the timestamp of the score variable
- class 'ScoreList' includes a list of 'Score' and a live score variable
- use file processing to store data
- the data from the 'highscores.txt' will be added to the highScoresList variable in class 'ScoreList' prior
- the liveScore will be added to the 'highscore.txt' file if it meets all of the requirements below:
  - the live score is higher than the highest score in the high scores list
  - the live score is not equals to any of the high scores in the high scores list
  - the live score is lower than the highest score but higher than one of the high scores in the high scores list
  * the algorithm is in class 'ScoreList' from line 170 to line 205, the name of the methods are checkBreakHighScores() and checkNotEqualHighScores()
- a pop up JDialog will be shown to congratz the player if highest score in the highscore list has been breaked


2. New home menu screen

- convert buttons to JButton, reason: sometimes the buttons of drawing has problem of execution delay
- background image
- new buttons, 'INSTRUCTIONS' button and 'HIGHSCORES' button
- 'INSTRUCTIONS' shows how to function the game
- 'HIGHSCORES' shows the high score list if there is any high score (the list will be empty at the beginning before player plays the game)


3. Increase the game difficulty

- increase the ball speed whenever the live score is divisible by 50
- decrease the width of the player when the live score reaches 100 
*the algorithm is in class 'Wall' from line 294 to line 301, the name of the method is increaseGameDifficulty()


# Maintenance

1. Packaging

- package the classes according to MVC pattern
- reason: easier for maintenance


2. MVC pattern

- create class 'HomeMenuPanelController', class 'GamePanelController', and class 'DebugConsolePanelController'
- seperate the listeners from the view classes(HomeMenuPanel, GamePanel, DebugConsolePanel) to the controller classes


3. Separate class 'Crack' from class 'Brick'

- reason: principle of single responsibility


4. Rename varibles
* the names within the curly brackets represent the classes of the variables
- inner -> innerColor {Ball, Brick}
- border -> borderColor {Ball, Brick}
- radiusA -> radiusWidth {Ball}
- radius B -> radiuHeight {Ball}

- reason: easier for future develepor


5. Rename methods
* the names within the curly brackets represent the classes of the methods
- movRight() -> moveRight() {player}


6. Rename classes
- DebugConsole -> DebugConsoleFrame
- DebugPanel -> DebugConsolePanel
- GameBoard -> GamePanel
- HomeMenu -> HomeMenuPanel


7. Encapsulation
- private all the variables
