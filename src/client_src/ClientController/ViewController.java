package ClientController;

import ClientView.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewController {

    private NameView nameView;
    private ServerInfoView serverInfoView;
    private ClientChatView clientChatView;
    private MainClientController mainClientController;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    //
    // Displays requested by parameter window
    // 1 - name prompt
    // 2 - server ip and port prompt
    // 3 - main chat window
    //
    public ViewController(MainClientController mcc) {
        mainClientController = mcc;
        setupNameView();
        serverInfoView = new ServerInfoView();
        clientChatView = new ClientChatView();

        switch (mcc.getChatStage()) {
            case 1:
                showNamePrompt();
                break;
            case 2:
                showServerInfoPrompt();
                break;
            case 3:
                showMainChatWindow();
                break;
        }
    }

    private void showNamePrompt() {
        hideAllWindows();
        nameView.show();
    }

    private void showServerInfoPrompt() {
        hideAllWindows();
        serverInfoView.addOkButtonActionListener
                (new ServerInfoViewOkButtonListener());
        serverInfoView.show();
    }

    private void showMainChatWindow() {
        hideAllWindows();
        clientChatView.getFrame().setVisible(true);
    }

    private void hideAllWindows() {
        nameView.hide();
        serverInfoView.hide();
        clientChatView.setVisible(false);
    }

    private void setupNameView() {
        nameView = new NameView();
        nameView.addOkButtonActionListener(new NameViewOkButtonListener());
    }

    private void showServerInfoErrorDialog() {
        JOptionPane.showMessageDialog(nameView.getEnterNameFrame(),
                "Error connecting to server.\n" +
                        "Please make sure you type server information correctly.",
                "Server information incorrect", JOptionPane.PLAIN_MESSAGE);
    }

    private void showNameErrorDialog() {
        JOptionPane.showMessageDialog(nameView.getEnterNameFrame(),
                "Name must be at least 1 letter and\n" +
                        "no more than 15 letters",
                "Name format incorrect", JOptionPane.PLAIN_MESSAGE);
    }

    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public NameView getNameView() {
        return nameView;
    }

    // ------------------------------------------------------------------------
    // Inner classes
    // ------------------------------------------------------------------------
    public class NameViewOkButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameView.getNameTextField().getText();
            if (name.length() <= 0 || name.length() > 15) {
                showNameErrorDialog();
            }
            else {
                mainClientController.setChatStage(2);
                showServerInfoPrompt();
                mainClientController.getClientController().getClientModel().
                        setClientName(name);
            }
        }
    }

    // FIXME: add error check for 'port' given by user as letters
    public class ServerInfoViewOkButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("-3");
            String ip = serverInfoView.getIpTextField().getText();
            System.out.println("-2");
            int port = Integer.parseInt(serverInfoView.getPortTextField().getText());
            System.out.println("-1");
            // connection error
            if (!mainClientController.getClientController().connectToServer
                    (ip, port)) {
                System.out.println("0");
                showServerInfoErrorDialog();
            }
            // connection succesful
            else {
                mainClientController.setChatStage(3);
                System.out.println("1");
                mainClientController.getClientController().sendInitialInfo();
                System.out.println("2");
                showMainChatWindow();
                System.out.println("3");
                mainClientController.getClientController().startThreadForDataReceiving();
            }
        }
    }

    public void updateClientsList(ArrayList<String > clients) {
        this.clientChatView.updateClientsList(clients);
    }

}
