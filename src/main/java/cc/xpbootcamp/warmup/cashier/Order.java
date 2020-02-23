package cc.xpbootcamp.warmup.cashier;

import java.util.List;

public class Order {

  public static double DISCOUNT_RATE = 0.02;
  public static int DISCOUNT_DAY = 3;
  private DateProvider dateProvider;
  String customerName;
  String address;
  List<LineItem> lineItems;

  public Order(DateProvider dateProvider, List<LineItem> lineItems) {
    this.dateProvider = dateProvider;
    this.lineItems = lineItems;
  }

  public String getCustomerName() {
    return customerName;
  }

  public String getCustomerAddress() {
    return address;
  }

  public double getTotalSalesTax() {
    return lineItems.stream()
        .reduce(
            0d,
            (totalSaleTax, lineItem) ->
                totalSaleTax + (lineItem.totalAmount() * OrderReceipt.SALE_RATE),
            Double::sum);
  }

  public double getTotalAmountWithoutTax() {
    return lineItems.stream()
        .reduce(
            0d,
            (totalAmountWithoutTax, lineItem) -> totalAmountWithoutTax + lineItem.totalAmount(),
            Double::sum);
  }

  private boolean todayIsDiscountDay() {
    return dateProvider.getCurrentDate().getDayOfWeek().getValue() == DISCOUNT_DAY;
  }

  public double getDiscount() {
    return todayIsDiscountDay()
        ? (getTotalAmountWithoutTax() + getTotalSalesTax()) * DISCOUNT_RATE
        : 0;
  }
}
