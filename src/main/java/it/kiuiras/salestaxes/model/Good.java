package it.kiuiras.salestaxes.model;

import it.kiuiras.salestaxes.util.category.CategoryAnalyzer;
import it.kiuiras.salestaxes.util.category.CategoryAnalyzerFactory;
import java.util.Set;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link Good} represents a shopping item.
 *
 * @author Andrea Grossi
 */
@Slf4j
@Data
public class Good implements Item {

  private static final String AT = " at ";

  private static final CategoryAnalyzer analyzer = CategoryAnalyzerFactory
      .getConfiguredCategoryAnalyzer();

  private String name;
  private Set<String> category;
  private int quantity;
  private boolean imported;
  private double price;

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isExempt() {
    return !category.isEmpty();
  }

  /**
   * Gets a {@link Good} from its textual description.
   * <p>The description should be formed by:</p>
   * <p>QUANTITY description with the *imported* word if necessary* at *PRICE</p>
   * <p>Where QUANTITY is a positive integer and PRICE a positive decimal number.</p>
   * <p>*imported* and * at * are keyword.</p>
   *
   * @param text the description
   * @return the descripted {@link Good}
   */
  public static Good fromText(String text) {
    Good good = new Good();

    good.setName(text.substring(text.indexOf(" ") + 1, text.lastIndexOf(AT)));

    good.setCategory(analyzer.getCategory(text));

    int quantity = 1;
    try {
      quantity = Integer.parseInt(text.substring(0, text.indexOf(" ")));
      if (quantity < 1) {
        System.out.println("Quantity value for good ".concat(good.getName())
            .concat(" is not valid. Uses 1 instead."));
        log.warn("Quantity value for good {} is not valid. Uses 1 instead.", good.getName());
        quantity = 1;
      }
    } catch (NumberFormatException nfe) {
      System.out.println(
          "Can't read quantity for the for good ".concat(good.getName()).concat(". Assume 1."));
      log.error("Can't read quantity for the good {}. Assume 1", good.getName());
      log.trace("Exception caught. {}", nfe.getMessage(), nfe);
    }
    good.setQuantity(quantity);

    good.setImported(text.toUpperCase().contains("IMPORTED"));

    double price = 0;
    try {
      price = Double.parseDouble(text.substring(text.lastIndexOf(AT) + AT.length()));
      if (price < 0) {
        System.out.println("Price value for the good ".concat(good.getName())
            .concat(" is not valid. Uses 0 instead."));
        log.warn("Price value for the good {} is not valid. Uses 0 instead.", good.getName());
        price = 0;
      }
    } catch (NumberFormatException nfe) {
      System.out.println("Can't read price for the good ".concat(good.getName())
          .concat(" is not valid. Uses 0 instead."));
      log.error("Can't read price for the good {}. Uses 0 instead.", good.getName());
      log.trace("Exception caught. {}", nfe.getMessage(), nfe);
    }
    good.setPrice(price);

    return good;
  }

}
