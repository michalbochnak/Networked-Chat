//
// Michal Bochnak, Netid: mbochn2
// Artur Wojcik, Netid: awojci5
// Jakub Glebocki, Netid: jglebo2
//
// CS 342 Project #5 - Networked Chat Server
// Dec 7, 2017
// UIC, Pat Troy
//
//

//Java class UsersConnectedView Allows the users who are connected to be displayed
// in a dialog box. It get's setup at the top of the header, it adds the client,
// updates the list for the new view as well as allows selection of which person
//you want to send the message to.



package ClientView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UsersConnectedView extends JPanel {

    private static final UsersConnectedView instance = new UsersConnectedView();

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private JLabel header;
    private JScrollPane usersListScrollPane;
    private DefaultListModel usersList;
    private JList list;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    private UsersConnectedView() {
        this.setPreferredSize(new Dimension(200, 500));
        this.setBackground(new Color(184, 30, 63));
        this.setLayout(new BorderLayout());
        this.usersList = new DefaultListModel();
        this.list = new JList();

        setupHeader();
        setupClientsListScrollPane();
    }

    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public static UsersConnectedView getInstance() {
        return instance;
    }


    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    private void setupHeader() {
        this.header = new JLabel("Users Connected:");
        this.header.setForeground(Color.white);
        this.add(header, BorderLayout.NORTH);
    }

    public void setupClientsListScrollPane() {
        this.usersListScrollPane = new JScrollPane();
        this.usersListScrollPane.setPreferredSize(new Dimension(200, 100));
        this.usersListScrollPane.getViewport().setBackground(Color.yellow);
        this.add(usersListScrollPane);
    }

    public void addClient(String id) {
        this.usersList.addElement(id);
        this.list = new JList(usersList);
        usersListScrollPane.setViewportView(list);
    }

    public void clear() {
        this.usersList.clear();
        this.list = new JList(usersList);
        usersListScrollPane.setViewportView(list);
    }

    public void updateWholeList(ArrayList<String> clients) {
        for (String name : clients) {
            this.usersList.addElement(name);
        }
        this.list = new JList(usersList);
        this.usersListScrollPane.setViewportView(list);
    }

    //
    // returns the nickname of user selected from the users list
    // "none" is returned if not found
    //
    public String retrieveSelected() {
        int index = list.getSelectedIndex();
        if ( index == -1 )
            return "none";
        else
            return (String)usersList.get(index);
    }

}


