/**************** ReAnn ********************/
package org.superherosquad;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private Scanner input = new Scanner(System.in);
    private View view = new View();

    /*
     * Main gameplay loop.
     * The reason this returns an integer is to change the game mode.
     */
    public int gamePlay(ArrayList<Room> rooms, ArrayList<Item> items, ArrayList<Puzzle> puzzle, ArrayList<Monster> monsters, ArrayList<NPC> npcs, Player p, int mode, int prevMode, int saveMode) {
        String playerInput;
        String[] tokens;
        switch (mode) {
            /*
             * First switch case checks the game mode.
             * The way the game interprets commands is based on this switch statement.
             */

            case 5: //Initial menu
            case 6: //Pause menu
                if (mode == 5 && prevMode != 5) { //Welcoming messages for the main menu.
                    view.print("Welcome to the Haunted Grounds game!\n\nMAIN MENU\n");
                    view.mainMenuHelp();
                }

                if (mode == 6 && prevMode != 6) { //Welcoming messages for the pause menu.
                    view.print("PAUSE MENU\n");
                    view.pauseMenuHelp();
                }

                playerInput = input.nextLine().toLowerCase(); //Parse user input
                tokens = playerInput.split(" ");

                switch (tokens[0]) { //Direct user input to the proper methods.
                    case "menuhelp": //Prints out the help message for the menu that the user is on.
                        if (mode == 5) {
                            view.mainMenuHelp();
                        }

                        if (mode == 6) {
                            view.pauseMenuHelp();
                        }
                        return mode;

                    case "exit": //Exit the program.
                        view.print("Thanks for playing!");
                        System.exit(0);
                        return mode;

                    case "continue": //Return the user from the pause menu to their currently-active game.
                        if (mode == 6) {
                            return 0;
                        } else {
                            view.invalid();
                        }
                        return mode;

                    case "newgame": //Create a new game by effectively resetting to the start state.
                        System.out.println("Starting a New Game!");
                        return 90;

                    case "newhard": //Create a new hard mode game.
                        System.out.println("Starting a New Hard-mode Game!");
                        return 80;

                    //TODO: Create the load game logic.
                    case "load": //Load a game from a previous save file
                        System.out.println("Loading game!");
                        //load game
                        return mode;

                    //TODO: Create the save game logic.
                    case "save": //Save a game from a previous save file
                        //save game
                        return mode;

                    default:
                        view.invalid();
                        return mode;
                }

        	case 0: //Navigating between rooms
        		view.room(p.getCurrentRoom().getName()); //Tells the player what room they are in.
        		view.print("Please input a command."); //Prompt the player for what they need to input.
                playerInput = input.nextLine().toLowerCase(); //Interpret player input.
                tokens = playerInput.split(" ");

                switch (tokens[0]) { //This is the first word of the input.

                    case "north": //Attempt to move to the room to the north.
                    case "n": //Included shorthand commands because I do not feel like typing out the entire word :)
                        p.moveRooms("n", rooms);
                        return mode;

                    case "south": //Attempt to move to the room to the south.
                    case "s":
                        p.moveRooms("s", rooms);
                        return mode;
                    case "east": //Attempt to move to the room to the east.
                    case "e":
                        p.moveRooms("e", rooms);
                        return mode;
                    case "west": //Attempt to move to the room to the west.
                    case "w":
                        p.moveRooms("w", rooms);
                        return mode;

                    case "exitroom": //Move to the room that the player was previously in.
                        p.exitRoom();
                        return mode;

                    case "inspectroom": //Inspect the room. This will start combat if there is a monster, tell the user that the room is dark if it is, or list the room's description, items, and puzzle.
                        mode = p.getCurrentRoom().inspect(p, mode);
                        return mode;

                    case "lights": //Turns the lights on in a dark room, or lets the user know the lights are already on.
                        p.getCurrentRoom().lightsOn();
                        return mode;

                    case "help": //Prints out the help menu.
                        view.helpMenu();
                        return mode;

                    default:
                        view.invalid();
                        return mode;
                }

            case 1:
                boolean playerTurn = true;
                boolean monsterTurn = false;
                boolean defending = false;
                Monster monster = p.getCurrentRoom().getRoomMonster();
                System.out.println(monster.getName() + " Attacked you! Starting combat!");
                while (monster.isAlive() && p.isAlive()) {

                    if (playerTurn) {
						view.print("What would you like to do?\n(A)ttack, (D)efend, Use {item name}, (R)un");
						playerInput = input.nextLine().toLowerCase();
						tokens = playerInput.split(" ");
                        switch (tokens[0]) {
                            case "attack":
                            case "a":
                                monster.loseHP(p.getAttack()); //if player attacks, deal damage to monster
                                System.out.println("You hit the monster for " + p.getAttack() + "! " +
                                        "Monster has " + monster.getHP() + "hp left.");
                                playerTurn = false;
                                monsterTurn = true;
                                break;
                            case "defend":
                            case "d":
                                defending = true;
                                System.out.println("You are defending.");
                                playerTurn = false;
                                monsterTurn = true;
                                break;
                            case "use":
                                if (p.hasItem(tokens[1])) {
                                    p.useConsumableItem(tokens[1]); //add item effect to player's health
                                }
                                playerTurn = false;
                                monsterTurn = true;
                                break;
                            case "run":
                            case "r":
                                p.setRunChance(monster);
                                System.out.println("Player run percentage is " + p.getRunChance() + "\nRun away successfully? " + p.runSuccess(monster));
                                playerTurn = false;
                                monsterTurn = true;
                                break;
                        }
                    } else if (monsterTurn) {
                        if (defending) {
                            p.loseHP(monster.getAttack() / 2); //if player defends, deal half of monster attack
                            System.out.println("Monster attacked and hit you for " + (monster.getAttack() / 2) + ". " +
                                    "Remaining HP: " + p.getHP());
                            playerTurn = true;
                            monsterTurn = false;
                        } else {
                            p.loseHP(monster.getAttack());
                            System.out.println("Monster attacked and hit you for " + (monster.getAttack()) + ". " +
                                    "Remaining HP: " + p.getHP());
                            playerTurn = true;
                            monsterTurn = false;
                        }
                    }
                } if (monster.isAlive() && !p.isAlive()) {
                System.out.println("You have been defeated in battle. Regroup and try again!");
                mode = 5;
                break;
            } else {
                System.out.println("You successfully defeated " + monster.getName() + "! " +
                        "You have earned " + monster.getCurrency() + " and gained items: " +
                        monster.getMonsterInventory());
                p.addItemsToInventory(monster.getMonsterInventory());
                p.addCurrency(monster.getCurrency());
                mode = prevMode;
                break;
            }
        }
        return mode;
    }
}