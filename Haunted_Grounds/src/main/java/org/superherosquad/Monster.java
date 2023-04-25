/**************** Cody ********************/
package org.superherosquad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Monster extends Character implements Serializable {

	private static final long serialVersionUID = -4442287009122303530L;
	private int[] monsterItemAssociations;
    private ArrayList<Item> monsterInventory = new ArrayList<>();

    private Item randomItem = new Item();

    private int randomCurrency = 0;

    //Cody
    public Monster(int Id, String name, int hp, int currency, String description,
                   int speed, int defense, int attack,
                   int[] monsterItemAssociations) {
        super(Id, name, hp, currency, description, speed, defense, attack);
        this.monsterItemAssociations = monsterItemAssociations;
    }

    public void addItems(Item item) { //Cody
        monsterInventory.add(item);
    }

    public int[] getMonsterItemAssociations() { //Cody
        return monsterItemAssociations;
    }

    public ArrayList<Item> getMonsterInventory() {//Cody
        return monsterInventory;
    }

    public Item dropRandomItem() {
        Random random = new Random();
        int index = random.nextInt(monsterInventory.size());
        setRandomItem(monsterInventory.get(index));
        return this.randomItem;
    }

    public Item getRandomItem() {
        return randomItem;
    }

    public void setRandomItem(Item item) {
        this.randomItem = item;
    }

    public int dropRandomCurrency() {
        Random random = new Random();
        randomCurrency = random.nextInt(5) + 2;
        return randomCurrency;
    }

    public int getRandomCurrency() {
        return randomCurrency;
    }

    public String toString() {
        return super.toString();
        // " [Monster Locations: " + monsterLocations.toString() + "]";
    }
}
