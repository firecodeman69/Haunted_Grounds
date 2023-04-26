/********************Collaboration*******************/
package org.superherosquad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.lang.Character;

public class Game {
    Player p = new Player(); //Cody
    Reader reader = new Reader(); //Cody
    private Controller controller = new Controller(); //Cody
    private static View view = new View(); //Cody
    private static Scanner input = new Scanner(System.in); //Cody
    
    private ArrayList<Room> gameRooms; //Cody
    private ArrayList<Item> gameItems; //ReAnn
    private ArrayList<Puzzle> gamePuzzles; //Cobi
    private ArrayList<Monster> gameMonsters; //Cody
    private ArrayList<NPC> gameNPCs; //Cobi
    private Shop shop = new Shop(); //Cobi
    private boolean hard = false; //Cobi
    private File saveFile = null; //Cobi - this is only tracked so it can be deleted if you die in hard mode.
    
    //Game modes - Cobi
    private int gameMode = -1; 
    private int prevMode = -1;
    private int saveMode = -1; //This mode is stored when the user goes into the pause menu.
    /**
     * Game Mode is an integer representing the state that the game is in.
     * 0 = free roam
     * 1 = combat
     * 2 = puzzle
     * 3 = talk
     * 4 = shop
     * 5 = initial menu
     * 6 = pause menu
     */

	//Credit: https://stackoverflow.com/questions/24191040/checking-to-see-if-a-string-is-letters-spaces-only
	public static boolean onlyLettersSpaces(String s){ //Cobi
		  for(int i = 0; i < s.length(); i++){
		    char ch = s.charAt(i);
		    if (Character.isLetter(ch) || ch == ' ') {
		      continue;
		    }
		    return false;
		  }
		  return true;
		}
    
    public void newGame(int mode) {
    	p = new Player();
        gameRooms = reader.newRoom(); //Cody
        gameItems = reader.newItem(); //ReAnn
        gamePuzzles = reader.newPuzzle(); //Cobi
        gameMonsters = reader.newMonster(); //Cody
        gameNPCs = reader.newNPC(gamePuzzles); //Cobi
        
        /***Cody***/
        reader.addItemToMonster(gameMonsters, gameItems);
        reader.addMonstersToRoom(gameRooms, gameMonsters);
        reader.addItemToRoom(gameRooms, gameItems);
        reader.addPuzzleToRoom(gameRooms, gamePuzzles);
        reader.addNPCToRoom(gameRooms, gameNPCs);

        gameMode = mode;
        p.setCurrentRoom(gameRooms.get(14)); //initialize the spawning room
        /****END***/
        
        for(Item i: gameItems) { //Cobi - adding all consumable items to the shop.
        	if(i.getType().charAt(0) == 'C') {
        		shop.getItems().add(i);
        	}
        } 
    }
    
	@SuppressWarnings({"unchecked"})
	public File loadGame() { //Cobi
		File file = null;
    	boolean valid = false;
    	String fileName = null;
    	
    	while(!valid) {
	        view.print("What is the name of the save file that you would like to load? Do not include the .dat extension. Enter 'back' to go back to the menu.");
	        view.userInput();
	        fileName = input.nextLine().trim();
	        
	        if(onlyLettersSpaces(fileName) && !fileName.isEmpty() && !fileName.equalsIgnoreCase("back")) { //Valid file name was entered.
		        valid = true; 
	        }
	        else if(fileName.equalsIgnoreCase("back")) { //User expresses the want to return to the menu that they were in.
	        	view.print("Returning to the menu.");
	        	return null;
	        }
	        else { //User has inputted an invalid command.
	        	view.print("That is not a valid file name");
	        }
    	}
    	fileName += ".dat";
    	file = new File(fileName);
    	
        ObjectInputStream ois = null; //initialize a 'value' for ObejectInputStream
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            gameRooms = (ArrayList<Room>) ois.readObject();
            gameItems = (ArrayList<Item>) ois.readObject();
            gamePuzzles = (ArrayList<Puzzle>) ois.readObject();
            gameMonsters = (ArrayList<Monster>) ois.readObject();
            gameNPCs = (ArrayList<NPC>) ois.readObject();
            p = (Player) ois.readObject();
            shop = (Shop) ois.readObject();
            gameMode = ois.readInt();
            prevMode = ois.readInt();
            saveMode = ois.readInt();
            hard = ois.readBoolean();
            
            if(gameMode == 6 && prevMode == 6) {
                view.print("");
                view.pauseMenu();
                view.userInput();
            }
            
        } catch (ClassNotFoundException cnf) {
        	view.print("ClassNotFoundException. Looks like we get to learn what this is.");
        	cnf.printStackTrace();
        } catch (FileNotFoundException fnf) {
        	view.print("There is no save file with that name. Returning to the menu.");
        	return null;
        } catch (IOException ioe) {
            view.print("IOException!");
            ioe.printStackTrace();
        } finally { //close the stream even if there is an exception thrown
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ioe) {
                view.print("The input stream couldn't be closed. How.");
            }
        }
        return file;
    }
    
    public static void main(String[] args) { //Cobi - this is run to start the game.
    	Game game = new Game();
    	game.newGame(5);
    	while (true) { //Cobi
    		
    		int m = game.controller.gamePlay(game.gameRooms, game.gameItems, game.gamePuzzles, game.gameMonsters, game.gameNPCs, game.p, game.shop, game.gameMode, game.prevMode, game.saveMode, game.hard);
    		
    		int setting = m / 10; //If this is a certain value, something special will happen after the gameplay loop.
    		int newMode = m % 10;
    		game.prevMode = game.gameMode;
    		game.gameMode = newMode;
    		
    		/*
    		 * This if statement stores the mode of the game before the pause menu was accessed.
    		 * Previous mode is meant to check if this is the first run of the loop in which the menu was accessed. If it is, it prints the menu's help message.
    		 * Save mode is meant to return the user to the previous mode of the game once continue is ran.
    		 */
    		
    		if(game.gameMode == 6 && game.prevMode != 6) { //If the pause menu was brought up, this stores the mode the game was in before the pause menu was brought up so it can return to that mode when the pause meneu is closed.
    			game.saveMode = setting;
    		}
    		
    		if(setting == 11) { //Dying in non-hard mode
    			view.print("Would you like to load from a save file? (Y/N)");
    			String choice = input.nextLine();
    			switch (choice) {
    				case "y", "Y" -> {
    					File temp = game.loadGame();
    					try {
    						Objects.requireNonNull(temp);
    					} catch (NullPointerException npe) {}
    				}
    				
    				case "n", "N" -> {
    					view.print("Returning to main menu.");
    				}
    			}
    		}
    		
    		if(setting == 10) { //Getting a 10 for setting means that the player died in hard mode, so say goodbye to your save file!
    			game.saveFile.delete();
    		}
    		
    		if(setting == 9 || setting == 8) { //This resets the game.
    			game.newGame(0);
    			
    			if(setting == 8) { //8 is passed in the hard mode activator.
    				game.hard = true;
    			} else { //9 is passed in the new game activator.
    				game.hard = false;
    			}
    			
    			view.print("What shall be the name of your character?");
                view.userInput();
    			game.p.setName(input.nextLine());
    		}
    		
    		if(setting == 7) { //This attempts to load a save file.
    			File temp = game.loadGame();
    			try {
    				Objects.requireNonNull(temp);
    				game.saveFile = temp; //Store the file that the current game was loaded from.
    			} catch (NullPointerException npe) { //npe is thrown if the user did not want to load a save file/tried to load a nonexistent save file. The previous mode is set to be a menu, so it won't print the welcoming menu messages, so we have to do it manually here.
                    if (game.gameMode == 5) {
                        view.gameIntro();
                        view.mainMenu();
                        view.mainMenuInputOptions();
                        view.userInput();
                    }

                    if (game.gameMode == 6) {
                        view.print("");
                        view.pauseMenu();
                        view.userInput();
                    }
    			} 
    		}
    	}
    }
}