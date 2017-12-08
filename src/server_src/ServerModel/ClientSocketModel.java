//
// Michal Bochnak, Netid: mbochn2
// Artur Wojcik, Netid: awojci5
// Jakub Glebocki, Netid: jglebo2
//
// CS 342 Project #5 - Networked Chat Server
// Dec 7, 2017
// UIC, Pat Troy
//


////The ClientSocketModel java class extends the socket class which allows it
// to create ClientSocket and set it to the on we are currently using. Basically
// the Client Socket gets Modelled in this  class. It Gets Nickname, Socket, Data
// in & out, and public Key. It also has setters to the Nickname and Public Key.



package ServerModel;

import ClientModel.Pair;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketModel extends Socket{

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private Socket clientSocket;
    private String nickname;
    private Pair publicKey;
    private ObjectOutputStream dataOut;
    private  ObjectInputStream dataIn;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ClientSocketModel (Socket newClientSocket) {
        this.clientSocket = newClientSocket;
        this.nickname = newClientSocket.getRemoteSocketAddress().toString();
        try {
            this.dataOut = new ObjectOutputStream(newClientSocket.getOutputStream());
            this.dataIn = new ObjectInputStream(newClientSocket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        publicKey = null;
    }


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public String getNickname() {
        return nickname;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public ObjectOutputStream getDataOut() {
        return dataOut;
    }

    public ObjectInputStream getDataIn() {
        return dataIn;
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


