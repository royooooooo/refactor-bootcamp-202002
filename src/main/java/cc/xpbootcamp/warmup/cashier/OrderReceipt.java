package cc.xpbootcamp.warmup.cashier;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class OrderReceipt {
  public static double SALE_ROTE = .10;
  public static double DISCOUNT_RATE = 0.02;
  public static int DISCOUNT_DAY = 3;
  public static String SUPERMARKET_TITLE = "===== 老王超市，值得信赖 ======\n";
  public static String DATE_FORMATTER_PATTERN = "yyyy年M月dd日，EE";
  public static String LINE = "-----------------------------------\n";
  public static String TOTAL_SALES_FORMATTER = "税额：%.2f\n";
  public static String DISCOUNT_FORMATTER = "折扣：%.2f\n";
  public static String TOTAL_AMOUNT = "总价：%.2f";

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

    return SUPERMARKET_TITLE;
  }

  private String getReceiptDateInformation() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER_PATTERN, Locale.CHINA);
    return "\n" + dateProvider.getCurrentDate().format(formatter) + "\n";
  }

  private String getReceiptBodyInformation() {
    return order.getOrderLineItemsTypeInformation();
  }

  private String getReceiptFooterInformation() {

    return LINE
        + getTotalSalesTaxInformation()
        + getDiscountInformation()
        + getTotalAmountInformation();
  }

  private String getTotalSalesTaxInformation() {
    return String.format(TOTAL_SALES_FORMATTER, order.getTotalSalesTax());
  }

  private String getDiscountInformation() {
    return todayIsDiscountDay() ? String.format(DISCOUNT_FORMATTER, getDiscount()) : "";
  }

  private String getTotalAmountInformation() {
    return String.format(
        TOTAL_AMOUNT, order.getTotalAmountWithoutTax() + order.getTotalSalesTax() - getDiscount());
  }

  private boolean todayIsDiscountDay() {
    return dateProvider.getCurrentDate().getDayOfWeek().getValue() == DISCOUNT_DAY;
  }

  private double getDiscount() {
    return todayIsDiscountDay()
        ? (order.getTotalAmountWithoutTax() + order.getTotalSalesTax()) * DISCOUNT_RATE
        : 0;
  }
}
