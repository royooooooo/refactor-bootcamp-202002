package cc.xpbootcamp.warmup.cashier;

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
        + order.getTotalSalesTax()
        + "Total Amount\t"
        + (getTotalAmountWithoutTax() + order.getTotalSalesTax());
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
