package it.kiuiras.salestaxes.util.category;

import java.util.List;
import java.util.Set;

/**
 * {@link CategoryAnalyzer} describes a generic utility class that provides methods to analyze
 * category contained in a {@link String}.
 *
 * @author Andrea Grossi
 */
public interface CategoryAnalyzer {

  /**
   * Gets the {@link List} of exempt categories contained in the given text.
   *
   * @param text the text to analyze
   * @return the {@link List} of exempt categories
   */
  Set<String> getCategory(String text);

}
