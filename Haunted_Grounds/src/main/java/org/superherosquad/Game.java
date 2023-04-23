package org.superherosquad;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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
    Shop shop; //Cobi
    private boolean hard = false; //Cobi
    //private static final long serialVersionUID = 1L; //For the save game method
    
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

    public void newGame() {
        gameRooms = reader.newRoom(); //Cody
        gameItems = reader.newItem(); //ReAnn
        gamePuzzles = reader.newPuzzle(); //Cobi
        gameMonsters = reader.newMonster(); //Cody
        gameNPCs = reader.newNPC(gamePuzzles); //Cobi
        //shop = reader.newShop(); //Cobi
        
        /***Cody***/
        reader.addItemToMonster(gameMonsters, gameItems);
        reader.addMonstersToRoom(gameRooms, gameMonsters);
        reader.addItemToRoom(gameRooms, gameItems);
        reader.addPuzzleToRoom(gameRooms, gamePuzzles);
        reader.addNPCToRoom(gameRooms, gameNPCs);

        p.setCurrentRoom(gameRooms.get(14)); //initialize the spawning room
        //System.out.println(p);
        //System.out.println(p.getCurrentRoom());
        /****END***/
    }

    public void saveGame(Player p) { //Cody
        ObjectOutputStream oos = null;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("playerSave.bin");
            oos = new ObjectOutputStream(fos); //Instantiate the ObjectOutputStream
            oos.writeObject(p); //Write the object to the file
        } catch (IOException ioe) {
            view.print("IOException!");
        } finally { //close the stream even if there is an exception thrown
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ioe) {
                view.print("Closing the outputstream failed.");
            }
        }
    }

    public Player loadGame(String fileName) { //Cody
        ObjectInputStream ois = null; //initialize a 'value' for ObejectInputStream
        FileInputStream fis;
        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            p = (Player) ois.readObject(); //set current player = the contents of the save file
            //loadedGame = (Game) ois.readObject();
        } catch (IOException | ClassNotFoundException ioe) { //multi catch statement instead of using 2 catch statements
            view.print(fileName);
            view.print("Either an IOException happened or the class couldn't be found! Youch!");
        } finally { //close the stream even if there is an exception thrown
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ioe) {
                view.print("Closing the input Stream failed buckoo");
            }
        }
        //return loadedGame;
        return p;
    }
    
    public static void main(String[] args) { //Cobi - this is run to start the game.
    	Game game = new Game();
    	game.newGame();
    	game.gameMode = 5;
    	while (true) { //Cobi
    		int m = game.controller.gamePlay(game.gameRooms, game.gameItems, game.gamePuzzles, game.gameMonsters, game.gameNPCs, game.p, game.gameMode, game.prevMode, game.saveMode);
    		
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
    			game.p.setName(input.nextLine());
    		}
    	}
    }
}