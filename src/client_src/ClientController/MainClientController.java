//
// Michal Bochnak, Netid: mbochn2
// Artur Wojcik, Netid: awojci5
// Jakub Glebocki, Netid: jglebo2
//
// CS 342 Project #5 - Networked Chat Server
// Dec 7, 2017
// UIC, Pat Troy
//



//The java class MainClientControllerView is a class which mainly calls and
// sets up the Client Controller. It has the ability to Get the chat Stage,
// Set the chat Stage, and Gets client controller as well as the view controller.




package ClientController;


public class MainClientController {

    //private static final MainClientController instance = new MainClientController();

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private ClientController clientController;
    private ViewController viewController;
    private int chatStage;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public MainClientController() {
        chatStage = 1;
        this.clientController = new ClientController(this);
        this.viewController = new ViewController(this);
    }


    // ------------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------------

    public int getChatStage() {
        return this.chatStage;
    }

    public void setChatStage(int chatStage) {
        this.chatStage = chatStage;
    }

    public ClientController getClientController() {
        return  clientController;
    }

    public ViewController getViewController() {
        return viewController;
    }
}
