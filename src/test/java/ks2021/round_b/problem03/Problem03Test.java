package ks2021.round_b.problem03;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author vadym
 */
class Problem03Test {

  @Test
  void run() throws IOException {
    String input = "2\n"
        + "2021\n"
        + "2020\n";
    String expected = "Case #1: 2021\n"
        + "Case #2: 1763\n";
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    new Solution().run(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), output);
    assertThat(output.toString(StandardCharsets.UTF_8))
        .isEqualTo(expected);
  }

  @Test
  void large() throws IOException {
    Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
      String input = "1\n"
          + "1000000000000000000\n";
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      new Solution().run(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), output);
      assertThat(output.toString(StandardCharsets.UTF_8))
          .isNotEmpty();
    });
  }

  @Test
  void six() throws IOException {
    Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
      String input = "1\n"
          + "6\n";
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      new Solution().run(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), output);
      assertThat(output.toString(StandardCharsets.UTF_8))
          .isEqualTo("Case #1: 6\n");
    });
  }
}