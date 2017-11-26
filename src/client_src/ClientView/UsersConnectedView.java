package ClientView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UsersConnectedView extends JPanel {

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
    public UsersConnectedView() {
        this.setPreferredSize(new Dimension(200, 500));
        this.setBackground(new Color(184, 30, 63));
        this.setLayout(new BorderLayout());
        this.usersList = new DefaultListModel();
        this.list = new JList();

        setupHeader();
        setupClientsListScrollPane();
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


