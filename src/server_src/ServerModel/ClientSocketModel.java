package ServerModel;

import java.net.Socket;

public class ClientSocketModel extends Socket{

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private String nickname;
    private Socket clientSocket;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ClientSocketModel (Socket newClientSocket) {
        this.nickname = newClientSocket.getRemoteSocketAddress().toString();
        this.clientSocket = newClientSocket;
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
