package Model;

import jdk.jfr.events.SocketReadEvent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkDataModel {

    private ServerSocket serverSocket;
    private String serverIP;
    private int port;
    private ObjectInputStream inData;
    private ObjectOutputStream outData;





}
