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
    private String testMsg;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ConversationMsgModel() {
        this.sender = "none";
        this.recipient = "none";
        this.encryptedMsg = new ArrayList<Integer>();
        this.testMsg = null;
    }

    public ConversationMsgModel(String sender, String recipient, String msg) {
        this.sender = sender;
        this.recipient = recipient;
        this.encryptedMsg =null;
        this.testMsg = msg;
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

    public String getTestMsg() {
        return testMsg;
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
