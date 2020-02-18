package cc.xpbootcamp.warmup.cashier;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class OrderReceipt {
  public static double SALE_ROTE = .10;
  public static double DISCOUNT_RATE = 0.02;
  public static String DISCOUNT_DAY = "星期三";

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
    return "-----------------------------------\n"
        + String.format("税额：%.2f\n", order.getTotalSalesTax())
        + generateDiscountInformation()
        + String.format(
            "总价：%.2f", order.getTotalAmountWithoutTax() + order.getTotalSalesTax() - getDiscount());
  }

  private String generateDiscountInformation() {
    return todayIsDiscountDay() ? String.format("折扣：%.2f\n", getDiscount()) : "";
  }

  private double getDiscount() {
    return todayIsDiscountDay()
        ? (order.getTotalAmountWithoutTax() + order.getTotalSalesTax()) * DISCOUNT_RATE
        : 0;
  }

  private boolean todayIsDiscountDay() {
    return generateReceiptDateInformation().contains(DISCOUNT_DAY);
  }
}
