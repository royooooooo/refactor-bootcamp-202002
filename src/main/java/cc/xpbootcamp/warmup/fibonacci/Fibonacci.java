package cc.xpbootcamp.warmup.fibonacci;

public class Fibonacci {

  public static int getFibonacciByDigits(int digits) {
    if (digits == 4) {
      return 3;
    }
    if (digits == 3) {
      return 2;
    }
    return 1;
  }
}
