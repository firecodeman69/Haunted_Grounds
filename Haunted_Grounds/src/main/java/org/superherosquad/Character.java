/****************Cody********************/
package org.superherosquad;

import java.io.Serializable;

public abstract class Character implements Serializable {
	private static final long serialVersionUID = 913398673281797944L;

	protected int Id;

    protected String name;
    protected int hp;
    protected int currency;

    protected String description;
    protected int speed;
    protected int defense;
    protected int attack;

    public Character() {} //no arg constructor is apparently necessary to make characters serializable.
    
    public Character(int Id, String name, int hp, int currency, String description, int speed, int defense, int attack) { //Main constructor for new character type objects
        this.Id = Id;
        this.name = name;
        this.hp = hp;
        this.currency = currency;
        this.description = description;
        this.speed = speed;
        this.defense = defense;
        this.attack = attack;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) { //Set id for new players
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getDescription() { //
        return description;
    }

    public void setDescription(String description) { //set description for new players
        this.description = description;
    }

    public int getHP() { //used for combat and to display HP information
        return hp;
    }

    public void setHP(int hp) { //Used for combat and item effects
        this.hp = hp;
    }
    public void addAttack(int attack) { //Adds consumable buff to character
        this.attack += attack;
    }

    public void addHP(int hp) { //Used for consumable item effects - hp
        this.hp += hp;
    }

    public void loseHP(int hp) { //Used for combat
        this.hp -= hp;
    }

    public int getCurrency() { //Show money player has currently
        return currency;
    }

    public void addCurrency(int currency) { //Used when gaining currency
        this.currency += currency;
    }

    public int getSpeed() { //Used for combat
        return speed;
    }

    public void addSpeed(int speed) { //Used for consumable item effects - speed
        this.speed += speed;
    }

    public void addDefense(int defense) { //Used for consumable item effects - defense
        this.defense += defense;
    }

    public int getDefense() { //Used for combat
        return defense;
    }

    public int getAttack() { //Used for combat
        return attack;
    }

    public boolean isAlive() { //Check if the player is alive
        return hp > 0;
    }

    public String toString() {
        return "[Name: " + name + "] "
                + "[HP: " + hp + "] "
                + "[currency amount: " + currency + "] "
                + "[description: " + description + "] "
                + "[speed: " + speed + "] "
                + "[defense: " + defense + "] "
                + "[attack: " + attack + "] ";
    }
}