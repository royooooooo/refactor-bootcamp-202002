package cc.xpbootcamp.warmup.fibonacci;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class FibonacciTest {

  @Test
  void should_return_1_when_calculate_given_1() {
    assertEquals(Fibonacci.getFibonacciByDigits(1), 1);
  }
}