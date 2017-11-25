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
    private Set<JLabel> usersSet;
    //private JPanel mainPanel;
    private JLabel header;
    private JScrollPane usersListScrollPane;
    private DefaultListModel usersList;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public UsersConnectedView() {
        usersSet = new HashSet<>();

        this.setPreferredSize(new Dimension(200, 500));
        this.setBackground(Color.YELLOW);
        this.setLayout(new BorderLayout());
        this.usersList = new DefaultListModel();

        //setupMainPanel();
        setupHeader();
        setupClientsListScrollPane();

        this.addClient("Test User");
    }

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------

//    private void setupMainPanel() {
//        mainPanel = new JPanel();
//        mainPanel.setBackground(Color.DARK_GRAY);
//        this.add(mainPanel);
//    }

    private void setupHeader() {
        header = new JLabel("Users Connected:");
        this.add(header, BorderLayout.NORTH);
    }

    /*
    private void setupClientsList() {
        clientsList = new JPanel();
        clientsList.setLayout(new GridLayout(10, 1));
        this.add(clientsList, BorderLayout.CENTER);
    }
    */

    public void setupClientsListScrollPane() {
        usersListScrollPane = new JScrollPane();
        usersListScrollPane.setPreferredSize(new Dimension(200, 100));
        this.add(usersListScrollPane);
    }

    public void addClient(String id) {
        this.usersList.addElement(id);
        JList list = new JList(usersList);
        usersListScrollPane.setViewportView(list);
    }

    // FIXME: implement
    public void removeClient(String id) {

    }

    public void clear() {
        this.usersList.clear();
        JList list = new JList(usersList);
        usersListScrollPane.setViewportView(list);
    }

    public void updateWholeList(ArrayList<String> clients) {
        for (String name : clients) {
            this.usersList.addElement(name);
        }
        JList list = new JList(usersList);
        this.usersListScrollPane.setViewportView(list);
    }
}
