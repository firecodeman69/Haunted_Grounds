/***********************************Cody************************************/
package org.superherosquad;

import java.util.ArrayList;

public class Room {
	private View view = new View();
	
    private int id;
    private String name;
    private String description;
    private int northRoom;
    private int southRoom;
    private int eastRoom;
    private int westRoom;

    private int monsterId;
    private int puzzleId;
    private int itemId;
    private int npcId;
    private ArrayList<Item> roomItems = new ArrayList<>();
    private Puzzle roomPuzzle;
    private Monster roomMonster;

    private NPC roomNPC;
    private boolean isVisited;
    private boolean isDark; //Cobi
    private boolean inspected = false;

    //Cody
    public Room(int id, String name, String description, int northRoom, int southRoom, int eastRoom, int westRoom, boolean isVisited, int monsterId, int puzzleId, int itemId, int npcId, boolean isDark) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.northRoom = northRoom;
        this.southRoom = southRoom;
        this.eastRoom = eastRoom;
        this.westRoom = westRoom;
        this.isVisited = isVisited;
        this.monsterId = monsterId;
        this.puzzleId = puzzleId;
        this.itemId = itemId;
        this.npcId = npcId;
        this.isDark = isDark;
    }

    public int getId() { //Cody
        return id;
    }

    public String getName() { //Cody
        return name;
    }

    public String getDescription() { //Cody
        return description;
    }
    
    public boolean getIsDark() { //Cobi
    	return isDark;
    }

    public void setItem(Item item) { //Cody
        roomItems.add(item);
    }

    public void setPuzzle(Puzzle puzzle) { //Cody
        roomPuzzle = puzzle;
    }
    
    public Puzzle getPuzzle() { //Cobi
    	return roomPuzzle;
    }

    public void removePuzzle() { //Cody
        roomPuzzle = null;
    }

    public Monster getRoomMonster() { //Cody
        return roomMonster;
    }

    public void setMonster(Monster monster) { //Cody
        roomMonster = monster;
    }

    public void removeMonster() { //Cody
        roomMonster = null;
    }

    public void setNPC(NPC npc) { //Cody
        roomNPC = npc;
    }
    
    public NPC getNPC() { //Cobi
    	return roomNPC;
    }

    public void setVisited() { //TreMya
        isVisited = true;
    }

    public boolean isVisited() { //TreMya
        return isVisited;
    }
    
    public boolean getInspected() { //Cobi
    	return inspected;
    }

    public ArrayList<Item> getItems() { //TreMya
        return roomItems;
    }

    public void addItem(Item item) {
        roomItems.add(item);
    }

    public Item getItem(String itemName) { //Cody
        for (Item i: roomItems) {
            if (i.getName().equalsIgnoreCase(itemName)) {
                return i;
            }
        }
        removeItem(itemName);
        return null;
    }

    public void removeItem(String itemName) { //Cody
        for (int i = 0; i < roomItems.size(); i++) {
            if (roomItems.get(i).getName().equalsIgnoreCase(itemName)) {
                roomItems.remove(roomItems.get(i));
            }
        }
    }

    public int inspect(Player p, int currentMode) { //Cobi & Cody
    	if(p.getCurrentRoom().getIsDark()) {
    		view.print("It is too dark for you to see in this room. You need to turn on the lights.");
    	}

    	else {
            if (p.roomHasMonster()) {
                currentMode = 1;
            } else {
            	inspected = true;
            	
            	view.print("Room description: " + p.getCurrentRoom().getDescription());
            	
            	if(p.getCurrentRoom().getItems().isEmpty()) {
            		view.print("There are no items in the current room.");
            	}
            	else {
                	view.print("Items in room: " + p.getCurrentRoom().getItems());
            	}
            	try {
            		p.getCurrentRoom().getPuzzle().getId();
        			view.print("Puzzle in room: " + p.getCurrentRoom().getPuzzle());
            	}
            	catch (NullPointerException nre) {
            		view.print("There is no puzzle in the current room.");
            	}
            }
    	}
        return currentMode;
    }

    public boolean hasItem(String itemName) {
        for (Item i: roomItems) {
            if (i.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }
    
    public void lightsOn() { //Cobi
    	if(isDark) {
    		isDark = false;
    		view.print("You have turned the lights on.");
    	}
    	else {
    		view.print("The lights are already on.");
    	}
    }
    
    public int activatePuzzle(int currentMode, ArrayList<Puzzle> puzzles) {
    	if(puzzleId == -1) {
    		view.print("There is no puzzle in this room.");
    	}
    	else {
    		currentMode = 2;
    		roomPuzzle.activate();
    		view.print("You have started the " + roomPuzzle + " puzzle. Here is the prompt:");
    		view.print(roomPuzzle.getQuestion());
    	}
    	return currentMode;
    }
    
    public int talk(int currentMode) {
    	if(npcId == -1) {
    		view.print("There is no NPC in this room to talk to.");
    	}
    	if(inspected == false) {
    		view.print("You can't tell if there's anyone in the room. Try inspecting first.");
    	}
    	else {
    		currentMode = 3;
    		view.print(roomNPC.getGreets());
    	}
    	return currentMode;
    }

    public int getMonsterId() {
        return monsterId;
    }

    public int getPuzzleId() {
        return puzzleId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getNpcId() {
        return npcId;
    }

    public int getNorthRoom() {
        return northRoom;
    }

    public void setNorthRoom(int i) {
    	northRoom = i;
    }

    public int getSouthRoom() {
        return southRoom;
    }

    public void setSouthRoom(int i) {
    	southRoom = i;
    }

    public int getEastRoom() {
        return eastRoom;
    }

    public void setEastRoom(int i) {
    	eastRoom = i;
    }

    public int getWestRoom() {
        return westRoom;
    }

    public void setWestRoom(int i) {
    	westRoom = i;
    }

    @Override
    public String toString() { //Cody
        return  "[Room Id: " + id + "] " +
                "[Room Name: " + name + "] " +
                "[Room Description: " + description + "] " +
                "[Room to the North: " + northRoom + "] " +
                "[Room to the South: " + southRoom + "] " +
                "[Room to the East: " + eastRoom + "] " +
                "[Room Items: " + roomItems + "] " +
                "[Room Puzzle: " + roomPuzzle + "] " +
                "[Room Monster: " + roomMonster + "] " +
                "[Has been visited?: " + isVisited + "] ";
    }
}