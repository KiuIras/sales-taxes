package it.kiuiras.salestaxes.util.category;

import it.kiuiras.salestaxes.util.ConfigurationHolder;

/**
 * Factory of {@link CategoryAnalyzer}. It provides methods to retrieves specific implementation of
 * a {@link CategoryAnalyzer}.
 *
 * @author Andrea Grossi
 */
public class CategoryAnalyzerFactory {

  public static final String FILE_CATEGORY = "FILE_CATEGORY";
  public static final String WORD_NET = "WORD_NET";

  /**
   * Gets the implementation of the {@link CategoryAnalyzer} accordingly to configuration.
   *
   * @return the configured implementation of {@link CategoryAnalyzer}
   */
  public static CategoryAnalyzer getConfiguredCategoryAnalyzer() {
    CategoryAnalyzer analyzer;
    switch (ConfigurationHolder.getInstance().getCategoryAnalyzer()) {
      case FILE_CATEGORY:
      default:
        analyzer = new FileCategoryAnalyzer();
        break;
      case WORD_NET:
        analyzer = new WordNetAnalyzer();
        break;
    }
    return analyzer;
  }

}
