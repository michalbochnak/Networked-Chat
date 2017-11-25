package ClientView;

import javax.swing.*;
import java.awt.*;

public class MsgsPanel extends JPanel {

    private JLabel headerInfo;
    private JScrollPane msgsScrollPane;
    private DefaultListModel msgsList;


    public MsgsPanel () {
        this.setPreferredSize(new Dimension(200, 200));
        this.setBackground(Color.pink);
        this.setLayout(new BorderLayout());
        this.msgsList = new DefaultListModel();

        setupHeader();
        setupMsgsScrollPane();
    }

    private void setupHeader() {
        headerInfo = new JLabel("Messages:");
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
