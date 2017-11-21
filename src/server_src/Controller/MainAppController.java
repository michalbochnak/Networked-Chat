package Controller;

import sun.applet.Main;

public class MainAppController {

    private ServerController serverController;
    private ViewController viewController;


    public MainAppController() {
        serverController = new ServerController();
        viewController = new ViewController();
    }


}
