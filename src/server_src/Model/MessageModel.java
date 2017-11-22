package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class MessageModel implements Serializable {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private String sender;
    private ArrayList<Integer> encryptedMsg;
    private String recipient;
    private boolean disconnecting;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public MessageModel () {
        this.sender = "none";
        this.recipient = "none";
        this.encryptedMsg = new ArrayList<Integer>();
        this.disconnecting = false;
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

    public boolean isDisconnecting() {
        return this.disconnecting;
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
