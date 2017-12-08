//
// Michal Bochnak, Netid: mbochn2
// Artur Wojcik, Netid: awojci5
// Jakub Glebocki, Netid: jglebo2
//
// CS 342 Project #5 - Networked Chat Server
// Dec 7, 2017
// UIC, Pat Troy
//


//The ConversationMsgModel java class is what controls the sending and receiving
// from which participant to which receiver. It Gets Both sender and Recipient
// as well as sets the Sender, Recipient and Encrypted message that is to be
// sent through the server to a client.



package ServerModel;

import java.io.Serializable;
import java.util.ArrayList;

public class ConversationMsgModel implements Serializable {


    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private String sender;
    private String recipient;
    private ArrayList<Integer> encryptedMsg;
    //private String testMsg;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ConversationMsgModel() {
        this.sender = "none";
        this.recipient = "none";
        this.encryptedMsg = new ArrayList<Integer>();
        //this.testMsg = null;
    }

    public ConversationMsgModel(String sender, String recipient, ArrayList<Integer> encrMsg) {
        this.sender = sender;
        this.recipient = recipient;
        this.encryptedMsg =null;
        this.encryptedMsg = encrMsg;
    }


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public String getSender() {
        return sender;
    }

    public ArrayList<Integer> getEncryptedMsg() {
        return encryptedMsg;
    }

    public String getRecipient() {
        return recipient;
    }

//    public String getTestMsg() {
//        return testMsg;
//    }


    // ------------------------------------------------------------------------
    // Setters
    // ------------------------------------------------------------------------
    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setEncryptedMsg(ArrayList<Integer> encryptedMsg) {
        this.encryptedMsg = encryptedMsg;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }


}


