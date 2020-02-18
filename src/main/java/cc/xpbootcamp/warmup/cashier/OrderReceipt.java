package cc.xpbootcamp.warmup.cashier;

public class OrderReceipt {
  public static double SALE_ROTE = .10;

  private Order order;

  public OrderReceipt(Order order) {
    this.order = order;
  }

  public String printReceipt() {
    return generateReceiptHeader() + generateReceiptBody() + generateReceiptFooter();
  }

  private String generateReceiptHeader() {
    return "===== 老王超市，值得信赖 ======\n" + order.getCustomerName() + order.getCustomerAddress();
  }

  private String generateReceiptBody() {
    return order.getOrderLineItemsTypeInformation();
  }

  private String generateReceiptFooter() {
    return "Sales Tax\t"
        + order.getTotalSalesTax()
        + "Total Amount\t"
        + (order.getTotalAmountWithoutTax() + order.getTotalSalesTax());
  }
}
