//
// Michal Bochnak, Netid: mbochn2
// Artur Wojcik, Netid: awojci5
// Jakub Glebocki, Netid: jglebo2
//
// CS 342 Project #5 - Networked Chat Server
// Dec 7, 2017
// UIC, Pat Troy
//


//MainServerController is the main java class which controls what happens with the
//server. It has a getter function for both the view and the Model in which it
// returns those packages so they can be used. It gets user info and sets up the
// IP & Port label


package ServerController;


public class MainServerController {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private ServerController serverController;
    private ViewController viewController;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public MainServerController() {
        serverController = new ServerController(this);
        viewController = new ViewController(this);

        // set IP and port labels
        viewController.setServerInfo(serverController.getServerModel().getIpAddress(),
                serverController.getServerModel().getServerPort());
    }

    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------
    public ServerController getServerController() {
        return serverController;
    }

    public ViewController getViewController() {
        return viewController;
    }


}


