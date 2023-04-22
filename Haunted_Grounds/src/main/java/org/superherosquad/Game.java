package org.superherosquad;

import java.io.*;
import java.util.ArrayList;

public class Game implements Serializable {

    public static void main(String[] args) {
        Game game = new Game();
        game.newGame();

        //System.out.println(c1);

        //game.saveGame(game); //Not currently working
        
        //game.loadGame("saveGame.txt"); //Not currently working

        System.out.println(game.p);

        //System.out.println(game.gameMonsters);
        //System.out.println(game.gameRooms);
        //System.out.println(game.gameNPCs);
        //System.out.println(game.gamePuzzles);
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
    private static final long serialVersionUID = 1L; //For the save game method
    Player p;
    public void newGame() {
        gameRooms = reader.newRoom(); //Cody
//        for (Room r: gameRooms) {
//            System.out.println(r);
//        }

        //gameItems = reader.newItem(); //ReAnn
//        for (Item i: gameItems) {
//            System.out.println(r);
//        }

        gamePuzzles = reader.newPuzzle(); //Cobi
//        for (Puzzle p: gamePuzzles) {
//            System.out.println(p);
//        }

        gameMonsters = reader.newMonster(); //Cody


        gameNPCs = reader.newNPC(gamePuzzles); //Cobi
//        for (NPC npc: gameNPCs) {
//            System.out.println(npc);
//        }
        //shop = reader.newShop(); //Cobi
        p = new Player();
        addMonstersToRoom();

        p.setId(0);
        p.setName("Cody");
        p.setHP(100);
        p.setCurrency(100);
        p.setDescription("First player of the game.");
        p.setSpeed(25);
        p.setDefense(25);
        p.setAttack(25);
        p.setCurrentRoom(gameRooms.get(0));
        //System.out.println(p);
    }

    public void saveGame(Game game) { //Cody
        ObjectOutputStream oos = null;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("saveGame.txt");
            oos = new ObjectOutputStream(fos); //Instantiate the ObjectOutputStream
            oos.writeObject(game); //Write the object to the file
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

    public Game loadGame(String fileName) { //Cody
        ObjectInputStream ois = null; //initialize a 'value' for ObejectInputStream
        FileInputStream fis;
        Game loadedGame = null;
        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            //p = (Player) ois.readObject(); //set current player = the contents of the save file
            loadedGame = (Game) ois.readObject();
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
        return loadedGame;
    }

    public void addMonstersToRoom() { //Cody
        for (Monster m: gameMonsters) {
            int[] locations = m.getMonsterLocation();
            for (int i = 0; i < locations.length; i++) {
                for (Room r: gameRooms) {
                    if (locations[i] == r.getId()) {
                        r.setMonster(m);
                    }
                }
            }
        }
    }//End of method

}