package ks2021.round_b.problem01;

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
      scanner.nextLine(); // go to the next line
      String s = scanner.nextLine();
      assert s.length() == n;
      char lastChar = 0;
      int streak = 0;
      outputStream.write(String.format("Case #%d:", i + 1).getBytes(StandardCharsets.UTF_8));
      for (char c : s.toCharArray()) {
        if (c > lastChar) {
          streak++;
        } else {
          streak = 1;
        }
        lastChar = c;
        outputStream.write((" " + streak).getBytes(StandardCharsets.UTF_8));
      }
      outputStream.write("\n".getBytes(StandardCharsets.UTF_8));
    }
  }
}
