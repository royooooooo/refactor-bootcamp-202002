package cc.xpbootcamp.warmup.fibonacci;

public class Fibonacci {

  public static int getFibonacciByDigits(int digits) {

    if (digits == 1 || digits == 2) {
      return 1;
    }

    return getFibonacciByDigits(digits - 1) + getFibonacciByDigits(digits - 2);
  }
}
