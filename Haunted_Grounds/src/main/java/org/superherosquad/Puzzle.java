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
		this.reward = i;
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
	
	public int onSolve(Room r, ArrayList<Item> items)
	{
		view.print(correctResp);
		
		if (reward > -1) { //This indicates the reward is an item ID, so we drop the item with the corresponding ID in the player's current rooom.
			for (Item i : items) {
				if (i.getId() == this.reward) {
					r.addItem(i);
					break;
				}
			}
		} else if(reward == -1){ //This indicates that the puzzle is a door unlock puzzle, so we unlock all locked connections in the room.
			if (r.getNorthRoom() > 100) {r.setNorthRoom(r.getNorthRoom() - 100);}
			if (r.getSouthRoom() > 100) {r.setSouthRoom(r.getSouthRoom() - 100);}
			if (r.getEastRoom() > 100) {r.setEastRoom(r.getEastRoom() - 100);}
			if (r.getWestRoom() > 100) {r.setWestRoom(r.getWestRoom() - 100);}
		} //If the reward is -2, that just unlocks the friend which has no discernable affect on the program except for marking the puzzle as solved.
		
		r.removePuzzle();
		
		return 0; //Once a puzzle is solved, the player will return to free roam mode.
	}

	@Override
	public String toString() {
		return getName();
	}
}