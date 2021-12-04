package ks2021.round_b.problem03;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author vadym
 */
public class Solution {

  public static void main(String[] args) throws IOException {
    new Solution().run(System.in, System.out);
  }

  void run(InputStream inputStream, OutputStream outputStream) throws IOException {
    Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
    int caseCount = scanner.nextInt();
    for (int i = 0; i < caseCount; i++) {
      BigInteger task = scanner.nextBigDecimal().toBigIntegerExact();
      BigInteger sqrt = task.sqrt();
      BigInteger lastPrime = Stream.iterate(sqrt.add(BigInteger.ONE), it -> it.add(BigInteger.ONE))
          .filter(it -> it.isProbablePrime(100))
          .findFirst()
          .orElseThrow();
      while (true) {
        if (sqrt.isProbablePrime(100)) {
          if (lastPrime.multiply(sqrt).compareTo(task) <= 0) {
            outputStream.write(String.format("Case #%d: %s\n", i + 1, lastPrime.multiply(sqrt))
                .getBytes(StandardCharsets.UTF_8));
            break;
          }
          lastPrime = sqrt;
        }
        sqrt = sqrt.subtract(BigInteger.ONE);
      }
    }
  }

}
