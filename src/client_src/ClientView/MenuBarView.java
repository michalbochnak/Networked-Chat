package ClientView;

import javax.swing.*;


public class MenuBarView extends  JMenuBar {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------
    private JMenu FileMenu, HelpMenu;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    public MenuBarView() {
        setupFileMenu();
        setupHelpMenu();
    }

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    private void setupFileMenu() {
        FileMenu = new JMenu("File");
        FileMenu.add(new JMenuItem("Test"));
        this.add(FileMenu);
    }

    private void setupHelpMenu() {
        HelpMenu = new JMenu("Help");
        HelpMenu.add(new JMenuItem("Help"));
        HelpMenu.add(new JMenuItem("About"));
        this.add(HelpMenu);
    }



}


