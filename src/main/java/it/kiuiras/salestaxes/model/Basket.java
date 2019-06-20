package it.kiuiras.salestaxes.model;

import java.util.List;
import lombok.Data;

/**
 * {@link Basket} represents a shopping basket that contains a list of generic items.
 *
 * @author Andrea Grossi
 */
@Data
public class Basket<Item> {

  private final List<Item> itemList;

}
