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

        enterNameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        enterNameFrame.setSize(450, 300);
        enterNameFrame.setLocationRelativeTo(null);
        enterNameFrame.setLayout(null);
        enterNameFrame.getContentPane().setBackground(new Color(0, 15, 15));
        enterNameFrame.setResizable(false);
    }

    private void addInfoLabel() {
        JLabel infoLabel = new JLabel("Enter your name:");
        infoLabel.setBounds(175, 70, 100, 30);
        infoLabel.setForeground(new Color(255, 0, 111));
        enterNameFrame.getContentPane().add(infoLabel);
    }

    private void setupNameTextField() {
        nameTextField = new JTextField();
        nameTextField.setBounds(125, 105, 200, 30);
        enterNameFrame.getContentPane().add(nameTextField);
    }

    private void setupOkButton() {
        okButton = new JButton("OK");
        okButton.setBounds(155, 190,140,30);
        enterNameFrame.getContentPane().add(okButton);
    }

    public void addOkButtonActionListener(ActionListener al) {
        okButton.addActionListener(al);
    }

    public void hide() {
        enterNameFrame.setVisible(false);
    }

    public void show() {
        enterNameFrame.setVisible(true);
    }


}


