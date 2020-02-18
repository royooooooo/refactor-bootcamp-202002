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
    return getReceiptHeader()
        + getReceiptDateInformation()
        + getReceiptBodyInformation()
        + getReceiptFooterInformation();
  }

  private String getReceiptHeader() {
    return "===== 老王超市，值得信赖 ======\n";
  }

  private String getReceiptDateInformation() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年M月dd日，EE", Locale.CHINA);
    return "\n" + dateProvider.getCurrentDate().format(formatter) + "\n";
  }

  private String getReceiptBodyInformation() {
    return order.getOrderLineItemsTypeInformation();
  }

  private String getReceiptFooterInformation() {
    return "-----------------------------------\n"
        + getTotalSalesTaxInformation()
        + getDiscountInformation()
        + getTotalAmountInformation();
  }

  private String getTotalSalesTaxInformation() {
    return String.format("税额：%.2f\n", order.getTotalSalesTax());
  }

  private String getDiscountInformation() {
    return todayIsDiscountDay() ? String.format("折扣：%.2f\n", getDiscount()) : "";
  }

  private String getTotalAmountInformation() {
    return String.format(
        "总价：%.2f", order.getTotalAmountWithoutTax() + order.getTotalSalesTax() - getDiscount());
  }

  private boolean todayIsDiscountDay() {
    return getReceiptDateInformation().contains(DISCOUNT_DAY);
  }

  private double getDiscount() {
    return todayIsDiscountDay()
        ? (order.getTotalAmountWithoutTax() + order.getTotalSalesTax()) * DISCOUNT_RATE
        : 0;
  }
}
