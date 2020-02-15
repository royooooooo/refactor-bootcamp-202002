package cc.xpbootcamp.warmup.fibonacci;

import static cc.xpbootcamp.warmup.fibonacci.Fibonacci.getFibonacciByDigits;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class FibonacciTest {

  @Test
  void should_return_1_when_calculate_given_1() {
    assertEquals(getFibonacciByDigits(1), 1L);
  }

  @Test
  void should_return_1_when_calculate_given_2() {
    assertEquals(getFibonacciByDigits(2), 1L);
  }

  @Test
  void should_return_2_when_calculate_given_3() {
    assertEquals(getFibonacciByDigits(3), 2L);
  }

  @Test
  void should_return_3_when_calculate_given_4() {
    assertEquals(getFibonacciByDigits(4), 3L);
  }

  @Test
  void should_return_13_when_calculate_given_7() {
    assertEquals(getFibonacciByDigits(7), 13);
  }

  @Test
  void should_return_12586269025L_when_calculate_given_50() {
    assertEquals(getFibonacciByDigits(50), 12586269025L);
  }
}
