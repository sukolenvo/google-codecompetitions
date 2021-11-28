package ks2021.round_b.problem02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
    Solver solver = new Solver();
    for (int i = 0; i < caseCount; i++) {
      int n = scanner.nextInt();
      scanner.nextLine(); // go to the next line
      String s = scanner.nextLine();
      List<Integer> a = Arrays.stream(s.split(" "))
          .map(Integer::parseInt)
          .collect(Collectors.toList());
      assert a.size() == n;
      solver.a = a;
      outputStream.write(String.format("Case #%d: %d%n", i + 1, solver.solve()).getBytes(StandardCharsets.UTF_8));
    }
  }

  private static class Solver {

    private List<Integer> a;

    int solve() {
      int maxLength = 0;
      int start = 0;
      while (start < a.size() - 1) {
        int diff = a.get(start + 1) - a.get(start);
        int end = start;
        while (end < a.size() - 1 && a.get(end + 1) - a.get(end) == diff) {
          end ++;
        }
        int extraReplacement = Math.max(replaceStart(start, diff), replaceEnd(end, diff));
        maxLength = Math.max(maxLength, 1 + end - start + extraReplacement);
        start = end + 1;
      }
      return maxLength;
    }

    int replaceStart(int from, int diff) {
      if (from <= 1) {
        return from;
      }
      int last = a.get(from) - diff;
      int i = from - 2;
      while (i >= 0) {
        if (a.get(i) == last - diff) {
          i--;
        } else {
          return from - i + 1;
        }
      }
      return from - i;
    }

    int replaceEnd(int from, int diff) {
      int itemsLeft = a.size() - from - 1;
      if (itemsLeft <= 1) {
        return itemsLeft;
      }
      int last = a.get(from) + diff;
      int i = from + 2;
      while (i < a.size()) {
        if (a.get(i) == last + diff) {
          i++;
        } else {
          return i - from - 1;
        }
      }
      return i - from;
    }
  }
}
