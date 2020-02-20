package cc.xpbootcamp.warmup.cashier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
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
    assertThat(output, containsString("-----------------------------------"));
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

    assertEquals(
        output,
        "===== 老王超市，值得信赖 ======\n\n"
            + "2020年2月17日，星期一\n\n"
            + "milk，10.00 ✖ 2，20.00\n"
            + "biscuits，5.00 ✖ 5，25.00\n"
            + "chocolate，20.00 ✖ 1，20.00\n"
            + "-----------------------------------\n"
            + "税额：6.50\n"
            + "总价：71.50");
  }

  @Test
  public void shouldPrintCorrectDateInformationInDifferentDays() {
    mockDate(2020, 2, 19, 12, 12);
    Order order = new Order(new ArrayList<>());
    OrderReceipt receipt = new OrderReceipt(order, dateProvider);

    String output = receipt.printReceipt();

    assertThat(output, containsString("2020年2月19日，星期三\n"));
  }

  @Test
  public void shouldPrintLineItemAndSalesTaxInformationWhenWednesday() {
    mockDate(2020, 2, 19, 12, 12);
    List<LineItem> lineItems =
        new ArrayList<LineItem>() {
          {
            add(new LineItem("巧克力", 21.5, 2));
            add(new LineItem("小白菜", 10, 1));
          }
        };
    OrderReceipt receipt = new OrderReceipt(new Order(lineItems), dateProvider);

    String output = receipt.printReceipt();

    assertEquals(
        output,
        "===== 老王超市，值得信赖 ======\n\n"
            + "2020年2月19日，星期三\n\n"
            + "巧克力，21.50 ✖ 2，43.00\n"
            + "小白菜，10.00 ✖ 1，10.00\n"
            + "-----------------------------------\n"
            + "税额：5.30\n"
            + "折扣：1.17\n"
            + "总价：57.13");
  }
}
