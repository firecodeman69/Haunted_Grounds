/**************** Cody ********************/
package org.superherosquad;

public abstract class Character {
    protected int Id;

    protected String name;
    protected int hp;
    protected int currency;

    protected String description;
    protected int speed;
    protected int defense;
    protected int attack;

    public Character() {}; //no arg constructor

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
    public void addAttack(int attack) {
        this.attack += attack;
    }

    public void loseAttack(int attack) {
        this.attack -= attack;
    }
    public void addHP(int hp) { //Used for combat and item effects
        this.hp += hp;
    }

    public void loseHP(int hp) { //Used for combat and item effects
        this.hp -= hp;
    }

    public int getCurrency() { //Show money player has currently
        return currency;
    }

    public void setCurrency(int currency) { //Set money for new players
        this.currency = currency;
    }

    public void addCurrency(int currency) { //Used when gaining currency
        this.currency += currency;
    }

    public int getSpeed() { //Used for combat
        return speed;
    }

    public void addSpeed(int speed) {
        this.speed += speed;
    }

    public void addDefense(int defense) {
        this.defense += defense;
    }

    public void setSpeed(int speed) { //updated by item effects
        this.speed = speed;
    }

    public int getDefense() { //Used for combat
        return defense;
    }

    public void setDefense(int defense) { //updated by item effects
        this.defense = defense;
    }

    public int getAttack() { //Used for combat
        return attack;
    }

    public void setAttack(int attack) { //updated by item effects
        this.attack = attack;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void takeDamage(int damage) {
        int totalDamage = damage - defense;
        if (totalDamage < 0) { //if defense is higher than the damage taken
            totalDamage = 0;
        }
        hp -= totalDamage;
        if (hp <= 0) { //if total hp is below 0
            hp = 0;
        }
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