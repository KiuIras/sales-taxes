package it.kiuiras.salestaxes.reader;

import it.kiuiras.salestaxes.model.Basket;
import it.kiuiras.salestaxes.model.Good;
import it.kiuiras.salestaxes.model.Item;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link FileBasketReader} provides methods needs to read a {@link Basket} from a file (using its
 * {@link Path}).
 *
 * @author Andrea Grossi
 */
@Slf4j
public class FileBasketReader implements BasketReader<Path> {

  /**
   * Reads the input and returns the described {@link Basket}
   *
   * @param input the {@link Path} of the input file
   * @return the {@link Basket} read from the input
   */
  public Basket readInput(Path input) {
    List<Item> items = new ArrayList<>();
    try {
      Files.readAllLines(input).forEach(line -> {
        items.add(Good.fromText(line));
      });
    } catch (Exception e) {
      System.out.println("Can't read the input.");
      log.error("Error while reading the input");
      log.trace("Exception caught. {}", e.getMessage(), e);
    }
    return new Basket<>(items);
  }
}
