package ks2021.round_a.problem02;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import ks2021.round_a.problem02.Solution.Segment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
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
  void large() throws IOException {
    String input = "1\n1000 1000\n";
    String row = IntStream.range(0, 1000).mapToObj(it -> "1").collect(Collectors.joining(" "));
    input += IntStream.range(0, 1000).mapToObj(it -> row).collect(Collectors.joining("\n"));
    ByteArrayOutputStream result = new ByteArrayOutputStream();
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    Assertions.assertTimeoutPreemptively(Duration.ofSeconds(30), () -> {
      new Solution().run(inputStream, result);
    });

    assertThat(result.toString())
        .isEqualTo("Case #1: 1659674000\n");
  }

  @Test
  void countLShapes() {
    assertThat(new Solution().countLShapes(2, 2))
        .isEqualTo(0);
    assertThat(new Solution().countLShapes(3, 3))
        .isEqualTo(0);
    assertThat(new Solution().countLShapes(4, 2))
        .isEqualTo(1);
    assertThat(new Solution().countLShapes(4, 3))
        .isEqualTo(1);
    assertThat(new Solution().countLShapes(4, 4))
        .isEqualTo(2);
    assertThat(new Solution().countLShapes(5, 4))
        .isEqualTo(2);
    assertThat(new Solution().countLShapes(6, 4))
        .isEqualTo(3);
  }

  @Nested
  class readSegments {

    @Test
    void fullLengthHorizontal() {
      String input = "1 3\n"
          + "1 1 1\n";
      assertThat(new Solution().readSegments(new Scanner(input)))
          .containsExactlyInAnyOrder(Segment.createHorizontal(0, 2, 0));
    }

    @Test
    void withGapHorizontal() {
      String input = "1 5\n"
          + "1 1 0 1 1\n";
      assertThat(new Solution().readSegments(new Scanner(input)))
          .containsExactlyInAnyOrder(Segment.createHorizontal(0, 1, 0),
              Segment.createHorizontal(3, 4, 0));
    }

    @Test
    void fullLengthVertical() {
      String input = "3 1\n"
          + "1\n1\n1\n";
      assertThat(new Solution().readSegments(new Scanner(input)))
          .containsExactlyInAnyOrder(Segment.createVertical(0, 2, 0));
    }

    @Test
    void withGapsVertical() {
      String input = "5 1\n"
          + "1\n1\n0\n1\n1\n";
      assertThat(new Solution().readSegments(new Scanner(input)))
          .containsExactlyInAnyOrder(Segment.createVertical(0, 1, 0),
              Segment.createVertical(3, 4, 0));
    }

    @Test
    void chessBoard() {
      String input = "5 5\n"
          + "1 0 1 0 1\n0 1 0 1 0\n1 0 1 0 1\n0 1 0 1 0\n1 0 1 0 1";
      assertThat(new Solution().readSegments(new Scanner(input)))
          .isEmpty();
    }

    @Test
    void case1() {
      String input = "2 40\n"
          + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 1 1\n"
          + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 1 1";
      assertThat(new Solution().readSegments(new Scanner(input)))
          .hasSize(6)
          .contains(Segment.createHorizontal(36, 39, 0),
              Segment.createHorizontal(36, 39, 1),
              Segment.createVertical(0, 1, 36),
              Segment.createVertical(0, 1, 37),
              Segment.createVertical(0, 1, 38),
              Segment.createVertical(0, 1, 39));
    }
  }

  @Nested
  class findIntersection {

    @Test
    void noIntersection() {
      assertThat(new Solution().findIntersection(Segment.createHorizontal(0, 10, 0), Segment.createVertical(1, 10, 0)))
          .isEmpty();
    }

    @Test
    void cornerIntersection() {
      assertThat(new Solution().findIntersection(Segment.createHorizontal(0, 10, 0), Segment.createVertical(0, 10, 0)))
          .contains(new int[]{0, 0});
    }

    @Test
    void middleIntersection() {
      assertThat(new Solution().findIntersection(Segment.createHorizontal(0, 10, 4), Segment.createVertical(0, 10, 5)))
          .contains(new int[]{5, 4});
    }

    @Test
    void tShape() {
      assertThat(new Solution().findIntersection(Segment.createHorizontal(0, 10, 0), Segment.createVertical(0, 10, 5)))
          .contains(new int[]{5, 0});
    }
  }

  @Nested
  class countLShapes {

    @Test
    void corner() {
      assertThat(new Solution().countLShapes(Segment.createHorizontal(0, 4, 0), Segment.createVertical(0, 4, 0)))
          .isEqualTo(2);
      assertThat(new Solution().countLShapes(Segment.createHorizontal(1, 5, 5), Segment.createVertical(4, 5, 5)))
          .isEqualTo(1);
    }

    @Test
    void tShape() {
      assertThat(new Solution().countLShapes(Segment.createHorizontal(0, 3, 0), Segment.createVertical(0, 3, 1)))
          .isEqualTo(2);
      assertThat(new Solution().countLShapes(Segment.createHorizontal(0, 4, 0), Segment.createVertical(0, 4, 1)))
          .isEqualTo(3);
    }

    @Test
    void smallCross() {
      assertThat(new Solution().countLShapes(Segment.createHorizontal(0, 2, 1), Segment.createVertical(0, 2, 1)))
          .isEqualTo(0);
      assertThat(new Solution().countLShapes(Segment.createHorizontal(0, 4, 2), Segment.createVertical(0, 4, 2)))
          .isEqualTo(0);
    }

    @Test
    void cross() {
      assertThat(new Solution().countLShapes(Segment.createHorizontal(0, 6, 3), Segment.createVertical(0, 6, 3)))
          .isEqualTo(8);
    }
  }

  @Test
  void testSet2() throws Exception {
    try (InputStream input = getClass().getClassLoader().getResourceAsStream("ts1_input.txt");
        InputStream expected = getClass().getClassLoader().getResourceAsStream("ts1_output.txt")) {
      ByteArrayOutputStream result = new ByteArrayOutputStream();
      new Solution().run(input, result);
      assertThat(result.toString())
          .isEqualTo(new String(expected.readAllBytes()));
    }

  }
}