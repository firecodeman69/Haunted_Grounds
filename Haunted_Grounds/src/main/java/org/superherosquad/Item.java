package org.superherosquad;

public class Item {

    protected int Id;
    protected String name;
    protected String description;
    protected int price;

    public Item(int Id, String name, String description, int price)
    {
        this.Id = Id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /*/
    /todo: complete the method.
    public void use()
    {
    }*/

    @Override
    public String toString() {
        return "[Name:'" + name + "] "
                + "[description:" + description + "] "
                + "[price: $" + price + "] ";
    }
}
