package com.nervestaple.tipcalculator.gui;

import com.nervestaple.tipcalculator.TipModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.logging.Logger;

/**
 * Provides the primary frame for the application.
 */
public class MainFrame extends JFrame {

    /**
     * Logger instance.
     */
    private static final Logger log = Logger.getLogger(TipModel.class.getName());

    // gui components
    private JLabel labelBill = new JLabel("Bill");
    private JLabel labelTipPercent = new JLabel("Tip %");
    private JLabel labelPeople = new JLabel("Number of People");
    private JLabel labelTip = new JLabel("Tip/Person");
    private JLabel labelTotal = new JLabel("Total Bill");
    private JScrollPane scrollPaneError = new JScrollPane();
    private JTextArea textAreaError = new JTextArea(3, 100);
    private JFormattedTextField textFieldBill;
    private JFormattedTextField textFieldTipPercent = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField textFieldPeople = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField textFieldTip = new JFormattedTextField(NumberFormat.getCurrencyInstance());
    private JFormattedTextField textFieldTotal = new JFormattedTextField(NumberFormat.getCurrencyInstance());
    private JButton buttonCalculate = new JButton("Calculate Tip");

    // models
    private TipModel tipModel = new TipModel();

    public MainFrame(String title) {
        super();

        setTitle(title);

        NumberFormat formatMoneyInput = NumberFormat.getInstance();
        formatMoneyInput.setMaximumFractionDigits(2);
        textFieldBill = new JFormattedTextField(formatMoneyInput);

        textAreaError.setWrapStyleWord(true);

        scrollPaneError.add(textAreaError);
        scrollPaneError.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneError.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        //scrollPaneError.setBorder(new EmptyBorder(0, 0, 0, 0));

        buttonCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                if (textFieldBill.getValue() != null) {
                    tipModel.setTotalBill(new BigDecimal(textFieldBill.getValue().toString()));
                } else {
                    tipModel.setTotalBill(null);
                }

                if (textFieldTipPercent.getValue() != null) {
                    tipModel.setTipPercentage(new BigDecimal(textFieldTipPercent.getValue().toString())
                            .multiply(new BigDecimal(0.01)));
                } else {
                    tipModel.setTipPercentage(null);
                }

                if (textFieldPeople.getValue() != null) {
                    tipModel.setPeople(Integer.valueOf(textFieldPeople.getValue().toString()));
                } else {
                    tipModel.setPeople(null);
                }

                tipModel.calculateTip();
                textFieldTip.setValue(tipModel.getTipPerPerson());
                textFieldTotal.setValue(tipModel.getTotalBillWithTip());
                textAreaError.setText(tipModel.getErrorMessage());
                pack();
                log.info("TipModel: " + tipModel);
            }
        });

        Box boxLeft = Box.createVerticalBox();
        boxLeft.add(labelBill);
        boxLeft.add(textFieldBill);
        boxLeft.add(labelTipPercent);
        boxLeft.add(textFieldTipPercent);
        boxLeft.add(labelPeople);
        boxLeft.add(textFieldPeople);

        Box boxRight = Box.createVerticalBox();
        boxRight.add(labelTip);
        boxRight.add(textFieldTip);
        boxRight.add(labelTotal);
        boxRight.add(textFieldTotal);

        Box boxBottom = Box.createHorizontalBox();
        boxBottom.add(Box.createHorizontalGlue());
        boxBottom.add(buttonCalculate);
        boxBottom.add(Box.createHorizontalStrut(10));

        Box boxTop = Box.createHorizontalBox();
        boxTop.add(Box.createHorizontalStrut(10));
        boxTop.add(boxLeft);
        boxTop.add(Box.createHorizontalStrut(10));
        boxTop.add(boxRight);
        boxTop.add(Box.createHorizontalStrut(10));

        Box boxError = Box.createHorizontalBox();
        boxError.add(Box.createHorizontalStrut(10));
        boxError.add(scrollPaneError);
        boxError.add(Box.createHorizontalStrut(10));

        Box boxMain = Box.createVerticalBox();
        boxMain.add(Box.createVerticalStrut(10));
        boxMain.add(boxError);
        boxMain.add(Box.createVerticalStrut(10));
        boxMain.add(boxTop);
        boxMain.add(Box.createVerticalStrut(10));
        boxMain.add(boxBottom);
        boxMain.add(Box.createVerticalStrut(10));

        getContentPane().add(boxMain);
        pack();
    }
}
