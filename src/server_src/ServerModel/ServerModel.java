package ServerModel;

import ClientView.ClientChatView;

import java.awt.image.AreaAveragingScaleFilter;
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
    private ArrayList<ClientSocketModel> clientsSockets;
    private ConversationMsgModel receivedData, dataToSend;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ServerModel() {
        startTheServer();
        clientsSockets = new ArrayList<ClientSocketModel>();
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

    public ArrayList<String> getClientsList() {
        ArrayList<String> clients = new ArrayList<String>();
        for (ClientSocketModel csm : clientsSockets) {
            clients.add(csm.getNickname());
        }
        return clients;
    }


    // ------------------------------------------------------------------------
    // Setters
    // ------------------------------------------------------------------------



    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    private void startTheServer() {
        try {
            this.server = new ServerSocket(50000);
        } catch (IOException e) {
            System.out.println("Cannot create new Server Socket");
            e.printStackTrace();
        }
    }

    public void startClientListenerThread(Thread waitForClientThread) {
        waitForClientThread.start();
    }

    public ConversationMsgModel receiveData () {
        ConversationMsgModel msg = null;
        try {
            msg = (ConversationMsgModel)dataIn.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return msg;
    }

    private void processReceivedData(ConversationMsgModel msg) {
        // client disconnecting
        if (msg.isDisconnecting()) {
            // close client socketx
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
        System.out.println("in acceptClient");
        try {
            newClient = server.accept();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newClient;
    }

    public void updateClientName(String newNickname, String ip, int port) {
        ClientSocketModel toUpdate = findClientByIpAndPort(ip, port);
        try {
            toUpdate.setNickname(newNickname);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ClientSocketModel findClientByIpAndPort(String ip, int port) {
        int length = clientsSockets.size();
        for (int i = 0; i < length; ++i) {
            Socket temp = clientsSockets.get(i);
            System.out.println("here");
            System.out.println(temp);
            System.out.println("findClientBy...: port:  " + temp.getPort()
                    + ", ip: " + temp.getRemoteSocketAddress());
            if (temp.getPort() == port && temp.getInetAddress().toString().equals(ip)) {
                return clientsSockets.get(i);
            }
        }
        return null;
    }

    //
    // Removes the client from clientsSockets and return the nickname
    // of the client that was removed
    //
    public String removeClient(Socket socket) {
        int length = clientsSockets.size();
        for (int i = 0; i < length; ++i) {
            if (clientsSockets.get(i).getClientSocket() == socket) {
                String clientName = clientsSockets.get(i).getNickname();
                clientsSockets.remove(i);
                return clientName;
            }
        }
        return null;    // not found
    }

    public void addClient(ClientSocketModel newClientSocketModel) {
        clientsSockets.add(newClientSocketModel);
    }


    // ------------------------------------------------------------------------
    // Inner classes
    // ------------------------------------------------------------------------


}






