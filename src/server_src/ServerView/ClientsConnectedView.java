package ServerView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ClientsConnectedView extends JPanel {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private JLabel header;
    private JScrollPane clientsListScrollPane;
    private DefaultListModel clientList;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ClientsConnectedView() {

        this.setPreferredSize(new Dimension(200, 500));
        this.setBackground(new Color(234, 30, 63));
        this.setLayout(new BorderLayout());
        this.clientList = new DefaultListModel();

        //setupMainPanel();
        setupHeader();
        setupClientsListScrollPane();
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


