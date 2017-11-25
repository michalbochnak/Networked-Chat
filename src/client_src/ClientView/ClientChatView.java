package ClientView;

import ServerView.MenuBarView;
import ServerView.ServerInfoView;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ClientChatView extends JPanel {
    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private JFrame frame;
    private JPanel mainPanel;
    private ServerView.MenuBarView menuBar;
    private ServerView.ServerInfoView serverInfo;
    private UsersConnectedView clientsList;
    private MsgsPanel msgsPanel;
    private SendMessagePanel sendMsgPanel;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    public ClientChatView() {
        setupFrame();
        setupMenuBar();
        setupMainPanel();
        setupServerInfo();
        setupClientsList();
        setupMsgsPanel();
        setupSendMsgPanel();

        frame.setVisible(false);
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

    public UsersConnectedView getClientsList() {
        return clientsList;
    }

    public ServerView.MenuBarView getMenuBar() {
        return menuBar;
    }

    public ServerView.ServerInfoView getServerInfo() {
        return serverInfo;
    }

    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public SendMessagePanel getSendMsgPanel() {
        return sendMsgPanel;
    }

    public MsgsPanel getMsgsPanel() {
        return msgsPanel;
    }

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------

    private void setupFrame() {
        frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setLayout(new BorderLayout());
        frame.setSize(500, 650);
        frame.setLocationRelativeTo(null);
        //frame.setResizable(false);
    }

    private void setupClientsList() {
        clientsList = new UsersConnectedView();
        //clientsList.setPreferredSize(new Dimension(200,600));
        clientsList.setBounds(10, 150, 300,100);
        mainPanel.add(clientsList);
    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        //mainPanel.setBounds(100, 100, 300, 300);
        mainPanel.setBackground(Color.yellow);
        frame.add(mainPanel, BorderLayout.CENTER);
    }

    private void setupServerInfo() {
        serverInfo = new ServerInfoView("-", "-");
        serverInfo.setBounds(10, 10, 300, 70);
        mainPanel.add(serverInfo);
    }

    private void setupMenuBar() {
        menuBar = new MenuBarView();
        frame.setJMenuBar(menuBar);
    }

    private void setupMsgsPanel() {
        msgsPanel = new MsgsPanel();
        msgsPanel.setBounds(10, 260, 300, 200);
        mainPanel.add(msgsPanel);
    }

    private void setupSendMsgPanel() {
        sendMsgPanel = new SendMessagePanel();
        sendMsgPanel.setBounds(10, 470, 300, 70);
        mainPanel.add(sendMsgPanel);
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

    public String getTypedMessage() {
        return this.sendMsgPanel.getTypedMessage();
    }

    // ------------------------------------------------------------------------
    // Inner classes
    // ------------------------------------------------------------------------

}
