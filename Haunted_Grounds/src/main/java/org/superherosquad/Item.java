/**************** ReAnn ********************/
package org.superherosquad;

import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = 755844382439330848L;
	View view = new View();
    protected int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int[] getRoomAssociations() {
        return roomAssociations;
    }

    public void setRoomAssociations(int[] roomAssociations) {
        this.roomAssociations = roomAssociations;
    }

    protected String name;
    protected String description;
    protected String type;
    protected String command;
    protected int effect;
    protected int price;
    protected int[] roomAssociations;

    public Item(int Id, String name, String description, String type, String command, int effect, int price, int[] roomAssociations)
    {
        this.Id = Id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.command = command;
        this.effect = effect;
        this.price = price;
        this.roomAssociations = roomAssociations;
    }

    public Item() {};



    /*/
    /TODO:: complete the method.
    public void use()
    {
    }*/


    @Override
    public String toString() {
        return getName();
    }

}
