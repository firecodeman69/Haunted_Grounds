/**************** Cody ********************/
package org.superherosquad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Character implements Serializable {

    ArrayList<Item> playerInventory = new ArrayList<>();
    ArrayList<Item> equippedItems = new ArrayList<>();
    Room currentRoom;
    Room previousRoom;
    View view = new View();
    double runChance = 0;

    public Player() {
        super(0, "Character 1", 1000, 0, "First player of the game.", 100, 100, 100);
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

    public void addItemToInventory(String itemName) {
            for (int i = 0; i < getCurrentRoom().getItems().size(); i++) {
                if (currentRoom.hasItem(itemName)) {
                    playerInventory.add(currentRoom.getItems().get(i));
                    System.out.println(currentRoom.getItems().get(i).getName() + " was added to your inventory. Use command Inventory to see it now.");
                    currentRoom.removeItem(itemName);
                }
            }
        }
    
    public void silentAdder(Item item) {
    	playerInventory.add(item);
    }

    public void addItemsToInventory(ArrayList<Item> itemAL) {
        playerInventory.addAll(itemAL);
    }

    public void dropItem(Item item) {
        for (Item i : playerInventory) {
            if (i.getName().equalsIgnoreCase(item.getName())) {
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
        if (playerInventory.size() < 0) {
            return ("You don't have any items in your inventory.");
        } else {
            return playerInventory.toString();
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

    public void spendCurrency(int c) { //Used for the shop
        currency -= c;
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

    public void inspectInventoryItem(String itemName) {
        for (Item item: playerInventory) {
            if(item.getName().equalsIgnoreCase(itemName)) {
                System.out.println(item.getDescription());
            } else {
                System.out.println("You don't have that item in your inventory.");
            }
        }
    }

    public boolean runSuccess() {
        return Math.ceil(Math.random() * 100) <= runChance;
    }

    public double getRunChance() {
        return runChance;
    }

    public void setRunChance(Monster monster) {
        runChance =  (((double) speed / (speed + monster.getSpeed())) * 100);
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
	            	if(this.currentRoom.getNorthRoom() >= 100) { //Locked connections have an ID of 100 or greater.
	            		view.print("The path in that direction is locked.");
	            		break;
	            	}
	            	else {
		            	for(Room r: rooms) {
		            		if(r.getId() == this.currentRoom.getNorthRoom()) {
		            			this.previousRoom = this.currentRoom;
		            			this.currentRoom = r;
		            			r.setVisited();
		            			break;
		            		}
		            	}
		            break;
	            	}
	            }
	            else { 
	            	view.print("You cannot go that way."); 
	            	break;
	            }

    		case "e":
	            if(this.currentRoom.getEastRoom() != -1) {
	            	if(this.currentRoom.getEastRoom() >= 100) {
	            		view.print("The path in that direction is locked.");
	            		break;
	            	}
	            	else {
		            	for(Room r: rooms) {
		            		if(r.getId() == this.currentRoom.getEastRoom()) {
		            			this.previousRoom = this.currentRoom;
		            			this.currentRoom = r;
		            			r.setVisited();
		            			break;
		            		}
		            	}
		            break;
	            	}
	            }
	            else {
	            	view.print("You cannot go that way.");
		            break;
	            }

    		case "s":
	            if(this.currentRoom.getSouthRoom() != -1) {
	            	if(this.currentRoom.getSouthRoom() >= 100) {
	            		view.print("The path in that direction is locked.");
	            		break;
	            	}
	            	else {
		            	for(Room r: rooms) {
		            		if(r.getId() == this.currentRoom.getSouthRoom()) {
		            			this.previousRoom = this.currentRoom;
		            			this.currentRoom = r;
		            			r.setVisited();
		            			break;
		            		}
		            	}
		            break;
	            	}
	            }
	            else {
	            	view.print("You cannot go that way.");
	            	break;
	            }


    		case "w":
	            if(this.currentRoom.getWestRoom() != -1) {
	            	if(this.currentRoom.getWestRoom() >= 100) {
	            		view.print("The path in that direction is locked.");
	            		break;
	            	}
	            	else {
		            	for(Room r: rooms) {
		            		if(r.getId() == this.currentRoom.getWestRoom()) {
		            			this.previousRoom = this.currentRoom;
		            			this.currentRoom = r;
		            			r.setVisited();
		            			break;
		            		}
		            	}
		            
	            	}
	            }
	            else {
	            	view.print("You cannot go that way.");
	            	break;
	            }

	         
	        default:
	        	view.print("This message should not be displayed during regular gameplay. Please report this bug to the developers.");
	        	break;
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

    public int initialCombat(Monster monster, Scanner input) {
        int playerTurn = -1;
        view.print("You see " + monster.getName() + " in the room with you.\n" +
                "What would you like to do?\n(A)ttack, (I)gnore, (R)un");
        String playerInput = input.nextLine().toLowerCase();
        String[] tokens = playerInput.split(" ");
        switch (tokens[0]) {
            case "attack", "a" -> {
                view.print("Good luck brave one - may you be successful in your combat.");
                playerTurn = 0;
            }
            case "ignore", "i" -> {
                view.print(monster.getName() + " Attacked you! Can't leave them on read so easily.\nStarting combat!");
                playerTurn = 1;
            }
            case "run", "r" -> {
                exitRoom();
            }
        }
        return playerTurn;
    }
}
