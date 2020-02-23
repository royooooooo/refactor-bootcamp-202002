package cc.xpbootcamp.warmup.cashier;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
  public static double SALE_RATE = .10;
  public static double DISCOUNT_RATE = 0.02;
  public static int DISCOUNT_DAY = 3;

  private LocalDateTime createDate;
  String customerName;
  String address;
  List<LineItem> lineItems;

  public Order(List<LineItem> lineItems, LocalDateTime createDate) {
    this.createDate = createDate;
    this.lineItems = lineItems;
  }

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public String getCustomerName() {
    return customerName;
  }

  public String getCustomerAddress() {
    return address;
  }

  public double getTotalSalesTax() {
    return getTotalAmountWithoutTax() * SALE_RATE;
  }

  public double getTotalAmountWithoutTax() {
    return lineItems.stream().mapToDouble(LineItem::totalAmount).sum();
  }

  private boolean todayIsDiscountDay() {
    return createDate.getDayOfWeek().getValue() == DISCOUNT_DAY;
  }

  public double getDiscount() {
    return todayIsDiscountDay()
        ? (getTotalAmountWithoutTax() + getTotalSalesTax()) * DISCOUNT_RATE
        : 0;
  }
}
