package ks2021.round_a.problem04;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import ks2021.round_a.problem04.Solution.Solver;
import org.junit.jupiter.api.Test;

/**
 * @author vadym
 */
class Problem04Test {

  @Test
  void run() throws IOException {
    String input = "3\n"
        + "3\n"
        + "1 -1 0\n"
        + "0 1 0\n"
        + "1 1 1\n"
        + "0 1 0\n"
        + "0 0 0\n"
        + "0 0 0\n"
        + "1 1 1\n"
        + "0 0 1\n"
        + "2\n"
        + "-1 -1\n"
        + "-1 -1\n"
        + "1 10\n"
        + "100 1000\n"
        + "1 0\n"
        + "0 1\n"
        + "3\n"
        + "-1 -1 -1\n"
        + "-1 -1 -1\n"
        + "0 0 0\n"
        + "1 1 3\n"
        + "5 1 4\n"
        + "0 0 0\n"
        + "0 0 0\n"
        + "0 0 0\n";
    String expected = "Case #1: 0\n"
        + "Case #2: 1\n"
        + "Case #3: 2\n";
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    new Solution().run(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), output);
    assertThat(output.toString(StandardCharsets.UTF_8))
        .isEqualTo(expected);
  }

  @Test
  void large() throws IOException {
    int size = 500;
    Solver solver = new Solver();
    solver.a = new int[size][size];
    solver.b = new int[size][size];
    for (int i = 0; i < size; i++) {
      Arrays.fill(solver.a[i], -1);
      Arrays.fill(solver.b[i], 1000);
    }
    assertThat(solver.solve())
        .isEqualTo(499 * 499 * 1000);
  }

  @Test
  void min() throws IOException {
    int size = 2;
    Solver solver = new Solver();
    solver.a = new int[size][size];
    solver.b = new int[size][size];
    for (int i = 0; i < size; i++) {
      Arrays.fill(solver.a[i], -1);
    }
    solver.b[0][0] = 1;
    solver.b[0][1] = 2;
    solver.b[1][0] = 3;
    solver.b[1][1] = 4;
    assertThat(solver.solve())
        .isEqualTo(1);
  }

  @Test
  void twoJoinedCycles() throws Exception {
    String input = "1\n"
        + "4\n"
        + "-1 -1 0 0\n"
        + "-1 -1 -1 0\n"
        + "0 0 -1 -1\n"
        + "0 0 -1 -1\n"
        + "2 2 0 0\n"
        + "2 2 1 0\n"
        + "0 0 2 2\n"
        + "0 0 2 2\n"
        + "0 0 0 0\n"
        + "0 0 0 0\n";
    String expected = "Case #1: 4\n";
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    new Solution().run(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), output);
    assertThat(output.toString(StandardCharsets.UTF_8))
        .isEqualTo(expected);
  }
}