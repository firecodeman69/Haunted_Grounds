/**************** Cody ********************/
package org.superherosquad;

public class Character {
    protected int Id;
    protected String name;
    protected int hp;
    protected int currency;
    protected String description;
    protected int speed;
    protected int defense;
    protected int attack;

    public Character(int Id, String name, int hp, int currency, String description, int speed, int defense, int attack) {
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHP() {
        return hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public int getCurrency() {
        return currency;
    }

    public void addCurrency(int currency) {
        this.currency += currency;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
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
