package cc.xpbootcamp.warmup.cashier;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.Collectors;

public class OrderReceipt {
  public static String SUPERMARKET_TITLE = "===== 老王超市，值得信赖 ======\n";
  public static String DATE_FORMATTER_PATTERN = "yyyy年M月dd日，EE";
  public static String LINE = "-----------------------------------\n";
  public static String TOTAL_SALES_FORMATTER = "税额：%.2f\n";
  public static String DISCOUNT_FORMATTER = "折扣：%.2f\n";
  public static String TOTAL_AMOUNT_FORMATTER = "总价：%.2f";

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
    return "\n"
        + order.lineItems.stream()
            .map(this::getLineItemTypeInformation)
            .collect(Collectors.joining());
  }

  private String getReceiptFooterInformation() {
    return LINE
        + getTotalSalesTaxInformation()
        + getDiscountInformation()
        + getTotalAmountInformation();
  }

  public String getLineItemTypeInformation(LineItem lineItem) {
    return String.format(
        "%s，%.2f ✖ %d，%.2f\n",
        lineItem.getDescription(),
        lineItem.getPrice(),
        lineItem.getQuantity(),
        lineItem.totalAmount());
  }

  private String getTotalSalesTaxInformation() {
    return String.format(TOTAL_SALES_FORMATTER, order.getTotalSalesTax());
  }

  private String getDiscountInformation() {
    return order.getDiscount() != 0 ? String.format(DISCOUNT_FORMATTER, order.getDiscount()) : "";
  }

  private String getTotalAmountInformation() {
    return String.format(
        TOTAL_AMOUNT_FORMATTER,
        order.getTotalAmountWithoutTax() + order.getTotalSalesTax() - order.getDiscount());
  }
}
