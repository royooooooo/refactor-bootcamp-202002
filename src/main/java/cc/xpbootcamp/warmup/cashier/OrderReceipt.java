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
        + order.getOrderLineItemsTypeInformation()
        + "Sales Tax\t"
        + order.getTotalSalesTax()
        + "Total Amount\t"
        + (order.getTotalAmountWithoutTax() + order.getTotalSalesTax());
  }

}
