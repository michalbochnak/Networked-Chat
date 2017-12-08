//
// Michal Bochnak, Netid: mbochn2
// Artur Wojcik, Netid: awojci5
// Jakub Glebocki, Netid: jglebo2
//
// CS 342 Project #5 - Networked Chat Server
// Dec 7, 2017
// UIC, Pat Troy
//


//The UpdateMSgModel java class is a class in which it models the updates for a
// message. Here we have a member of clientList,which we can Get the clientList
// and Update the Client list as needed.


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


