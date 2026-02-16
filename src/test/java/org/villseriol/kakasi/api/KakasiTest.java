// This software is released into the Public Domain. See copying.txt for details.
package org.villseriol.kakasi.api;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class KakasiTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public KakasiTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(KakasiTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }
}
