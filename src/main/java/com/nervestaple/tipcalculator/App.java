package com.nervestaple.tipcalculator;

import com.nervestaple.tipcalculator.gui.MainFrame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Bootstraps our exciting tip calculator.
 */
public class App {

    public static void main( String[] args ) {
        new App();
    }

    public App() {

        final MainFrame mainFrame = new MainFrame("Tip Calculator");

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                System.exit(0);
            }
        });

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainFrame.setVisible(true);
            }
        });
    }
}
