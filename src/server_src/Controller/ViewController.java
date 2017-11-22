package Controller;

import View.*;
import javax.swing.*;
import java.awt.*;

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


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    public ViewController() {
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
        frame.setSize(750, 750);
        frame.setLocationRelativeTo(null);
        //frame.setResizable(false);
    }

    private void setupClientsList() {
        clientsList = new ClientsConnectedView();
        //clientsList.setPreferredSize(new Dimension(200,600));
        clientsList.setBounds(10, 150, 400,500);
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
        serverInfo.setBounds(10, 10, 400, 100);
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


    // ------------------------------------------------------------------------
    // Inner classes
    // ------------------------------------------------------------------------

}
