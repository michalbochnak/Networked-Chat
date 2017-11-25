package ClientView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NameView {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private JFrame enterNameFrame;
    private JTextField nameTextField;
    private JButton okButton;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public NameView () {
        setupFrame();
        addInfoLabel();
        setupNameTextField();
        setupOkButton();

//        enterNameFrame.setVisible(true);
    }

    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JFrame getEnterNameFrame() {
        return enterNameFrame;
    }

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    private void setupFrame () {
        enterNameFrame = new JFrame("Networked Chat - User Info");
        enterNameFrame.setSize(600, 200);
        enterNameFrame.setLocationRelativeTo(null);
        enterNameFrame.setLayout(null);
        enterNameFrame.getContentPane().setBackground(Color.pink);
        enterNameFrame.setResizable(false);
    }

    private void addInfoLabel() {
        JLabel infoLabel = new JLabel("Enter your name:");
        infoLabel.setBounds(250, 25, 100, 30);
        enterNameFrame.getContentPane().add(infoLabel);
    }

    private void setupNameTextField() {
        nameTextField = new JTextField();
        nameTextField.setBounds(200, 60, 200, 30);
        enterNameFrame.getContentPane().add(nameTextField);
    }

    private void setupOkButton() {
        okButton = new JButton("OK");
        okButton.setBounds(230, 120,140,30);
        enterNameFrame.getContentPane().add(okButton);
    }

    public void addOkButtonActionListener(ActionListener al) {
        okButton.addActionListener(al);
    }

    public void hide() {
        //enterNameFrame.setEnabled(false);a
        enterNameFrame.setVisible(false);
    }

    public void show() {
        //enterNameFrame.setEnabled(false);a
        enterNameFrame.setVisible(true);
    }
}
