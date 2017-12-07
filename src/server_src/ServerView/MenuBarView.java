package ServerView;

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
    // Getters
    // ------------------------------------------------------------------------
    public static MenuBarView getInstance() {
        return instance;
    }

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------
    private void setupFileMenu() {
        FileMenu = new JMenu("File");
        FileMenu.add(new JMenuItem("Exit"));
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


