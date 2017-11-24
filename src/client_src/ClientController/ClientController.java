package ClientController;

import ClientModel.*;
import ServerModel.InitialClientInfoMsgModel;

public class ClientController {

    private ClientModel clientModel;
    private MainClientController mainClientController;

    public ClientController(MainClientController mcc) {
        mainClientController = mcc;
        clientModel = new ClientModel();
    }


    public boolean connectToServer(String ip, int port) {
        return clientModel.connectToServer(ip, port);
    }

    public ClientModel getClientModel() {
        return clientModel;
    }

    public void sendInitialInfo() {
        InitialClientInfoMsgModel msg = new InitialClientInfoMsgModel
                (clientModel.getClientName(),
                        clientModel.getClientSocket().getLocalAddress().toString(),
                        clientModel.getClientSocket().getLocalPort());

        clientModel.sendData(msg);
    }

}
