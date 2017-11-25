package ClientController;

import ClientModel.*;
import ServerModel.InitialClientInfoMsgModel;
import ServerModel.UpdateMsgModel;
import javax.swing.*;


public class ClientController {

    private ClientModel clientModel;
    private MainClientController mainClientController;

    public ClientController(MainClientController mcc) {
        mainClientController = mcc;
        clientModel = new ClientModel();
    }

    public boolean connectToServer(String ip, int port) {
        return clientModel.connectToServer(ip, port);
    }

    public ClientModel getClientModel() {
        return clientModel;
    }

    /*
    public void sendInitialInfo() {
        InitialClientInfoMsgModel msg = new InitialClientInfoMsgModel
                (clientModel.getClientName(),
                        clientModel.getClientSocket().getLocalAddress().toString(),
                        clientModel.getClientSocket().getLocalPort());

        clientModel.sendData(msg);
    }
*/

    public void startThreadForDataReceiving() {
        new Thread(new waitForServerData()).start();
    }

    private void showServerDisconnectedDialog() {
        JOptionPane.showMessageDialog(null,
                "Server disconnected.\n Chat Client will be closed.");
    }

    public boolean usernameAvailable(String name) {
        String ip = clientModel.getClientSocket().getLocalAddress().toString();
        int port = clientModel.getClientSocket().getLocalPort();
        InitialClientInfoMsgModel msg = new InitialClientInfoMsgModel
                (name, ip, port, false);

        clientModel.sendData(msg);
        // wait for response

        System.out.println("Here 1");

        Object data;
            data = clientModel.receiveData();

        System.out.println("Here 2");

        if (data.getClass().getSimpleName().equals("InitialClientInfoMsgModel")) {
            InitialClientInfoMsgModel response = (InitialClientInfoMsgModel)data;
            if (response.isNameAvailable()) {
                System.out.println("True returned...");
                clientModel.setClientName(name);
                return true;
            }
        }
        System.out.println("False returned...");
        return false;
    }

    private void processReceivedData(Object data) {

    }


    // ------------------------------------------------------------------------
    // Inner classes
    // ------------------------------------------------------------------------
    public class waitForServerData implements Runnable {

        private void processReceivedData(Object data) {
            String className = data.getClass().getSimpleName().toString();

            System.out.println("Class name: " + className);
            switch (className) {
                case "UpdateMsgModel":
                    processUpdateMsg(data);
                    break;
                case "ConversationMsgModel":
                    processConversationMsg(data);
                    break;
            }
        }

        private void processUpdateMsg(Object data) {
            UpdateMsgModel msg = (UpdateMsgModel)data;
            mainClientController.getViewController().updateClientsList(msg.getClientsList());
        }

        private void processConversationMsg(Object data) {

        }

        @Override
        public void run() {
            Object data;
            while (true) {
                try {
                    data = clientModel.getDataIn().readObject();
                    processReceivedData(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    showServerDisconnectedDialog();
                    System.exit(0);
                    break;
                }
            }
        }
    }

}



