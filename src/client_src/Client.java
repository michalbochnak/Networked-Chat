



import Model.ClientModel;
import Model.MessageModel;


public class Client {

    public static void main(String args[]) {

        //
        // Connection test, IP and port must be hardcoded as server specifies them
        //
        ClientModel client = new ClientModel("10.9.223.169", 51403);
        MessageModel msg = new MessageModel();
        msg.setSender("Michal");
        client.sendData(msg);
    }

}




//
// For copying and pasting when creating new class
//

// ------------------------------------------------------------------------
// Members
// ------------------------------------------------------------------------



// ------------------------------------------------------------------------
// Constructors
// ------------------------------------------------------------------------



// ------------------------------------------------------------------------
// Getters
// ------------------------------------------------------------------------



// ------------------------------------------------------------------------
// Setters
// ------------------------------------------------------------------------



// ------------------------------------------------------------------------
// Methods
// ------------------------------------------------------------------------



// ------------------------------------------------------------------------
// Inner classes
// ------------------------------------------------------------------------