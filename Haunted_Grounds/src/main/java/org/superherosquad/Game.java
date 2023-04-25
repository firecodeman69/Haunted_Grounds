package org.superherosquad;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Character;

public class Game {
    Player p = new Player();
    Reader reader = new Reader();
    private Controller controller = new Controller();
    private static View view = new View();
    private static Scanner input = new Scanner(System.in);
    
    private ArrayList<Room> gameRooms; //Cody
    private ArrayList<Item> gameItems; //ReAnn
    private ArrayList<Puzzle> gamePuzzles; //Cobi
    private ArrayList<Monster> gameMonsters; //Cody
    private ArrayList<NPC> gameNPCs; //Cobi
    private Shop shop = new Shop(); //Cobi
    private boolean hard = false; //Cobi
    
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
	public static boolean onlyLettersSpaces(String s){
		  for(int i = 0; i < s.length(); i++){
		    char ch = s.charAt(i);
		    if (Character.isLetter(ch) || ch == ' ') {
		      continue;
		    }
		    return false;
		  }
		  return true;
		}
    
    public void newGame() {
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
        
        for(Item i: gameItems) { //Cobi - adding all consumable items to the shop.
        	if(i.getType().equalsIgnoreCase("c")); {
        		shop.getItems().add(i);
        	}
        }

        p.setCurrentRoom(gameRooms.get(14)); //initialize the spawning room
        /****END***/
    }
    
	@SuppressWarnings({"unchecked"})
	public void loadGame() { //Cobi
    	boolean valid = false;
    	String fileName = null;
    	while(!valid) {
	        view.print("What is the name of the save file that you would like to load? Do not include the .dat extension.");
	        fileName = input.nextLine().trim();
	        if(!onlyLettersSpaces(fileName) || fileName.isEmpty()) {
	        	view.print("That is not a valid file name.");
	        	continue;
	        }
	        valid = true;
    	}
    	fileName.concat(".dat");
    	
        ObjectInputStream ois = null; //initialize a 'value' for ObejectInputStream
        try {
            ois = new ObjectInputStream(new FileInputStream(fileName));
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
        } catch (ClassNotFoundException cnf) {
        	view.print("ClassNotFoundException. Looks like we get to learn what this is.");
        	cnf.printStackTrace();
        } catch (FileNotFoundException fnf) {
        	view.print("There is no save file with that name.");
        } catch (IOException ioe) {
            view.print("IOException!");
        } finally { //close the stream even if there is an exception thrown
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ioe) {
                view.print("The input stream couldn't be closed. How.");
            }
        }
    }
    
    public static void main(String[] args) { //Cobi - this is run to start the game.
    	Game game = new Game();
    	game.newGame();
    	game.gameMode = 5;
    	while (true) { //Cobi
    		int m = game.controller.gamePlay(game.gameRooms, game.gameItems, game.gamePuzzles, game.gameMonsters, game.gameNPCs, game.p, game.shop, game.gameMode, game.prevMode, game.saveMode, game.hard);
    		
    		int setting = m / 10;
    		int newMode = m % 10;
    		game.prevMode = game.gameMode;
    		game.gameMode = newMode;
    		
    		/*
    		 * This if statement stores the mode of the game before the pause menu was accessed.
    		 * Previous mode is meant to check if this is the first run of the loop in which the menu was accessed. If it is, it prints the menu's help message.
    		 * Save mode is meant to return the user to the previous mode of the game once continue is ran.
    		 */
    		if(game.gameMode == 6 && game.prevMode != 6) {
    			game.saveMode = setting;
    		}
    		
    		if(setting == 9 || setting == 8) { //Cobi - this resets the game.
    			game.gameMode = 0;
    			game.prevMode = -1;
    			if(setting == 8) { //80 is returned by the new hard mode game activator.
    				game.hard = true;
    			}
    			else { //90 is returned by the new game activator - this is here in case a regular game is started after a hard game.
    				game.hard = false;
    			}
    			
    			view.print("What shall be the name of your character?");
                view.userInput();
    			game.p.setName(input.nextLine());
    		}
    		
    		if(setting == 7) { //Cobi - this loads the save file.
    			game.loadGame();
    		}
    	}
    }
}