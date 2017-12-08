//
// Michal Bochnak, Netid: mbochn2
// Artur Wojcik, Netid: awojci5
// Jakub Glebocki, Netid: jglebo2
//
// CS 342 Project #5 - Networked Chat Server
// Dec 7, 2017
// UIC, Pat Troy
//


//The java class ClientView is a class in which it shows the client. This si where
//classes are called in order for them to setup and put the pieces(frames) together
// and link them. In this File it sets up the Menu in which are "Quit, Help, About"
//buttons the user can click and use,



package ClientView;

import ClientController.ViewController;

import javax.swing.*;
import java.awt.event.ActionListener;


public class MenuBarView extends  JMenuBar {

    private static final MenuBarView instance = new MenuBarView();

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private JMenu FileMenu, HelpMenu;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    private MenuBarView() {
        setupFileMenu();
        setupHelpMenu();
    }

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    public static MenuBarView getInstance() {
        return instance;
    }

    private void setupFileMenu() {
        FileMenu = new JMenu("File");
        FileMenu.add(new JMenuItem("Quit"));
        this.add(FileMenu);
    }

    private void setupHelpMenu() {
        HelpMenu = new JMenu("Help");
        HelpMenu.add(new JMenuItem("Help"));
        HelpMenu.add(new JMenuItem("About"));
        this.add(HelpMenu);
    }

    public void addQuitListener(ActionListener listener) {
        FileMenu.getItem(0).addActionListener(listener);
    }

    public void addHelpListener(ActionListener listener) {
        HelpMenu.getItem(0).addActionListener(listener);
    }

    public void addAboutListener(ActionListener listener) {
        HelpMenu.getItem(1).addActionListener(listener);
    }



}


