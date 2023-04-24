//Cobi
package org.superherosquad;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Character;

public class Saver {
	View view = new View();
	private Scanner input = new Scanner(System.in);
	
	//Credit: https://stackoverflow.com/questions/24191040/checking-to-see-if-a-string-is-letters-spaces-only
	public static boolean onlyLettersSpaces(String s){
		  for(int i = 0; i < s.length(); i++){
		    char ch = s.charAt(i);
		    if (Character.isLetter(ch) || ch == ' ') {
		      continue;
		    }
		    return false;
		  }
		  return true;
		}
	
    public void saveGame(ArrayList<Room> rooms, ArrayList<Item> items, ArrayList<Puzzle> puzzles, ArrayList<Monster> monsters, ArrayList<NPC> npcs, Player p, Shop shop, int mode, int prevMode, int saveMode) {
    	boolean valid = false;
    	String fileName = null;
    	while(!valid) {
	        view.print("What would you like to name the save file? Please only include letters and spaces.");
	        fileName = input.nextLine().trim();
	        if(!onlyLettersSpaces(fileName) || fileName.isEmpty()) {
	        	view.print("That is not a valid file name.");
	        	continue;
	        }
	        valid = true;
    	}
    	fileName.concat(".dat");
    	
        ObjectOutputStream oos = null;
        try { //Try block in case the oos doesn't create.
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName));
            output.writeObject(rooms);
            output.writeObject(items);
            output.writeObject(puzzles);
            output.writeObject(monsters);
            output.writeObject(npcs);
            output.writeObject(p);
            output.writeObject(shop);
            output.writeInt(mode);
            output.writeInt(prevMode);
            output.writeInt(saveMode);
            view.print("Game saved as " + fileName);
        } catch (IOException ioe) {
            view.print("IOException!");
        } finally { //close the stream even if there is an exception thrown
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ioe) {
                view.print("Closing the outputstream failed.");
            }
        }
    }
}