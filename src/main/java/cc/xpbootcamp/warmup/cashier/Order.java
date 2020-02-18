package cc.xpbootcamp.warmup.cashier;

import java.util.List;

public class Order {
  String customerName;
  String address;
  List<LineItem> lineItems;

  public Order(String customerName, String address, List<LineItem> lineItems) {
    this.customerName = customerName;
    this.address = address;
    this.lineItems = lineItems;
  }

  public String getCustomerName() {
    return customerName;
  }

  public String getCustomerAddress() {
    return address;
  }

  public List<LineItem> getLineItems() {
    return lineItems;
  }

  public double getTotalSalesTax() {
    return lineItems.stream()
        .reduce(
            0d,
            (totalSaleTax, lineItem) ->
                totalSaleTax + (lineItem.totalAmount() * OrderReceipt.SALE_ROTE),
            Double::sum);
  }
}
