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
    private void processDisconnectedClient(Socket socket) {
        removeClientSocketFromServerList(socket);
        sendUpdateToAllClients();
    }

    private void removeClientSocketFromServerList(Socket socket) {
        String nickname = this.getServerModel().removeClient(socket);
        // remove from GUI clients connected list
        this.mainServerController.getViewController().getClientsList().removeClient(nickname);
    }

    private void sendUpdateToAllClients() {
        ArrayList<String> clientsList = serverModel.getClientsList();
        for (ClientSocketModel csm: serverModel.getClientsSocketArray()) {
            sendUpdateToClient(csm, clientsList);
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
                System.out.println("Waiting..");
                Socket tempSocket = serverModel.acceptClient();
                System.out.println("after AcceptClient");
                processNewClient(tempSocket);
                System.out.println("Client port is: " + tempSocket.getLocalPort());
            }
        }
    }

    //
    // Class to wait for the message from the particular client
    //
    class waitForClientData implements Runnable {

        private Socket clientSocket;
        private ObjectInputStream dataIn;
        private ObjectOutputStream dataOut;
        private Object data;

        public waitForClientData(ClientSocketModel csm) {
            clientSocket = csm.getClientSocket();
            dataOut = csm.getDataOut();
            // to allow the client to establish data in stream
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
                    sendUpdateToAllClients();
                    break;
                case "ConversationMsgModel":
                    processConversationMsg(data);
                    break;
            }
        }

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

        private void processConversationMsg(Object data) {
            ConversationMsgModel msg = (ConversationMsgModel)data;
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
