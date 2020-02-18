package cc.xpbootcamp.warmup.cashier;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class OrderReceipt {
  public static double SALE_ROTE = .10;

  private Order order;
  private DateProvider dateProvider;

  public OrderReceipt(Order order, DateProvider dateProvider) {
    this.order = order;
    this.dateProvider = dateProvider;
  }

  public String printReceipt() {
    return generateReceiptHeader()
        + generateReceiptDateInformation()
        + generateCustomerInformation()
        + generateReceiptBody()
        + generateReceiptFooter();
  }

  private String generateReceiptDateInformation() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年M月dd日，EE", Locale.CHINA);
    return "\n" + dateProvider.getCurrentDate().format(formatter) + "\n";
  }

  private String generateReceiptHeader() {
    return "===== 老王超市，值得信赖 ======\n";
  }

  private String generateCustomerInformation() {
    return order.getCustomerName() + order.getCustomerAddress();
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
