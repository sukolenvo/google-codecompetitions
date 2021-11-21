package ks2021.round_a.problem02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    for (int i = 0; i < caseCount; i++) {
      List<Segment> segments = readSegments(scanner);
      List<Segment> horizontals = segments.stream().filter(it -> it.horizontal).collect(Collectors.toList());
      List<Segment> verticals = segments.stream().filter(it -> !it.horizontal).collect(Collectors.toList());
      int lShapes = 0;
      for (Segment horizontal : horizontals) {
        for (Segment vertical : verticals) {
          lShapes += countLShapes(horizontal, vertical);
          assert lShapes >= 0;
        }
      }
      outputStream.write(String.format("Case #%d: %d\n", i + 1, lShapes).getBytes(StandardCharsets.UTF_8));
    }
  }

  int countLShapes(Segment horizontal, Segment vertical) {
    Optional<int[]> intersection = findIntersection(horizontal, vertical);
    if (intersection.isEmpty()) {
      return 0;
    }
    int lShapes = 0;
    lShapes += countLShapes(intersection.get()[0] - horizontal.startX + 1, intersection.get()[1] - vertical.startY + 1);
    lShapes += countLShapes(intersection.get()[0] - horizontal.startX + 1, vertical.endY - intersection.get()[1] + 1);
    lShapes += countLShapes(horizontal.endX - intersection.get()[0] + 1, intersection.get()[1] - vertical.startY + 1);
    lShapes += countLShapes(horizontal.endX - intersection.get()[0] + 1, vertical.endY - intersection.get()[1] + 1);
    return lShapes;
  }

  int countLShapes(int length1, int length2) {
    if (length1 < 2 || length2 < 2) {
      return 0;
    }
    return Math.min(length1 / 2, length2) - 1 + Math.min(length2 / 2, length1) - 1;
  }

  Optional<int[]> findIntersection(Segment horizontal, Segment vertical) {
    assert horizontal.horizontal;
    assert !vertical.horizontal;
    if (horizontal.startY > vertical.endY || horizontal.startY < vertical.startY) {
      return Optional.empty();
    }
    if (vertical.startX > horizontal.endX || vertical.startY < horizontal.startX) {
      return Optional.empty();
    }
    return Optional.of(new int[]{vertical.startX, horizontal.startY});
  }

  List<Segment> readSegments(Scanner scanner) {
    boolean[][] grid = readGrid(scanner);
    List<Segment> result = new ArrayList<>();
    for (int i = 0; i < grid.length; i++) {
      Integer start = null;
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j]) {
          if (start == null) {
            start = j;
          }
        } else {
          if (start != null && j - start > 1) {
            result.add(Segment.createHorizontal(start, j - 1, i));
          }
          start = null;
        }
      }
      if (start != null && start < grid[0].length - 1) {
        result.add(Segment.createHorizontal(start, grid[0].length - 1, i));
      }
    }
    for (int i = 0; i < grid[0].length; i++) {
      Integer start = null;
      for (int j = 0; j < grid.length; j++) {
        if (grid[j][i]) {
          if (start == null) {
            start = j;
          }
        } else {
          if (start != null && j - start > 1) {
            result.add(Segment.createVertical(start, j - 1, i));
          }
          start = null;
        }
      }
      if (start != null && start < grid.length - 1) {
        result.add(Segment.createVertical(start, grid.length - 1, i));
      }
    }
    return result;
  }

  private boolean[][] readGrid(Scanner scanner) {
    int rows = scanner.nextInt();
    int columns = scanner.nextInt();
    scanner.nextLine(); // go to the next line
    boolean[][] grid = new boolean[rows][columns];
    for (int i = 0; i < rows; i++) {
      String row = scanner.nextLine();
      String[] values = row.split(" ");
      assert values.length == columns;
      for (int j = 0; j < values.length; j++) {
        assert values[j].equals("1") || values[j].equals("0");
        grid[i][j] = values[j].equals("1");
      }
    }
    return grid;
  }

  static class Segment {

    int startX;
    int endX;
    int startY;
    int endY;
    boolean horizontal;

    static Segment createHorizontal(int startX, int endX, int y) {
      Segment segment = new Segment();
      segment.startX = startX;
      segment.endX = endX;
      segment.startY = segment.endY = y;
      segment.horizontal = true;
      return segment;
    }

    static Segment createVertical(int startY, int endY, int x) {
      Segment segment = new Segment();
      segment.startY = startY;
      segment.endY = endY;
      segment.startX = segment.endX = x;
      segment.horizontal = false;
      return segment;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Segment segment = (Segment) o;
      return startX == segment.startX && endX == segment.endX && startY == segment.startY && endY == segment.endY
          && horizontal == segment.horizontal;
    }

    @Override
    public int hashCode() {
      return Objects.hash(startX, endX, startY, endY, horizontal);
    }

    @Override
    public String toString() {
      return "Segment{" +
          "startX=" + startX +
          ", endX=" + endX +
          ", startY=" + startY +
          ", endY=" + endY +
          ", horizontal=" + horizontal +
          '}';
    }
  }
}
