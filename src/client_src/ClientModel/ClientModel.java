package ClientModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ClientModel {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private String clientName;
    private Socket clientSocket;
    private ObjectInputStream dataIn;
    private ObjectOutputStream dataOut;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ClientModel() {
        clientName = "none";
        clientSocket = null;
        dataOut = null;
        dataIn = null;
    }

    public ClientModel(String serverIp, int serverPort) {
        connectToServer(serverIp, serverPort);
        //setDataOut();
        //setDataIn();
    }


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public String getClientName() {
        return clientName;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public ObjectInputStream getDataIn() {
        return dataIn;
    }

    // ------------------------------------------------------------------------
    // Setters
    // ------------------------------------------------------------------------


    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    public boolean connectToServer(String serverIp, int serverPort) {
        try {
            clientSocket = new Socket(serverIp, serverPort);
            setupInOutStream();
            return true;
        } catch (Exception e) {
            System.out.println("Failed");
            e.printStackTrace();
            return false;
        }
    }

    // ObjectOutputStream must be set first,
    // also, ObjectOutStream on the server side must be flushed
    // before client will set ObjectInputStream
    private void setupInOutStream() {
        System.out.println("A");
        setupDataOutStream();
        System.out.println("B");
        setupDataInStream();
        System.out.println("C");
    }

    private void setupDataInStream() {
        try {
            dataIn = new ObjectInputStream(clientSocket.getInputStream());
        } catch (Exception e) {
            System.out.println("Exc... ln 86");
            e.printStackTrace();
        }
    }

    private void setupDataOutStream() {
        try {
            dataOut = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void setDataOut() {
//        try {
//            dataOut = new ObjectOutputStream(clientSocket.getOutputStream());
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    private void setDataIn() {
//        try {
//            dataIn = new ObjectInputStream(clientSocket.getInputStream());
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }

    public void sendData(Object msg) {
        try {
            this.dataOut.writeObject(msg);
            this.dataOut.flush();
            this.dataOut.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object receiveData() {
        Object data = null;
        try {
            data = this.getDataIn().readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    //checks if number is prime
    private boolean isPrime(int n) {
        if(n<=1){
            return  false;
        }
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }

    // ------------------------------------------------------------------------
    // Inner classes
    // ------------------------------------------------------------------------


}
