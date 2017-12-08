//
// Michal Bochnak, Netid: mbochn2
// Artur Wojcik, Netid: awojci5
// Jakub Glebocki, Netid: jglebo2
//
// CS 342 Project #5 - Networked Chat Server
// Dec 7, 2017
// UIC, Pat Troy
//


//The MessagesPanel Java class is the java class in which it sets up the message
// dialog box which allows the user to type in different messages. It sets up the
// header, The scroller message as well as takes the Person who you are sending
//the message to under consideration.





package ClientView;

import javax.swing.*;
import java.awt.*;

public class MsgsPanel extends JPanel {

    private static final MsgsPanel instance = new MsgsPanel();

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private JLabel headerInfo;
    private JScrollPane msgsScrollPane;
    private DefaultListModel msgsList;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    private MsgsPanel () {
        this.setPreferredSize(new Dimension(200, 200));
        this.setBackground(new Color(234, 30, 63));
        this.setLayout(new BorderLayout());
        this.msgsList = new DefaultListModel();

        setupHeader();
        setupMsgsScrollPane();
    }

    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public static MsgsPanel getInstance() {
        return instance;
    }

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    private void setupHeader() {
        this.headerInfo = new JLabel("Messages:");
        this.headerInfo.setForeground(Color.white);
        this.add(headerInfo, BorderLayout.NORTH);
    }

    private void setupMsgsScrollPane() {
        msgsScrollPane = new JScrollPane();
        msgsScrollPane.setPreferredSize(new Dimension(200, 200));
        this.add(msgsScrollPane);
    }

    public void addMessage(String name, String msg) {
        String msgAndRecipient = name + ": " + msg;
        this.msgsList.addElement(msgAndRecipient);
        JList list = new JList(msgsList);
        msgsScrollPane.setViewportView(list);
    }



}


