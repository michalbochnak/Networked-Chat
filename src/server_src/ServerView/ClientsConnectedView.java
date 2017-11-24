package ServerView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ClientsConnectedView extends JPanel {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------

    private Set<JLabel> clientsSet;
    //private JPanel mainPanel;
    private JLabel header;
    private JScrollPane clientsListScrollPane;
    private DefaultListModel clientList;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    public ClientsConnectedView() {
        clientsSet = new HashSet<>();

        this.setPreferredSize(new Dimension(200, 500));
        this.setBackground(Color.YELLOW);
        this.setLayout(new BorderLayout());
        this.clientList = new DefaultListModel();

        //setupMainPanel();
        setupHeader();
        setupClientsListScrollPane();
    }

    // FIXME: For testing only, to be removed
    public void test() {
        addClient("User 1");
        addClient("User 2");
        addClient("User 3");
        addClient("User 4");
        removeClient("User 2");
        removeClient("User 3");
    }


    //
    // FIXME: remove, to test scroll pane
    //
    public void test2() {
        DefaultListModel listModel = new DefaultListModel();
        listModel.addElement("Michal");
        listModel.addElement("Bochnak");
        JList list = new JList(listModel);
        clientsListScrollPane.setViewportView(list);
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
        header = new JLabel("Clients Connected:");
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
        clientsListScrollPane = new JScrollPane();
        clientsListScrollPane.setPreferredSize(new Dimension(200, 100));
        this.add(clientsListScrollPane);
    }

    public void addClient(String id) {
       this.clientList.addElement(id);
       JList list = new JList(clientList);
       clientsListScrollPane.setViewportView(list);
    }

    // FIXME: implement
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



