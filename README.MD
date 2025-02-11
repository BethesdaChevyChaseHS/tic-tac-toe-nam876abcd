[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/mb-Lw4Ik)
[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=18149730)

# Setup steps:
1. Forward port 6080
2. Open Lwjgl3Launcher.java. This file contains the main method you will use to test your code. 

# Additional Resources:
[Scene2d with libgdx](https://libgdx.com/wiki/graphics/2d/scene2d/scene2d-ui)
[Buttons](https://javadoc.io/doc/com.badlogicgames.gdx/gdx/latest/com/badlogic/gdx/scenes/scene2d/ui/Button.html)
[Table](https://javadoc.io/doc/com.badlogicgames.gdx/gdx/latest/com/badlogic/gdx/scenes/scene2d/ui/Table.html)
[Text Fields](https://javadoc.io/doc/com.badlogicgames.gdx/gdx/latest/com/badlogic/gdx/scenes/scene2d/ui/TextField.html)
[Label](https://javadoc.io/doc/com.badlogicgames.gdx/gdx/latest/com/badlogic/gdx/scenes/scene2d/ui/Label.html)
If this is blocked on school Wifi, a pdf copy is available on Canvas. 

## Checkpoint 1
Due 2/14
### Goal
Create the player selection screen. 

### Programming Steps
0. Change to [your name]'s tic tac toe and your background image on the title screen. To change the image, look in the assets folder. 
1. Complete the constructor of the PlayerSelectionScreen class to create the buttons displaying different player options. 
2. Complete the method setPlayer in TicTacToe.java. This method should set the player and handle navigation

## Checkpoint 2
Due 2/21
### Goal
Create the Basic tic tac toe screen and implement basic play in Human vs Human. Humans should be able to play multiple games against each other. 

### Programming Steps
1. Set up your display in the GameDisplay constructor. The code to draw the board itself is given, you will need to add the other things (like a display of whose turn it is and who is playing who)
2. Complete the missing methods in Board.java. Check out the Mark enum before diving in. 
3. Complete the handleMoveMade method
4. Complete the showResults method
5. Complete the getRecord() method in Player.java, and display these stats somewhere on the screen. 

## Checkpoint 3
Due 2/27
### Goal
Create simulation mode, where you can face off different AIs you create against each other. Implement Random and Slightly Smart AI. 

### Programming Steps
1. Implement RandomAI. This should select a move randomly. That's it. Play against it to check that it works. 
2. Create a new class called SlightlySmartAi that extends Player. It should follow the same pattern as Human and RandomAI. This AI should first check if any moves win - by playing the move on a copy of the board and checking if it wins. If it does, play the move, if it does not, erase the test move and try again. If no moves win immediately, it should choose a move randomly. Play this AI and see if it works!
3. Finish the NumSimulationScreen class - add a title and continue button. If isSimulated, show this screen before going to GameDisplay. 
4. Update render() and handleMoveMade() according to instructions in comments within GameDisplay.java. Test the simulated games. 

## Checkpoint 4
Due 3/7
### Goal 
Implement perfect AI using minimax algorithm. We will do an in class activity to go over this algorithm. 

### Programming Steps
1. Create a new class called SmartAI. Implement Minimax. Test. 


# Testing:
To test your code, interact with the GUI you create! Click run with the file Lwjgl3Launcher.java open. This can be found in /lwjgl3/src/main/java/bcc/snowman/lwjgl3.
The running version of your project will be available at the forwarded address from port 6080. 

# Publishing:
NOTE - currently this is not working. I am not sure why, but am troubleshooting. For now, all submission will be done with git commands. 
Run 
```
1) npm run build
2) npm i gh-pages
3) npm run deploy
```

# Submission:
To submit, make a commit for each checkpoint and push
```
1) git add . 
2) git commit -m "checkpoint 1 completed."
3) git push
```
This will save your project forever and ever, so that it is not deleted with the codespace you are currently using.