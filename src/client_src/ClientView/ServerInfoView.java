package ClientView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ServerInfoView {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private JFrame serverInfoFrame;
    private JTextField ipTextField, portTextField;
    private JButton okButton;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ServerInfoView () {
        setupFrame();
        addInfoLabels();
        setupTextFields();
        setupOkButton();

        serverInfoFrame.setVisible(true);
    }

    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public JTextField getIpTextField() {
        return ipTextField;
    }

    public JTextField getPortTextField() {
        return portTextField;
    }

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    private void setupFrame () {
        serverInfoFrame = new JFrame("Networked Chat - Host Selection");
        serverInfoFrame.setSize(600, 200);
        serverInfoFrame.setLocationRelativeTo(null);
        serverInfoFrame.setLayout(null);
        serverInfoFrame.getContentPane().setBackground(Color.pink);
        serverInfoFrame.setResizable(false);
    }

    private void addInfoLabels() {
        addIpLabel();
        addPortLabel();
    }

    private void addIpLabel () {
        JLabel infoLabel = new JLabel("Enter host ip:");
        infoLabel.setBounds(120, 25, 100, 30);
        serverInfoFrame.getContentPane().add(infoLabel);
    }

    private void addPortLabel() {
        JLabel infoLabel = new JLabel("Enter host port:");
        infoLabel.setBounds(390, 25, 100, 30);
        serverInfoFrame.getContentPane().add(infoLabel);
    }

    private void setupTextFields() {
       setupIpTextField();
       setupPortTextField();
    }

    private void setupIpTextField() {
        ipTextField = new JTextField();
        ipTextField.setBounds(60, 60, 200, 30);
        serverInfoFrame.getContentPane().add(ipTextField);
    }

    private void setupPortTextField() {
        portTextField = new JTextField();
        portTextField.setBounds(340, 60, 200, 30);
        serverInfoFrame.getContentPane().add(portTextField);
    }

    private void setupOkButton() {
        okButton = new JButton("OK");
        okButton.setBounds(230, 120,140,30);
        serverInfoFrame.getContentPane().add(okButton);
    }

    public void addOkButtonActionListener(ActionListener al) {
        okButton.addActionListener(al);
    }

    public void hide() {
        //serverInfoFrame.setEnabled(false);
        serverInfoFrame.setVisible(false);
    }

    public void show() {
        //serverInfoFrame.setEnabled(false);
        serverInfoFrame.setVisible(true);
    }

}
