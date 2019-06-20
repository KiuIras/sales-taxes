package it.kiuiras.salestaxes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for SalesTaxesApplication.
 */
public class SalesTaxesApplicationTest
    extends TestCase {

  /**
   * Creates the test case.
   *
   * @param testName name of the test case
   */
  public SalesTaxesApplicationTest(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(SalesTaxesApplicationTest.class);
  }

  /**
   * Tests the supplied data.
   */
  public void testSuppliedData() {
    for (int i = 1; i <= 3; i++) {
      String inputFileName = "example/shopping_basket" + i + ".example";
      String outputFileName = "output/receipt_details" + i + ".example";
      String expectedOutputFileName = "example/output" + i + ".example";
      SalesTaxesApplication.main(new String[]{"-i", inputFileName, "-o", outputFileName});
      try {
        assertTrue("Expected output and supplied data doesn't match",
            Files.readAllLines(Paths.get(outputFileName))
                .containsAll(Files.readAllLines(Paths.get(expectedOutputFileName))));
      } catch (IOException e) {
        fail("Exception while trying to test supplied data");
      }
    }
    assert true;
  }
}
