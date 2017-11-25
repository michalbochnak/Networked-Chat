package ClientController;

import ClientModel.*;
import ServerModel.InitialClientInfoMsgModel;
import ServerModel.UpdateMsgModel;


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

    public void startThreadForDataReceiving() {
        new Thread(new waitForServerData()).start();
    }


    // ------------------------------------------------------------------------
    // Inner classes
    // ------------------------------------------------------------------------

    public class waitForServerData implements Runnable {

        private void processReceivedData(Object data) {
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
            mainClientController.getViewController().updateClientsList(msg.getClientsList());
        }

        private void processConversationMsg(Object data) {

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
                    break;
                }
            }

        }
    }

}



