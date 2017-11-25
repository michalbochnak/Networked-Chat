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
    // 1 - server ip and port prompt
    // 2 - name prompt
    // 3 - main chat window
    //
    public ViewController(MainClientController mcc) {
        mainClientController = mcc;
        setupNameView();
        serverInfoView = new ServerInfoView();
        clientChatView = new ClientChatView();

        switch (mcc.getChatStage()) {
            case 1:
                showServerInfoPrompt();
                break;
            case 2:
                showNamePrompt();
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

        private void showNameTooLongDialog() {
            JOptionPane.showMessageDialog(nameView.getEnterNameFrame(),
                    "Name must be at least 1 letter and\n" +
                            "no more than 20 letters",
                    "Name format incorrect", JOptionPane.PLAIN_MESSAGE);
        }

        private void showNameTakenDialog() {
            JOptionPane.showMessageDialog(nameView.getEnterNameFrame(),
                    "Name you entered is already taken by the other chat user.\n" +
                            "Please, use different nickname",
                    "Name format incorrect", JOptionPane.PLAIN_MESSAGE);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameView.getNameTextField().getText();
            if (nameLengthValid(name)) {
                showNameTooLongDialog();
            }
            else if (!nameAlreadyTaken(name)) {
                showNameTakenDialog();
            }
            else {
                mainClientController.setChatStage(3);
                showMainChatWindow();
                mainClientController.getClientController().startThreadForDataReceiving();
               // mainClientController.getClientController().getClientModel().
              //          setClientName(name);
              //  mainClientController.getClientController().sendInitialInfo();
            }
        }

        private boolean nameLengthValid(String name) {
            return (name.length() <= 0) || (name.length() >= 20);
        }

        // Checks with the server if name choosen is not taken
        private boolean nameAlreadyTaken(String name) {
            return mainClientController.getClientController().usernameAvailable(name);

        }
    }

    // FIXME: add error check for 'port' given by user as letters
    public class ServerInfoViewOkButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String ip = serverInfoView.getIpTextField().getText();
            int port = Integer.parseInt(serverInfoView.getPortTextField().getText());
            // connection error
            if (!mainClientController.getClientController().connectToServer
                    (ip, port)) {
                showServerInfoErrorDialog();
            }
            // connection succesful
            else {
                mainClientController.setChatStage(2);
                showNamePrompt();
            }
        }
    }

    public void updateClientsList(ArrayList<String > clients) {
        this.clientChatView.updateClientsList(clients);
    }

}
