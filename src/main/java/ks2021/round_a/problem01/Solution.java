package ks2021.round_a.problem01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

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
      int n = scanner.nextInt();
      int k = scanner.nextInt();
      scanner.nextLine(); // n k ends with space
      String s = scanner.nextLine();
      assert s.length() == n;
      int score = 0;
      for (int j = 0; j < n / 2; j++) {
        if (s.charAt(j) != s.charAt(n - j - 1)) {
          score++;
        }
      }
      outputStream.write(String.format("Case #%d: %d%n", i + 1, Math.abs(k - score)).getBytes(StandardCharsets.UTF_8));
    }
  }
}
