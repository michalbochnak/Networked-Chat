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
//    private boolean disconnecting;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ConversationMsgModel() {
        this.sender = "none";
        this.recipient = "none";
        this.encryptedMsg = new ArrayList<Integer>();
//        this.disconnecting = false;
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

//    public boolean isDisconnecting() {
//        return this.disconnecting;
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


    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------



    // ------------------------------------------------------------------------
    // Inner classes
    // ------------------------------------------------------------------------


}
