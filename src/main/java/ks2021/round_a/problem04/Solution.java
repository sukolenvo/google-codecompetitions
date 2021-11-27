package ks2021.round_a.problem04;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

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
      scanner.nextLine(); // move to the next line
      solver.a = readMatrix(n, n, scanner);
      solver.b = readMatrix(n, n, scanner);
      solver.r = readMatrix(1, n, scanner);
      solver.c = readMatrix(1, n, scanner);
      outputStream.write(String.format("Case #%d: %d\n", i + 1, solver.solve()).getBytes(StandardCharsets.UTF_8));
    }
  }

  int[][] readMatrix(int rows, int columns, Scanner scanner) {
    int[][] result = new int[rows][columns];
    for (int i = 0; i < rows; i++) {
      String line = scanner.nextLine();
      String[] values = line.split(" ");
      assert values.length == columns;
      for (int j = 0; j < values.length; j++) {
        result[i][j] = Integer.parseInt(values[j]);
      }
    }
    return result;
  }

  static class Solver {

    int[][] a;
    int[][] b;
    int[][] r;
    int[][] c;

    int solve() {
      int totalHours = 0;
      for (int i = 0; i < b.length; i++) {
        totalHours += reduceGraph(i);
      }
      return totalHours;
    }

    private int reduceGraph(int row) {
      Set<Integer> visitedRows = new HashSet<>();
      Set<Integer> visitedColumns = new HashSet<>();
      Queue<List<Integer>> edges = new PriorityQueue<>(Comparator.comparing(costRowColumn -> costRowColumn.get(0)));
      visitedRows.add(row);
      for (int j = 0; j < b.length; j++) {
        if (b[row][j] > 0) {
          edges.add(List.of(-b[row][j], row, j));
          b[row][j] = 0;
        }
      }
      int totalHours = 0;
      while (!edges.isEmpty()) {
        List<Integer> costRowColumn = edges.poll();
        boolean containsRow = visitedRows.contains(costRowColumn.get(1));
        boolean containsColumn = visitedColumns.contains(costRowColumn.get(2));
        assert containsRow || containsColumn;
        if (containsRow && containsColumn) {
          totalHours -= costRowColumn.get(0);
        } else if (containsRow) {
          visitedColumns.add(costRowColumn.get(2));
          for (int i = 0; i < b.length; i++) {
            if (b[i][costRowColumn.get(2)] > 0) {
              edges.add(List.of(-b[i][costRowColumn.get(2)], i, costRowColumn.get(2)));
              b[i][costRowColumn.get(2)] = 0;
            }
          }
        } else {
          visitedRows.add(costRowColumn.get(1));
          for (int j = 0; j < b.length; j++) {
            if (b[costRowColumn.get(1)][j] > 0) {
              edges.add(List.of(-b[costRowColumn.get(1)][j], costRowColumn.get(1), j));
              b[costRowColumn.get(1)][j] = 0;
            }
          }
        }
      }
      return totalHours;
    }
  }
}
