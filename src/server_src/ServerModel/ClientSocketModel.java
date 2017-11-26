package ServerModel;

import ClientModel.Pair;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketModel extends Socket{

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private Socket clientSocket;
    private String nickname;
    private Pair publicKey;
    private ObjectOutputStream dataOut;
    private  ObjectInputStream dataIn;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ClientSocketModel (Socket newClientSocket) {
        this.clientSocket = newClientSocket;
        this.nickname = newClientSocket.getRemoteSocketAddress().toString();
        try {
            this.dataOut = new ObjectOutputStream(newClientSocket.getOutputStream());
            this.dataIn = new ObjectInputStream(newClientSocket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        publicKey = null;
    }


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public String getNickname() {
        return nickname;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public ObjectOutputStream getDataOut() {
        return dataOut;
    }

    public ObjectInputStream getDataIn() {
        return dataIn;
    }

    public Pair getPublicKey() {
        return publicKey;
    }


    // ------------------------------------------------------------------------
    // Setters
    // ------------------------------------------------------------------------
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPublicKey(Pair publicKey) {
        this.publicKey = publicKey;
    }


}


