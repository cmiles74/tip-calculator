package com.nervestaple.tipcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Logger;

/**
 * Provides a data object that encapsulates the information needed to calculate the tip.
 */
public class TipModel {

    /**
     * Logger instance.
     */
    private static final Logger log = Logger.getLogger(TipModel.class.getName());

    /**
     * The total cost of the bill.
     */
    private BigDecimal totalBill;

    /**
     * The number of people who will evenly split the bill.
     */
    private Integer people;

    /**
     * The percentage of the bill that we'll be tipping.
     */
    private BigDecimal tipPercentage;

    /**
     * The total cost of the tip.
     */
    private BigDecimal totalTip;

    /**
     * The cost of the tip per person.
     */
    private BigDecimal tipPerPerson;

    /**
     * The cost of the bill including the tip.
     */
    private BigDecimal totalBillWithTip;

    /**
     * If the state of the model is invalid, this field will contain an error message.
     */
    private String errorMessage;

    public TipModel() {
        reset();
    }

    public BigDecimal getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(BigDecimal totalBill) {
        this.totalBill = totalBill;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public BigDecimal getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(BigDecimal tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public BigDecimal getTotalTip() {
        return totalTip;
    }

    public void setTotalTip(BigDecimal totalTip) {
        this.totalTip = totalTip;
    }

    public BigDecimal getTipPerPerson() {
        return tipPerPerson;
    }

    public void setTipPerPerson(BigDecimal tipPerPerson) {
        this.tipPerPerson = tipPerPerson;
    }

    public BigDecimal getTotalBillWithTip() {
        return totalBillWithTip;
    }

    public void setTotalBillWithTip(BigDecimal totalBillWithTip) {
        this.totalBillWithTip = totalBillWithTip;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "TipModel{" +
                "totalBill=" + totalBill +
                ", people=" + people +
                ", tipPercentage=" + tipPercentage +
                ", totalTip=" + totalTip +
                ", tipPerPerson=" + tipPerPerson +
                ", totalBillWithTip=" + totalBillWithTip +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    /**
     * Clears all of the values in the model.
     */
    public void reset() {
        setTotalBill(new BigDecimal(0L));
        setPeople(0);
        setTipPercentage(new BigDecimal(0L));
        setTotalTip(new BigDecimal(0L));
        setTipPerPerson(new BigDecimal(0L));
        setTotalBillWithTip(new BigDecimal(0L));
        setErrorMessage(null);
    }

    /**
     * Returns true if the state of the model is valid, false if it is not.
     *
     * @return boolean Flag indicating model validity
     */
    public boolean isValid() {
        validate();
        return errorMessage == null || errorMessage.length() == 0 ? true : false;
    }

    /**
     * Calculates the model and updates the tip. Returns false if the model is in an error state.
     *
     * @return Flag indicating model validity
     */
    public boolean calculateTip() {
        boolean value = isValid();

        if(isValid()) {

            setTotalTip(getTotalBill().multiply(getTipPercentage().setScale(2, BigDecimal.ROUND_HALF_UP)));
            setTipPerPerson(getTotalTip().divide(new BigDecimal(getPeople()), RoundingMode.HALF_UP)
                    .setScale(2, BigDecimal.ROUND_HALF_UP));
            setTotalBillWithTip(getTotalBill().add(getTotalTip()));
        }

        return value;
    }

    /**
     * Validates the state of the model.
     */
    private void validate() {
        StringBuilder errorMessageBuillder = new StringBuilder();

        if(getPeople() == null || getPeople() < 1) {
            errorMessageBuillder.append("At least one person needs to tip");
        }

        if(getTipPercentage() == null || getTipPercentage().compareTo(new BigDecimal(0.01)) <= 0) {
            if(errorMessageBuillder.length() > 0) {
                errorMessageBuillder.append("\n\r");
            }

            errorMessageBuillder.append("A tip percentage is required");
        }

        if(getTotalBill() == null || getTotalBill().compareTo(new BigDecimal(0L)) < 0) {
            if(errorMessageBuillder.length() > 0) {
                errorMessageBuillder.append("\n\r");
            }

            errorMessageBuillder.append("The total bill is required");
        }

        setErrorMessage(errorMessageBuillder.toString());
    }
}
