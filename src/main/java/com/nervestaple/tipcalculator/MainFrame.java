package com.nervestaple.tipcalculator;

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
    private JLabel labelPeople = new JLabel("People");
    private JLabel labelTip = new JLabel("Tip/Person");
    private JLabel labelTotal = new JLabel("Total Bill");
    private JFormattedTextField textFieldBill;
    private JFormattedTextField textFieldTipPercent = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField textFieldPeople = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField textFieldTip = new JFormattedTextField(NumberFormat.getCurrencyInstance());
    private JFormattedTextField textFieldTotal = new JFormattedTextField(NumberFormat.getCurrencyInstance());
    private JButton buttonCalculate = new JButton("Calculate Tip");

    // models
    private TipModel tipModel = new TipModel();

    /**
     * Creates a new Swing frame.
     *
     * @param title Title for the frame
     */
    public MainFrame(String title) {
        super();
        setTitle(title);

        // setup our input fields
        NumberFormat formatMoneyInput = NumberFormat.getInstance();
        formatMoneyInput.setMaximumFractionDigits(2);
        textFieldBill = new JFormattedTextField(formatMoneyInput);
        textFieldBill.setColumns(5);
        textFieldBill.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldTipPercent.setColumns(5);
        textFieldTipPercent.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldPeople.setColumns(5);
        textFieldPeople.setHorizontalAlignment(SwingConstants.RIGHT);

        // setup our output fields
        Font font = textFieldTip.getFont();
        Font bigBoldFont = new Font(font.getName(), Font.BOLD, font.getSize() + 4);
        textFieldTip.setEditable(false);
        textFieldTip.setFont(bigBoldFont);
        textFieldTip.setBorder(new EmptyBorder(0, 0, 0, 0));
        textFieldTotal.setEditable(false);
        textFieldTotal.setFont(bigBoldFont);
        textFieldTotal.setBorder(new EmptyBorder(0, 0, 0, 0));
        textFieldTotal.setColumns(5);

        // calculate the tip when the button is clicked
        buttonCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                calculateTip();
            }
        });

        // layout our components
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

        Box boxMain = Box.createVerticalBox();
        boxMain.add(Box.createVerticalStrut(10));
        boxMain.add(boxTop);
        boxMain.add(Box.createVerticalStrut(10));
        boxMain.add(boxBottom);
        boxMain.add(Box.createVerticalStrut(10));

        getContentPane().add(boxMain);
        pack();
        setResizable(false);

        // fill in some data to hint the customer
        textFieldBill.setValue(100.00);
        textFieldTipPercent.setValue(20);
        textFieldPeople.setValue(1);
        calculateTip();
    }

    /**
     * Calculates the tip and updates the output fields.
     */
    private void calculateTip() {
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
    }
}
