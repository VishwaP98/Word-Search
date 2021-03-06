# Word-Search App
Created by Vishwa Patel, Developed using Kotlin


## This is basically a Word Search game that has certain words in the grid which the user has to find.

 The words stored in the grid will be in any one of these directions - (Right, Down, Right Diagonal Up, Right Diagonal Down)

If the user finds it hard to find a word, they can just click on the word given below the grid to get a hint on the placement direction.

# Features:

 1. Placement of Words in the grid will be random everytime user runs the app
 2. Hint feature is provided to aid users who are struggling to find the word in the grid
 3. Users can swipe to select the word from the gridView
 4. Users are given a list of words they have to find from the grid and everytime a word is found, it gets crossed off

<div align="center">
<p float="center">
  <img src="https://github.com/VishwaP98/Word-Search/blob/master/ScreenShots/ScreenShot1.png" width="250" height="400" alt="No Words Found yet" hspace="50">
 
 <p float="center">No Words Found yet!</p>
 
 
 <img src="https://github.com/VishwaP98/Word-Search/blob/master/ScreenShots/ScreenShot4.png" width="280" height="450" alt="No Words Found yet" hspace="50">
 
 <p float="center">Hints Provided here</p> 
 
 <img src="https://github.com/VishwaP98/Word-Search/blob/master/ScreenShots/ScreenShot2.png" width="250" height="400" alt="No Words Found yet" hspace="50">
 
 <p float="center">Selecting a word</p>
 
 
 <img src="https://github.com/VishwaP98/Word-Search/blob/master/ScreenShots/ScreenShot3.png" width="280" height="450" alt="No Words Found yet" hspace="50">
 <p float="center">All words are selected</p>
</p>
</div>

To add more about how words are added to the grid, we want to sort the words from shortest to longest and then starting putting the longest ones first in the grid as we will have more options to put the longer words first in the grid and shorter words will still be able to have enough possible placements.

