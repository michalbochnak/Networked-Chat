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
        clientsList = new ClientsConnectedView();
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
        serverInfo = new ServerInfoView("none", "none");
        serverInfo.setBounds(20, 20, 200, 50);
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
            System.exit(1);
        }

    }

    public class AboutListener implements  ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.exit(1);
        }

    }


}


