package ClientView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ServerInfoView {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private JFrame serverInfoFrame;
    private JTextField ipTextField, portTextField, pTextField, qTextField;
    private JButton okButton;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ServerInfoView () {
        setupFrame();
        addInfoLabels();
        setupTextFields();
        setupOkButton();

        serverInfoFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//        serverInfoFrame.setVisible(false);
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

    public JTextField getpTextField() {
        return pTextField;
    }

    public JTextField getqTextField() {
        return qTextField;
    }

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    private void setupFrame () {
        serverInfoFrame = new JFrame("Networked Chat - Host Selection");
        serverInfoFrame.setSize(450, 300);
        serverInfoFrame.setLocationRelativeTo(null);
        serverInfoFrame.setLayout(null);
        serverInfoFrame.getContentPane().setBackground(new Color(0, 15, 15));
        serverInfoFrame.setResizable(false);
    }

    private void addInfoLabels() {
        addIpLabel();
        addPortLabel();
        addPQLabel();
    }

    private void addIpLabel () {
        JLabel infoLabel = new JLabel("Enter host ip:");
        infoLabel.setBounds(100, 25, 100, 30);
        infoLabel.setForeground(new Color(255, 0, 111));
        serverInfoFrame.getContentPane().add(infoLabel);
    }

    private void addPortLabel() {
        JLabel infoLabel = new JLabel("Enter host port:");
        infoLabel.setBounds(270, 25, 100, 30);
        infoLabel.setForeground(new Color(255, 0, 111));
        serverInfoFrame.getContentPane().add(infoLabel);
    }

    private void addPQLabel() {
        JLabel infoLabel = new JLabel("Enter two prime numbers, leave blank to generate random:");
        infoLabel.setBounds(60, 110, 400,  30);
        infoLabel.setForeground(new Color(255, 0, 111));
        serverInfoFrame.getContentPane().add(infoLabel);
    }

    private void setupTextFields() {
       setupIpTextField();
       setupPortTextField();
       setupPTextField();
       setupQTextField();
    }

    private void setupIpTextField() {
        ipTextField = new JTextField();
        ipTextField.setBounds(60, 55, 150, 30);
        serverInfoFrame.getContentPane().add(ipTextField);
    }

    private void setupPortTextField() {
        portTextField = new JTextField();
        portTextField.setBounds(240, 55, 150, 30);
        serverInfoFrame.getContentPane().add(portTextField);
    }

    private void setupPTextField() {
        pTextField = new JTextField();
        pTextField.setBounds(110, 140, 100, 30);
        serverInfoFrame.getContentPane().add(pTextField);
    }

    private void setupQTextField() {
        qTextField = new JTextField();
        qTextField.setBounds(240, 140, 100, 30);
        serverInfoFrame.getContentPane().add(qTextField);
    }

    private void setupOkButton() {
        okButton = new JButton("OK");
        okButton.setBounds(155, 210,140,30);
        serverInfoFrame.getContentPane().add(okButton);
    }

    public void addOkButtonActionListener(ActionListener al) {
        okButton.addActionListener(al);
    }

    public void hide() {
        serverInfoFrame.setVisible(false);
    }

    public void show() {
        serverInfoFrame.setVisible(true);
    }


}


