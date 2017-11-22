package View;

import javax.swing.*;

public class MenuBarV extends JMenuBar{

    private JMenu FileMenu, HelpMenu;

    public MenuBarV() {
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
