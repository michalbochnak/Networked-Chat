package ServerModel;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketModel extends Socket{

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private Socket clientSocket;
    private String nickname;
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

    // ------------------------------------------------------------------------
    // Setters
    // ------------------------------------------------------------------------
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------



    // ------------------------------------------------------------------------
    // Inner classes
    // ------------------------------------------------------------------------


}
