package com.nervestaple.tipcalculator;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

/**
 * Bootstraps our exciting tip calculator.
 */
public class App {

    /**
     * Logger instance.
     */
    private static final Logger log = Logger.getLogger(TipModel.class.getName());

    /**
     * Bootstraps the application.
     *
     * @param args Arguments from the command line
     */
    public static void main( String[] args ) {
        new App();
    }

    /**
     * Creates a new application instance.
     */
    public App() {

        // take a stab at setting the look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
            // fall back to the icky metal theme
            log.info("Couldn't set look and feel to " + UIManager.getSystemLookAndFeelClassName());
        }

        // create our main GUI frame
        final MainFrame mainFrame = new MainFrame("Tips!");

        // quit application when the window closes
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                System.exit(0);
            }
        });

        // make our frame visible
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainFrame.setVisible(true);
            }
        });
    }
}
