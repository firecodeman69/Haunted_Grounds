/**************** Cody ********************/
package org.superherosquad;

import java.io.Serializable;
import java.util.ArrayList;

public class Player extends Character implements Serializable {

    ArrayList<Item> playerInventory;
    ArrayList<Item> equippedItems;
    Room currentRoom;
    Room previousRoom;
    View view = new View();
    double runChance = 0;

    public Player() {
    	super(0, "Character 1", 1000000, 9999999, "First player of the game.", 250000, 250000, 250000);
    } //create a default player
    
    public Player(int Id, String name, int hp, int currency, String description, //to be used for loading??
                  int speed, int defense, int attack,
                  Room currentRoom) {
        super(Id, name, hp, currency, description, speed, defense, attack);
        this.currentRoom = currentRoom;
    }

    public ArrayList<Item> getPlayerInventory() {
        return playerInventory;
    }

    public String addItemToInventory(Item item) {
        playerInventory.add(item);
        return (item.getName() + " was added to your inventory. Use command Inventory to see it now.");
    }

    public void addItemsToInventory(ArrayList<Item> itemAL) {
        playerInventory.addAll(itemAL);
    }


    public void dropItem(Item item) {
        for (Item i: playerInventory) {
          if(i.getName().equalsIgnoreCase(item.getName())) {
              playerInventory.remove(item);
          }
        }
    }

    public void equipItem(String itemName) {
        for (Item i: playerInventory) {
            if(i.getName().equalsIgnoreCase(itemName)) {
                equippedItems.add(i);
            }
        }
    }

    public void unEquipItem(Item item) {
        for (Item i: equippedItems) {
          if(i.getName().equalsIgnoreCase(item.getName())) {
              equippedItems.remove(item);
         }
       }
    }

    public String showInventory() {
        if (playerInventory != null) {
            return playerInventory.toString();
        } else {
            return ("You don't have any items in your inventory.");
        }
    }


    public Item explore(String name) {
        for (Item i : currentRoom.getItems()) {
            if (i.getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return null;
    }

    public void spendCurrency(int currency) { //Used for the shop
        this.currency -= currency;
    }

    public boolean hasItem(String itemName) {
        for (Item item: playerInventory) {
            if(item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void useConsumableItem(String itemName) {
        for (Item item: playerInventory) {
            if(item.getName().equalsIgnoreCase(itemName)) {
                if(item.getType().equalsIgnoreCase("use")) {
                    hp += item.getEffect();
                }
            }
        }
    }

    public boolean runSuccess(Monster monster) {
        runChance = (double) (speed/(speed + monster.getSpeed()));
        return Math.ceil(Math.random() * 100) <= runChance;
    }

    public double getRunChance() {
        return runChance;
    }

    public void setRunChance(double runChance) {
        this.runChance = runChance;
    }

    public boolean roomHasMonster() {
        if (currentRoom.getRoomMonster() != null) {
            return true;
        }
        return false;
    }


    public void moveRooms(String d, ArrayList<Room> rooms) { //Cobi
    	switch (d) { //Checks direction
    		/*
    		 * All directional cases do the same thing for each different direction.
    		 * It checks through all of the rooms in the ArrayList. When it finds a matching rooms, it sets the player's current room to that room and the room that the player was in to the previous room.
    		 * If it doesn't find a matching room, the user wil be told they cannot go that way.
    		 */
    		case "n":
	            if(this.currentRoom.getNorthRoom() != -1) {
	            	for(Room r: rooms) {
	            		if(r.getId() == this.currentRoom.getNorthRoom()) {
	            			this.previousRoom = this.currentRoom;
	            			this.currentRoom = r;
	            			r.setVisited();
	            			break;
	            		}
	            	}
	            }
	            else view.print("You cannot go that way.");
	            break;

    		case "e":
	            if(this.currentRoom.getEastRoom() != -1) {
	            	for(Room r: rooms) {
	            		if(r.getId() == this.currentRoom.getEastRoom()) {
	            			this.previousRoom = this.currentRoom;
	            			this.currentRoom = r;
	            			r.setVisited();
	            			break;
	            		}
	            	}
	            }
	            else view.print("You cannot go that way");
	            break;

    		case "s":
	            if(this.currentRoom.getSouthRoom() != -1) {
	            	for(Room r: rooms) {
	            		if(r.getId() == this.currentRoom.getSouthRoom()) {
	            			this.previousRoom = this.currentRoom;
	            			this.currentRoom = r;
	            			r.setVisited();
	            			break;
	            		}
	            	}
	            }
	            else view.print("You cannot go that way.");
	            break;

    		case "w":
	            if(this.currentRoom.getWestRoom() != -1) {
	            	for(Room r: rooms) {
	            		if(r.getId() == this.currentRoom.getWestRoom()) {
	            			this.previousRoom = this.currentRoom;
	            			this.currentRoom = r;
	            			r.setVisited();
	            			break;
	            		}
	            	}
	            }
	            else view.print("You cannot go that way.");
	            break;
	         
	        default:
	        	view.print("This message should not be displayed during regular gameplay. Please report this bug to the developers.");
    	}
    }

    public void exitRoom() { //Cobi
    	Room temp = this.currentRoom;
    	this.currentRoom = this.previousRoom;
    	this.previousRoom = temp;
    	view.print("You have the exited the room that you were in.");
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room newRoom) {
        currentRoom = newRoom;
    }

    public Room getPreviousRoom() {
        return previousRoom;
    }

    public void setPreviousroom(Room oldRoom) {
        previousRoom = oldRoom;
}
