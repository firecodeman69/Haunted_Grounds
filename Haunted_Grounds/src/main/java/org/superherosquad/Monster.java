/**************** Cody ********************/
package org.superherosquad;

import java.io.Serializable;
import java.util.ArrayList;

public class Monster extends Character implements Serializable {

	private static final long serialVersionUID = -4442287009122303530L;
	private int[] monsterItemAssociations;
    private ArrayList<Item> monsterInventory = new ArrayList<>();

    public Monster(int Id, String name, int hp, int currency, String description,
                   int speed, int defense, int attack,
                   int[] monsterItemAssociations) {
        super(Id, name, hp, currency, description, speed, defense, attack);
        this.monsterItemAssociations = monsterItemAssociations;
    }

    public void addItems(Item item) {
        monsterInventory.add(item);
    }

    public int[] getMonsterItemAssociations() {
        return monsterItemAssociations;
    }

    public ArrayList<Item> getMonsterInventory() {
        return monsterInventory;
    }

    public String toString() {
        return super.toString();
        // " [Monster Locations: " + monsterLocations.toString() + "]";
    }
}
