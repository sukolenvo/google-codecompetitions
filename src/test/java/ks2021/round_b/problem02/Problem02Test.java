package ks2021.round_b.problem02;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

/**
 * @author vadym
 */
class Problem02Test {

  @Test
  void run() throws IOException {
    String input = "3\n"
        + "4\n"
        + "9 7 5 3\n"
        + "9\n"
        + "5 5 4 5 5 5 4 5 6\n"
        + "4\n"
        + "8 5 2 0\n";
    String expected = "Case #1: 4\n"
        + "Case #2: 6\n"
        + "Case #3: 4\n";
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    new Solution().run(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), output);
    assertThat(output.toString(StandardCharsets.UTF_8))
        .isEqualTo(expected);
  }

  @Test
  void firstChanged() throws IOException {
    String input = "1\n"
        + "4\n"
        + "0 3 4 5\n";
    String expected = "Case #1: 4\n";
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    new Solution().run(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), output);
    assertThat(output.toString(StandardCharsets.UTF_8))
        .isEqualTo(expected);
  }
}