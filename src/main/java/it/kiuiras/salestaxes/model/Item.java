package it.kiuiras.salestaxes.model;

/**
 * Interfaces of a shopping item.
 * <p>It provides methods necessary to calculate and print the receipt details</p>
 */
public interface Item {

  /**
   * Gets the name of the item
   *
   * @return the name of the item
   */
  String getName();

  /**
   * Gets the quantity of the item
   *
   * @return the quantity of the item
   */
  int getQuantity();

  /**
   * Returns true if item is imported, false otherwise.
   *
   * @return true if item is imported, false otherwise
   */
  boolean isImported();

  /**
   * Gets the price of the item
   *
   * @return the price of the item
   */
  double getPrice();

  /**
   * Returns true if the item belongs to an exempt category, false otherwise.
   *
   * @return true if the item belongs to an exempt category, false otherwise
   */
  boolean isExempt();

}
