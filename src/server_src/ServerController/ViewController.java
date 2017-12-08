//
// Michal Bochnak, Netid: mbochn2
// Artur Wojcik, Netid: awojci5
// Jakub Glebocki, Netid: jglebo2
//
// CS 342 Project #5 - Networked Chat Server
// Dec 7, 2017
// UIC, Pat Troy
//


//The java Class ViewController is a class which controls what gets displayed
// to the screen for the server. It has getter functions to get the Frame, main
// panel, Client List, Menu Bar, Server Info, and Main Server Controller. It sets
// up each of those in this class and allows for the menu item Listeners to be
// active(so when user clicks it, it responds appropriately).





package ServerController;

import ServerView.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewController {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private JFrame frame;
    private JPanel mainPanel;
    private ClientsConnectedView clientsList;
    private MenuBarView menuBar;
    private ServerInfoView serverInfo;
    private MainServerController mainServerController;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ViewController(MainServerController mainServerController) {
        this.mainServerController = mainServerController;
        setupFrame();
        setupMenuBar();
        setupMainPanel();
        setupServerInfo();
        setupClientsList();
        addMenuItemsListeners();
        frame.setVisible(true);
    }


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public JFrame getFrame() {
        return frame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public ClientsConnectedView getClientsList() {
        return clientsList;
    }

    public MenuBarView getMenuBar() {
        return menuBar;
    }

    public ServerInfoView getServerInfo() {
        return serverInfo;
    }

    public MainServerController getMainServerController() {
        return mainServerController;
    }


    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    private void setupFrame() {
        frame = new JFrame("Chat Server");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(247, 365);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    private void setupClientsList() {
        clientsList = ClientsConnectedView.getInstance();
        clientsList.setBounds(20, 90, 200,200);
        mainPanel.add(clientsList);
    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(38,  40, 66));
        frame.add(mainPanel, BorderLayout.CENTER);
    }

    private void setupServerInfo() {
        serverInfo = ServerInfoView.getInstance();
        serverInfo.setBounds(20, 20, 200, 50);
        mainPanel.add(serverInfo);
    }

    private void setupMenuBar() {
        menuBar = MenuBarView.getInstance();
        frame.setJMenuBar(menuBar);
    }

    public void setServerInfo(String ip, int port) {
        serverInfo.setIpLabel(ip);
        serverInfo.setPortLabel(port);
    }

    public void updateClientsList(ArrayList<String> clients) {
        this.clientsList.clear();
        this.clientsList.updateWholeList(clients);
    }

    public void addMenuItemsListeners() {
        menuBar.addQuitListener(new QuitListener());
        menuBar.addHelpListener(new HelpListener());
        menuBar.addAboutListener(new AboutListener());

    }

    public class QuitListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.exit(1);
        }

    }

    public class HelpListener implements  ActionListener {

        public void actionPerformed(ActionEvent e) {

            JOptionPane.showMessageDialog(null,
                    "GUI based programs in Java Swing that allows multiple\n" +
                            "people to connect together and send encrypted messages\n" +
                            "to a specific connected person. The Java Socket and \n" +
                            "ServerSocket classes are used for the networking implementation.\n" +
                            "The RSA encryption/decryption code is designed and written\n" +
                            "by the authors of this program based on the resources provided \n" +
                            "by the Professor Troy in the given assignment. To Send a message\n" +
                            "select a person from the list and type a message server will\n" +
                            "transfer encrypted message to the selected person. Message will\n" +
                            "be decrypted on receiver side.\n",
                    "Help",
                    JOptionPane.PLAIN_MESSAGE);
        }

    }

    public class AboutListener implements  ActionListener {

        public void actionPerformed(ActionEvent e) {

            JOptionPane.showMessageDialog(null,
                    "Project 5 UIC Fall 2017\n" +
                            "Networked Chat with RSA Encryption/Decryption\n\n" +
                            "Program Authors: \n" +
                            "Artur Wojcik        - awojci5\n" +
                            "Michal Bochnak  - mbochn2\n" +
                            "Jakub Glebocki   - jglebo2\n\n",
                    "About",
                    JOptionPane.PLAIN_MESSAGE);
        }

    }


}


