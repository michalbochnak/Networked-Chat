package ClientController;


public class MainClientController {

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
