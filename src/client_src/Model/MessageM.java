package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class MessageM implements Serializable {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private String sender;
    private ArrayList<Integer> encryptedMsg;
    private String recipient;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public MessageM() {
        this.sender = "none";
        this.recipient = "none";
        this.encryptedMsg = new ArrayList<Integer>();
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


    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------



    // ------------------------------------------------------------------------
    // Inner classes
    // ------------------------------------------------------------------------


}
