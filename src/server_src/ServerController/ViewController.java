package ServerController;

import ServerView.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewController {


    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private JFrame frame;
    private JPanel mainPanel;
    private JLabel clientsListHeader;
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

    public JLabel getClientsListHeader() {
        return clientsListHeader;
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
    // Setters
    // ------------------------------------------------------------------------



    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------

    private void setupFrame() {
        frame = new JFrame("Chat Server");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setLayout(new BorderLayout());
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        //frame.setResizable(false);
    }

    private void setupClientsList() {
        clientsList = new ClientsConnectedView();
        //clientsList.setPreferredSize(new Dimension(200,600));
        clientsList.setBounds(10, 150, 300,100);
        mainPanel.add(clientsList);
    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        //mainPanel.setBounds(100, 100, 300, 300);
        mainPanel.setBackground(Color.pink);
        frame.add(mainPanel, BorderLayout.CENTER);
    }

    private void setupServerInfo() {
        serverInfo = new ServerInfoView("none", "none");
        serverInfo.setBounds(10, 10, 300, 70);
        mainPanel.add(serverInfo);
    }

    private void setupMenuBar() {
        menuBar = new MenuBarView();
        frame.setJMenuBar(menuBar);
    }

    public void setServerInfo(String ip, int port) {
        serverInfo.setIpLabel(ip);
        serverInfo.setPortLabel(port);
    }

    public void addClient(String id) {
        clientsList.addClient(id);
    }

    public void updateClientsList(ArrayList<String> clients) {
        this.clientsList.clear();
        this.clientsList.updateWholeList(clients);
    }


    // ------------------------------------------------------------------------
    // Inner classes
    // ------------------------------------------------------------------------

}
