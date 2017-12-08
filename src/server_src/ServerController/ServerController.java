//
// Michal Bochnak, Netid: mbochn2
// Artur Wojcik, Netid: awojci5
// Jakub Glebocki, Netid: jglebo2
//
// CS 342 Project #5 - Networked Chat Server
// Dec 7, 2017
// UIC, Pat Troy
//

//Server Controller is where the server is being controlled in this java class.
// It process a New Client, Deals with client being disconnected, removes the
// client in case user decides to disconnect or some other arbitrary situation
// happens. In the event of quitting it updates the client by removing it and
// updating the list. It allows for a message to be sent to the client. It waits
// for the client to send data and then deals with that data appropriately(by
// processing it, and the message).


package ServerController;

import ServerModel.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ServerController {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private ServerModel serverModel;
    private MainServerController mainServerController;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ServerController(MainServerController mainServerController) {
        serverModel = ServerModel.getInstance() ;
        serverModel.startClientListenerThread(new Thread(new waitForClient()));
        this.mainServerController = mainServerController;
    }

    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public ServerModel getServerModel() {
        return serverModel;
    }



    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------

    //
    // Set up all the parts and start new thread for new connected client
    //
    private void processNewClient(Socket newClientSocket) {
        ClientSocketModel csm = new ClientSocketModel(newClientSocket);
        serverModel.addClient(csm);

        Thread clientThread = new Thread(new waitForClientData(csm));
        clientThread.start();
    }

    private void processDisconnectedClient(ClientSocketModel socket) {
        removeClientSocketFromServerList(socket.getClientSocket());
        sendUpdateToAllClients();
    }

    private void removeClientSocketFromServerList(Socket socket) {
        String nickname = this.getServerModel().removeClient(socket);
        // remove from GUI clients connected list
        this.mainServerController.getViewController().getClientsList().removeClient(nickname);
    }

    private void sendUpdateToAllClients() {
        UpdateMsgModel msg = new UpdateMsgModel(serverModel.getClientsList());
        for (ClientSocketModel csm: serverModel.getClientsSocketArray()) {
            sendMessageToClient(csm, msg);
        }
    }

    private void sendMessageToClient(ClientSocketModel csm, Object data) {
        try {
            csm.getDataOut().writeObject(data);
            csm.getDataOut().flush();
            csm.getDataOut().reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ------------------------------------------------------------------------
    // Inner classes
    // ------------------------------------------------------------------------

    //
    // Class to wait for the client connections
    //
    public class waitForClient implements Runnable {
        @Override
        public void run() {
            while (true) {
                Socket tempSocket = serverModel.acceptClient();
                processNewClient(tempSocket);
            }
        }
    }

    //
    // Class to wait for the message from the particular client
    //
    class waitForClientData implements Runnable {

        private ClientSocketModel clientSocket;
        private ObjectInputStream dataIn;
        private ObjectOutputStream dataOut;
        private Object data;

        public waitForClientData(ClientSocketModel csm) {
            clientSocket = csm;
            dataOut = csm.getDataOut();
            // flush to allow the client to establish data in stream
            try {
                dataOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dataIn = csm.getDataIn();
        }

        private Object receiveData() throws Exception {
            try {
                data = dataIn.readObject();
                return data;
            } catch (Exception e) {
                throw e;
            }
        }

        private void processReceivedData(Object data) {
            String className = data.getClass().getSimpleName().toString();

            switch (className) {
                case "InitialClientInfoMsgModel":
                    processInitialMsg(data);
                    break;
                case "ConversationMsgModel":
                    processConversationMsg(data);
                    break;
            }
        }

        private synchronized void processConversationMsg(Object data) {
            ConversationMsgModel msg = (ConversationMsgModel)data;
            ClientSocketModel recipient = serverModel.findRecipientSocket(msg.getRecipient());
            // forward message
            System.out.println("Forwarding msg to " + recipient.getNickname());
            sendMessageToClient(recipient, msg);
        }

        private synchronized void processInitialMsg(Object data) {
            System.out.println("In processInitialMsg ");
            InitialClientInfoMsgModel msg = (InitialClientInfoMsgModel)data;
            if (mainServerController.getServerController().serverModel.clientExist
                    (msg.getNickname())) {
                // respond to client that client name is not available
                msg.setNameAvailable(false);
                sendMessageToClient(clientSocket, msg);
            }
            else {
                // associate client name with socket
                serverModel.updateClientInfo(msg);
                mainServerController.getViewController().updateClientsList
                        (serverModel.getClientsNamesList());
                // sent true back
                msg.setNameAvailable(true);
                // send response
                sendMessageToClient(clientSocket, msg);
                // send update to all clients
                sendUpdateToAllClients();

                System.out.println("Done...");
            }
        }

        @Override
        public void run () {
            while (true) {
                try {
                   data = receiveData();
                   processReceivedData(data);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                    processDisconnectedClient(clientSocket);
                    break;  // client disconnected
                }
            }
        }

    }


}


