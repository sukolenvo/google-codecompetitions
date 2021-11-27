package ks2021.round_a.problem03;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

/**
 * @author vadym
 */
class Problem03Test {

  @Test
  void run() throws IOException {
    String input = "3\n"
        + "1 3\n"
        + "3 4 3\n"
        + "1 3\n"
        + "3 0 0\n"
        + "3 3\n"
        + "0 0 0\n"
        + "0 2 0\n"
        + "0 0 0\n";
    String expected = "Case #1: 0\n"
        + "Case #2: 3\n"
        + "Case #3: 4\n";
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    new Solution().run(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), output);
    assertThat(output.toString(StandardCharsets.UTF_8))
        .isEqualTo(expected);
  }

  @Test
  void runSimmetric() throws IOException {
    String input = "1\n"
        + "3 3\n"
        + "0 0 0\n"
        + "0 10 0\n"
        + "0 0 0\n";
    String expected = "Case #1: 68\n";
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    new Solution().run(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), output);
    assertThat(output.toString(StandardCharsets.UTF_8))
        .isEqualTo(expected);
  }
}