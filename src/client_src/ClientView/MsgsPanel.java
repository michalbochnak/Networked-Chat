package ClientView;

import javax.swing.*;
import java.awt.*;

public class MsgsPanel extends JPanel {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private JLabel headerInfo;
    private JScrollPane msgsScrollPane;
    private DefaultListModel msgsList;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public MsgsPanel () {
        this.setPreferredSize(new Dimension(200, 200));
        this.setBackground(new Color(234, 30, 63));
        this.setLayout(new BorderLayout());
        this.msgsList = new DefaultListModel();

        setupHeader();
        setupMsgsScrollPane();
    }


    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    private void setupHeader() {
        this.headerInfo = new JLabel("Messages:");
        this.headerInfo.setForeground(Color.white);
        this.add(headerInfo, BorderLayout.NORTH);
    }

    private void setupMsgsScrollPane() {
        msgsScrollPane = new JScrollPane();
        msgsScrollPane.setPreferredSize(new Dimension(200, 200));
        this.add(msgsScrollPane);
    }

    public void addMessage(String name, String msg) {
        String msgAndRecipient = name + ": " + msg;
        this.msgsList.addElement(msgAndRecipient);
        JList list = new JList(msgsList);
        msgsScrollPane.setViewportView(list);
    }



}


