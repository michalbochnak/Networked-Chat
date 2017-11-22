package Model;

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
    private ArrayList<Socket> clients;
    private MessageModel receivedData, dataToSend;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ServerModel() {
        startTheServer();
        startClientListenerThread();
        clients = new ArrayList<Socket>();
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

    private void startClientListenerThread() {
        Thread waitForClientThread = new Thread(new waitForClient());
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


    // ------------------------------------------------------------------------
    // Inner classes
    // ------------------------------------------------------------------------

    //
    // Class to wait for the client connections
    //
    class waitForClient implements Runnable {
        @Override
        public void run() {
            while (true) {
                Socket tempSocket = new Socket();
                try {
                    System.out.println("Waiting..");
                    tempSocket = server.accept();
                    processNewClient(tempSocket);
                    System.out.println("Client port is: " + tempSocket.getLocalPort());
                } catch (IOException e) {
                    System.out.println("Error in waiting for client");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        //
        // Set up all the parts and start new thread for new connected client
        //
        private void processNewClient(Socket newClientSocket) {
            clients.add(newClientSocket);
            setDataOut(newClientSocket);
            setDataIn(newClientSocket);
            Thread clientThread = new Thread(new waitForClientData());
            clientThread.start();
        }

        private void setDataOut(Socket clientSocket) {
            try {
                dataOut = new ObjectOutputStream(clientSocket.getOutputStream());
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

        private void setDataIn(Socket clientSocket) {
            try {
                dataIn = new ObjectInputStream(clientSocket.getInputStream());
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    //
    // Class to wait for the message from the particular client
    //
    class waitForClientData implements Runnable {
        @Override
        public void run () {

          //  while (true) {

                try {
                    receivedData = receiveData();
                    System.out.println("data received: " + receivedData.getSender());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }

        //    }
        }
    }


}
