package ks2021.round_a.problem03;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

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
      int r = scanner.nextInt();
      int j = scanner.nextInt();
      scanner.nextLine(); // move to the next line
      long boxesNeeded = new Solver(readMatrix(r, j, scanner)).solve();
      outputStream.write(String.format("Case #%d: %d\n", i + 1, boxesNeeded).getBytes(StandardCharsets.UTF_8));
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

  private static class Solver {
    private final int[][] matrix;
    private final Queue<Task> pendingTask = new ArrayDeque<>();

    private Solver(int[][] matrix) {
      this.matrix = matrix;
    }

    long solve() {
      long boxesUsed = 0;
      for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
          addTasksToLiftNeighbours(i, j);
          boxesUsed += processLiftTasks();
        }
      }
      return boxesUsed;
    }

    private void addTasksToLiftNeighbours(int i, int j) {
      pendingTask.add(new Task(i + 1, j, matrix[i][j] - 1));
      pendingTask.add(new Task(i, j + 1, matrix[i][j] - 1));
      pendingTask.add(new Task(i - 1, j, matrix[i][j] - 1));
      pendingTask.add(new Task(i, j - 1, matrix[i][j] - 1));
    }

    private long processLiftTasks() {
      long result = 0;
      while (!pendingTask.isEmpty()) {
        Task task = pendingTask.poll();
        int i = task.i;
        int j = task.j;
        if (i >= matrix.length || i < 0 || j >= matrix[0].length || j < 0) {
          continue;
        }
        if (matrix[i][j] >= task.minHeight) {
          continue;
        }
        result += task.minHeight - matrix[i][j];
        matrix[i][j] = task.minHeight;
        addTasksToLiftNeighbours(i, j);
      }
      return result;
    }

    private static class Task {
      final int i;
      final int j;
      final int minHeight;

      public Task(int i, int j, int minHeight) {
        this.i = i;
        this.j = j;
        this.minHeight = minHeight;
      }
    }

  }
}
