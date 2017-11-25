package ServerModel;

import java.io.Serializable;
import java.util.ArrayList;

public class UpdateMsgModel implements Serializable {

    ArrayList<String> clientsList;


    public UpdateMsgModel(ArrayList<String> list) {
        clientsList = list;
    }

    public ArrayList<String> getClientsList() {
        return clientsList;
    }
}
