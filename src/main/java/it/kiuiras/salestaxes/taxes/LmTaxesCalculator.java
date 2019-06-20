package it.kiuiras.salestaxes.taxes;

import it.kiuiras.salestaxes.model.Item;
import it.kiuiras.salestaxes.util.ConfigurationHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link LmTaxesCalculator} is a {@link TaxesCalculator} that uses for the taxes calculation the
 * policy described in the LM test.
 *
 * @author Andrea Grossi
 */
@Slf4j
public class LmTaxesCalculator implements TaxesCalculator {

  private static final double BASIC_TAX = ConfigurationHolder.getInstance().getBasicSalesTax();
  private static final double IMPORT_DUTY = ConfigurationHolder.getInstance().getImportDuty();

  /**
   * Calculates the taxes using the policy described in the LM test.
   *
   * @param item the {@link Item} to consider
   * @return the taxes on the {@link Item}
   */
  @Override
  public double calcTax(Item item) {
    double taxRate = 0;
    if (!item.isExempt()) {
      taxRate += BASIC_TAX;
    }
    if (item.isImported()) {
      taxRate += IMPORT_DUTY;
    }
    double taxes = item.getPrice() / 100 * taxRate;
    /**
     * Rounds the taxes up to the nearest 0.05
     */
    taxes = Math.ceil(taxes * 20) / 20d;
    log.trace("Taxes for the item {}: {}", item.getName(), taxes);
    return taxes;
  }
}
