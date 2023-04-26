/***********************************ReAnn, Cobi, & Cody************************************/
package org.superherosquad;

import java.io.Serializable;
import java.util.ArrayList;

public class View implements Serializable {
	private String blue = "\u001B[34m";
	private String red = "\u001B[31m";
	private String reset = "\u001B[0m";
	private String orange = "\u001B[38;2;255;165;0m";
	private String green =  "\u001B[32m";
	private String darkGreen = "\u001B[32";
	private String lightYellow  = "\033[93m";
	private static final long serialVersionUID = 673824491807579304L;

	public void print(String s)
	{
		System.out.println(s);
	}

	public void userInput() {System.out.print("\n> ");} // ReAnn

	public void invalid()
	{
		print("\n\u001B[31mThat is an invalid command.\u001B[0m");
		print("Try again!");
	}
	
	public void room(String s)
	{
		print("You are currently in the \033[1m" + "\033[93m" + s + "\033[0m" + ".");
	}

	public void encounterMonster(Monster monster) { //Cody - Displays when a player inspects room and encounters a monster
		print("You see " + orange + monster.getName() + reset + " in the room with you.\n"
				+ blue + "What would you like to do?" + reset + "\n(A)ttack, (I)gnore, (R)un");
	}

	public void combatWithMonster() { //Cody - Displays during the combat loop with a monster
		print(blue + "What would you like to do?" + reset + "\n(A)ttack, (D)efend, (U)se {item name}, (R)un\n" +
				"Use '(I)tem' to open your inventory menu.");
	}

	public void initAttackOption(Player p) { //Cody - initalCombat attack option. This is before combat starts
		print("Good luck " + orange + p.getName() + reset + " may you be successful in your combat.");
	}

	public void initIgnoreOption(Monster monster) { //Cody - initalCombat ignore option. This is before combat starts
		print(orange + monster.getName() + reset + " Attacked you! Can't leave them on read so easily.\nStarting combat!");
	}

	public void initRunOption() { //Cody - initalCombat run option. This is before combat starts
		print("Run to the previous room! Whew, that was close!");
	}

	public void combatInventoryMenuPrompt() { //Cody - shows the inventory menu is open during combat
		print("Inventory Menu\nUse 'Exit' to leave this menu any time.");
	}

	public void monsterDefeat(Monster monster, Player p) { //Cody - displays when a monster is defeated
		print("You successfully defeated " + monster.getName() + "! " +
				green + "You have earned " + monster.getRandomCurrency() + reset + " claw bucks and monster dropped item: " + blue
				+ monster.getRandomItem().getName() + reset + "\nIt has been added to your inventory\n");
	}

	public void monsterAttacked(Monster monster, Player p) { //Cody - displays when monster attacks
		print("Monster attacked and hit you for " + (monster.getAttack()) + ". "
				+ green + "Remaining HP: " + p.getHP() + reset);
	}

	public void monsterAttackedDefending(Monster monster, Player p) { //Cody - displays when monster attacks and player is defending
		print("Monster attacked and hit you for " + (monster.getAttack() / 2) + ". "
				+ green + "Remaining HP: " + p.getHP() + reset);
	}

	public void printRunSuccess(Player p) { //Cody - displays upon run command during combat
		print("Player run percentage is " + p.getRunChance() + "%\nRun away successfully? " + p.runSuccess() + "\n");
	}

	public void playerDefeat() { //Cody - displays upon player death in combat
		print(red + "You have been defeated in battle. Regroup and try again!" + reset);
	}
	
	public void playerHardDefeat() {
		print(red + "You have been defeated in battle! You do not have the strength to go forth. You have perished." + reset);
	}

	public void playerDefending() { //Cody - displays when the player is defending during combat
		print("You are defending. Damage dealt will be half");
	}

	public void playerAttacked(Monster monster, Player p) { //Cody - displays when the player has attacked during combat
		print("You hit the monster for " + p.getAttack() + "! "
				+ "Monster has " + red + monster.getHP() + "hp left." + reset);
	}

	public void combatInventory(ArrayList<Item> playerInventory) { //Cody - shows the inventory while in combat
		print(orange + playerInventory.toString() + reset);
	}

	public void printExpelled() {
		print("How dare you try and attack " + blue + "Queen B" + reset + "?! You have been " + red + "EXPELLED!!!\n" + reset + orange + "Go back to the main menu and think about your actions." + reset);
	}
	
	public void finalBossNotification() {
		print(orange + "You hear a loud, menacing growl in the horizon! You know that it must be time for the final fight." + reset);
	}
	
	// ReAnn
	public void mainMenu() {print("" +
							    "\033[1m\033[93m  - Main Menu -\033[0m\n" +
			                    "\033[1m    New Game\n" +
			                    "New Hard Mode Game\n" +
								"    Load Game\n" +
								"    Menu Help\n" +
								"      Exit\033[0m\n");}

	// ReAnn
	public void pauseMenu() {print(
			"\033[1m\033[93m  - Pause Menu -\033[0m\n" +
					"\033[1m    New Game\n" +
					"New Hard Mode Game\n" +
					"    Load Game\n" +
					"    Save Game\n" +
					"    Menu Help\n" +
					"    Continue \n" +
					"      Exit\033[0m\n\n" +
					"Input Options: \"newgame\" | \"newhard\" | \"load\" | \"save\" | \"menuhelp\" | \"continue\" \"exit\"");}
	// End of Work

	public void mainMenuInputOptions() { //Displays main menu input options
		print("Input Options: \"newgame\" | \"newhard\" | \"load\" | \"menuhelp\" | \"exit\"");
	}

	public void gameIntro() { //Prints out the game intro
		print("\n=======================================================================================================================================================================================\n");
		print("\033[1m--- HAUNTED GROUNDS ---\033[0m\n");
		print("Welcome to the haunting adventure that takes place in GGC's now deserted campus.\n" +
				"Your main mission is to find your missing friend Jack.  \n" +
				"As you explore the dilapidated grounds, the looming presence of the campus spirit sends shivers down your spine. \n" +
				"With each step, you never know what spine-chilling encounter awaits you.\n" +
				"Though no student has attended this campus in ages, you must tread with caution because the campus spirit is still strong. \n" +
				"\033[1mBeware, you're in for a scare...\033[0m\n");
	}

	public void startingNew() { //When starting new game
		print("\nStarting a New Game...\n");
	}
	public void startingNewHard() { //When starting new hard game
		print("Starting a New Hard-mode Game!");
	}

	public void helpPrompt() { //printed after room description
		print("Type \"help\" for the list of game commands.");
	}

	public void inspectError() { //error message thrown on inspect command
		print("" +
				"\033[31mYou must enter the name of an item to use the inspect command.\033[0m");
	}

	public void youReallyDie() {
		print("Also, you're not like us. If you die... " + red + "you really die." + reset);
	}

	public void shopQuantityNotNumberError() {
		print("");
		print("\033[31mYou must send an integer for the quantity in this command.\033[0m");
	}

	public void shopQuantityError() {
		print("");
		print("\033[31mYou must send a quantity to use this command.\033[0m");
	}
	
	public void cobiEasterEgg() {
		print(lightYellow + "You " + red + "can't " + blue + "run " + orange + "and " + lightYellow + "you " + red + "can't " + blue + "hide!" + reset);
	}

	public void shopPrint(Item i) {
    	print(i.getName() + ": $" + i.getPrice());
    }
    
    public void endGame() {
    	print("And so, the campus spirit has been vanquished. You have saved your friend from the perilous paws of the campus spirit, vanquished many foes, solved many challenging puzzles, and even met a ghost teacher on the way. You and your friend return to where you came. The end.");
    }

	public void helpMenu() { //ReAnn & Cobi
		print("""
				 =======================================================================================================================================================================================
				            
				 GAME COMMANDS ->

				 \u001B[31mMENU:\u001B[0m
				 "menu" : When in the game, the user can use this command to display the main menu.
				 "menuhelp" : When in the main menu, the user can use this command to see information about the load and save features.
				 "newgame" : When in the main menu, this command allows the user to create a new game and save file.
				 "newhard" : When in the main menu, this command is the same as the new game command, but if the player dies during the game, the game is over and cannot be returned to.
				 "load" : When in the main menu, use this command to load a save.
				 "save" : When in the main menu, use this command to save all progress.
				 "continue" : When in the main menu, this command returns the user back into the current game(only displayed if the user is already in a game).
				 "exit" : When in the main menu, the user can use this command to leave the game.

				 \u001B[31mPLAYER/ROOM:\u001B[0m
				 "north" / "south" / "east" / "west" : Command used to move the player to the room in the specified direction if said room exists.
				 "exitroom" : When in a room, the user can use this command to return to the previous room.
				 "help" : When in the game, the user can use this command to display a list of commands followed by a brief description for each one.
				 "inspectroom" : When in a room, the user can use this command to see a list of all contents in the current room.
				 "lights" : When in a room with lights, the user can use this command to turn on the lights.
				 "inspect " + item : Users can use this command to read a description of an item located in the room or inventory.
				 "inventory" : Command used to view all items currently in the player's inventory. Also, displays current items equipped.
				 "startpuzzle" : When in a room with a puzzle, the user can use this command to start attempting to solve the puzzle.
				 "talk" : When in a rooom with an NPC, the user can use this command to initiate conversation with the NPC. 
				            
				 \u001B[31mCOMBAT:\u001B[0m
				 "attack" : When in combat, the user can use this command to attack the current enemy.
				 "defend" : When in combat, the user can use this command to defend against the current enemy's next attack.
				 "item" : When in combat, the user can use this command to use a consumable item or switch weapons.
				 "run" : When in combat, the user can use this command to run away from the current enemy.
				            
				 \u001B[31mPUZZLE/RIDDLE:\u001B[0m
				 "answer " + answer : When solving a puzzle, the user can use this command followed by user input to try and solve the puzzle.
				 "hint" : When solving a puzzle, the user can use this command to receive a hint.
				            
				 \u001B[31mITEMS:\u001B[0m
				 "pickup " + item name : Users can use this command to read a description of an item located in the room or inventory.
				 "drop " + item name : Users can use this command to drop an item followed by the name of the item.
				 "equip " + item name : Users can use this command to unequip an item followed by the name of the item. The item returns to the character's inventory.
				 "use " + item name : Users can use this command to use an item followed by the name of the item.
				 "drink " + item name : Users can use this command to drink an item followed by the name of the item.
				            
				 \u001B[31mNPCS/SHOP:\u001B[0m
				 "shop" : User can use this command for the purchase of consumables.
				 "riddle" : User can use this command to initiate a riddle with the NPC.
				 "leave" : User can use this command to leave the shop or end the conversion with the NPC.
				 "buy " + item name + quantity: When in the shop, the user can use this command to purchase consumables.
				 "list" : When in the shop, the user can use this command to list all items available for purchase in the shop.
				            
				 =======================================================================================================================================================================================                                                                          
				 """);
	}


	public void mainMenuHelp() {
		print("""
    =======================================================================================================================================================================================\n
				 MAIN MENU COMMANDS ->
				 
				\u001B[31m"newgame"\u001B[0m : Use this command to create a new game and save file.
				\u001B[31m"newhard"\u001B[0m : This command is the same as the new game command, but if the player dies during the game, the game is over and cannot be returned to.          			  
				\u001B[31m"load "\u001B[0m + save name : Use this command to load a save by inputting the previously determined save name.      	              
				\u001B[31m"menuhelp"\u001B[0m : Use this command to see a list of commands that can be used in the menu.
				\u001B[31m"exit"\u001B[0m : Use this command to leave the game.
				\n=======================================================================================================================================================================================
				 """);
	}

	public void pauseMenuHelp() {
		print("""
    =======================================================================================================================================================================================\n
				 PAUSE MENU COMMANDS ->
				 
				\u001B[31m"newgame"\u001B[0m : Use this command to create a new game and save file.
				\u001B[31m"newhard"\u001B[0m : This command is the same as the new game command, but if the player dies during the game, the game is over and cannot be returned to.          			  
				\u001B[31m"load "\u001B[0m + save name : Use this command to load a save by inputting the previously determined save name.      	              
				\u001B[31m"menuhelp"\u001B[0m : Use this command to see a list of commands that can be used in the menu.
				"continue" : Use this command to return to the current game.
				\u001B[31m"exit"\u001B[0m : Use this command to leave the game.
				\n=======================================================================================================================================================================================
				 """);
	}
}
