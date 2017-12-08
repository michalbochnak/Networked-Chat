//
// Michal Bochnak, Netid: mbochn2
// Artur Wojcik, Netid: awojci5
// Jakub Glebocki, Netid: jglebo2
//
// CS 342 Project #5 - Networked Chat Server
// Dec 7, 2017
// UIC, Pat Troy
//


//The Java class ClientsConnectedView extends JPanel. Basically this class allows
// the visibility of clients connected to the server. It setups the header, and
// setups client List Scroll panel. It also ahs the capability to remove a client,
// clear the name from the List and Update the whole list.



package ServerView;

import ClientModel.ClientModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ClientsConnectedView extends JPanel {

    private static final ClientsConnectedView instance = new ClientsConnectedView();

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private JLabel header;
    private JScrollPane clientsListScrollPane;
    private DefaultListModel clientList;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    private ClientsConnectedView() {

        this.setPreferredSize(new Dimension(200, 500));
        this.setBackground(new Color(234, 30, 63));
        this.setLayout(new BorderLayout());
        this.clientList = new DefaultListModel();

        //setupMainPanel();
        setupHeader();
        setupClientsListScrollPane();
    }

    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public static ClientsConnectedView getInstance() {
        return instance;
    }

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    private void setupHeader() {
        this.header = new JLabel("Clients Connected:", SwingConstants.CENTER);
        this.header.setForeground(Color.white);
        this.add(header, BorderLayout.NORTH);
    }

    public void setupClientsListScrollPane() {
        clientsListScrollPane = new JScrollPane();
        clientsListScrollPane.setPreferredSize(new Dimension(200, 100));
        this.add(clientsListScrollPane);
    }

    public void addClient(String id) {
       this.clientList.addElement(id);
       JList list = new JList(clientList);
       clientsListScrollPane.setViewportView(list);
    }

    public void removeClient(String id) {
        this.clientList.removeElement(id);
    }

    public void clear() {
        this.clientList.clear();
        JList list = new JList(clientList);
        clientsListScrollPane.setViewportView(list);
    }

    public void updateWholeList(ArrayList<String> clients) {
        for (String name : clients) {
            this.clientList.addElement(name);
        }
        JList list = new JList(clientList);
        this.clientsListScrollPane.setViewportView(list);
    }


}


