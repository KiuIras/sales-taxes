package it.kiuiras.salestaxes.util.category;

import it.kiuiras.salestaxes.util.ConfigurationHolder;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link FileCategoryAnalyzer} provides methods to analyze text reading the categories from a
 * properties file.
 *
 * @author Andrea Grossi
 */
@Slf4j
public class FileCategoryAnalyzer implements CategoryAnalyzer {

  private final static String CATEGORY_FILE_PATH = ConfigurationHolder.getInstance().getCategoryFilePath();

  private Properties categoryMap;

  /**
   * Public constructor of a {@link FileCategoryAnalyzer}.
   * <p>It reads and stores good/category mapping from a file.</p>
   */
  public FileCategoryAnalyzer() {
    categoryMap = new Properties();
    try {
      categoryMap.load(new FileInputStream(CATEGORY_FILE_PATH));
    } catch (IOException e) {
      log.error(
          "Error while retrieving exempt categories from file: the exemption mechanism will be deactivated");
      log.trace("Exception caught. {}", e.getMessage(), e);
    }
  }

  /**
   * Gets the list of exempt categories reading it from the file.
   *
   * @param text the text to analyze
   * @return the list of exempt categories
   */
  @Override
  public Set<String> getCategory(String text) {
    text = text.toUpperCase();
    Set<String> categorySet = new HashSet<>();
    for (Object k : categoryMap.keySet()) {
      String good = (String) k;
      if (text.toUpperCase().contains(good.toUpperCase())) {
        String category = categoryMap.getProperty(good);
        if (ConfigurationHolder.getInstance().getExemption().contains(category)) {
          categorySet.add(category);
        }
      }
    }
    return categorySet;
  }

}
