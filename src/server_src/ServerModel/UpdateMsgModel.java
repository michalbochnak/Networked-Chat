package ServerModel;

import java.io.Serializable;
import java.util.ArrayList;

public class UpdateMsgModel implements Serializable {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    ArrayList<ClientPublicProfile> clientsList;


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public ArrayList<ClientPublicProfile> getClientsList() {
        return clientsList;
    }


    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    public UpdateMsgModel(ArrayList<ClientPublicProfile> list) {
        clientsList = list;
    }


}


