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

  public double getTotalSalesTax() {
    return lineItems.stream()
        .reduce(
            0d,
            (totalSaleTax, lineItem) ->
                totalSaleTax + (lineItem.totalAmount() * OrderReceipt.SALE_ROTE),
            Double::sum);
  }

  public double getTotalAmountWithoutTax() {
    return lineItems.stream()
        .reduce(
            0d,
            (totalAmountWithoutTax, lineItem) -> totalAmountWithoutTax + lineItem.totalAmount(),
            Double::sum);
  }

  public String getOrderLineItemsTypeInformation() {
    return lineItems.stream()
        .reduce(
            "",
            (typeInformation, lineItem) -> typeInformation + lineItem.getLineItemTypeInformation(),
            String::concat);
  }
}
