/*
 * Copyright (c) 2021 Practice Insight Pty Ltd. All Rights Reserved.
 */

package ks2021.round_a.problem01;

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
        + "5 1\n"
        + "ABCAA\n"
        + "4 2\n"
        + "ABAA";
    String expected = "Case #1: 0\n"
        + "Case #2: 1\n";
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    new Solution().run(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), output);
    assertThat(output.toString(StandardCharsets.UTF_8))
        .isEqualTo(expected);
  }
}