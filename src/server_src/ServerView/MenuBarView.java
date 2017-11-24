package ServerView;

import javax.swing.*;

public class MenuBarView extends  JMenuBar {

    private JMenu FileMenu, HelpMenu;

    public MenuBarView() {
        setupFileMenu();
        setupHelpMenu();
    }

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
