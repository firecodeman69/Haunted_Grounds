/**************** ReAnn ********************/
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
