/**************** Cody ********************/
package org.superherosquad;

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
//        return playerInventory;
//    }

//    public void addItemToInventory(Item item) {
//        playerInventory.add(item);
//    }

//    public void addItemsToInventory(ArrayList<Item> itemAL) {
//        playerInventory.addAll(itemAL);
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
