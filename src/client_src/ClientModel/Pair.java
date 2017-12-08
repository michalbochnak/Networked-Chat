//
// Michal Bochnak, Netid: mbochn2
// Artur Wojcik, Netid: awojci5
// Jakub Glebocki, Netid: jglebo2
//
// CS 342 Project #5 - Networked Chat Server
// Dec 7, 2017
// UIC, Pat Troy
//


//The java Class Pair is a class in which it has a Key and a n number to which
// it uses, sets, and gets the key in order for it to be usable and manageable



package ClientModel;

import java.io.Serializable;

public class Pair implements Serializable {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private int key;
    private int n;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public Pair(int key, int n) {
        this.key = key;
        this.n = n;
    }


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public int getKey() {
        return key;
    }

    public int getN() {
        return n;
    }


    // ------------------------------------------------------------------------
    // Setters
    // ------------------------------------------------------------------------
    public void setKey(int key) {
        this.key = key;
    }

    public void setN(int n) {
        this.n = n;
    }
}
