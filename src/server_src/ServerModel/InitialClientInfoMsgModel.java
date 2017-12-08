//
// Michal Bochnak, Netid: mbochn2
// Artur Wojcik, Netid: awojci5
// Jakub Glebocki, Netid: jglebo2
//
// CS 342 Project #5 - Networked Chat Server
// Dec 7, 2017
// UIC, Pat Troy
//


//The InitialClientInfoMsgModel java class is a class which sets the client Info
// nickname, port, ip, and public key. It then has a bunch of Getter functions
// in order to be able to retain that information anywhere else in the file. It
// also has a SetNameAvailability yo make sure the name is available and can be
// set(aka not in use).


package ServerModel;

import ClientModel.Pair;

import java.io.Serializable;

public class InitialClientInfoMsgModel implements Serializable {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private String nickname;
    private String ip;
    private int port;
    private boolean nameAvailable;
    private Pair publicKey;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public InitialClientInfoMsgModel (String nickname, String ip, int port,
                                      boolean nameAvailable, Pair publicKey) {

        this.nickname = nickname;
        this.ip = ip;
        this.port = port;
        this.nameAvailable = nameAvailable;
        this.publicKey = publicKey;
    }

    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public String getNickname() {
        return nickname;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public Pair getPublicKey() {
        return publicKey;
    }

    public boolean isNameAvailable() {
        return nameAvailable;
    }


    // ------------------------------------------------------------------------
    // Setters
    // ------------------------------------------------------------------------
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setNameAvailable(boolean nameAvailable) {
        this.nameAvailable = nameAvailable;
    }


}


