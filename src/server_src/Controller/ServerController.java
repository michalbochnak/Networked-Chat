package Controller;

import Model.ClientSocketModel;
import Model.ServerModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerController {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private ServerModel serverModel;
    private MainAppController mainAppController;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ServerController(MainAppController mainAppController) {
        serverModel = new ServerModel();
        serverModel.startClientListenerThread(new Thread(new waitForClient()));
        this.mainAppController = mainAppController;
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
        serverModel.setDataOut(newClientSocket);
        serverModel.setDataIn(newClientSocket);
        mainAppController.getViewController().getClientsList().addClient
                (newClientSocket.getInetAddress().getHostAddress());

        //Thread clientThread = new Thread(new waitForClientData());
        //clientThread.start();
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
                //try {
                    System.out.println("Waiting..");
                    Socket tempSocket = serverModel.acceptClient();
                    processNewClient(tempSocket);
                    System.out.println("Client port is: " + tempSocket.getLocalPort());
                //} catch (IOException e) {
                //    System.out.println("Error in waiting for client");
                //    System.out.println(e.getMessage());
                 //   e.printStackTrace();
               // }
            }
        }
    }







}
