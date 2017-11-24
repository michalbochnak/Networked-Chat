package ClientController;


public class MainClientController {

    private ClientController clientController;
    private ViewController viewController;
    private int chatStage;


    public MainClientController() {
        chatStage = 1;
        this.clientController = new ClientController(this);
        this.viewController = new ViewController(this);
    }

    public int getChatStage() {
        return this.chatStage;
    }

    public void setChatStage(int chatStage) {
        this.chatStage = chatStage;
    }


    public ClientController getClientController() {
        return  clientController;
    }
}
