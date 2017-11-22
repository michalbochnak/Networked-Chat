package View;

import javax.swing.*;
import java.awt.*;


//
// Class contains two labels that will hold information about the
// the server IP address and port that server is listening on
//


public class ServerInfoView extends JPanel {

    private JLabel titleLabel;
    private JLabel ipLabel;
    private JLabel portLabel;

    public ServerInfoView (String ip, String port) {
        setupTitleLabel();
        setupIpLabel(ip);
        setupPortLabel(port);

        this.setBackground(Color.white);
        this.setLayout(new GridLayout(3, 1));
    }

    private void setupTitleLabel() {
        titleLabel = new JLabel();
        titleLabel.setText("Server Information:");
        this.add(titleLabel);
    }

    private void setupIpLabel ( String ip) {
        ipLabel = new JLabel();
        this.ipLabel.setText(ip);
        this.add(ipLabel);
    }

    private void setupPortLabel (String port) {
        portLabel = new JLabel();
        this.portLabel.setText(port);
        this.add(portLabel);
    }



    public void setIpLabel(String ip) {
        this.ipLabel.setText("IP: " + ip);
    }

    public void setPortLabel(int port) {
        this.portLabel.setText("Port: " + Integer.toString(port));
    }


}
