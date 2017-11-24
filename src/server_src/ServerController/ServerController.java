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
        serverModel.addClient(new ClientSocketModel(newClientSocket));
        //serverModel.setDataOut(newClientSocket);
        //serverModel.setDataIn(newClientSocket);
        mainServerController.getViewController().addClient
                (newClientSocket.getInetAddress().getHostAddress());

        System.out.println("before thread creation...");
        Thread clientThread = new Thread(new waitForClientData(newClientSocket));
        System.out.println("Thread created, about to start the thread...");
        clientThread.start();
        System.out.println("Thread started...");
    }

    // FIXME: impl
    private void processDisconnectedClient(Socket socket) {
        // remove from server list
        String nickname = this.getServerModel().removeClient(socket);
        // remove from GUI clients connected list
        this.mainServerController.getViewController().getClientsList().removeClient(nickname);
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

        public waitForClientData(Socket socket) {
            clientSocket = socket;
            setupDataOut(socket);
            // to allow the client to establish data in stream
            try {
                dataOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            setupDataIn(socket);
        }

        private void setupDataIn (Socket socket) {
            try {
                System.out.println("11");
                this.dataIn = new ObjectInputStream(clientSocket.getInputStream());
                System.out.println("12");
            } catch (IOException e) {
                System.out.println("exc: "+ e.getMessage());
                e.printStackTrace();
            }
        }

        private void setupDataOut(Socket socket) {
            try {
                this.dataOut = new ObjectOutputStream(clientSocket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
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
