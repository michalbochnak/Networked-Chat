package ServerView;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

//
// Class contains two labels that will hold information about the
// the server IP address and port that server is listening on
//

public class ServerInfoView extends JPanel {

    private static final ServerInfoView instance = new ServerInfoView("none", "none");

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private JLabel titleLabel;
    private JLabel ipLabel;
    private JLabel portLabel;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    private ServerInfoView (String ip, String port) {
        setupTitleLabel();
        setupIpLabel(ip);
        setupPortLabel(port);

        this.setBackground(new Color(234, 30, 63));
        this.setLayout(new GridLayout(3, 1));
    }

    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public static ServerInfoView getInstance() {
        return instance;
    }

    // ------------------------------------------------------------------------
    // Setters
    // ------------------------------------------------------------------------
    public void setIpLabel(String ip) {
        this.ipLabel.setText("IP: " + ip);
    }

    public void setPortLabel(int port) {
        this.portLabel.setText("Port: " + Integer.toString(port));
    }

    public void setBackgroundColor(Color color) {
        this.setBackground(color);
    }

    public void setServerInfoView (String ip, String port) {
        setupTitleLabel();
        setupIpLabel(ip);
        setupPortLabel(port);
    }

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    private void setupTitleLabel() {
        titleLabel = new JLabel("Server Information:", SwingConstants.CENTER);
        titleLabel.setForeground(Color.white);
        this.add(titleLabel);
    }

    private void setupIpLabel ( String ip) {
        this.ipLabel = new JLabel(ip, SwingConstants.CENTER);
        this.ipLabel.setForeground(Color.white);
        this.add(ipLabel);
    }

    private void setupPortLabel (String port) {
        this.portLabel = new JLabel(port, SwingConstants.CENTER);
        this.portLabel.setForeground(Color.white);
        this.add(portLabel);
    }


}


