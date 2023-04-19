package org.superherosquad;

import java.util.ArrayList;

public class Game {

    public static void main(String[] args) {
        Game game = new Game();
        game.newGame();
        //System.out.println("Hello world!");
        //Character c1 = new Character(1, "Cody", 100, 0, "First player of the game.", 25, 25, 25);
        //Character c2 = new Character(1, "Cody", 100, 0, "First player of the game.", 25, 25, 25);
        //Character c3 = new Character(1, "Cody", 100, 0, "First player of the game.", 25, 25, 25);
        //Character c4 = new Character(1, "Cody", 100, 0, "First player of the game.", 25, 25, 25);
        //System.out.println(c1);
    }


    /***************************Cody********************/
    //todo: determine if this is the right place for this to go
    //todo: determine if this is the right approach
    Reader reader = new Reader();
    //ArrayList<Room> gameRooms; //TreMya
    //ArrayList<Item> gameItems; //ReAnn
    ArrayList<Puzzle> gamePuzzles; //Cobi
    ArrayList<Monster> gameMonsters; //Cody
    ArrayList<NPC> gameNPCs; //Cobi
    //Shop shop; //Cobi
    //Controller c1 = new Controller();
    Player p;
    public void newGame() {
        //gameRooms = reader.newRoom(); //TreMya
        //gameItems = reader.newItem(); //ReAnn
        gamePuzzles = reader.newPuzzle(); //Cobi

        gameMonsters = reader.newMonster(); //Cody
//        for (Monster m: gameMonsters) {
//            System.out.println(m);
//        }

        gameNPCs = reader.newNPC(gamePuzzles); //Cobi
        //shop = reader.newShop(); //Cobi
        p = new Player();
    }
}