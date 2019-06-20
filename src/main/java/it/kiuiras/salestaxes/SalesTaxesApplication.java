package it.kiuiras.salestaxes;

import it.kiuiras.salestaxes.model.Basket;
import it.kiuiras.salestaxes.model.Item;
import it.kiuiras.salestaxes.model.Receipt;
import it.kiuiras.salestaxes.reader.BasketReader;
import it.kiuiras.salestaxes.reader.FileBasketReader;
import it.kiuiras.salestaxes.taxes.LmTaxesCalculator;
import it.kiuiras.salestaxes.taxes.TaxesCalculator;
import it.kiuiras.salestaxes.util.ConfigurationHolder;
import it.kiuiras.salestaxes.writer.FileReceiptWriter;
import it.kiuiras.salestaxes.writer.ReceiptWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import lombok.extern.slf4j.Slf4j;

/**
 * Main class of the "Sales Taxes" application.
 *
 * @author Andrea Grossi
 */
@Slf4j
public class SalesTaxesApplication {

  private final static String INPUT_OPTION = "-i";
  private final static String OUTPUT_OPTION = "-o";
  private final static String CATEGORY_OPTION = "-category";
  private final static String HELP_OPTION = "-help";

  private static final String DEFAULT_INPUT_PATH = "example/shopping_basket1.example";
  private static final String DEFAULT_OUTPUT_PATH = "output/receipt_details.txt";
  private static final String DEFAULT_CATEGORY_PATH = "conf/categories.properties";

  private static String inputPath;
  private static String outputPath;

  /**
   * The entry point of the "Sales Taxes" application.
   *
   * @param args the arguments to parse
   */
  public static void main(String[] args) {

    parseArgs(args);

    log.info("Starting Sales Taxes application");

    /**
     * Reads the input
     */
    log.debug("Reading the input...");
    BasketReader basketReader = new FileBasketReader();
    Path inputFile = Paths.get(inputPath);
    Basket basket = basketReader.readInput(inputFile);
    log.trace("Input: {}", basket);

    /**
     * Calculates sales taxes
     */
    log.debug("Calculates sales taxes...");
    TaxesCalculator taxesCalculator = new LmTaxesCalculator();
    LinkedHashMap<Item, Double> receiptItems = new LinkedHashMap<>();
    basket.getItemList().forEach(i -> {
      Item item = (Item) i;
      receiptItems.put(item, taxesCalculator.calcTax(item));
    });
    Receipt receipt = new Receipt(receiptItems);

    /**
     * Produces the output
     */
    log.debug("Produces the output...");
    ReceiptWriter receiptWriter = new FileReceiptWriter();
    Path outputFile = Paths.get(outputPath);
    receiptWriter.writeReceipt(receipt, outputFile);
    log.info("Terminating Sales Taxes application");
  }

  /**
   * Parses arguments.
   *
   * @param args the arguments to parse
   */
  private static void parseArgs(String[] args) {
    ArrayList<String> arguments = new ArrayList<>(Arrays.asList(args));
    int catIndex = arguments.indexOf(CATEGORY_OPTION);
    String categoryPath = catIndex != -1 ? arguments.get(catIndex + 1) : DEFAULT_CATEGORY_PATH;
    ConfigurationHolder.getInstance().setCategoryFilePath(categoryPath);
    if (arguments.contains(HELP_OPTION)) {
      printsHelpMessage();
    }
    int inputIndex = arguments.indexOf(INPUT_OPTION);
    inputPath = inputIndex != -1 ? arguments.get(inputIndex + 1) : DEFAULT_INPUT_PATH;
    int outputIndex = arguments.indexOf(OUTPUT_OPTION);
    outputPath = outputIndex != -1 ? arguments.get(outputIndex + 1) : DEFAULT_OUTPUT_PATH;
  }

  /**
   * Prints to the standard output the help message and exit.
   */
  private static void printsHelpMessage() {
    String helpMessage = "\nUsage: ./sales-taxes.sh [-options]\n\n"
        .concat("where options includes:\n\n")
        .concat("-category      path to exempt categories file (default: ")
        .concat(DEFAULT_CATEGORY_PATH)
        .concat(")\n")
        .concat("-help          print this help message\n")
        .concat("-i             path to input file (default: ").concat(DEFAULT_INPUT_PATH)
        .concat(")\n")
        .concat("-o             path to output file (default: ").concat(DEFAULT_OUTPUT_PATH)
        .concat(")");
    System.out.println(helpMessage);
    System.exit(0);
  }
}
