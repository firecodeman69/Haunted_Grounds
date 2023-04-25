/**************** Cody ********************/
package org.superherosquad;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Character implements Serializable {
    private String blue = "\u001B[34m";
    private String red = "\u001B[31m";
    private String reset = "\u001B[0m";
    String orange = "\u001B[38;2;255;165;0m";
    private String green =  "\u001B[32m";

    private ArrayList<Item> playerInventory = new ArrayList<>();
    private ArrayList<Item> equippedItems = new ArrayList<>();
    private Room currentRoom;
    private Room previousRoom;
    private View view = new View();
    private double runChance = 0;
    private double runSuccess = 0;



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
                }else {
                    view.print("That item is not in the room. Did you enter the correct thing?");
                }
            }
        }
    
    public void silentAdder(Item item) {
    	playerInventory.add(item);
    }

    public void addItemsToInventory(ArrayList<Item> itemAL) {
        playerInventory.addAll(itemAL);
    }

    public void dropItem(String itemName) {
        for (int i = 0; i < playerInventory.size(); i++) {
            if (hasItem(itemName)) {
                currentRoom.addItem(playerInventory.get(i));
                playerInventory.remove(playerInventory.get(i));
                System.out.println(currentRoom.getItems().get(i).getName() + " was removed from your inventory. Use command 'inspectroom' to see it now.");
            }
        }
    }

    public void equipItem(String itemName) {
        for (Item i: playerInventory) {
            if(i.getName().equalsIgnoreCase(itemName)) {
                if (i.getType().equals("E")) {
                    equippedItems.add(i);
                    playerInventory.remove(i);
                    addHP(i.getEffect());
                    view.print(i.getName() + " has been equipped.\n" + i.getEffect() + " added to your hp. It is now " + getHP());
                }else if (i.getType().equals("EW")) {
                    equippedItems.add(i);
                    playerInventory.remove(i);
                    addAttack(i.getEffect());
                    view.print(i.getName() + " has been equipped.\n" + i.getEffect() + " added to your attack. It is now " + getAttack());
                }
            }
        }
    }

    public void unEquipItem(String itemName) {
        for (Item i: equippedItems) {
            if(i.getName().equalsIgnoreCase(itemName)) {
                    equippedItems.remove(i);
                    playerInventory.add(i);
                    loseHP(i.getEffect());
                    view.print(i.getName() + " has been une-equipped.\n" + i.getEffect() + " removed from your hp. It is now " + getHP());
            }
        }
    }

    public String showInventory() {
        if (playerInventory.size() < 1) {
            return (red + "You don't have any items in your inventory." + reset);
        } else {
            return (orange + playerInventory + reset);
        }
    }

    public void showEquipped() {
        if (equippedItems.size() < 1) {
            view.print(red + "You don't have any items equipped." + reset);
        } else {
            view.print(orange + equippedItems + reset);
        }
    }

    public Item getItem(String itemName) {
        for (Item i: playerInventory) {
            if (i.getName().equalsIgnoreCase(itemName)) return i;
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

    public void useConsumableItem(Item item) {
                switch (item.getType()) {
                    case "CHP" -> {
                        addHP(item.getEffect());
                        view.print("Successfully used " + item.getName() + "! " + green + "HP Gained: " + item.getEffect()
                                + "\nHP is now: " + getHP() + reset);
                        playerInventory.remove(item);
                    }
                    case "CDF" -> {
                        addDefense(item.getEffect());
                        view.print("Successfully used " + item.getName() + "! " + green + "Defense Gained: " + item.getEffect()
                                + "\nDefense is now: " + getDefense() + reset);
                        playerInventory.remove(item);
                    }
                    case "CSP" -> {
                        addSpeed(item.getEffect());
                        view.print("Successfully used " + item.getName() + "! " + green + "Speed Gained: " + item.getEffect()
                                + "\nSpeed is now: " + getSpeed() + reset);
                        playerInventory.remove(item);
                    }
                    case "CAT" -> {
                        addAttack(item.getEffect());
                        view.print("Successfully used " + item.getName() + "! " + green + "Attack Gained: " + item.getEffect()
                                + "\nAttack is now: " + getAttack() + reset);
                        playerInventory.remove(item);
                    }
                    default -> view.print(red + "That is not a consumable item or you do not have this item." + reset);
                }
    }

    public void inspectInventoryItem(Item item) {
        view.print(item.getDescription());
    }

    public boolean runSuccess() {
        return runSuccess <= runChance;
    }

    public double getRunChance() {
        return runChance;
    }

    public void setRunChance(Monster monster) {
        runSuccess = Math.ceil(Math.random() * 100);
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
		            break;
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
    
    public boolean finalBossCheck(ArrayList<Monster> monsters, ArrayList<Puzzle> puzzles) {
    	for(Monster m: monsters) { //Check if all monsters are defeated.
    		if(m.getHP() > 0) {
    			return false; //If there is any monster undefeated, abort check.
    		}
    	}
    	
    	for(Puzzle p: puzzles) { //Check if all puzzles are solved.
    		if(!(p.getId() == -1)) {
    			return false; //If there is any unsolved puzzle, abort check.
    		}
    	}
    	return true; //Getting here means that the check hasn't been aborted, so the conditions to activate the final boss battle have been met.
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room newRoom) {
        currentRoom = newRoom;
    }

    public int playerDeath() {
        return 5;
    }

    public int playerHardDeath(String fileName) {
        return 5;
    }

    public Room getPreviousRoom() {
        return previousRoom;
    }

    public void setPreviousroom(Room oldRoom) {
        previousRoom = oldRoom;
    }

}
