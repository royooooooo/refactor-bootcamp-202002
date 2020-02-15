package cc.xpbootcamp.warmup.cashier;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part of order. It computes
 * the total order amount (amount of individual lineItems + total sales tax) and prints it.
 */
public class OrderReceipt {
  public static double SALE_ROTE = .10;

  private Order order;

  public OrderReceipt(Order order) {
    this.order = order;
  }

  public String printReceipt() {
    return "======Printing Orders======\n"
        + order.getCustomerName()
        + order.getCustomerAddress()
        + getOrderLineItemsTypeInformation()
        + "Sales Tax\t"
        + getTotalSalesTax()
        + "Total Amount\t"
        + (getTotalAmountWithoutTax() + getTotalSalesTax());
  }

  public double getTotalSalesTax() {
    return order.getLineItems().stream()
        .reduce(
            0d,
            (totalSaleTax, lineItem) -> totalSaleTax + (lineItem.totalAmount() * SALE_ROTE),
            Double::sum);
  }

  public double getTotalAmountWithoutTax() {
    return order.getLineItems().stream()
        .reduce(
            0d,
            (totalAmountWithoutTax, lineItem) -> totalAmountWithoutTax + lineItem.totalAmount(),
            Double::sum);
  }

  public String getOrderLineItemsTypeInformation() {
    return order.getLineItems().stream()
        .reduce(
            "",
            (typeInformation, lineItem) -> typeInformation + lineItem.getLineItemTypeInformation(),
            String::concat);
  }
}
