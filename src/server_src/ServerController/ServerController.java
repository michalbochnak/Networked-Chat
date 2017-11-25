package ServerController;

import ServerModel.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


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
        serverModel = new ServerModel();
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

    public void start() {

    }

    //
    // Set up all the parts and start new thread for new connected client
    //
    private void processNewClient(Socket newClientSocket) {
        ClientSocketModel csm = new ClientSocketModel(newClientSocket);
        serverModel.addClient(csm);
        mainServerController.getViewController().addClient
                (csm.getNickname());

        System.out.println("before thread creation...");
        Thread clientThread = new Thread(new waitForClientData(csm));
        System.out.println("Thread created, about to start the thread...");
        clientThread.start();
        System.out.println("Thread started...");
    }

    // FIXME: impl
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

    private void sendUpdateToClient(ClientSocketModel csm, ArrayList<String> clientsList) {
        System.out.println("Sending update to: " + csm.getNickname());
        try {
            UpdateMsgModel msg = new UpdateMsgModel(clientsList);
            ObjectOutputStream dataOut = csm.getDataOut();
            dataOut.writeObject(msg);
            dataOut.flush();
            dataOut.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessageToClient(ClientSocketModel csm, Object data) {
        System.out.println("Sending message to " + csm.getNickname());
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
/*
        private void processInitialMsg(Object data) {
            InitialClientInfoMsgModel msg = (InitialClientInfoMsgModel)data;
            String name = msg.getNickname();
            String ip = msg.getIp();
            int port = msg.getPort();
            // set client nickname
            serverModel.updateClientName(name, ip, port);
            // update gui list
            mainServerController.getViewController().updateClientsList(serverModel.getClientsList());
        }
*/
        private void processConversationMsg(Object data) {
            ConversationMsgModel msg = (ConversationMsgModel)data;
        }

        private void processInitialMsg(Object data) {
            System.out.println("In processInitialMsg ");
            InitialClientInfoMsgModel msg = (InitialClientInfoMsgModel)data;
            if (mainServerController.getServerController().serverModel.clientExist(msg.getNickname())) {
                // respond to client that client name is not available
                msg.setNameAvailable(false);
                sendMessageToClient(clientSocket, msg);
            }
            else {
                // associate client name with socket
                serverModel.updateClientName(msg.getNickname(), msg.getIp(), msg.getPort());
                mainServerController.getViewController().updateClientsList(serverModel.getClientsList());
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
