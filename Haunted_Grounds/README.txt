--------------------------------------------------------------------------------------ROOM--------------------------------------------------------------------------------------

Open rooms.txt, you will see a list of elements pertaining to each Room within a new game.

The following is the order of the monster data (delimited by '#'):

Id
Name
Description
roomConnections (North, South, East, West)*
isVisited
monsterId
puzzleId
itemId

*Data delimited by ',' (comma) in text file.

--------------------------------------------------------------------------------------MONSTERS--------------------------------------------------------------------------------------

Open monster.txt, you will see a list of elements pertaining to each monster.

The following is the order of the monster data (delimited by '#'):

Id
Name
HP
Currency
Description
Speed
Defense
Attack
Monster Items (Dropped upon defeat)*

*Data delimited by ',' (comma) in text file.
---------------------------------------------------------------------------------GENERAL CONTROLS-----------------------------------------------------------------------------------
For the controls of the game, you will be prompted to enter your player name.


|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|                                                                               Table of Commands                                                                                   |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|----------------------------------------------------------------------------------Navigation---------------------------------------------------------------------------------------|
|Move N: Move North                                                                                                                                                                 |
|Move E: Move North                                                                                                                                                                 |
|Move S: Move North                                                                                                                                                                 |
|Move W: Move North                                                                                                                                                                 |
|                                                                                                                                                                                   |
|---------------------------------------------------------------------------------Within Rooms--------------------------------------------------------------------------------------|
|InspectRoom: List Room Items                                                                                                                                                       |
|CurrentRoom: Lists current room details                                                                                                                                            |
|Pickup {ItemName}: add to inventory                                                                                                                                                |
|Drop {ItemName}: Remove from inventory                                                                                                                                             |
|                                                                                                                                                                                   |
|-------------------------------------------------------------------------------Item Interaction------------------------------------------------------------------------------------|
|"pickup" + item name : Users can use this command to read a description of an item located in the room or inventory.                                                               |
|"drop " + item name : Users can use this command to drop an item followed by the name of the item.                                                                                 |
|"equip " + item name : Users can use this command to unequip an item followed by the name of the item. The item returns to the character's inventory.                              |
|"use " + item name : Users can use this command to use an item followed by the name of the item.                                                                                   |
|"drink " + item name : Users can use this command to drink an item followed by the name of the item.                                                                               |
|"inspectroom" : When in a room, the user can use this command to see a list of all contents in the current room.                                                                   |
|"exitroom" : When in a room, the user can use this command to return to the previous room.                                                                                         |
|"turn on lights" : When in a room with lights, the user can use this command to turn on the lights.                                                                                |
|                                                                                                                                                                                   |
|-------------------------------------------------------------------------------------Help------------------------------------------------------------------------------------------|
|Help: List all available commands                                                                                                                                                  |
|                                                                                                                                                                                   |
|----------------------------------------------------------------------------------Menu Options-------------------------------------------------------------------------------------|
|"menu" : When in the game, the user can use this command to display the main menu.                                                                                                 |
|"menuhelp" : When in the main menu, the user can use this command to see information about the load and save features.                                                             |
|"new game" : When in the main menu, this command allows the user to create a new game and save file.                                                                               |
|"new hard mode game" : When in the main menu, this command is the same as the new game command, but if the player dies during the game, the game is over and cannot be returned to.|
|"load " + save name : When in the main menu, use this command to load a save by inputting the previously determined save name.                                                     |
|"save " + save name : When in the main menu, use this command with a save name to save all progress. This command saves all information about the player and the map.              |
|"continue" : When in the main menu, this command returns the user back into the current game(only displayed if the user is already in a game).                                     |
|"exit" : When in the main menu, the user can use this command to leave the game.                                                                                                   |
|                                                                                                                                                                                   |
|-------------------------------------------------------------------------------------Player----------------------------------------------------------------------------------------|
|"move" + direction : Command used to move in the specified direction of 'North', 'South', 'East', or 'West'.                                                                       |
|"help" : When in the game, the user can use this command to display a list of commands followed by a brief description for each one.                                               |
|"inspect " + item : Users can use this command to read a description of an item located in the room or inventory.                                                                  |
|"inventory" : Command used to view all items currently in the player's inventory. Also, displays current items equipped.                                                           |
|                                                                                                                                                                                   |
|-------------------------------------------------------------------------------------Combat----------------------------------------------------------------------------------------|
|"attack" : When in combat, the user can use this command to attack the current enemy.                                                                                              |
|"defend" : When in combat, the user can use this command to defend against the current enemy's next attack.                                                                        |
|"item" : When in combat, the user can use this command to use a consumable item or switch weapons.                                                                                 |
|"run" : When in combat, the user can use this command to run away from the current enemy.                                                                                          |
|                                                                                                                                                                                   |
|-----------------------------------------------------------------------------------Puzzle/Riddle-----------------------------------------------------------------------------------|
|"answer " + answer : When solving a puzzle, the user can use this command followed by user input to try and solve the puzzle.                                                      |
|"hint" : When solving a puzzle, the user can use this command to receive a hint.                                                                                                   |
|                                                                                                                                                                                   |
|--------------------------------------------------------------------------------------NPCS-----------------------------------------------------------------------------------------|
|"talk" : User can use this command to interact with the NPCs and receive riddles.                                                                                                  |
|"shop" : User can use this command for the purchase of consumables.                                                                                                                |
|"riddle" : User can use this command to initiate a riddle.                                                                                                                         |
|"leave" : User can use this command to leave the shop and end the conversion with the NPC.                                                                                         |
|"buy" + item name + quantity: The user can use this commands in the shop to but consumables.                                                                                       |
|                                                                                                                                                                                   |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|

--------------------------Insert game overview here???---------------------------