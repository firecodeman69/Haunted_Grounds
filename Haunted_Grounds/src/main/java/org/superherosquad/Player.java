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

//    public ArrayList<Item> getPlayerInventory() {
//        return playerInventory.toString;
//    }

//    public void addItemToInventory(Item item) {
//        playerInventory.add(item);
//    }

//    public void addItemsToInventory(ArrayList<Item> itemAL) {
//        playerInventory.addAll(itemAL);
//    }

    /*******************Could be used instead of addItemToInventory***************/
//    public String pickUpItem(Item item) {
//        playerInventory.add(item);
//        return (item.getName() + " was added to your inventory. Use command Inventory to see it now.");
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

    //todo: change the helpmenu to reflect the menu of our game
//    public String helpMenu() {
//        return """
//                N: Allows the player to move north (if there is a room available).
//                E: Allows the player to move east (if there is a room available).
//                S: Allows the player to move south (if there is a room available).
//                W: Allows the player to move west (if there is a room available).
//                Explore: Check a room for items.
//                Pickup{itemname}: Pick up (remove) an item from a room, and add it to your inventory.
//                Drop{itemname}: Drop (remove) an item from your inventory and add it to current room.
//                Inspect{itemname}: Return the description for an item in your inventory.
//                Inventory: List all of the current items in your inventory.
//                CurrentRoom: List the current room and the exits for that room.
//                Quit: I hope you never want to, but this is your way out of the game if you wish.
//                """;
//    }

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
