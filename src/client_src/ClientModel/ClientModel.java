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
    private Pair publicKey, privateKey, pq;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ClientModel() {
        clientName = "none";
        clientSocket = null;
        dataOut = null;
        dataIn = null;
        publicKey = null;
        privateKey = null;
        pq = null;
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
    public void setPublicKey(Pair publicKey) {
        this.publicKey = publicKey;
    }

    public void setPq(Pair pq) {
        this.pq = pq;
    }

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

    //
    // ObjectOutputStream must be set first,
    // also, ObjectOutStream on the server side must be flushed
    // before client will set ObjectInputStream
    //
    private void setupInOutStream() {
        setupDataOutStream();
        setupDataInStream();
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


}


