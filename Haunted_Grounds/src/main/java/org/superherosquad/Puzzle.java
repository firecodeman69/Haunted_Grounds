//Cobi

package org.superherosquad;

import java.util.ArrayList;

public class Puzzle 
{
	private int id;
	private String name;
	private String question;
	private String solution;
	private String hint;
	private int rewardItem;
	//private boolean doorReward;
	private String description;
	private String correctResp;
	private String wrongResp;
	private boolean isSolved = false;
	
	public Puzzle(int i, String n, String q, String s, String h, int r, String d, String c, String w)
	{
		this.id = i;
		this.name = n;
		this.question = q;
		this.solution = s;
		this.hint = h;
		this.rewardItem = i;
		this.description = d;
		this.correctResp = c;
		this.wrongResp = c;
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
	
	public String getCorrectResp()
	{
		return this.correctResp;
	}
	
	public String getWrongResp()
	{
		return this.wrongResp;
	}
	
	public boolean getIsSolved()
	{
		return this.isSolved;
	}
	
	public void itemSolve(Room r, ArrayList<Item> items)
	{
		for(Item i: items) {
			if(i.getId() == this.rewardItem) {
				r.addItem(i);
				break;
			}
		}
	}
	
	public void doorSolve()
	{
		
	}
	
	public void markSolved()
	{
		this.isSolved = false;
	}

	@Override
	public String toString() {
		return "[Id: " + id + "] " +
				"[Name: " + name + "] " +
				"[Question: " + question + "] " +
				"[Solution: " + solution + "] " +
				"[Hint: " + hint + "] " +
				"[Description: " + description + "] " +
				"[Correct Response: " + correctResp + "] " +
				"[Incorrect Response: " + wrongResp + "] " +
				"[Is Solved?: " + isSolved + "] ";

	}
	
}