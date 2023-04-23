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
    private boolean isDark;

    public Room(int id, String name, String description, int northRoom, int southRoom, int eastRoom, int westRoom, boolean isVisited, int monsterId, int puzzleId, int itemId, int npcId, boolean isDark) { //Cody
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

    public Monster getRoomMonster() {
        return roomMonster;
    }

    public void setMonster(Monster monster) { //Cody
        roomMonster = monster;
    }

    public void removeMonster() { //Cody
        roomMonster = null;
    }

    public void setNPC(NPC npc) {
        roomNPC = npc;
    }

    public void setVisited() { //TreMya
        isVisited = true;
    }

    public boolean isVisited() { //TreMya
        return isVisited;
    }

    public ArrayList<Item> getItems() { //TreMya
        return roomItems;
    }

    public void addItem(Item item) {
        roomItems.add(item);
    }

    public void dropItem(String itemName) { //Cody
        for (Item i: roomItems) {
            if (i.getName().equalsIgnoreCase(itemName)) {
                roomItems.remove(i);
            }
        }
    }
    
    //TODO: Fill in the process for initiating combat.
    //TODO: Figure out how to deal with null values in the items and puzzles.
    public void inspect(Player p) {
    	if(p.getCurrentRoom().getIsDark()) {
    		view.print("It is too dark for you to see in this room. You need to turn on the lights.");
    	}
    	/*else if(p.getCurrentRoom().getMonsterId() != -1) {
    		//Initiate combat
    	}*/
    	else {
        	view.print("Room description: " + p.getCurrentRoom().getDescription());
        	view.print("Items in room: " + p.getCurrentRoom().getItems());
        	view.print("Puzzles in room: " + p.getCurrentRoom().getPuzzle());
    	}
    }
    
    public void lightsOn() {
    	if(isDark) {
    		isDark = false;
    		view.print("You have turned the lights on.");
    	}
    	else {
    		view.print("The lights are already on.");
    	}
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

    public int getSouthRoom() {
        return southRoom;
    }

    public int getEastRoom() {
        return eastRoom;
    }

    public int getWestRoom() {
        return westRoom;
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