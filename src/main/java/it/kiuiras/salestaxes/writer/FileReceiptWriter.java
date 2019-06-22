package it.kiuiras.salestaxes.writer;

import it.kiuiras.salestaxes.model.Receipt;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link FileReceiptWriter} provides methods to write a {@link Receipt} on a file (using its {@link
 * Path}).
 *
 * @author Andrea Grossi
 */
@Slf4j
public class FileReceiptWriter implements ReceiptWriter<Path> {

  /**
   * Writes the given {@link Receipt} on a file.
   *
   * @param receipt the receipt to write
   */
  @Override
  public void writeReceipt(Receipt receipt, Path path) {
    try {
      if (!path.toAbsolutePath().getParent().toFile().exists()) {
        path.getParent().toFile().mkdir();
      }
      path.toFile().createNewFile();
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
        writer.write(receipt.printReceipt());
      }
    } catch (Exception e) {
      System.out.println("Can't write the output.");
      log.error("Error while writing the output. {}", e.getMessage(), e);
    }
  }
}
