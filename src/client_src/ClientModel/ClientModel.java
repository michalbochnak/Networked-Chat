//
// Michal Bochnak, Netid: mbochn2
// Artur Wojcik, Netid: awojci5
// Jakub Glebocki, Netid: jglebo2
//
// CS 342 Project #5 - Networked Chat Server
// Dec 7, 2017
// UIC, Pat Troy
//

//The Java Class ClientModelModels the Client Program. It uses Getter functions
// to get information such as Name, socket, Data,Public Key, as well as private
// Key. It sets ClientName, RSA information and client Name. It set's up different
// streams such as receiving and sending Data. It also does different calculations
// based on which were derived form the geeks website.

package ClientModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;


public class ClientModel {

    private static final ClientModel instance = new ClientModel();

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private String clientName;
    private Socket clientSocket;
    private ObjectInputStream dataIn;
    private ObjectOutputStream dataOut;
    private Pair publicKey;          // (e, n)
    private Pair privateKey;        // (d, n)
    private Pair pq;                        // (p, q)


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    private ClientModel() {
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
    public static ClientModel getInstance() {
        return instance;
    }

    public String getClientName() {
        return clientName;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public ObjectInputStream getDataIn() {
        return dataIn;
    }

    public Pair getPublicKey() {
        return publicKey;
    }

    public Pair getPrivateKey() {
        return privateKey;
    }

    // ------------------------------------------------------------------------
    // Setters
    // ------------------------------------------------------------------------
    public void setPublicKey(Pair publicKey) {
        this.publicKey = publicKey;
    }

    public void setPrivateKey(Pair privateKey) {
        this.privateKey = privateKey;
    }

    public void setPq(Pair pq) {
        this.pq = pq;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setRSAInfo(Pair pqPair) {
        int p = pqPair.getKey();
        int q = pqPair.getN();
        //System.out.println("Setting RSA: " + p + ", " + q);
        setPq(new Pair (p, q));
        setPublicKey(new Pair(calculateE(p, q), calculateN(p, q)));
        //System.out.println("Pu: " +publicKey.getKey() + " " + publicKey.getN() );
        setPrivateKey(new Pair(calculateD(calculateE(p, q), p, q), calculateN(p, q)));
        //System.out.println("Pr: " +privateKey.getKey() + " " + privateKey.getN() );
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

    private int calculateD(int e, int p, int q) {

        int phi = (p-1)*(q-1);
        BigInteger E = new BigInteger(Integer.toString(e));
        BigInteger PHI = new BigInteger(Integer.toString(phi));
        BigInteger D = E.modInverse(PHI);

        return D.intValue();
    }

    //based on geekforgeeks website
    private int calculateE(int p, int q) {
        int e=2;
        int phi = (p-1)*(q-1);
        while (e < phi)
        {
            // e must be co-prime to phi and
            // smaller than phi.
            if (gcd(e, phi)==1)
                break;
            else
                e++;
        }
        return e;
    }

    // Returns gcd of a and b
    //based on geekforgeeks website
    private int gcd(int a, int h)
    {
        int temp;
        while (true)
        {
            temp = a%h;
            if (temp == 0)
                return h;
            a = h;
            h = temp;
        }
    }

    private int calculateN(int p, int q) {
        return p*q;
    }


}


