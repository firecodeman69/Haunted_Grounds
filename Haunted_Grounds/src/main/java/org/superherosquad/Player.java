/**************** Cody ********************/
package org.superherosquad;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Player extends Character implements Serializable {
	private static final long serialVersionUID = -7574146038049098335L;
	//private String blue = "\u001B[34m";
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
        super(0, "Character 1", 1000, 0, "Bravest student of them all! Can handle any classes. Earned a 5.0 GPA from taking Health class", 100, 100, 100);
    } //create a default player

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

    public void addMonsterItemToInventory(Item item) {
        playerInventory.add(item);
    }

    public void dropItem(Item item) {
                if(hasItem(item)) {
                    currentRoom.addItem(item);
                    playerInventory.remove(item);
                    view.print(item.getName() + " was removed from your inventory. Use command 'inspectroom' to see it now.");
                }else {
                    view.print("You do not have that item in your inventory. Use 'inventory' to display the list of items you have.");
                }
    }

    public void equipItem(Item item) {
        if (hasItem(item)) {
            if (item.getType().equals("E")) {
                equippedItems.add(item);
                playerInventory.remove(item);
                addHP(item.getEffect());
                view.print(item.getName() + " has been equipped.\n" + item.getEffect() + " added to your hp. It is now " + getHP());
                boostAllStats();
            } else if (item.getType().equals("EW")) {
                equippedItems.add(item);
                playerInventory.remove(item);
                addAttack(item.getEffect());
                view.print(item.getName() + " has been equipped.\n" + item.getEffect() + " added to your attack. It is now " + getAttack());
            } else view.print("That item is not an equip-able item. Please try inspecting item to see the effect.");
        }else {
            view.print("You do not have that item.");
        }
    }

    public void unEquipItem(Item item) {
        if (hasItemEquiped(item)) {
                    equippedItems.remove(item);
                    playerInventory.add(item);
                    loseHP(item.getEffect());
                    view.print(item.getName() + " has been un-equipped.\n" + item.getEffect() + " removed from your hp. It is now " + getHP()
                            + "\nUse 'inventory' to see it in your inventory");
            } else view.print("You don't have that item equipped. Use command 'equipped' to see currently equipped items.");
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

    public Item getEquippedItem(String itemName) {
        for (Item i: equippedItems) {
            if (i.getName().equalsIgnoreCase(itemName)) return i;
        }
        return null;
    }

    public void spendCurrency(int c) { //Used for the shop
        currency -= c;
    }

    public boolean hasItem(Item item) {
        for (Item i: playerInventory) {
            if(i.getName().equalsIgnoreCase(item.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean hasItemEquiped(Item item) {
        for (Item i: equippedItems) {
            if(i.getName().equalsIgnoreCase(item.getName())) {
                return true;
            }
        }
        return false;
    }

    public void boostAllStats() {
        int count = 0;
        for (Item i: equippedItems) {
            if (i.getType().equalsIgnoreCase("E")) {
                count++;
            }
        }
        if (count == 4) {
            view.print("All stats boosted 5x!");
            this.attack *= 5;
            this.defense *= 5;
            this.speed *= 5;
            this.hp *= 5;
        }
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

    public void useConsumableItemCombat(Item item) {
        switch (item.getType()) {
            case "CHP" -> {
                addHP(item.getEffect());
                view.print("Successfully used " + item.getName() + "! " + green + "HP Gained: " + item.getEffect()
                        + "\nHP is now: " + getHP() + reset);
                playerInventory.remove(item);
            }
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
    	try {this.previousRoom.getId();}
    	catch (NullPointerException npe) {
    		view.cobiEasterEgg();
    		System.exit(0);
    	}
    	
        Room temp = this.currentRoom;
        this.currentRoom = this.previousRoom;
        this.previousRoom = temp;
        view.print("You have the exited the room that you were in.");
    }
    
    public boolean finalBossCheck(ArrayList<Monster> monsters, ArrayList<Puzzle> puzzles) { //Cobi
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

    public int playerDeath(boolean hard) {
        if (hard) { //Cobi - hard mode functionality handled here.
        	return 105;
        }
        else {
        	return 115;
        }
    }

    public Room getPreviousRoom() {
        return previousRoom;
    }

    public void setPreviousroom(Room oldRoom) {
        previousRoom = oldRoom;
    }

}
