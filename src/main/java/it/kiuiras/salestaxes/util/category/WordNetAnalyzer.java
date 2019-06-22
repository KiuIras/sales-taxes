package it.kiuiras.salestaxes.util.category;

import it.kiuiras.salestaxes.util.ConfigurationHolder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.dictionary.Dictionary;

/**
 * {@link WordNetAnalyzer} provides methods to analyze text using the WordNet library
 * (https://wordnet.princeton.edu/).
 * <p>Only for test purpose: the results may be incorrect!</p>
 */
@Slf4j
public class WordNetAnalyzer implements CategoryAnalyzer {

  private Dictionary dictionary;

  /**
   * Public constructor of a {@link WordNetAnalyzer}.
   */
  public WordNetAnalyzer() {
    try {
      dictionary = Dictionary.getDefaultResourceInstance();
    } catch (JWNLException e) {
      log.error("Error while initializing the WordNet dictionary. {}", e.getMessage(), e);
    }
  }

  /**
   * Gets the list of exempt categories analyzing the given text with the WordNet library.
   *
   * @param text the text to analyze
   * @return the list of exempt categories
   */
  @Override
  public Set<String> getCategory(String text) {
    text = text.toUpperCase();
    Set<String> categorySet = new HashSet<>();
    Arrays.stream(text.split(" ")).forEach(w -> {
      try {
        /**
         * Retrieves the dictionary entry for the given word.
         */
        IndexWord iw = dictionary.lookupIndexWord(POS.NOUN, w);
        if (iw != null) {
          iw.getSenses().stream().forEach(s -> {
            log.trace("{}: {}", iw.getLemma(), s);
            /**
             * Checks if the exempt categories are present in the dictionary entry.
             */
            ConfigurationHolder.getInstance().getExemption().forEach(exCat -> {
              if (s.containsWord(exCat)) {
                categorySet.add(exCat);
              }
            });
          });
        }
      } catch (JWNLException e) {
        log.warn("Error while analyzing the given text. {}", e.getMessage(), e);
      }
    });
    return categorySet;
  }
}
