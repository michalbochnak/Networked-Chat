package ServerController;


public class MainServerController {

    private ServerController serverController;
    private ViewController viewController;


    // constructor
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
