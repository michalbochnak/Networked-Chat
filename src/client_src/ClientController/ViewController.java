package ClientController;

import ClientView.*;
import ServerModel.ConversationMsgModel;
import ClientModel.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewController {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
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


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public ClientChatView getClientChatView() {
        return clientChatView;
    }


    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
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
        clientChatView.getSendMsgPanel().addSendMsgButtonListener(new SendMsgButtonListener());
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

    public void updateClientsList(ArrayList<String > clients) {
        this.clientChatView.updateClientsList(clients);
    }


    // ------------------------------------------------------------------------
    // Inner classes
    // ------------------------------------------------------------------------
    public class NameViewOkButtonListener implements ActionListener {

        private void showNameTooLongDialog() {
            JOptionPane.showMessageDialog(null,
                    "Name must be at least 1 letter and\n" +
                            "no more than 20 letters",
                    "Name format incorrect", JOptionPane.PLAIN_MESSAGE);
        }

        private void showNameTakenDialog() {
            JOptionPane.showMessageDialog(null,
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
                mainClientController.getViewController().getClientChatView().setNameLabel
                        (mainClientController.getClientController().getClientModel().getClientName());
                showMainChatWindow();
                mainClientController.getClientController().startThreadForDataReceiving();
                ConversationMsgModel msg = new ConversationMsgModel();
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

    public class ServerInfoViewOkButtonListener implements ActionListener {

        //checks if number is prime
        private boolean isPrime(int n) {
            if(n<=1){
                return  false;
            }
            for (int i = 2; i < n; i++)
                if (n % i == 0)
                    return false;

            return true;
        }

        private boolean sumMoreThan(int a, int b, int threshold) {
            return (a * b) > threshold;
        }

        private void showServerInfoErrorDialog() {
            JOptionPane.showMessageDialog(nameView.getEnterNameFrame(),
                    "Some of the provided information is incorrect.\n" +
                            "Please make sure server is running and make sure\n" +
                            "you typed server information correctly.\n" +
                            "Leave prime numbers fields empty or\n" +
                            "make sure typed in numbers are prime and their product\n" +
                            "is greater than " + (128*128*128) + ".",
                    "Server information incorrect", JOptionPane.PLAIN_MESSAGE);
        }

        private void tryToConnect(String ip, String portString) {
            int port = -1;
            try {
                port = Integer.parseInt(portString);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            // connection successful
            if (mainClientController.getClientController().connectToServer
                    (ip, port)) {
                mainClientController.setChatStage(2);
                showNamePrompt();

                String serverIp = mainClientController.getClientController().getClientModel()
                        .getClientSocket().getLocalAddress().getHostAddress().toString();
                int serverPort = mainClientController.getClientController().getClientModel()
                        .getClientSocket().getPort();

                mainClientController.getViewController().getClientChatView()
                        .setServerInfo(serverIp, serverPort);
            }
            // connection not succesful
            else {
                showServerInfoErrorDialog();
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String ip = serverInfoView.getIpTextField().getText();
            String portString = serverInfoView.getPortTextField().getText();
            String pNumber = serverInfoView.getpTextField().getText();
            String qNumber = serverInfoView.getqTextField().getText();

            if (pNumber.length() <= 0 && qNumber.length() <= 0) {
                // generate random from file
                    // FIXME: read p & q from file
                // try to connect
                if (ip.length() > 0 && portString.length() > 0) {
                   tryToConnect(ip, portString);
                } else {
                    showServerInfoErrorDialog();
                }
            }
            else {
                try {
                    int p = Integer.parseInt(pNumber);
                    int q = Integer.parseInt(qNumber);
                    if ( (p != q) && isPrime(p) && isPrime(q) && sumMoreThan(p, q, (128 * 128 * 128))) {
                        mainClientController.getClientController().getClientModel().setPq(new Pair(p, q));
                        tryToConnect(ip, portString);
                    }
                    else {
                        showServerInfoErrorDialog();
                    }
                } catch (Exception exc) {
                    exc.printStackTrace();
                    showServerInfoErrorDialog();
                }
            }
        }
    }

    public class SendMsgButtonListener implements ActionListener {

        private void showMsgToShortDialog() {
            JOptionPane.showMessageDialog(nameView.getEnterNameFrame(),
                    "Type your message in.",
                    "Message too short", JOptionPane.PLAIN_MESSAGE);
        }

        private void showSelectRecipientDialog() {
            JOptionPane.showMessageDialog(nameView.getEnterNameFrame(),
                    "Choose recipient from the users list.",
                    "Recipient not specified", JOptionPane.PLAIN_MESSAGE);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            String msgToSend = clientChatView.getTypedMessage();
            String msgRecipient = clientChatView.getClientsList().retrieveSelected();
            String msgSender = mainClientController.getClientController().getClientModel()
                    .getClientName();

            if (msgToSend.length() <= 0) {
                showMsgToShortDialog();
            }
            else if (msgRecipient.equals("none")) {
                showSelectRecipientDialog();
            }
            else {
                clientChatView.getMsgsPanel().addMessage
                        ("Me (to " + msgRecipient + ")", msgToSend);
                ConversationMsgModel msg = new ConversationMsgModel
                        (msgSender, msgRecipient, msgToSend);
                mainClientController.getClientController().sendMessage(msg);
            }
            // check if is longer than 0
            // send
        }
    }



}


