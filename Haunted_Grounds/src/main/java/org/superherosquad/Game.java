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
        addItemToMonster();
        addMonstersToRoom();
        addItemToRoom();
        addPuzzleToRoom();
        addNPCToRoom();

        p.setCurrentRoom(gameRooms.get(14));
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
            System.out.println("IOException!");
        } finally { //close the stream even if there is an exception thrown
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ioe) {
                System.out.println("Closing the outputstream failed.");
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
            System.out.println(fileName);
            System.out.println("Either an IOException happened or the class couldn't be found! Youch!");
        } finally { //close the stream even if there is an exception thrown
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ioe) {
                System.out.println("Closing the input Stream failed buckoo");
            }
        }
        //return loadedGame;
        return p;
    }

    public void addMonstersToRoom() { //Cody - adds monsters to rooms
        for (Room r : gameRooms) {
            for (Monster m : gameMonsters) {
                if (r.getMonsterId() == m.getId()) {
                    r.setMonster(m);
                }
            }
        }
    }

    public void addPuzzleToRoom() { //Cody - adds puzzles to rooms
        for (Room r : gameRooms) {
            for (Puzzle p : gamePuzzles) {
                if (r.getPuzzleId() == p.getId()) {
                    r.setPuzzle(p);
                }
            }
        }
    }

    public void addItemToRoom() { //Cody - adds items to rooms
        for (Room r : gameRooms) {
            for (Item i: gameItems) {
                if (r.getItemId() == i.getId()) {
                    r.addItem(i);
                }
            }
        }
    }

    public void addItemToMonster() { //Cody - adds items to rooms
        for (Monster m : gameMonsters) {
            for (Item item: gameItems) {
                for (int i = 0; i < m.getMonsterItemAssociations().length; i++) {
                    if (m.getMonsterItemAssociations()[i] == item.getId()) m.addItems(item);
                }
            }
        }
    }

    public void addNPCToRoom() { //Cody - adds puzzles to rooms
        for (Room r : gameRooms) {
            for (NPC npc : gameNPCs) {
                if (r.getPuzzleId() == npc.getId()) {
                    r.setNPC(npc);
                }
            }
        }
    }
    
    public static void main(String[] args) { //Cobi - this is run to start the game.
    	Game game = new Game();
    	game.newGame();
    	game.gameMode = 5;
    	while (true) {
    		int m = game.controller.gamePlay(game.gameRooms, game.gameItems, game.gamePuzzles, game.gameMonsters, game.gameNPCs, game.p, game.gameMode, game.prevMode);
    		int setting = m / 10;
    		int newMode = m % 10;
    		game.prevMode = game.gameMode;
    		game.gameMode = newMode;
    		
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