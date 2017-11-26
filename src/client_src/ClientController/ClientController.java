



package ClientController;

import ClientModel.*;
import ServerModel.ClientPublicProfile;
import ServerModel.ConversationMsgModel;
import ServerModel.InitialClientInfoMsgModel;
import ServerModel.UpdateMsgModel;
import javax.swing.*;
import java.util.ArrayList;


public class ClientController {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private ClientModel clientModel;
    private MainClientController mainClientController;
    private ArrayList<ClientPublicProfile> usersConnected;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ClientController(MainClientController mcc) {
        mainClientController = mcc;
        clientModel = new ClientModel();
        usersConnected = null;
    }


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public ClientModel getClientModel() {
        return clientModel;
    }

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    public boolean connectToServer(String ip, int port) {
        return clientModel.connectToServer(ip, port);
    }

    public void startThreadForDataReceiving() {
        new Thread(new waitForServerData()).start();
    }

    private void showServerDisconnectedDialog() {
        JOptionPane.showMessageDialog(null,
                "Server disconnected.");
    }

    //
    // Function send message with name to the server and waits for the server response,
    // if name is available it is added to the client list on server side and set as a name
    // on the client side and true is returned, false is returned otherwise
    //
    public boolean usernameAvailable(String name) {

        String ip = clientModel.getClientSocket().getLocalAddress().toString();
        int port = clientModel.getClientSocket().getLocalPort();
        // FIXME: remove
        Pair publicKey = new Pair(77, 88);

        InitialClientInfoMsgModel msg = new InitialClientInfoMsgModel
                (name, ip, port, false, publicKey);

        // send data with selected username
        clientModel.sendData(msg);

        // wait for response
        Object data = clientModel.receiveData();

        if (data.getClass().getSimpleName().equals("InitialClientInfoMsgModel")) {
            InitialClientInfoMsgModel response = (InitialClientInfoMsgModel)data;
            if (response.isNameAvailable()) {
                // name available
                clientModel.setClientName(name);
                return true;
            }
        }
        // name not available
        return false;
    }

    public void sendMessage(Object msg) {
        clientModel.sendData(msg);
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
            usersConnected = msg.getClientsList();
            removeClientUsername(usersConnected);
            mainClientController.getViewController().updateClientsList(buildNamesArray(usersConnected));
        }

        private void removeClientUsername(ArrayList<ClientPublicProfile> usersConnected) {
            int toRemove = -1;
            for (int i = 0; i < usersConnected.size(); ++i) {
                if (usersConnected.get(i).getNickname().equals(clientModel.getClientName())) {
                    toRemove = i;
                    break;
                }
            }
            // remove
            try {
                usersConnected.remove(toRemove);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

        private ArrayList<String> buildNamesArray (ArrayList<ClientPublicProfile> users) {
            ArrayList<String> list = new ArrayList<String>();
            for (ClientPublicProfile c : users) {
                list.add(c.getNickname());
            }
            return list;
        }

        private void processConversationMsg(Object data) {
            ConversationMsgModel msg = (ConversationMsgModel)data;
            mainClientController.getViewController().getClientChatView().getMsgsPanel()
                    .addMessage(msg.getSender(), msg.getTestMsg());
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



