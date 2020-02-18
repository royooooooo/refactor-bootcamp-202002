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

  private String generateReceiptBody() {
    return order.getOrderLineItemsTypeInformation();
  }

  private String generateReceiptFooter() {
    return "-----------------------------------"
        + "税额："
        + String.format("%.2f", order.getTotalSalesTax())
        + "总价："
        + String.format("%.2f", order.getTotalAmountWithoutTax() + order.getTotalSalesTax());
  }
}
