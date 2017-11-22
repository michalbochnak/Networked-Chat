package Model;

import Controller.ServerController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerModel {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private ServerSocket server;
    private ObjectInputStream dataIn;
    private ObjectOutputStream dataOut;
    private ArrayList<ClientSocketModel> clients;
    private MessageModel receivedData, dataToSend;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ServerModel() {
        startTheServer();
        clients = new ArrayList<ClientSocketModel>();
        receivedData = null;
        dataToSend = null;
        this.dataIn = null;
        this.dataOut = null;
    }


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public String getIpAddress() {
        String tempIp = "none";
        try {
            tempIp = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            System.out.println("Cannot retrieve IP Address");
            e.printStackTrace();
        }
        return tempIp;
    }

    public int getServerPort() {
        return server.getLocalPort();
    }


    // ------------------------------------------------------------------------
    // Setters
    // ------------------------------------------------------------------------



    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    private void startTheServer() {
        try {
            this.server = new ServerSocket(0);
        } catch (IOException e) {
            System.out.println("Cannot create new Server Socket");
            e.printStackTrace();
        }
    }

    public void startClientListenerThread(Thread waitForClientThread) {
        waitForClientThread.start();
    }

    private MessageModel receiveData () {
        MessageModel msg = null;
        try {
            msg = (MessageModel)dataIn.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return msg;
    }

    private void processReceivedData(MessageModel msg) {
        // client disconnecting
        if (msg.isDisconnecting()) {
            // close client socket
            // close client input stream
            // close client output stream
        }
    }


    public void setDataOut(Socket clientSocket) {
        try {
            dataOut = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void setDataIn(Socket clientSocket) {
        try {
            dataIn = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Socket acceptClient() {
        Socket newClient = null;
        try {
            newClient = server.accept();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newClient;
    }

    public void addClient(ClientSocketModel newClientSocketModel) {
        clients.add(newClientSocketModel);
    }
}


    // ------------------------------------------------------------------------
    // Inner classes
    // ------------------------------------------------------------------------


/*
    //
    // Class to wait for the message from the particular client
    //
    class waitForClientData implements Runnable {
        @Override
        public void run () {

          //  while (true) {

                try {
                    receivedData = receiveData();
                    processReceivedData(receivedData);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }

        //    }
        }
    }
        */

