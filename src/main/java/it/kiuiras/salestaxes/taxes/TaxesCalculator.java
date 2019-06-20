package it.kiuiras.salestaxes.taxes;

import it.kiuiras.salestaxes.model.Item;

/**
 * This interface descript the methods that should be implemented by Tax calculator.
 *
 * @author Andrea Grossi
 */
public interface TaxesCalculator {

  /**
   * Calculates the taxes of the given {@link Item}
   *
   * @param item the {@link Item} to consider
   * @return the taxes on the {@link Item}
   */
  double calcTax(Item item);

}
