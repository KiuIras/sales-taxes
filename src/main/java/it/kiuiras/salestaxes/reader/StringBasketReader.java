package it.kiuiras.salestaxes.reader;

import it.kiuiras.salestaxes.model.Basket;
import it.kiuiras.salestaxes.model.Good;
import it.kiuiras.salestaxes.model.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link StringBasketReader} provides methods needs to read a {@link Basket} described in the
 * input.
 *
 * @author Andrea Grossi
 */
@Slf4j
public class StringBasketReader implements BasketReader<String> {

  /**
   * Reads the input and returns the described {@link Basket}
   *
   * @param input the {@link String} that contains the input
   * @return the {@link Basket} read from the input
   */
  public Basket readInput(String input) {
    List<Item> items = new ArrayList<>();
    Stream.of(input.split("\n")).forEach(line -> items.add(Good.fromText(line)));
    Basket<Item> basket = new Basket(items);
    return basket;
  }
}
