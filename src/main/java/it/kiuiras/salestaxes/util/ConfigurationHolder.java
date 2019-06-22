package it.kiuiras.salestaxes.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link ConfigurationHolder} reads from a file and stores the configurable values used in the
 * application.
 * <p>It implements the singleton pattern to guarantee the presence of a unique instance of
 * this object.</p>
 *
 * @author Andrea Grossi
 */
@Slf4j
public class ConfigurationHolder {

  private final static ConfigurationHolder singleton = new ConfigurationHolder();

  private final static String CONFIGURATION_FILE_PATH = "conf/salestaxes.properties";

  @Getter
  private double basicSalesTax = 10;
  @Getter
  private double importDuty = 5;
  @Getter
  private List<String> exemption = Arrays.asList("BOOK", "FOOD", "MEDICAL");

  @Getter
  private String categoryAnalyzer = "FileCategory";
  @Getter
  @Setter
  private String categoryFilePath;

  /**
   * Private constructor to avoid the instantiation of this class from the outside.
   * <p>It initializes the configuration holder reading configured values from the file</p>
   */
  private ConfigurationHolder() {
    readConfiguration();
  }

  /**
   * Gets the singleton instance of the {@link ConfigurationHolder}.
   *
   * @return the singleton instance of the {@link ConfigurationHolder}
   */
  public static ConfigurationHolder getInstance() {
    return singleton;
  }

  /**
   * Reads the configuration from file.
   */
  private void readConfiguration() {
    Properties properties = new Properties();
    try {
      properties.load(new FileInputStream(CONFIGURATION_FILE_PATH));
      log.trace("Read from file: {}", properties);
    } catch (IOException e) {
      log.error("Error in the read of the configuration file. Default values will be used. {}",
          e.getMessage(), e);
    }
    if (!properties.isEmpty()) {
      try {
        basicSalesTax = Double.parseDouble(properties.getProperty("BasicSalesTax"));
      } catch (NullPointerException npe) {
        log.warn("The basic sales taxes value is not configured: default value will be used. {}",
            npe.getMessage(), npe);
      } catch (NumberFormatException nfe) {
        log.warn(
            "The basic sales taxes value is not a valid number: default value will be used. {}",
            nfe.getMessage(), nfe);
      }
      try {
        importDuty = Double.parseDouble(properties.getProperty("ImportDuty"));
      } catch (NullPointerException npe) {
        log.warn("The import duty value is not configured: default value will be used. {}",
            npe.getMessage(), npe);
      } catch (NumberFormatException nfe) {
        log.warn("The import duty value is not a valid number: default value will be used. {}",
            nfe.getMessage(), nfe);
      }
      exemption = Arrays.asList(properties.getProperty("Exemption").split(","));
      try {
        categoryAnalyzer = properties.getProperty("CategoryAnalyzer");
      } catch (NullPointerException npe) {
        log.warn("No Category Analyzer is configured: default Analyzer will be used. {}",
            npe.getMessage(), npe);
      }
    }
  }

}
