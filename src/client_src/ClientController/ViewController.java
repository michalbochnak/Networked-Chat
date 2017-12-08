//
// Michal Bochnak, Netid: mbochn2
// Artur Wojcik, Netid: awojci5
// Jakub Glebocki, Netid: jglebo2
//
// CS 342 Project #5 - Networked Chat Server
// Dec 7, 2017
// UIC, Pat Troy
//

//ViewController java class is a class in which it basically it lets the user view
//different things of the interface. It allowed the user to see the name, Show server
// Information, display the char window as well as hide all windows. When a new user
// connects it also allows to update the Client List. It has a few different menu items:
// About, Help, & Quit. When user clicks on help and about, it explains the program
// to the player as well as helps them on how to user the interface. Quit allows for
// the chats to be disconnected when user uses the quit button. It also Allows user
// to send a message in which the program is listening for.



package ClientController;

import ClientModel.Pair;
import ClientView.*;
import ServerModel.ClientPublicProfile;
import ServerModel.ConversationMsgModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ViewController {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private NameView nameView;
    private ServerInfoView serverInfoView;
    private ClientChatView clientChatView;
    private MainClientController mainClientController;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    //
    // Displays requested by parameter window
    // 1 - server ip and port prompt
    // 2 - name prompt
    // 3 - main chat window
    //
    public ViewController(MainClientController mcc) {
        mainClientController = mcc;
        setupNameView();
        serverInfoView = ServerInfoView.getInstance();
        clientChatView = ClientChatView.getInstance();
        addMenuBarListeners();

        switch (mainClientController.getChatStage()) {
            case 1:
                showServerInfoPrompt();
                break;
            case 2:
                showNamePrompt();
                break;
            case 3:
                showMainChatWindow();
                break;
        }
    }


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------


    public ClientChatView getClientChatView() {
        return clientChatView;
    }


    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    private void showNamePrompt() {
        hideAllWindows();
        nameView.show();
    }

    private void showServerInfoPrompt() {
        hideAllWindows();
        serverInfoView.addOkButtonActionListener
                (new ServerInfoViewOkButtonListener());
        serverInfoView.show();
    }

    private void showMainChatWindow() {
        hideAllWindows();
        clientChatView.getFrame().setVisible(true);
        clientChatView.getSendMsgPanel().addSendMsgButtonListener(new SendMsgButtonListener());
    }

    private void hideAllWindows() {
        nameView.hide();
        serverInfoView.hide();
        clientChatView.setVisible(false);
    }

    private void setupNameView() {
        nameView = NameView.getInstance();
        nameView.addOkButtonActionListener(new NameViewOkButtonListener());
    }

    public void updateClientsList(ArrayList<String > clients) {
        this.clientChatView.updateClientsList(clients);
    }

    private void addMenuBarListeners() {
        clientChatView.getMenuBar().addQuitListener(new QuitListener());
        clientChatView.getMenuBar().addHelpListener(new HelpListener());
        clientChatView.getMenuBar().addAboutListener(new AboutListener());
    }

    // ------------------------------------------------------------------------
    // Inner classes
    // ------------------------------------------------------------------------
    public class NameViewOkButtonListener implements ActionListener {

        private void showNameTooLongDialog() {
            JOptionPane.showMessageDialog(null,
                    "Name must be at least 1 letter and\n" +
                            "no more than 20 letters",
                    "Name format incorrect", JOptionPane.PLAIN_MESSAGE);
        }

        private void showNameTakenDialog() {
            JOptionPane.showMessageDialog(null,
                    "Name you entered is already taken by the other chat user.\n" +
                            "Please, use different nickname",
                    "Name format incorrect", JOptionPane.PLAIN_MESSAGE);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameView.getNameTextField().getText();
            if (nameLengthValid(name)) {
                showNameTooLongDialog();
            }
            else if (!nameAlreadyTaken(name)) {
                showNameTakenDialog();
            }
            else {
                mainClientController.setChatStage(3);
                mainClientController.getViewController().getClientChatView().setNameLabel
                        (mainClientController.getClientController().getClientModel().getClientName());
                showMainChatWindow();
                mainClientController.getClientController().startThreadForDataReceiving();
                ConversationMsgModel msg = new ConversationMsgModel();
                // mainClientController.getClientController().getClientModel().
                //          setClientName(name);
                //  mainClientController.getClientController().sendInitialInfo();
            }
        }

        private boolean nameLengthValid(String name) {
            return (name.length() <= 0) || (name.length() >= 20);
        }

        // Checks with the server if name choosen is not taken
        private boolean nameAlreadyTaken(String name) {
            return mainClientController.getClientController().usernameAvailable(name);

        }
    }

    public class ServerInfoViewOkButtonListener implements ActionListener {

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

        private boolean productMoreThan(int a, int b, int threshold) {
            return (a * b) > threshold;
        }

        private void showServerInfoErrorDialog() {
            JOptionPane.showMessageDialog(nameView.getEnterNameFrame(),
                    "Some of the provided information is incorrect.\n" +
                            "Please make sure server is running and make sure\n" +
                            "you typed server information correctly.\n" +
                            "Leave prime numbers fields empty or\n" +
                            "make sure typed in numbers are prime and their product\n" +
                            "is greater than " + (128*128*128*128) + ".",
                    "Server information incorrect", JOptionPane.PLAIN_MESSAGE);
        }

        private void showPrimesNotFoundDialog() {
            JOptionPane.showMessageDialog(nameView.getEnterNameFrame(),
                    "File do not contain enough prime numbers.",
                    "Primes read error", JOptionPane.PLAIN_MESSAGE);
        }



        private void tryToConnect(String ip, String portString) {
            int port = -1;
            try {
                port = Integer.parseInt(portString);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            // connection successful
            if (mainClientController.getClientController().connectToServer
                    (ip, port)) {
                mainClientController.setChatStage(2);
                showNamePrompt();

                String serverIp = mainClientController.getClientController().getClientModel()
                        .getClientSocket().getLocalAddress().getHostAddress().toString();
                int serverPort = mainClientController.getClientController().getClientModel()
                        .getClientSocket().getPort();

                mainClientController.getViewController().getClientChatView()
                        .setServerInfo(serverIp, serverPort);
            }
            // connection not succesful
            else {
                showServerInfoErrorDialog();
            }
        }

        private Pair pickPrimesFromFile() {
            ArrayList<Integer> primes = new ArrayList<Integer>();
            try {
                //Open file
                Scanner input = new Scanner(new File("primeNumbers.rsc"));

                //Process each line
                while (input.hasNextLine()) {
                    Scanner line = new Scanner(input.nextLine());
                    int num = line.nextInt();
                    if (isPrime(num)) {
                        primes.add(num);
                    }
                }
            }
            catch (FileNotFoundException exception) {
                System.err.println("File not found. Cannot load file specified by " +
                        "command line argument.");
            }

             if (primes.size() < 2) {
                return null;
             }

            Random rand = new Random();
            int n = rand.nextInt(primes.size());
            int p = primes.get(n);
            primes.remove(n);
            int q = primes.get(rand.nextInt(primes.size()));

            if (!productMoreThan(p, q, (128*128*128*128)))
                    return null;

            return new Pair(p, q);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String ip = serverInfoView.getIpTextField().getText();
            String portString = serverInfoView.getPortTextField().getText();
            String pNumber = serverInfoView.getpTextField().getText();
            String qNumber = serverInfoView.getqTextField().getText();

            // primes not provided by user
            if (pNumber.length() <= 0 && qNumber.length() <= 0) {
                // generate random from file
                // try to connect
                if (ip.length() > 0 && portString.length() > 0) {
                    Pair pqRandom = pickPrimesFromFile();
                    if (pqRandom != null) {
                        mainClientController.getClientController().getClientModel()
                                .setRSAInfo(pqRandom);
                        tryToConnect(ip, portString);
                    }
                    else {
                        showPrimesNotFoundDialog();
                    }
                }
                else {
                    showServerInfoErrorDialog();
                }
            }
            // primes provided by user
            else {
                try {
                    int p = Integer.parseInt(pNumber);
                    int q = Integer.parseInt(qNumber);
                    if ( (p != q) && isPrime(p) && isPrime(q) && productMoreThan(p, q, (128 * 128 * 128*128))) {
                        mainClientController.getClientController().getClientModel()
                                .setRSAInfo(new Pair(p, q));
                        tryToConnect(ip, portString);
                    }
                    else {
                        showServerInfoErrorDialog();
                    }
                } catch (Exception exc) {
                    exc.printStackTrace();
                    showServerInfoErrorDialog();
                }
            }

            try {
                for (ClientPublicProfile c : mainClientController.getClientController()
                        .getUsersConnected()) {
                    System.out.println(c.getPublicKey().getN());
                }
            } catch (Exception exc) {
                // ignore
            }

        }
    }

    public class SendMsgButtonListener implements ActionListener {

        private void showMsgToShortDialog() {
            JOptionPane.showMessageDialog(nameView.getEnterNameFrame(),
                    "Type your message in.",
                    "Message too short", JOptionPane.PLAIN_MESSAGE);
        }

        private void showSelectRecipientDialog() {
            JOptionPane.showMessageDialog(nameView.getEnterNameFrame(),
                    "Choose recipient from the users list.",
                    "Recipient not specified", JOptionPane.PLAIN_MESSAGE);
        }


        //encode message using RSA
        public ArrayList encrypt(String message, Pair publicKey) {

            int e = publicKey.getKey();
            int n = publicKey.getN();

            String msg = stringPadding(message);
            ArrayList<Integer> asciiValue = convertToAscii(msg);
            ArrayList<Integer> fourCharacters = calculateFourChars(asciiValue);
            ArrayList<Integer> encrypted = encodeMsg(fourCharacters, e, n);

            return encrypted;
        }

        //encodes array with converted ascii using RSA
        private ArrayList<Integer> encodeMsg(ArrayList<Integer> fourCharacters, int e, int n) {
            ArrayList<Integer> encrypted = new ArrayList<>();

            BigInteger N = new BigInteger(Integer.toString(n));
            BigInteger E = new BigInteger(Integer.toString(e));

            for (int i = 0; i < fourCharacters.size(); i++) {
                int num = fourCharacters.get(i);
                BigInteger MSG = new BigInteger(Integer.toString(num));
                BigInteger C1 = MSG.pow(E.intValue());
                BigInteger C = C1.mod(N);
                encrypted.add(C.intValue());
            }

            return encrypted;
        }


        //checks if string is evenly divided by 3 if not adds '\0' at the end
        private String stringPadding(String msg) {

            //make sure msg length is multiple of 3
            while (msg.length() % 4 != 0) {
                msg = msg + '\0';
            }

            return msg;
        }

        //convert four characters to single value using "ascii value" * 128^(pos)
        private ArrayList<Integer> calculateFourChars(ArrayList<Integer> asciiValue) {
            int value = 0;
            ArrayList<Integer> converted = new ArrayList<>();
            for (int i = 0; i < asciiValue.size(); i++) {
                value += asciiValue.get(i) * Math.pow(128, power(i));
                //4 for 4
                if ((i + 1) % 4 == 0) {
                    converted.add(value);
                    value = 0;
                }
            }

            return converted;
        }

        //convert string to the ascii value
        private ArrayList<Integer> convertToAscii(String msg) {
            ArrayList<Integer> ascii = new ArrayList<>();
            int character;
            for (int i = 0; i < msg.length(); i++) {
                character = msg.charAt(i);
                ascii.add(character);
            }
            return ascii;
        }

        //calculate power of index
        private int power(int num) {
            int power = num % 4;
            return power;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            String msgToSend = clientChatView.getTypedMessage();
            String msgRecipient = clientChatView.getClientsList().retrieveSelected();
            String msgSender = mainClientController.getClientController().getClientModel()
                    .getClientName();

            if (msgToSend.length() <= 0) {
                showMsgToShortDialog();
            }
            else if (msgRecipient.equals("none")) {
                showSelectRecipientDialog();
            }
            else {
                clientChatView.getMsgsPanel().addMessage
                        ("Me (to " + msgRecipient + ")", msgToSend);
                // encrypt the msg
                ArrayList<Integer> encrMsg = encrypt(msgToSend,
                        mainClientController.getClientController()
                                .findPublicKeyByName(msgRecipient));

                ConversationMsgModel msg = new ConversationMsgModel
                        (msgSender, msgRecipient, encrMsg);
                mainClientController.getClientController().sendMessage(msg);
            }
            // check if is longer than 0
            // send
        }
    }

    public class QuitListener implements  ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.exit(1);
        }

    }

    public class HelpListener implements  ActionListener {

        public void actionPerformed(ActionEvent e) {

            JOptionPane.showMessageDialog(null,
                    "GUI based programs in Java Swing that allows multiple\n" +
                            "people to connect together and send encrypted messages\n" +
                            "to a specific connected person. The Java Socket and \n" +
                            "ServerSocket classes are used for the networking implementation.\n" +
                            "The RSA encryption/decryption code is designed and written\n" +
                            "by the authors of this program based on the resources provided \n" +
                            "by the Professor Troy in the given assignment. To Send a message\n" +
                            "select a person from the list and type a message server will\n" +
                            "transfer encrypted message to the selected person. Message will\n" +
                            "be decrypted on receiver side.\n",
                    "Help",
                    JOptionPane.PLAIN_MESSAGE);
        }

    }

    public class AboutListener implements  ActionListener {


        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,
                    "Project 5 UIC Fall 2017\n" +
                            "Networked Chat with RSA Encryption/Decryption\n\n" +
                            "Program Authors: \n" +
                            "Artur Wojcik        - awojci5\n" +
                            "Michal Bochnak  - mbochn2\n" +
                            "Jakub Glebocki   - jglebo2\n\n",
                    "About",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

}


