package org.superherosquad;

import java.util.ArrayList;

public class Rooms {
    private int id;
    private String name;
    private String description;
    private Rooms north;
    private Rooms south;
    private Rooms east;
    private Rooms west;

    private ArrayList<Item> items;

    private String itemActions;

    private boolean isVisited;

    private boolean hasMonster;

    public Rooms(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        items = new ArrayList<>();
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setRoomAtDirection(String direction, Rooms roomById) {
        if (direction.equalsIgnoreCase("North")) {
            north = roomById;
        } else if (direction.equalsIgnoreCase("South")) {
            south = roomById;
        } else if (direction.equalsIgnoreCase("East")) {
            east = roomById;
        } else if (direction.equalsIgnoreCase("West")) {
            west = roomById;
        }
    }

    public int getNumber()
    {
        return id;
    }

    public Rooms getRoomAtDirection(char input)
    {
        Rooms r;
        if (input == 'N' || input == 'n') {
            r = north;
        } else if (input == 'S' || input == 's') {
            r = south;
        } else if (input == 'E' || input == 'e') {
            r = east;
        } else {
            r = west;
        }
        return r;
    }

    public boolean isHasMonster()
    {
        return hasMonster;
    }

    public void setHasMonster(boolean hasMonster)
    {
        this.hasMonster = hasMonster;
    }

    public void setItemActions(String action)
    {
        itemActions = itemActions + " " + action;
    }

    public void setVisited() {
        isVisited = true;
    }

    public boolean isVisited() {
        return isVisited;
    }

    @Override
    public String toString() {
        return name + "\n" + description;
    }

    public void addItem(Item item)
    {
        if (item == null) return;
        this.items.add(item);
    }

    public boolean contains(Item item)
    {
        for (Item val: items)
        {
            if (val.getDescription().equals(item.getDescription()))
                return true;
        }
        return false;
    }

    public String getDescription()
    {
        return description;
    }

    public String getItemActions()
    {
        return itemActions;
    }

    public void setItemActions(String description, int id)
    {
        for (Item val: items)
        {
            if (val.getID() == id)
            {
                val.setRoomItems(val.getRoomItems() + " " +description);
                System.out.println("Room: " + this.id + " " + val.getRoomItems());
                return;
            }
        }
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    public void removeItem(Item item)
    {
        items.remove(item);
    }
}