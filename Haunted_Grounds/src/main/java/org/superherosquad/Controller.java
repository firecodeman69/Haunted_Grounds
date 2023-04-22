/**************** ReAnn ********************/
package org.superherosquad;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private Scanner input;
    private ArrayList<Room> gameRooms; //Cody
    private ArrayList<Item> gameItems; //ReAnn
    private ArrayList<Puzzle> gamePuzzles; //Cobi
    private ArrayList<Monster> gameMonsters; //Cody
    private ArrayList<NPC> gameNPCs; //Cobi
    //Shop shop; //Cobi
    private static final long serialVersionUID = 1L; //For the save game method
    Player p = new Player();
    Reader reader = new Reader();

    public void gamePlay(Controller c1) {
        input = new Scanner(System.in);
        String playerInput;
        boolean playing = true;
        boolean menuOpen = true;
        while (playing) {
            if (menuOpen) {
                c1.mainMenu(c1);
                menuOpen = false;
            }
            else {
                System.out.println("Game begin!");
                break;
            }

            playerInput = input.nextLine().toLowerCase();
            switch (playerInput) {
                case "north":
                    System.out.println("Do the north thing.");
                    if(p.currentRoom.getNorthRoom() != -1) p.setCurrentRoom(gameRooms.get(p.currentRoom.getNorthRoom()));
                    else System.out.println("You can't go that way honey-boo-boo!");
                case "south":
                    System.out.println("Do the south thing.");
                    if(p.currentRoom.getSouthRoom() != -1) p.setCurrentRoom(gameRooms.get(p.currentRoom.getSouthRoom()));
                    else System.out.println("You can't go that way honey-boo-boo!");
                case "east":
                    System.out.println("Do the east thing.");
                    if(p.currentRoom.getEastRoom() != -1) p.setCurrentRoom(gameRooms.get(p.currentRoom.getEastRoom()));
                    else System.out.println("You can't go that way honey-boo-boo!");
                case "west":
                    System.out.println("Do the west thing.");
                    if(p.currentRoom.getWestRoom() != -1) p.setCurrentRoom(gameRooms.get(p.currentRoom.getWestRoom()));
                    else System.out.println("You can't go that way honey-boo-boo!");
            }
        }
    }

    public void mainMenu(Controller c1) {
        // TODO: Implement game mechanics and logic here
        input = new Scanner(System.in);
        String playerInput;
        System.out.println("Welcome to the Haunted Grounds game!");
        boolean menuOpen = true;

        while (menuOpen) {
            System.out.println("Please enter a command.");
            playerInput = input.nextLine().toLowerCase();
            String[] commands = playerInput.split(" ");
            System.out.println("You entered: " + playerInput);


            //Main menu
            switch (commands[0]) {
                case "quit":
                    System.out.println("Thanks for playing!");
                    System.exit(0);
                    break;
                case "new":
//                case "hard":
//                    System.out.println("Starting a New Hard-mode Game!");
//                    menuOpen = false;
//                    //hard game logic
//                    break;
                    System.out.println("Starting a New Game!");
                    c1.newGame(c1);
//                    c1.gamePlay(c1);
                    break;
                case "load":
                    System.out.println("Loading game!");
                    c1.loadGame("playerSave.bin");
                    break;
            }
            menuOpen = false;
        }
    }

    public void newGame(Controller c1) {
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

        p.setId(0);
        p.setName("Character 1");
        p.setHP(1000000);
        p.setCurrency(9999999);
        p.setDescription("First player of the game.");
        p.setSpeed(250000);
        p.setDefense(250000);
        p.setAttack(2500000);
        p.setCurrentRoom(gameRooms.get(0));
        System.out.println(p);
        System.out.println(p.getCurrentRoom());
        c1.gamePlay(c1);
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
            System.out.println("IOException! Oh no!");
        } finally { //close the stream even if there is an exception thrown
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ioe) {
                System.out.println("Closing the outputstream failed bucko");
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
    }//End of method

    public void addPuzzleToRoom() { //Cody - adds puzzles to rooms
        for (Room r : gameRooms) {
            for (Puzzle p : gamePuzzles) {
                if (r.getPuzzleId() == p.getId()) {
                    r.setPuzzle(p);
                }
            }
        }
    }//End of method

    public void addItemToRoom() { //Cody - adds items to rooms
        for (Room r : gameRooms) {
            for (Item i: gameItems) {
                if (r.getItemId() == i.getId()) {
                    r.addItem(i);
                }
            }
        }
    }//End of method

    public void addItemToMonster() { //Cody - adds items to rooms
        for (Monster m : gameMonsters) {
            for (Item item: gameItems) {
                for (int i = 0; i < m.getMonsterItemAssociations().length; i++) {
                    if (m.getMonsterItemAssociations()[i] == item.getId()) m.addItems(item);
                }
            }
        }
    }//End of method

    public void addNPCToRoom() { //Cody - adds puzzles to rooms
        for (Room r : gameRooms) {
            for (NPC npc : gameNPCs) {
                if (r.getPuzzleId() == npc.getId()) {
                    r.setNPC(npc);
                }
            }
        }
    }//End of method
}
