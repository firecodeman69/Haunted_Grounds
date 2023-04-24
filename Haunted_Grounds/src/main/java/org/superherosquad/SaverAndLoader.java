//Cobi
package org.superherosquad;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.lang.Character;

public class SaverAndLoader {
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
	
    public void saveGame() {
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

    /*public Player loadGame(String fileName) { //Cody
        ObjectInputStream ois = null; //initialize a 'value' for ObejectInputStream
        FileInputStream fis;
        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            p = (Player) ois.readObject(); //set current player = the contents of the save file
            //loadedGame = (Game) ois.readObject();
        } catch (IOException | ClassNotFoundException ioe) { //multi catch statement instead of using 2 catch statements
            view.print(fileName);
            view.print("Either an IOException happened or the class couldn't be found! Youch!");
        } finally { //close the stream even if there is an exception thrown
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ioe) {
                view.print("Closing the input Stream failed buckoo");
            }
        }
        //return loadedGame;
        return p;
    }*/
}