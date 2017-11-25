package ServerModel;

import java.io.Serializable;
import java.util.ArrayList;

public class UpdateMsgModel implements Serializable {

    ArrayList<ClientPublicProfile> clientsList;

    public UpdateMsgModel(ArrayList<ClientPublicProfile> list) {
        clientsList = list;
    }

    public ArrayList<ClientPublicProfile> getClientsList() {
        return clientsList;
    }
}
