//Cobi
package org.superherosquad;
import java.util.ArrayList;

public class Puzzle 
{
	View view = new View();
	private int id;
	private String name;
	private String question;
	private String solution;
	private String hint;
	private int reward;
	private String description;
	private String correctResp;
	private String wrongResp;
	private boolean active = false;
	
	public Puzzle(int i, String n, String q, String s, String h, int r, String d, String c, String w)
	{
		this.id = i;
		this.name = n;
		this.question = q;
		this.solution = s;
		this.hint = h;
		this.reward = r;
		this.description = d;
		this.correctResp = c;
		this.wrongResp = w;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getQuestion()
	{
		return this.question;
	}
	
	public String getSolution()
	{
		return this.solution;
	}
	
	public String getHint()
	{
		return this.hint;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public boolean getActive()
	{
		return this.active;
	}
	
	public void activate()
	{
		this.active = true;
	}
	
	public void incorrect()
	{
		if(!wrongResp.equals("X")) {
			view.print(wrongResp);
		}
	}
	
	public int onSolve(Room r, ArrayList<Room> rooms, ArrayList<Item> items)
	{
		view.print(correctResp);
		
		if (reward > -1) { //This indicates the reward is an item ID, so we drop the item with the corresponding ID in the player's current rooom.
			for (Item i : items) {
				if (i.getId() == this.reward) {
					r.addItem(i);
					break;
				}
			}
		} else if(reward == -1){ //This indicates that the puzzle is a door unlock puzzle, so we unlock all locked connections in the room. A number over 100 for a room connection indicates a locked room connection, and a number over 200 indicates a mutually locked room connection.
			if (r.getNorthRoom() > 100) {
				r.setNorthRoom(r.getNorthRoom() - 100); //Unlocks the connection from this room to the south room.
				for(Room r2: rooms) { //This loop finds the room to the north of the current room. When it finds the room, it will subtract 100 from the connection to the room to its south (which is the connection to the current room).
					if(r2.getId() == r.getNorthRoom() || r2.getId() == (r.getNorthRoom() - 100)) {
						r2.setSouthRoom(r2.getSouthRoom() - 100); //Unlocks the connection to this room from the south room.
						break;
					}
				}
			}
			
			//The previous if statement is repeated for the remaining three directions similar to movement.
			if (r.getSouthRoom() > 100) {
				r.setSouthRoom(r.getSouthRoom() - 100);
				for(Room r2: rooms) {
					if(r2.getId() == r.getSouthRoom() || r2.getId() == (r.getSouthRoom() - 100)) {
						r2.setNorthRoom(r2.getNorthRoom() - 100);
						break;
					}
				}
			}
			
			if (r.getEastRoom() > 100) {
				r.setEastRoom(r.getEastRoom() - 100);
				for(Room r2: rooms) {
					if(r2.getId() == r.getEastRoom() || r2.getId() == (r.getEastRoom() - 100)) {
						r2.setWestRoom(r2.getWestRoom() - 100);
						break;
					}
				}
			}
			if (r.getWestRoom() > 100) {
				r.setWestRoom(r.getWestRoom() - 100);
				for(Room r2: rooms) {
					if(r2.getId() == r.getWestRoom() || r2.getId() == (r.getWestRoom() - 100)) {
						r2.setEastRoom(r2.getEastRoom() - 100);
					}
				}
			}
		} //If the reward is -2, that just unlocks the friend which has no discernable affect on the program except for marking the puzzle as solved.
		
		r.removePuzzle();
		
		return 0; //Once a puzzle is solved, the player will return to free roam mode.
	}

	@Override
	public String toString() {
		return name;
	}
}