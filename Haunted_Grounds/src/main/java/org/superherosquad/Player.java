/**************** Cody ********************/
package org.superherosquad;

public class Player extends Character {

    //ArrayList<Item> playerInventory;
    int currentLocation;
    int previousLocation;
    public Player(int Id, String name, int hp, int currency, String description,
                  int speed, int defense, int attack,
                  //ArrayList<Item> playerInventory,
                  int currentLocation, int previousLocation) {
        super(Id, name, hp, currency, description, speed, defense, attack);
        //this.playerInventory = playerInventory;
        this.currentLocation = currentLocation;
        this.previousLocation = previousLocation;
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
