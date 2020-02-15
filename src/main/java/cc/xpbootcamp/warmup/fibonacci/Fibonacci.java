package cc.xpbootcamp.warmup.fibonacci;

public class Fibonacci {

  public static long getFibonacciByDigits(int digits) {

    if (digits == 1 || digits == 2) {
      return 1L;
    }

    return getFibonacciByDigits(digits - 1) + getFibonacciByDigits(digits - 2);
  }
}
