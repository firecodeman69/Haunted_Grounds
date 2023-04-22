package org.superherosquad;

import java.io.*;

public class Game implements Serializable {

    public static void main(String[] args) {
        Controller c1 = new Controller();
        c1.gamePlay(c1);
    }

}