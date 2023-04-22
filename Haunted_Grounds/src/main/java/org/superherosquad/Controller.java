/**************** ReAnn ********************/
package org.superherosquad;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private Scanner input = new Scanner(System.in);
    private View view = new View();
    private String invalid = "That is an invalid command.";

    public void gamePlay(ArrayList<Room> rooms, ArrayList<Item> items, ArrayList<Puzzle> puzzle, ArrayList<Monster> monsters, ArrayList<NPC> npcs, Player p, int mode) {
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
        		
        		if(mode == 5) { //Welcoming messages for the main menu.
        			view.print("Welcome to the Haunted Grounds game!\nMAIN MENU\n");
        			view.print(mainhelp);
        		}
        		
        		if(mode == 6) { //Welcoming messages for the pause menu.
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
		    			break;
		    			
		    		case "exit": //Exit the program.
		    			view.print("Thanks for playing!");
		    			System.exit(0);
		    		
		    		//TODO: Fix the continue command to where it brings you to the mode you were in before you entered the menu.
		    		case "continue": //Return the user from the pause menu to their currently-active game.
		    			if(mode == 6) {
		    				mode = 0;
		    			}
		    			break;
		    		
		    		//TODO: Create the new game logic.
		        	case "new": //Create a new game.
		        		System.out.println("Starting a New Game!");
		        		//new game logic
		        		break;
		        		
		        	//TODO: Create the new hard game logic.
		          	case "hard": //Create a new hard mode game.
		          		System.out.println("Starting a New Hard-mode Game!");
		            	//hard game logic 
		          		break;
		
		          	//TODO: Create the new game logic.
		        	case "load": //Load a game from a previous save file
		        		System.out.println("Loading game!");
		        		//load game
		        		break;
		        		
		        	default: //This is reached if none of the previous cases were reached.
		        		view.print(invalid);
		        		break;
        		}
        		break;
        		
        	case 0:
                playerInput = input.nextLine().toLowerCase();
                tokens = playerInput.split(" ");
                
        		switch (tokens[0]) {
		        case "north":
		            System.out.println("Do the north thing.");
		            if(p.currentRoom.getNorthRoom() != -1) p.setCurrentRoom(rooms.get(p.currentRoom.getNorthRoom()));
		            else System.out.println("You can't go that way honey-boo-boo!");
		            break;
		        case "south":
	                System.out.println("Do the south thing.");
	                if(p.currentRoom.getSouthRoom() != -1) p.setCurrentRoom(rooms.get(p.currentRoom.getSouthRoom()));
	                else System.out.println("You can't go that way honey-boo-boo!");
	                break;
	            case "east":
	                System.out.println("Do the east thing.");
	                if(p.currentRoom.getEastRoom() != -1) p.setCurrentRoom(rooms.get(p.currentRoom.getEastRoom()));
	                else System.out.println("You can't go that way honey-boo-boo!");
	                break;
	            case "west":
	                System.out.println("Do the west thing.");
	                if(p.currentRoom.getWestRoom() != -1) p.setCurrentRoom(rooms.get(p.currentRoom.getWestRoom()));
	                else System.out.println("You can't go that way honey-boo-boo!");
	                break;
        		}
        }  
    }
}