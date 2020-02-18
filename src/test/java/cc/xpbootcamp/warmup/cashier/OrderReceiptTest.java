package cc.xpbootcamp.warmup.cashier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class OrderReceiptTest {

  private DateProvider dateProvider = mock(DateProvider.class);

  public void mockDate(int year, int month, int dayOfMonth, int hour, int minute) {
    when(dateProvider.getCurrentDate())
        .thenReturn(LocalDateTime.of(year, month, dayOfMonth, hour, minute));
  }

  @Test
  void shouldPrintCustomerInformationOnOrder() {
    mockDate(2020, 2, 17, 12, 12);
    Order order = new Order(new ArrayList<>());
    OrderReceipt receipt = new OrderReceipt(order, dateProvider);

    String output = receipt.printReceipt();

    assertThat(output, containsString("===== 老王超市，值得信赖 ======"));
    assertThat(output, containsString("2020年2月17日，星期一"));
  }

  @Test
  public void shouldPrintLineItemAndSalesTaxInformation() {
    mockDate(2020, 2, 17, 12, 12);
    List<LineItem> lineItems =
        new ArrayList<LineItem>() {
          {
            add(new LineItem("milk", 10.0, 2));
            add(new LineItem("biscuits", 5.0, 5));
            add(new LineItem("chocolate", 20.0, 1));
          }
        };
    OrderReceipt receipt = new OrderReceipt(new Order(lineItems), dateProvider);

    String output = receipt.printReceipt();

    assertThat(output, containsString("===== 老王超市，值得信赖 ======"));
    assertThat(output, containsString("2020年2月17日，星期一\n"));
    assertThat(output, containsString("milk，10.0 ✖ 2，20.0\n"));
    assertThat(output, containsString("biscuits，5.0 ✖ 5，25.0\n"));
    assertThat(output, containsString("chocolate，20.0 ✖ 1，20.0\n"));
    assertThat(output, containsString("Sales Tax\t6.5"));
    assertThat(output, containsString("Total Amount\t71.5"));
  }

  @Test
  public void shouldPrintCorrectDateInformationInDifferentDays() {
    mockDate(2020, 2, 19, 12, 12);
    Order order = new Order(new ArrayList<>());
    OrderReceipt receipt = new OrderReceipt(order, dateProvider);

    String output = receipt.printReceipt();

    assertThat(output, containsString("2020年2月19日，星期三\n"));
  }
}
