/***********************************Cody************************************/
package org.superherosquad;

import java.util.ArrayList;

public class Room {
    private int id;
    private String name;
    private String description;
    private int northRoom;
    private int southRoom;
    private int eastRoom;
    private int westRoom;

    private ArrayList<Item> roomItems;
    private Puzzle roomPuzzle;
    private Monster roomMonster;

    private boolean isVisited;

    public Room(int id, String name, String description, int northRoom, int southRoom, int eastRoom, int westRoom, boolean isVisited) { //Cody
        this.id = id;
        this.name = name;
        this.description = description;
        this.northRoom = northRoom;
        this.southRoom = southRoom;
        this.eastRoom = eastRoom;
        this.westRoom = westRoom;
        this.isVisited = isVisited;
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

    public void setItem(Item item) { //Cody
        roomItems.add(item);
    }

    public void setPuzzle(Puzzle puzzle) { //Cody
        roomPuzzle = puzzle;
    }

    public void removePuzzle() { //Cody
        roomPuzzle = null;
    }

    public void setMonster(Monster monster) { //Cody
        roomMonster = monster;
    }

    public void removeMonster() { //Cody
        roomMonster = null;
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

    public void removeItem(Item item) { //Cody
        for (Item i: roomItems) {
            if (i.getName().equalsIgnoreCase(i.getName())) {
                roomItems.remove(item);
            }
        }
    }

    @Override
    public String toString() { //Cody
        return  "[Room Id: " + id + "] " +
                "[Room Name: " + name + "] " +
                "[Room Description:" + description + "] " +
                "[Room to the North:" + northRoom + "] " +
                "[Room to the South:" + southRoom + "] " +
                "[Room to the East:" + eastRoom + "] " +
                "[Room to the West:" + westRoom + "] ";
    }
}