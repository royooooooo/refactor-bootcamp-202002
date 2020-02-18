package cc.xpbootcamp.warmup.cashier;

import java.util.List;

public class Order {

  List<LineItem> lineItems;

  public Order(List<LineItem> lineItems) {
    this.lineItems = lineItems;
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
            "\n",
            (typeInformation, lineItem) -> typeInformation + lineItem.getLineItemTypeInformation(),
            String::concat);
  }
}
