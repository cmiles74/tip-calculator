package com.nervestaple.tipcalculator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.math.BigDecimal;
import java.util.logging.Logger;

/**
 * Unit test for tip model.
 */
public class TipModelTest extends TestCase {

    /**
     * Logger instance.
     */
    private static final Logger log = Logger.getLogger(TipModelTest.class.getName());

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TipModelTest(String testName)
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TipModelTest.class );
    }

    /**
     * Rigorous Test :-)
     */
    public void testApp() {
        TipModel model = new TipModel();
        model.setTotalBill(new BigDecimal(10L));
        model.setPeople(2);
        model.setTipPercentage(new BigDecimal(0.2));
        model.calculateTip();
        log.info("Value: " + model);
        assertTrue(model.getTotalTip().compareTo(new BigDecimal(2L)) == 0);
    }
}
