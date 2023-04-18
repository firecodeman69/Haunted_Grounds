/**************** Cody ********************/
package org.superherosquad;

import java.util.ArrayList;

public class Player extends Character {

    //ArrayList<Item> playerInventory;
    //ArrayList<Item> equippedItems;
    int currentLocation;
    int previousLocation;
    public Player(int Id, String name, int hp, int currency, String description,
                  int speed, int defense, int attack,
                  int currentLocation) {
        super(Id, name, hp, currency, description, speed, defense, attack);
        //this.playerInventory = new ArrayList<Item>();
        //this.equippedItems = new ArrayList<Item>();
        this.currentLocation = currentLocation;
    }

//    public ArrayList<Item> showInventory() {
//        return playerInventory.toString;
//    }

//    public String addItemToInventory(Item item) {
//        playerInventory.add(item);
//        return (item.getName() + " was added to your inventory. Use command Inventory to see it now.");
//    }

//    public void addItemsToInventory(ArrayList<Item> itemAL) {
//        playerInventory.addAll(itemAL);
//    }


//    public void dropItem(Item item) {
//        for (Item i: equippedItems) {
//          if(i.getName.equalsIgnoreCase(item)) {
//              playerInventory.remove(item);
//          }
//        }
//    }

//        public void equipItem(Item item) {
//        equippedItems.add(item);
//    }

//        public void unEquipItem(Item item) {
//        for (Item i: equippedItems) {
//          if(i.getName.equalsIgnoreCase(item)) {
//              equippedItems.remove(item);
//          }
//        }
//    }

//    public String showInventory() {
//        if (playerInventory != null) {
//            return playerInventory.toString();
//        } else {
//            return ("You don't have any items in your inventory.");
//        }
//    }

//    public Item explore(String name) {
//        for (Item i : currentRoom.roomItems) {
//            if (i.getName().equalsIgnoreCase(name)) {
//                return i;
//            }
//        }
//        return null;
//    }

 /*   //todo: change the helpmenu to reflect the menu of our game
    public String helpMenu() {
        return """
                =======================================================================================================================================================================================
                GAME COMMANDS ->

                MENU:
                "menu" : When in the game, the user can use this command to display the main menu.
                "menuhelp" : When in the main menu, the user can use this command to see information about the load and save features.
                "new game" : When in the main menu, this command allows the user to create a new game and save file.
                "new hard mode game" : When in the main menu, this command is the same as the new game command, but if the player dies during the game, the game is over and cannot be returned to.
                "load " + save name : When in the main menu, use this command to load a save by inputting the previously determined save name.
                "save " + save name : When in the main menu, use this command with a save name to save all progress. This command saves all information about the player and the map.
                "continue" : When in the main menu, this command returns the user back into the current game(only displayed if the user is already in a game).
                "exit" : When in the main menu, the user can use this command to leave the game.

                PLAYER:
                "move" + direction : Command used to move in the specified direction of 'North', 'South', 'East', or 'West'.
                "help" : When in the game, the user can use this command to display a list of commands followed by a brief description for each one.
                "inspect " + item : Users can use this command to read a description of an item located in the room or inventory.
                "inventory" : Command used to view all items currently in the player's inventory. Also, displays current items equipped.
                
                COMBAT:
                "attack" : When in combat, the user can use this command to attack the current enemy.
                "defend" : When in combat, the user can use this command to defend against the current enemy's next attack.
                "item" : When in combat, the user can use this command to use a consumable item or switch weapons.
                "run" : When in combat, the user can use this command to run away from the current enemy.
                
                PUZZLE/RIDDLE:
                "answer " + answer : When solving a puzzle, the user can use this command followed by user input to try and solve the puzzle.
                "hint" : When solving a puzzle, the user can use this command to receive a hint.
                
                ITEMS:
                "pickup" + item name : Users can use this command to read a description of an item located in the room or inventory.
                "drop " + item name : Users can use this command to drop an item followed by the name of the item.
                "equip " + item name : Users can use this command to unequip an item followed by the name of the item. The item returns to the character's inventory.
                "use " + item name : Users can use this command to use an item followed by the name of the item.
                "drink " + item name : Users can use this command to drink an item followed by the name of the item.
                "inspectroom" : When in a room, the user can use this command to see a list of all contents in the current room.
                "exitroom" : When in a room, the user can use this command to return to the previous room.
                "turn on lights" : When in a room with lights, the user can use this command to turn on the lights.
                
                NPCS:
                "talk" : User can use this command to interact with the NPCs and receive riddles.
                "shop" : User can use this command for the purchase of consumables.
                "riddle" : User can use this command to initiate a riddle.
                "leave" : User can use this command to leave the shop and end the conversion with the NPC.
                "buy" + item name + quantity: The user can use this commands in the shop to but consumables.
                =======================================================================================================================================================================================                                                                          
                """;
    }*/

    public int getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(int currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getPreviousLocation() {
        return previousLocation;
    }

    public void setPreviousLocation(int previousLocation) {
        this.previousLocation = previousLocation;
    }

}
