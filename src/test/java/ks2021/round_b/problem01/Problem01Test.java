package ks2021.round_b.problem01;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

/**
 * @author vadym
 */
class Problem01Test {

  @Test
  void run() throws IOException {
    String input = "2\n"
        + "4\n"
        + "ABBC\n"
        + "6\n"
        + "ABACDA\n";
    String expected = "Case #1: 1 2 1 2\n"
        + "Case #2: 1 2 1 2 3 1\n";
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    new Solution().run(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), output);
    assertThat(output.toString(StandardCharsets.UTF_8))
        .isEqualTo(expected);
  }
}