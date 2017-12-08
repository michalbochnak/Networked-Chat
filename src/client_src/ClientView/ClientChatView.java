//
// Michal Bochnak, Netid: mbochn2
// Artur Wojcik, Netid: awojci5
// Jakub Glebocki, Netid: jglebo2
//
// CS 342 Project #5 - Networked Chat Server
// Dec 7, 2017
// UIC, Pat Troy
//


//The Java class ClientChatView is the java class which displays the ClientChatView
// Window which allows the user to perform user-interface(Type into dialog box,
// Click on help menu, etc). It set's up all the panels to their appropriate place
// for user-interaction. It also displays Server connected info. It sets bounds,
//fonts foreground displays name label and much more.



package ClientView;

import ServerView.MenuBarView;
import ServerView.ServerInfoView;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ClientChatView extends JPanel {

    private static final ClientChatView instance = new ClientChatView();

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private JFrame frame;
    private JPanel mainPanel;
    private ClientView.MenuBarView menuBar;
    private ServerView.ServerInfoView serverInfo;
    private UsersConnectedView clientsList;
    private MsgsPanel msgsPanel;
    private SendMessagePanel sendMsgPanel;
    private JLabel nameLabel;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    private ClientChatView() {
        setupFrame();
        setupMenuBar();
        setupMainPanel();
        setupServerInfo();
        setupClientsList();
        setupMsgsPanel();
        setupSendMsgPanel();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(false);
    }


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public static ClientChatView getInstance() {
        return instance;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public UsersConnectedView getClientsList() {
        return clientsList;
    }

    public ClientView.MenuBarView getMenuBar() {
        return menuBar;
    }

    public ServerView.ServerInfoView getServerInfo() {
        return serverInfo;
    }

    public SendMessagePanel getSendMsgPanel() {
        return sendMsgPanel;
    }

    public MsgsPanel getMsgsPanel() {
        return msgsPanel;
    }

    public String getTypedMessage() {
        return this.sendMsgPanel.getTypedMessage();
    }


    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    private void setupFrame() {
        frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setLayout(new BorderLayout());
        frame.setSize(392, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        //mainPanel.setBounds(100, 100, 300, 300);
        mainPanel.setBackground(new Color(0, 15, 15));
        frame.add(mainPanel, BorderLayout.CENTER);
    }

    private void setupServerInfo() {
        serverInfo = ServerInfoView.getInstance();
        serverInfo.setBounds(195, 10, 180, 60);
        serverInfo.setBackgroundColor(null);
        mainPanel.add(serverInfo);
    }

    private void setupClientsList() {
        clientsList = UsersConnectedView.getInstance();
        //clientsList.setPreferredSize(new Dimension(200,600));
        clientsList.setBounds(10, 85, 365, 100);
        mainPanel.add(clientsList);
    }

    private void setupMsgsPanel() {
        msgsPanel = MsgsPanel.getInstance();
        msgsPanel.setBounds(10, 190, 365, 200);
        mainPanel.add(msgsPanel);
    }

    private void setupSendMsgPanel() {
        sendMsgPanel = SendMessagePanel.getInstance();
        sendMsgPanel.setBounds(10, 395, 365, 33);
        mainPanel.add(sendMsgPanel);
    }

    private void setupMenuBar() {
        menuBar = ClientView.MenuBarView.getInstance();
        frame.setJMenuBar(menuBar);
    }

    public void setServerInfo(String ip, int port) {
        serverInfo.setIpLabel(ip);
        serverInfo.setPortLabel(port);
    }

    public void setNameLabel(String name) {
        nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setBounds(10, 10, 180, 60);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setForeground(new Color(234, 30, 63));
        mainPanel.add(nameLabel);
    }

    public void addClient(String id) {
        clientsList.addClient(id);
    }

    public void updateClientsList(ArrayList<String> clients) {
        this.clientsList.clear();
        this.clientsList.updateWholeList(clients);
    }


}


