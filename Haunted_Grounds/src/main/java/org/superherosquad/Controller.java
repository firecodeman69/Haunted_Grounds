/**************** ReAnn ********************/
package org.superherosquad;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private Scanner input = new Scanner(System.in);
    private View view = new View();

    public int gamePlay(ArrayList<Room> rooms, ArrayList<Item> items, ArrayList<Puzzle> puzzle, ArrayList<Monster> monsters, ArrayList<NPC> npcs, Player p, int mode, int prevMode) {
    	String playerInput;
    	String[] tokens;
    	
        switch (mode) {
        /*
         * First switch case checks the game mode.
         * The way the game interprets commands is based on this switch statement.
         */
        	
        	case 5: //Initial menu
        	case 6: //Pause menu
        		String mainhelp = 
        		      """
          			  List of main menu commands:
          			  
      	              "menuhelp" : Use this command to see a list of commands that can be used in the menu.
      	              "newgame" : Use this command to create a new game and save file.
      	              "newhard" : This command is the same as the new game command, but if the player dies during the game, the game is over and cannot be returned to.
      	              "load " + save name : Use this command to load a save by inputting the previously determined save name.
      	              "exit" : Use this command to leave the game.
          			  """;
        		
        		String pausehelp = 
    				  """
        			  List of pause menu commands:
        			  
    	              "menuhelp" : Use this command to see a list of commands that can be used in the menu.
    				  "continue" : Use this command to return to the current game.      	              
    	              "newgame" : Use this command to create a new game and save file.
    	              "newhard" : This command is the same as the new game command, but if the player dies during the game, the game is over and cannot be returned to.
    	              "load " + save name : Use this command to load a save by inputting the previously determined save name.
    	              "save " + save name : Use this command with a save name to save all progress. This command saves all information about the player and the map.
    	              "exit" : Use this command to leave the game.
        			  """;
        		
        		if(mode == 5 && prevMode != 5) { //Welcoming messages for the main menu.
        			view.print("Welcome to the Haunted Grounds game!\nMAIN MENU\n");
        			view.print(mainhelp);
        		}
        		
        		if(mode == 6 && prevMode != 6) { //Welcoming messages for the pause menu.
        			view.print("PAUSE MENU");
        			view.print(pausehelp);
        		}
        		
                playerInput = input.nextLine().toLowerCase(); //Parse user input
                tokens = playerInput.split(" ");
                
        		switch(tokens[0]) { //Direct user input to the proper methods.
		    		case "menuhelp": //Prints out the help message for the menu that the user is on.
		    			if(mode == 5) {
		    				view.print(mainhelp);
		    			}
		    			
		    			if(mode == 6) {
		    				view.print(pausehelp);
		    			}
		    			return mode;
		    			
		    		case "exit": //Exit the program.
		    			view.print("Thanks for playing!");
		    			System.exit(0);
		    			return mode;
		    		
		    		case "continue": //Return the user from the pause menu to their currently-active game.
		    			if(mode == 6) {
		    				return 0;
		    			}
		    			else {
		    				view.invalid();
		    			}
		    			return mode;
		    		
		    		//TODO: Create the new game logic.
		        	case "newgame": //Create a new game.
		        		System.out.println("Starting a New Game!");
		        		//new game logic
		        		return 0;
		        		
		        	//TODO: Create the new hard game logic.
		          	case "newhard": //Create a new hard mode game.
		          		System.out.println("Starting a New Hard-mode Game!");
		            	//hard game logic 
		          		return mode;
		
		          	//TODO: Create the new game logic.
		        	case "load": //Load a game from a previous save file
		        		System.out.println("Loading game!");
		        		//load game
		        		return mode;
		        		
		        	default: //This is reached if none of the previous cases were reached.
		        		view.invalid();
		        		return mode;
        		}
        		
        	case 0:
        		view.print("Please input a direction.");
                playerInput = input.nextLine().toLowerCase();
                tokens = playerInput.split(" ");
                
        		switch (tokens[0]) {
		        case "north":
		        case "n":
		            view.print("Do the north thing!");
		            p.moveRooms("n", rooms);
		            return mode;
		        case "south":
		        case "s":
	                view.print("Do the south thing.");
	                p.moveRooms("s", rooms);
	                return mode;
	            case "east":
	            case "e":
	                view.print("Do the east thing.");
	                p.moveRooms("e", rooms);
	                return mode;
	            case "west":
	            case "w":
	                view.print("Do the west thing.");
	                p.moveRooms("w", rooms);
	                return mode;
	            default:
	            	view.invalid();
	            	return mode;
        		}
        }  
    return mode;
    }
}