package it.kiuiras.salestaxes.writer;

import it.kiuiras.salestaxes.model.Receipt;

/**
 * {@link ReceiptWriter} describes a generic writer of an output containing a receipt.
 *
 * @author Andrea Grossi
 */
public interface ReceiptWriter<T> {

  /**
   * Writes the given {@link Receipt}.
   *
   * @param receipt the receipt to write
   * @param destination where the receipt has to be written
   */
  void writeReceipt(Receipt receipt, T destination);

}
