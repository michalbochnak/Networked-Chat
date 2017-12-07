package ServerModel;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServerModel {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private ServerSocket server;
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
    }


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public String getIpAddress() {
        String tempIp = "none";
        try {
            tempIp = InetAddress.getLocalHost().getHostAddress();
            System.out.println(tempIp);
        } catch (Exception e) {
            System.out.println("Cannot retrieve IP Address");
            e.printStackTrace();
        }
        return tempIp;
    }

    public int getServerPort() {
        return server.getLocalPort();
    }

    public ArrayList<ClientPublicProfile> getClientsList() {
        ArrayList<ClientPublicProfile> clients = new ArrayList<ClientPublicProfile>();
        for (ClientSocketModel csm : clientsSockets) {
            clients.add(new ClientPublicProfile(csm.getNickname(), csm.getPublicKey()));
        }
        return clients;
    }

    public ArrayList<ClientSocketModel> getClientsSocketArray() {
        return clientsSockets;
    }

    public ArrayList<String> getClientsNamesList() {
        ArrayList<String> clients = new ArrayList<String>();
        for (ClientSocketModel csm : clientsSockets) {
            clients.add(csm.getNickname());
        }
        return clients;
    }


    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    private void startTheServer() {
        try {
            this.server = new ServerSocket(5000);
        } catch (IOException e) {
            System.out.println("Cannot create new Server Socket");
            e.printStackTrace();
        }
    }

    public void startClientListenerThread(Thread waitForClientThread) {
        waitForClientThread.start();
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

    public void updateClientInfo(InitialClientInfoMsgModel msg) {
        ClientSocketModel toUpdate = findClientByIpAndPort(msg.getIp(), msg.getPort());
        System.out.println("Client: " + msg.getIp() + msg.getPort());
        try {
            toUpdate.setNickname(msg.getNickname());
            toUpdate.setPublicKey(msg.getPublicKey());
            System.out.println("publicK: " + msg.getPublicKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ClientSocketModel findClientByIpAndPort(String ip, int port) {
        int length = clientsSockets.size();
        for (int i = 0; i < length; ++i) {
            Socket temp = clientsSockets.get(i).getClientSocket();

            System.out.println("params:  " + port
                    + ", ip: " + ip);
            System.out.println("socket :  " + temp.getPort()
                    + ", ip: " + temp.getRemoteSocketAddress().toString());

            String[] parts = temp.getRemoteSocketAddress().toString()
                    .split("\\:"); // String array, each element is text between dots

            String remoteIp = parts[0];


            if (temp.getPort() == port && remoteIp.equals(ip)) {
                System.out.println("Found");
                return clientsSockets.get(i);
            }
        }
        return null;
    }

    public boolean clientExist(String name) {
        for (ClientSocketModel csm : clientsSockets) {
            if (csm.getNickname().equals(name))
                return true;    // found
        }
        return false;           // not found
    }

    public ClientSocketModel findRecipientSocket(String name) {
        for (ClientSocketModel csm : clientsSockets) {
            if (csm.getNickname().equals(name)) {
                return csm;
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


}


