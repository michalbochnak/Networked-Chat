//
// Michal Bochnak, Netid: mbochn2
// Artur Wojcik, Netid: awojci5
// Jakub Glebocki, Netid: jglebo2
//
// CS 342 Project #5 - Networked Chat Server
// Dec 7, 2017
// UIC, Pat Troy
//


//The ClientPublicProfile java class is a class which gets the clients who are
// connected to the profile as well as gets their Nickname and public Key for
// usage. It has a setter function for the Nickname as well.



package ServerModel;

import ClientModel.Pair;

import java.io.Serializable;

public class ClientPublicProfile implements Serializable {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private String nickname;
    private Pair publicKey;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ClientPublicProfile (String name, Pair publicKey) {
        nickname = name;
        this.publicKey = publicKey;
    }


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public String getNickname() {
        return nickname;
    }

    public Pair getPublicKey() {
        return publicKey;
    }


    // ------------------------------------------------------------------------
    // Setters
    // ------------------------------------------------------------------------
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPublicKey(Pair publicKey) {
        this.publicKey = publicKey;
    }


}


