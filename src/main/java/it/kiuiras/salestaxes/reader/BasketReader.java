package it.kiuiras.salestaxes.reader;

import it.kiuiras.salestaxes.model.Basket;

/**
 * {@link BasketReader} describes a generic reader of an input containing a shopping list.
 *
 * @author Andrea Grossi
 */
public interface BasketReader<T> {

  /**
   * Reads the input and returns a shopping {@link Basket}.
   *
   * @param input the input
   * @return a {@link Basket}
   */
  Basket readInput(T input);

}
