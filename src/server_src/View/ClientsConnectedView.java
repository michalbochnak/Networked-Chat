package View;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class ClientsConnectedView extends JPanel {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------

    private Set<JLabel> clientsSet;
    //private JPanel mainPanel;
    private JLabel header;
    private JPanel clientsList;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    public ClientsConnectedView() {
        clientsSet = new HashSet<>();

        this.setPreferredSize(new Dimension(200, 500));
        this.setBackground(Color.YELLOW);
        this.setLayout(new BorderLayout());


        //setupMainPanel();
        setupHeader();
        setupClientsList();

        //test();
    }

    // FIXME: For testing only, to be removed
    public void test() {
        addClient("User 1");
        addClient("User 2");
        addClient("User 3");
        addClient("User 4");
        removeClient("User 2");
        removeClient("User 3");

        updateClientsView();
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

    private void setupClientsList() {
        clientsList = new JPanel();
        clientsList.setLayout(new GridLayout(10, 1));
        this.add(clientsList, BorderLayout.CENTER);
    }

    public void addClient(String id) {
       clientsSet.add(new JLabel(id));
    }

    public void removeClient(String id) {
        JLabel toRemove = findClientLabel(id);
        if ( toRemove == null) {
            System.out.println("Client not on the list");
        }
        else {
            clientsSet.remove(toRemove);
        }
    }

    private JLabel findClientLabel(String id) {
        System.out.println(clientsSet.size());
        for (JLabel c : clientsSet) {
            if (c.getText().equals(id)) {
                return c;
            }
        }

        return null;
    }

    private void updateClientsView() {
        clearClients();
        displayClients();
    }

    private void clearClients() {
        for (JLabel cl : clientsSet) {
            clientsList.remove(cl);
        }
    }

    private void displayClients() {
        for (JLabel cl : clientsSet)
            clientsList.add(cl);
    }


}



