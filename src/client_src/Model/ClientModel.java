package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientModel {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private String clientID;
    private Socket clientSocket;
    private ObjectInputStream dataIn;
    private ObjectOutputStream dataOut;
    private MessageM receivedData, dataToSend;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ClientModel(String serverIp, int serverPort) {
        connectToServer(serverIp, serverPort);
        setDataOut();
        setDataIn();
    }


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------



    // ------------------------------------------------------------------------
    // Setters
    // ------------------------------------------------------------------------



    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    private void connectToServer(String serverIp, int serverPort) {
        try {
        clientSocket = new Socket(serverIp, serverPort);
        } catch (Exception e) {
            System.out.println("Failed");
            e.printStackTrace();
        }
    }

    private void setDataOut() {
        try {
            dataOut = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void setDataIn() {
        try {
            dataIn = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public void sendData(MessageModel msg) {
        try {
            this.dataOut.writeObject(msg);
            this.dataOut.flush();
            this.dataOut.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // ------------------------------------------------------------------------
    // Inner classes
    // ------------------------------------------------------------------------


}
