package ClientView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SendMessagePanel extends JPanel {

    private static final SendMessagePanel instance = new SendMessagePanel();

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private JTextArea typeMsgTextArea;
    private JButton sendMsgButton;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    private SendMessagePanel () {
        this.setPreferredSize(new Dimension(300, 150));
        this.setBackground(Color.GRAY);
        this.setLayout(new BorderLayout());

        setupSendMsgButton();
        setupTypeMsgTextField();
    }


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public static SendMessagePanel getInstance() {
        return instance;
    }

    public String getTypedMessage() {
        return typeMsgTextArea.getText();
    }

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    private void setupSendMsgButton() {
        sendMsgButton = new JButton("Send");
        this.add(sendMsgButton, BorderLayout.EAST);
    }

    private void setupTypeMsgTextField () {
        typeMsgTextArea = new JTextArea();
        typeMsgTextArea.setLineWrap(true);
        this.add(typeMsgTextArea);
    }

    public void addSendMsgButtonListener(ActionListener al) {
        this.sendMsgButton.addActionListener(al);
    }


}


