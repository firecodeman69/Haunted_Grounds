package org.superherosquad;

import java.io.*;
import java.util.ArrayList;

public class Game {

    public static void main(String[] args) {
        Game game = new Game();
        game.newGame();
        //System.out.println("Hello world!");
        Player p1 = new Player(1, "Cody", 100, 0, "First player of the game.", 25, 25, 25,0);
        //Character c2 = new Character(2, "ReAnn", 100, 0, "First player of the game.", 25, 25, 25);
        //Character c3 = new Character(3, "TreMya", 100, 0, "First player of the game.", 25, 25, 25);
        //Character c4 = new Character(4, "Cobi", 100, 0, "First player of the game.", 25, 25, 25);
        //System.out.println(c1);
        game.saveGame(p1);

        game.loadGame("playerSave.txt");
        System.out.println(p1);
    }


    /***************************Cody********************/
    //todo: determine if this is the right place for this to go
    //todo: determine if this is the right approach
    Reader reader = new Reader();
    ArrayList<Room> gameRooms; //Cody
    //ArrayList<Item> gameItems; //ReAnn
    ArrayList<Puzzle> gamePuzzles; //Cobi
    ArrayList<Monster> gameMonsters; //Cody
    ArrayList<NPC> gameNPCs; //Cobi
    //Shop shop; //Cobi
    //Controller c1 = new Controller();
    Player p;
    public void newGame() {
        gameRooms = reader.newRoom(); //Cody
        for (Room r: gameRooms) {
            System.out.println(r);
        }

        //gameItems = reader.newItem(); //ReAnn
//        for (Item i: gameItems) {
//            System.out.println(r);
//        }

        gamePuzzles = reader.newPuzzle(); //Cobi
        for (Puzzle p: gamePuzzles) {
            System.out.println(p);
        }

        gameMonsters = reader.newMonster(); //Cody
        for (Monster m: gameMonsters) {
            System.out.println(m);
        }

        gameNPCs = reader.newNPC(gamePuzzles); //Cobi
        for (NPC npc: gameNPCs) {
            System.out.println(npc);
        }
        //shop = reader.newShop(); //Cobi
        p = new Player();
    }

    public void saveGame(Player player) {
        ObjectOutputStream oos = null;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("playerSave.txt");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(player);
        } catch (IOException ioe) {
            //todo: change this to do nothing?
            System.out.println("File not found!!");
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

    public Player loadGame(String fileName) {
        ObjectInputStream ois = null; //initialize a 'value' for ObejectInputStream
        FileInputStream fis;
        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            p = (Player) ois.readObject(); //set current player = the contents of the save file
        } catch (IOException | ClassNotFoundException ioe) { //multi catch statement instead of using 2 catch statements
            //todo: change this to do nothing?
            System.out.println("File not found!!");
        } finally { //close the stream even if there is an exception thrown
            try {
                if (ois != null) { //todo:is there a better way to handle this?
                    ois.close();
                }
            } catch (IOException ioe) {
                System.out.println("Closing the input Stream failed buckoo");
            }
        }
        return p;
    }
}