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


