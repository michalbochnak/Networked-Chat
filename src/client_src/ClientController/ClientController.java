//
// Michal Bochnak, Netid: mbochn2
// Artur Wojcik, Netid: awojci5
// Jakub Glebocki, Netid: jglebo2
//
// CS 342 Project #5 - Networked Chat Server
// Dec 7, 2017
// UIC, Pat Troy
//
// ClientController.java
//


//The class ClientController class controls certain aspects of the client. There
// are different functions here to get things such as The clientmodel, all users
// connected, connect to the server, start threading to be able to receive data,
// show the dialog for user type in information, check if user is available, send
// messages. It also does things such as waiting for the server to send in data,
// updating the message, removing client username, and a bit of decryption such as
// on private Key and D & N as specified in the write up. Lastly there is a "Run"
// function which Allows for the object being sen tin to be read and processed.

package ClientController;

import ClientModel.*;
import ServerModel.ClientPublicProfile;
import ServerModel.ConversationMsgModel;
import ServerModel.InitialClientInfoMsgModel;
import ServerModel.UpdateMsgModel;
import javax.swing.*;
import java.math.BigInteger;
import java.util.ArrayList;


public class ClientController {


    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private ClientModel clientModel;
    private MainClientController mainClientController;
    private ArrayList<ClientPublicProfile> usersConnected;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public ClientController(MainClientController mcc) {
        mainClientController = mcc;
        clientModel = ClientModel.getInstance();
        usersConnected = null;
    }


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------

    public ClientModel getClientModel() {
        return clientModel;
    }

    public ArrayList<ClientPublicProfile> getUsersConnected() {
        return usersConnected;
    }

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    public boolean connectToServer(String ip, int port) {
        return clientModel.connectToServer(ip, port);
    }

    public void startThreadForDataReceiving() {
        new Thread(new waitForServerData()).start();
    }

    private void showServerDisconnectedDialog() {
        JOptionPane.showMessageDialog(null,
                "Server disconnected.");
    }

    //
    // Function send message with name to the server and waits for the server response,
    // if name is available it is added to the client list on server side and set as a name
    // on the client side and true is returned, false is returned otherwise
    //
    public boolean usernameAvailable(String name) {

        String ip = clientModel.getClientSocket().getLocalAddress().toString();
        int port = clientModel.getClientSocket().getLocalPort();

        InitialClientInfoMsgModel msg = new InitialClientInfoMsgModel
                (name, ip, port, false, clientModel.getPublicKey());

        // send data with selected username
        clientModel.sendData(msg);

        // wait for response
        Object data = clientModel.receiveData();

        if (data.getClass().getSimpleName().equals("InitialClientInfoMsgModel")) {
            InitialClientInfoMsgModel response = (InitialClientInfoMsgModel)data;
            if (response.isNameAvailable()) {
                // name available
                clientModel.setClientName(name);
                return true;
            }
        }
        // name not available
        return false;
    }

    public void sendMessage(Object msg) {
        clientModel.sendData(msg);
    }

    public Pair findPublicKeyByName(String name) {
        Pair publicKey = null;

        for (int i = 0; i < usersConnected.size(); ++i) {
            System.out.println("name: " + name + "currName: "
                    + usersConnected.get(i).getNickname());
            System.out.println("currKey: " + usersConnected.get(i).getPublicKey());
            if (usersConnected.get(i).getNickname() == name) {
                publicKey = usersConnected.get(i).getPublicKey();
                break;
            }
        }

        //System.out.println("Returning " + publicKey.getKey() + " " + publicKey.getN());

        return publicKey;
    }


    // ------------------------------------------------------------------------
    // Inner classes
    // ------------------------------------------------------------------------
    public class waitForServerData implements Runnable {

        private synchronized void processReceivedData(Object data) {
            String className = data.getClass().getSimpleName().toString();

            System.out.println("Class name: " + className);
            switch (className) {
                case "UpdateMsgModel":
                    processUpdateMsg(data);
                    break;
                case "ConversationMsgModel":
                    processConversationMsg(data);
                    break;
            }
        }

        private void processUpdateMsg(Object data) {
            UpdateMsgModel msg = (UpdateMsgModel)data;
            usersConnected = msg.getClientsList();
            removeClientUsername(usersConnected);
            mainClientController.getViewController().updateClientsList(buildNamesArray(usersConnected));
        }

        private void removeClientUsername(ArrayList<ClientPublicProfile> usersConnected) {
            int toRemove = -1;
            for (int i = 0; i < usersConnected.size(); ++i) {
                if (usersConnected.get(i).getNickname().equals(clientModel.getClientName())) {
                    toRemove = i;
                    break;
                }
            }
            // remove
            try {
                usersConnected.remove(toRemove);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

        private ArrayList<String> buildNamesArray (ArrayList<ClientPublicProfile> users) {
            ArrayList<String> list = new ArrayList<String>();
            for (ClientPublicProfile c : users) {
                list.add(c.getNickname());
            }
            return list;
        }

        public String decrypt(ArrayList<Integer> array, Pair privateKey) {

            int d = privateKey.getKey();
            int n = privateKey.getN();

            ArrayList<Integer> fourChars = decryptFourChars(array, d, n);
            String msg = unconvertedFourChars(fourChars);

            return msg;
        }

        //changes array of ascii values into string
        private String unconvertedFourChars(ArrayList<Integer> fourChars) {

            String message = "";

            for (int i = 0; i < fourChars.size(); i++) {
                double number = fourChars.get(i);
                double first = number % 128;
                message += (char) first;
                double second = ((number - first) % Math.pow(128, 2)) / 128;
                message += (char) second;
                double third = ((number - second) % Math.pow(128, 3)) / Math.pow(128, 2);
                message += (char) third;
                double fourth = ((number - third) % Math.pow(128, 4)) / Math.pow(128, 3);
                message += (char) fourth;

            }
            return message;
        }

        //decrypts encrypted message using rsa return array with sum of ascii values
        private ArrayList<Integer> decryptFourChars(ArrayList<Integer> array, int d, int n) {

            ArrayList<Integer> decrypted = new ArrayList<>();
            BigInteger N = new BigInteger(Integer.toString(n));

            for (int i = 0; i < array.size(); i++) {

                //
                BigInteger C = new BigInteger(array.get(i).toString());
                BigInteger Exponent = new BigInteger(Integer.toString(d));
                BigInteger M = C.modPow(Exponent,N);
                decrypted.add(M.intValue());
                //

            }

            return decrypted;
        }

        private void processConversationMsg(Object data) {
            ConversationMsgModel msg = (ConversationMsgModel)data;

            System.out.println("Received: ");
            // print enc msg
            for (Integer i : msg.getEncryptedMsg())
                System.out.println("e: " + i);

            String decrMsg = decrypt(msg.getEncryptedMsg(), clientModel.getPrivateKey());
            mainClientController.getViewController().getClientChatView().getMsgsPanel()
                    .addMessage(msg.getSender(),decrMsg);
        }

        @Override
        public void run() {
            Object data;
            while (true) {
                try {
                    data = clientModel.getDataIn().readObject();
                    processReceivedData(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    showServerDisconnectedDialog();
                    System.exit(0);
                    break;
                }
            }
        }
    }



}



