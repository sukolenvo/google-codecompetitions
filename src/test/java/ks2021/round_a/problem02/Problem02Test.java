package ks2021.round_a.problem02;

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
    String input = "2\n"
        + "4 3\n"
        + "1 0 0\n"
        + "1 0 1\n"
        + "1 0 0\n"
        + "1 1 0\n"
        + "6 4\n"
        + "1 0 0 0\n"
        + "1 0 0 1\n"
        + "1 1 1 1\n"
        + "1 0 1 0\n"
        + "1 0 1 0\n"
        + "1 1 1 0";
    String expected = "Case #1: 1\n"
        + "Case #2: 9\n";
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    new Solution().run(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), output);
    assertThat(output.toString(StandardCharsets.UTF_8))
        .isEqualTo(expected);
  }

  @Test
  void countLShapes() {
    assertThat(new Solution().countLShapes(4, 4))
        .isEqualTo(2);
  }
}