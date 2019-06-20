package it.kiuiras.salestaxes.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedHashMap;

/**
 * This class defines a {@link Receipt} that stores the detailed items and prices (taxes included).
 */
public class Receipt {

  private final LinkedHashMap<Item, Double> itemWithTaxes;

  public Receipt(LinkedHashMap<Item, Double> itemWithTaxes) {
    this.itemWithTaxes = itemWithTaxes;
  }

  /**
   * Gets the total sales taxes.
   *
   * @return the total sales taxes
   */
  private double getSalesTaxes() {
    return itemWithTaxes.values().stream().reduce(0d, Double::sum);
  }

  /**
   * Gets the total.
   *
   * @return the total
   */
  private double getTotal() {
    return itemWithTaxes.keySet().stream().map(Item::getPrice).reduce(0d, Double::sum)
        + getSalesTaxes();
  }

  /**
   * Gets the {@link String} with the properly formatted receipt.
   *
   * @return the properly formatted receipt
   */
  public String printReceipt() {
    NumberFormat nf = new DecimalFormat("0.00");
    StringBuilder sb = new StringBuilder();
    itemWithTaxes.forEach(
        (item, taxes) -> sb.append(item.getQuantity()).append(" ").append(item.getName())
            .append(": ").append(nf.format(item.getPrice() + taxes)).append("\n"));
    sb.append("Sales Taxes: ").append(nf.format(getSalesTaxes())).append("\n");
    sb.append("Total: ").append(nf.format(getTotal())).append("\n");
    return sb.toString();
  }

}
